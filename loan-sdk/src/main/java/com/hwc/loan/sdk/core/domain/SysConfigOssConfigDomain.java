package com.hwc.loan.sdk.core.domain;

import java.util.*;
import java.math.*;
import com.hwc.base.sdk.core.RequestBase;

public class SysConfigOssConfigDomain {

    private String folder;
    private String accesskeyid;
    private String accesskeysecret;
    private String bucketname;
    private String endpoint;

    public void setFolder(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return this.folder;
    }

    public void setAccesskeyid(String accesskeyid) {
        this.accesskeyid = accesskeyid;
    }

    public String getAccesskeyid() {
        return this.accesskeyid;
    }

    public void setAccesskeysecret(String accesskeysecret) {
        this.accesskeysecret = accesskeysecret;
    }

    public String getAccesskeysecret() {
        return this.accesskeysecret;
    }

    public void setBucketname(String bucketname) {
        this.bucketname = bucketname;
    }

    public String getBucketname() {
        return this.bucketname;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return this.endpoint;
    }

} 