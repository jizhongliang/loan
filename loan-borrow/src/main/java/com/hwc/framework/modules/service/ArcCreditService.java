package com.hwc.framework.modules.service;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.framework.modules.model.ArcCredit;
import com.hwc.mybatis.core.Service;

import java.util.List;

/**
 * 2017/10/23.
 */
public interface ArcCreditService extends Service<ArcCredit> {

    /**
     * 设置用户额度
     *  @param userId
     * @param quota
     */
    Response setUserQuota(Long userId, Double quota);

    /**
     * 判断是否可以借款
     *
     * @param userId
     * @param borrow_amount
     * @return
     */
    boolean isQuota(Long userId, Double borrow_amount);

    /**
     * 冻结额度
     *
     * @param userId
     * @return
     */
    boolean freezeQuota(Long userId);

    /**
     * 获取额度
     *
     * @param userId
     * @return
     */
    ArcCredit getCreditByUserid(Long userId) throws ServiceException;

    /**
     * 增加额度
     *
     * @param userId
     * @param amount
     */
    void increaseQuota(Long userId, Double amount);

    /**
     * 借款减去额度
     *
     * @param userId
     * @param amount
     */
    void subtractQuota(Long userId, Double amount);

    List<Double> getQuotaRang(Long userId, Double min, Double step);

    Response getQuota(IdRequest<Long> request);
}
