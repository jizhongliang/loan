package com.hwc.loan.sdk.borrow.request;

import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageSysListResponse;

public class ManageSysListRequest extends RequestBase<ManageSysListResponse> {

    /**  */
    private static final long  serialVersionUID = -8171717838355279181L;

    public static final String METHOD           = "/manage/sys/list";

    private String             type;
    private String             name;
    private String             code;
    private int                page;
    private int                pageSize;

    public ManageSysListRequest() {
        super(METHOD);
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return this.type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPage() {
        return this.page;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return this.pageSize;
    }

}