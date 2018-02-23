package com.hwc.framework.modules.domain.response;

import com.hwc.framework.modules.domain.DWContacts;

import java.util.List;

public class WContactsImportListResponse {

    private int successNum;

    private int failNum;

    public int getSuccessNum() {
        return successNum;
    }

    public void setSuccessNum(int successNum) {
        this.successNum = successNum;
    }

    public int getFailNum() {
        return failNum;
    }

    public void setFailNum(int failNum) {
        this.failNum = failNum;
    }
}
