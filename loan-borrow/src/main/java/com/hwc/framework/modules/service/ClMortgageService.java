package com.hwc.framework.modules.service;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BorrowAuditBean;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.BorrowQueryRequest;
import com.hwc.framework.modules.domain.MortgageBean;

import com.hwc.framework.modules.model.ClMortgage;
import com.hwc.mybatis.core.Service;

import java.util.List;

/**
 * 2017/10/23.
 */
public interface ClMortgageService extends Service<ClMortgage> {
    /**
     * 根据用户获取抵押物
     *
     * @param userId
     * @return
     */
    ClMortgage getMortgage(Long userId);

    ClMortgage getMortgageHome(Long userId);

    public Response mortgageApply(MortgageBean bean);


    /**
     * 订单初审
     *
     * @param state
     * @param mid
     * @param sysUserId
     */
    Response trialAudit(String state, Long mid, String remark, Long sysUserId);

    /**
     * 初审后上传补充资料
     *
     * @param mid
     * @param url
     * @param cat
     * @return
     */
    Response uploadOtherImg(Long mid, List<String> url, String cat);

    /**
     * 设置 抵押物额度
     *
     * @param bean
     */
    Response setMortgageQuota(MortgageBean bean);

    /**
     * 审核抵押物
     */
    Response auditMortgage(BorrowAuditBean bean);

    Response auditFinalMortgage(BorrowAuditBean bean);

    Response getMortgageList(BorrowQueryRequest bean);

    /**
     * 获取抵押物信息
     * @param id
     * @return
     */
    public MortgageBean getMortgagBean(Long id);
   

    void handleWhiteListForMortgage(Long uid);

    /**
     * 根据手机号修改
     * @param bean
     * @return
     */
    Response updateMortgageByMobile(MortgageBean bean);
}
