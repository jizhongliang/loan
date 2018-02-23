package com.hwc.framework.modules.service;

import java.util.List;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.bo.FarmNotifyDataResultBo;
import com.hwc.framework.modules.bo.RepayDataResultBo;
import com.hwc.framework.modules.model.CLBorrow;

/**
 * Created by jzl on 2017/12/25.
 */
public interface BorrowService {
    /**
     * 处理打款通知
     * @param result
     * @return
     */
    Response handleFarmNotify(FarmNotifyDataResultBo result);

    /**
     * 查询订单实体
     * @param orderNo
     * @return
     */
    CLBorrow findBorrowByOrderNo(String orderNo);

    /**
     * 获取 借款id 获取借款订单
     *
     * @param borrow_id
     * @return
     */
    public CLBorrow getBorrow(Long borrow_id);

    /**
     * 更新还款信息
     * @param resultBo
     */
    void updateBorrowInfo(RepayDataResultBo resultBo);
}
