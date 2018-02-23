package com.hwc.loan.sdk.user.request;

import com.hwc.base.sdk.core.ItemsRequest;
import com.hwc.loan.sdk.user.domain.DWContactsDomain;
import com.hwc.loan.sdk.user.response.ImportWContactsListResponse;

import java.util.List;


public class ImportWContactsListRequest extends ItemsRequest<ImportWContactsListResponse> {

    public static final String METHOD = "/api/user/wContacts/importWContactsList";

    public ImportWContactsListRequest() {
        super(METHOD);
    }

    private List<DWContactsDomain> list;

    public List<DWContactsDomain> getList() {
        return list;
    }

    public void setList(List<DWContactsDomain> list) {
        this.list = list;
    }
}
