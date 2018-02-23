package com.hwc.framework.modules.service;

import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.CLBorrowRepay;
import com.hwc.framework.modules.model.CLBorrowRepayThird;

import java.util.List;

/**
 * Created by jzl on 2017/12/26.
 */
public interface BorrowRepayThirdService {
    /**
     * 生成与第三方还款记录
     * @param borrow
     * @param repayList
     */
    void generateRepayThird(CLBorrow borrow, List<CLBorrowRepay> repayList);

    void updateBorrowRepayThirdInfo(RepayDataResultBo resultBo);

    CLBorrowRepayThird findOneByBorrowRepayId(Long repayId);

    void deleteRepayThirdByRepayId(List<CLBorrowRepay> repayList);
}
