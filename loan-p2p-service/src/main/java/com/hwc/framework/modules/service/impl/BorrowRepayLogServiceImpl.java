package com.hwc.framework.modules.service.impl;

import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.dao.CLBorrowRepayLogMapper;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.CLBorrowRepayLog;
import com.hwc.framework.modules.service.BorrowRepayLogService;
import com.hwc.framework.utils.ParamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by jzl on 2017/12/25.
 */
@Service
public class BorrowRepayLogServiceImpl implements BorrowRepayLogService {
    private static Logger logger = LoggerFactory.getLogger(BorrowRepayLogServiceImpl.class);
    @Autowired
    private CLBorrowRepayLogMapper borrowRepayLogMapper;

    /**
     * 生成还款记录
     * @param repayList
     */
    @Override
    public void generateRepayLogs(List<CLBorrowRepay> repayList) {
        for (CLBorrowRepay repay : repayList) {
            CLBorrowRepayLog log = new CLBorrowRepayLog();
            log.setRepayId(repay.getId());
            log.setBorrowId(repay.getBorrowId());
            log.setUserId(repay.getUserId());
            log.setPenaltyDay(repay.getPenaltyDay());
            log.setPenaltyAmout(repay.getPenaltyAmout());
            log.setRepayWay("40");  //新华金典
            log.setCreateTime(new Date());
            borrowRepayLogMapper.insertSelective(log);
        }
    }

    /**
     * 更新记录
     * @param resultBo
     */
    @Override
    public void updateRepayLogInfo(RepayDataResultBo resultBo) {
        //amount,serial_number,repay_time
        CLBorrowRepayLog repayLog = new CLBorrowRepayLog();
        repayLog.setAmount(ParamUtil.isEmpty(resultBo.getTotalAmount()) ? BigDecimal.ZERO : new BigDecimal(resultBo.getTotalAmount()));
        repayLog.setSerialNumber(resultBo.getRepaymentTransaction());
        repayLog.setRepayTime(new Date());
        repayLog.setRepayId(ParamUtil.isEmpty(resultBo.getStages()) ? 0 : Long.valueOf(resultBo.getStages()));
        repayLog.setPenaltyAmout(ParamUtil.isEmpty(resultBo.getOverdueFine()) ? BigDecimal.ZERO : new BigDecimal(resultBo.getOverdueFine()));
        borrowRepayLogMapper.updateRepayLogInfo(repayLog);
    }

    /**
     * 删除还款记录
     * @param repayList
     */
    @Override
    public void deleteRepayLogByRepayId(List<CLBorrowRepay> repayList) {
        for (CLBorrowRepay repay : repayList) {
            borrowRepayLogMapper.deleteRepayLogByRepayId(repay.getId());
        }
    }
}
