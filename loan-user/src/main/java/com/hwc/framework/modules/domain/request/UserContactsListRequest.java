package com.hwc.framework.modules.domain.request;


import com.hwc.base.api.ItemsRequest;

public class UserContactsListRequest extends ItemsRequest {

    private Long userId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
