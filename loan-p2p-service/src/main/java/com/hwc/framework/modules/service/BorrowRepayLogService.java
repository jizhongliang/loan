package com.hwc.framework.modules.service;

import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.model.CLBorrowRepay;

import java.util.List;

/**
 * Created by jzl on 2017/12/25.
 */
public interface BorrowRepayLogService {
    /**
     * 生成还款流水
     * @param repayList
     */
    void generateRepayLogs(List<CLBorrowRepay> repayList);

    /**
     * 更新记录
     * @param resultBo
     */
    void updateRepayLogInfo(RepayDataResultBo resultBo);

    void deleteRepayLogByRepayId(List<CLBorrowRepay> repayList);
}
