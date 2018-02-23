package com.hwc.framework.modules.service.impl;

import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.dao.CLBorrowRepayThirdMapper;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.CLBorrowRepayThird;
import com.hwc.framework.modules.service.BorrowRepayThirdService;
import com.hwc.framework.utils.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by jzl on 2017/12/26.
 */
@Service
public class BorrowRepayThirdServiceImpl implements BorrowRepayThirdService {
    private static Logger logger = LoggerFactory.getLogger(BorrowRepayThirdServiceImpl.class);
    @Autowired
    private CLBorrowRepayThirdMapper borrowRepayThirdMapper;

    /**
     * 生成与第三方还款记录
     * @param borrow
     * @param repayList
     */
    @Override
    public void generateRepayThird(CLBorrow borrow, List<CLBorrowRepay> repayList) {
        for (CLBorrowRepay repay : repayList) {
            CLBorrowRepayThird repayThird = new CLBorrowRepayThird();
            repayThird.setOrderNo(borrow.getOrderNo());
            repayThird.setBorrowRepayId(repay.getId());
            repayThird.setStatus(2);//还款中
            repayThird.setCreateTime(new Date());
            borrowRepayThirdMapper.insertSelective(repayThird);
        }
    }

    /**
     * 更新还款记录
     * @param resultBo
     */
    @Override
    public void updateBorrowRepayThirdInfo(RepayDataResultBo resultBo) {
        CLBorrowRepayThird repayThird = new CLBorrowRepayThird();
        repayThird.setThirdRepayNo(resultBo.getRepaymentTransaction());
        repayThird.setRepayAmount(ParamUtil.isEmpty(resultBo.getTotalAmount()) ? BigDecimal.ZERO : new BigDecimal(resultBo.getTotalAmount()));
        repayThird.setPrincipleAmount(ParamUtil.isEmpty(resultBo.getRepayAmount()) ? BigDecimal.ZERO : new BigDecimal(resultBo.getRepayAmount()));
        repayThird.setInterest(ParamUtil.isEmpty(resultBo.getInterest()) ? BigDecimal.ZERO : new BigDecimal(resultBo.getInterest()));
        repayThird.setPenaltyAmout(ParamUtil.isEmpty(resultBo.getOverdueFine()) ? BigDecimal.ZERO : new BigDecimal(resultBo.getOverdueFine()));
        //状态 0未还款，1已还款，2还款中
        repayThird.setStatus(0 == resultBo.getStatus() ? 1 : 1 == resultBo.getStatus() ? 0 : 2 == resultBo.getStatus() ? 2 : 3);
        repayThird.setUpdateTime(new Date());
        repayThird.setBorrowRepayId(ParamUtil.isEmpty(resultBo.getStages()) ? 0 : Long.valueOf(resultBo.getStages()));
        borrowRepayThirdMapper.updateBorrowRepayThirdInfo(repayThird);
    }

    /**
     * 查询实体，根据repayId
     * @param repayId
     * @return
     */
    @Override
    public CLBorrowRepayThird findOneByBorrowRepayId(Long repayId) {
        return borrowRepayThirdMapper.findOneByBorrowRepayId(repayId);
    }

    /**
     * 删除与第三方还款记录
     * @param repayList
     */
    @Override
    public void deleteRepayThirdByRepayId(List<CLBorrowRepay> repayList) {
        for (CLBorrowRepay repay : repayList) {
            borrowRepayThirdMapper.deleteRepayThirdByRepayId(repay.getId());
        }
    }
}
