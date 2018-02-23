package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.user.response.UserMessageListPageResponse;


public class UserMessageListPageRequest extends ItemsRequest<UserMessageListPageResponse> {

    public static final String METHOD = "/mana/user/message/list";

    public UserMessageListPageRequest() {
        super(METHOD);
    }

    /**
     * 用户ID
     */
    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
