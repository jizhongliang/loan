package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.user.response.UserContactsListPageResponse;


public class UserContactsListPageRequest extends ItemsRequest<UserContactsListPageResponse> {

    public static final String METHOD = "/mana/user/contacts/list";

    public UserContactsListPageRequest() {
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
