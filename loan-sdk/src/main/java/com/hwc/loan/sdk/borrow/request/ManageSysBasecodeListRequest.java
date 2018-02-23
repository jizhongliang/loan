package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageSysBasecodeListResponse;

public class ManageSysBasecodeListRequest extends RequestBase<ManageSysBasecodeListResponse> {

    public static final String METHOD = "/manage/sys/basecodeList";

    private String cat;
    private String code;
    private String descript;
    private String halt;
    private int page;
    private int pageSize;

    public ManageSysBasecodeListRequest() {
        super(METHOD);
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCat() {
        return this.cat;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public void setDescript(String descript) {
        this.descript = descript;
    }

    public String getDescript() {
        return this.descript;
    }

    public void setHalt(String halt) {
        this.halt = halt;
    }

    public String getHalt() {
        return this.halt;
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