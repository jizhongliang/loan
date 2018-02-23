package com.hwc.loan.sdk.user.domain;

import java.util.List;

public class DManaUserMapDomain {

    private ManagerUserModelDomain userbase;

    private DUserAuthDataDomain userAuthData;

    private DUserAuthDomain userAuth;

    private List<DUserEmerContactsDomain> userEmerList;

    private DUserOtherInfoDomain userOtherInfo;

    public ManagerUserModelDomain getUserbase() {
        return userbase;
    }

    public void setUserbase(ManagerUserModelDomain userbase) {
        this.userbase = userbase;
    }

    public DUserAuthDomain getUserAuth() {
        return userAuth;
    }

    public void setUserAuth(DUserAuthDomain userAuth) {
        this.userAuth = userAuth;
    }

    public List<DUserEmerContactsDomain> getUserEmerList() {
        return userEmerList;
    }

    public void setUserEmerList(List<DUserEmerContactsDomain> userEmerList) {
        this.userEmerList = userEmerList;
    }

    public DUserOtherInfoDomain getUserOtherInfo() {
        return userOtherInfo;
    }

    public void setUserOtherInfo(DUserOtherInfoDomain userOtherInfo) {
        this.userOtherInfo = userOtherInfo;
    }

    public DUserAuthDataDomain getUserAuthData() {
        return userAuthData;
    }

    public void setUserAuthData(DUserAuthDataDomain userAuthData) {
        this.userAuthData = userAuthData;
    }
}
