package com.hwc.framework.modules.third;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by   on 2017/11/29.
 */
public interface BorrowGenXinNotifyService {
    void borrowApply(JSONObject jsonObject);


    /**
     * 申请被拒绝消息通知
     * credit_borrow_refuse
     *
     * @param jsonObject
     */
    void refuseBorrowNotify(JSONObject jsonObject);

    /**
     * 审核通过
     * credit_borrow_pass
     *
     * @param jsonObject
     */
    void borrowPassNotify(JSONObject jsonObject);

    /**
     * 逾期通知
     * repay_borrow_overdue
     *
     * @param jsonObject
     */
    void borrowOverdueNotify(JSONObject jsonObject);


    /**
     * 快到期提醒
     * repay_borrow_expire
     *
     * @param jsonObject
     */
    void repayExpireNotify(JSONObject jsonObject);
    	/** 
    	 * 放款成功提醒
    	 * @param jsonObject
    	 */
    void paySuccessNotify(JSONObject jsonObject) ;
    /** 
     * 还款通知提醒
     * @param jsonObject
     */
    void repaySuccess(JSONObject jsonObject);
}
