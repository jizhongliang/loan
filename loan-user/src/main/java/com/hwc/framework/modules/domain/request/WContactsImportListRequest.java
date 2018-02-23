package com.hwc.framework.modules.domain.request;

import com.hwc.framework.modules.domain.DWContacts;

import java.util.List;

public class WContactsImportListRequest {

    /**
     * 导入集合
     */
    private List<DWContacts> list;

    public List<DWContacts> getList() {
        return list;
    }

    public void setList(List<DWContacts> list) {
        this.list = list;
    }
}
