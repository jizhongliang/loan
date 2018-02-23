package com.hwc.framework.modules.service.impl;

import com.hwc.base.api.Response;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.bo.FarmNotifyDataResultBo;
import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.dao.CLBorrowMapper;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.CLBorrowThird;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.framework.modules.service.BorrowRepayService;
import com.hwc.framework.modules.service.BorrowService;
import com.hwc.framework.modules.service.BorrowThirdService;
import com.hwc.framework.utils.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by jzl on 2017/12/25.
 */
@Service
public class BorrowServiceImpl implements BorrowService {
    private static Logger logger = LoggerFactory.getLogger(BorrowServiceImpl.class);
    @Autowired
    private CLBorrowMapper borrowMapper;
    @Autowired
    private BorrowThirdService borrowThirdService;
    @Autowired
    private BorrowRepayService borrowRepayService;
    @Autowired
    private ArcCreditService arcCreditService;



    /**
     * 处理打款通知方法
     * @param result
     * @return
     */
    @Transactional
    @Override
    public Response handleFarmNotify(FarmNotifyDataResultBo result) {
        if (ParamUtil.isEmpty(result)) {
            logger.info("handleFarmNotify方法中的result实体为空");
            return Response.fail("result为空");
        }

        switch (result.getStatus()) {
            //0:success,1:fail
            case 0:
                Response response = handleFarmSuccess(result);
                return response;
            case 1:
                Response response1 = handleFarmFailed(result);
                return response1;
            default:
                return Response.fail("打款状态异常");
        }
    }

    /**
     * 查询订单
     * @param orderNo
     * @return
     */
    @Override
    public CLBorrow findBorrowByOrderNo(String orderNo) {

        return borrowMapper.findBorrowByOrderNo(orderNo);
    }


    /**************************************************************************************************/

    /**
     * 放款失败要处理的逻辑
     * @param result
     * @return
     */
    private Response handleFarmFailed(FarmNotifyDataResultBo result) {
        //表：cl_borrow,cl_borrow_third
        CLBorrow borrow = fillNotifyUpdateBorrow(result);
        borrowMapper.updateBorrowForNotify(borrow);

        //cl_borrow_third
        CLBorrowThird borrowThird = fillNotifyInsertBorrowThird(result);
        borrowThirdService.insertOne(borrowThird);
        //恢复额度
        CLBorrow borrowEntity = borrowMapper.findBorrowByOrderNo(result.getThirdTransaction());
        arcCreditService.increaseQuota(borrowEntity.getUserId(), borrowEntity.getAmount().doubleValue());

        return Response.success();
    }

    /**
     * 填充要新增的borrowThird实体
     * @param result
     * @return
     */
    private CLBorrowThird fillNotifyInsertBorrowThird(FarmNotifyDataResultBo result) {
        CLBorrowThird borrowThird = new CLBorrowThird();
        borrowThird.setOrderNo(result.getThirdTransaction());
        borrowThird.setPayAmount(new BigDecimal(result.getPayAmount()));
        borrowThird.setThirdNo(result.getPayMentTransaction());
        borrowThird.setStatus(result.getStatus());//状态：0成功，1放款失败
        borrowThird.setCreateTime(new Date());
        borrowThird.setUpdateTime(new Date());
        return borrowThird;
    }

    /**
     * 填充要更新的borrow
     * @param result
     * @return
     */
    private CLBorrow fillNotifyUpdateBorrow(FarmNotifyDataResultBo result) {
        CLBorrow borrow = new CLBorrow();
        borrow.setOrderNo(result.getThirdTransaction());
        borrow.setIsnotify("T");
        borrow.setLoanTime(new Date());
        switch (result.getStatus()) {
            case 0: //success
                borrow.setState("30");
                break;
            case 1: //fail
                borrow.setState("31");
                break;
            default://unknow
                break;

        }
        return borrow;
    }

    /**
     * 放款成功要处理的逻辑
     * @param result
     * @return
     */
    private Response handleFarmSuccess(FarmNotifyDataResultBo result) {
        //表：cl_borrow,cl_borrow_third,cl_borrow_repay
        //cl_borrow
        CLBorrow borrow = fillNotifyUpdateBorrow(result);
        borrowMapper.updateBorrowForNotify(borrow);

        //cl_borrow_third
        CLBorrowThird borrowThird = fillNotifyInsertBorrowThird(result);
        borrowThirdService.insertOne(borrowThird);

        //cl_borrow_repay
        CLBorrow borrowModel = borrowMapper.findBorrowByOrderNo(result.getThirdTransaction());
        //处理分期和不分期付款
        borrowRepayService.handleBorrowRepayPlan(borrowModel);
        return Response.success();
    }

    /**
     * 借款id 获取借款订单
     *
     * @param borrow_id
     * @return
     */
	@Override
	public CLBorrow getBorrow(Long borrow_id) {
		//表cl_borrow
		return borrowMapper.selectByPrimaryKey(borrow_id);
	}

    /**
     * 还款后更新借款订单信息
     * @param resultBo
     */
    @Override
    public void updateBorrowInfo(RepayDataResultBo resultBo) {
        /**
         * 只有state可以更新
         * 更新为已还款的前提条件：还款计划表里的已还款的期数和借款表的分期数相等
         */
        CLBorrowRepay repay = borrowRepayService.findBorrowRepayById(ParamUtil.isEmpty(resultBo.getStages()) ? 0 : Long.valueOf(resultBo.getStages()));
        CLBorrow borrow = borrowMapper.selectByPrimaryKey(repay.getBorrowId());
        int total = borrow.getPeriods();
        int counts = borrowRepayService.getAlreadyRepayCountsByBorrowId(borrow.getId());
        if (counts >= total) {
            borrow.setState(Constant.BORROW_STATE_ALREADY_REPAY);
            borrowMapper.updateBorrowToRepaySuccess(borrow.getId());
        }
    }
}
