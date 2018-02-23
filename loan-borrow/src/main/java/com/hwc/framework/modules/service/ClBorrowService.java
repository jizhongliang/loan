package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowProgress;
import com.hwc.mybatis.core.Service;

import java.util.List;
import java.util.Map;

/**
 * 2017/10/30.
 */
public interface ClBorrowService extends Service<ClBorrow> {

    BorrowBean getBorrowBean(Long borrow_id);

    /**
     * 放款后回调更新订单状态
     *
     * @param borrow_id
     */
    void loanCallback(Long borrow_id, String state);

    /**
     * 订单全部还完后，才会更新订单的状态
     *
     * @param borrow_id
     */
    void repaymentSuccessCallback(Long borrow_id);

    void overdue(Long borrow_id);
    void updates(Long borrowId,String state);
    /**
     * 获取 根据用id 获取放款成功的订单
     *
     * @param userId
     * @return
     */
    List<ClBorrow> getBorrowLoanSuccess(Long userId);

    public ClBorrow getBorrow(Long borrow_id);
    List<ClBorrow> getByUserId(Long userId,String state,String borrowType);
    List<ClBorrow> getByUserIds(Long userId,String borrowType);
    /**
     * 借款列表
     * @param bean
     * @return
     */
    Response getBorrowBeanList(BorrowQueryRequest bean);

    String findBorrowlastTenM(Long userId);
    ClBorrow findByUserIdDesc(Long userId);
    Map<String,String> findRate(Long userId);

}
