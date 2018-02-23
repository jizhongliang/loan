package com.hwc.framework.modules.third;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;

/**
 * Created by   on 2017/11/30.
 */
public interface MortgageGenXinNotifyService {
    /**
     * 抵押申请
     *
     * @param jsonObject mortgage_apply
     */
    void mortgageApplyNotify(JSONObject jsonObject);

    /**
     * 通过初审通知
     * "mortgage_trail"
     *
     * @param jsonObject
     */
    void mortgageTrailNotify(JSONObject jsonObject);

    /**
     * 申请被拒绝通知
     * "mortgage_refuse"
     *
     * @param jsonObject
     */
    void mortgageRefuseNotify(JSONObject jsonObject);

    /**
     * 复审通过消息通知
     * mortgage_review
     *
     * @param jsonObject
     */
    void mortgageReviewNotify(JSONObject jsonObject);

    /**
     * 最终审核通过消息通知
     * "mortgage_auth"
     *
     * @param jsonObject
     */
    void mortgageAuthNotify(JSONObject jsonObject);

    /**
     * "mortgage_withdrawals"
     * 抵押 提现 成功通知
     */
    void mortgageWithdrawalsNotify(JSONObject jsonObject);
}
