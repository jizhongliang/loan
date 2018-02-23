package com.hwc.loan.sdk.borrow.request;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;
import com.hwc.loan.sdk.borrow.response.ManageMortgageUpImageResponse;

public class ManageMortgageUpImageRequest extends RequestBase<ManageMortgageUpImageResponse> {

    public static final String METHOD = "/manage/mortgage/upImage";

    private List url;
    private String cat;
    private Long mid;
    private Long userId;

    public ManageMortgageUpImageRequest() {
        super(METHOD);
    }

    public void setUrl(List url) {
        this.url = url;
    }

    public List getUrl() {
        return this.url;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getCat() {
        return this.cat;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getMid() {
        return this.mid;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return this.userId;
    }

} 