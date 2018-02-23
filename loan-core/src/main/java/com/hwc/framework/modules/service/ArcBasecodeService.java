package com.hwc.framework.modules.service;

import java.util.List;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BaseCode;
import com.hwc.framework.modules.domain.BaseCodeRequest;
import com.hwc.framework.modules.model.ArcBasecode;
import com.hwc.mybatis.core.Service;

/**
 * 2017/11/01.
 */
public interface ArcBasecodeService extends Service<ArcBasecode> {

    /**
     * 获取字典数据的描述
     *
     * @param cat
     * @param code
     * @return
     */
    String getDescript(String cat, String code);

    String getExtValue(String cat, String code);

    public BaseCode getBaseCode(String cat, String code);

    public List<BaseCode> getBasecodeList(String cat);
    public List<BaseCode> getBankcodeList(String cat);
    Response getBasecodeList(BaseCodeRequest request);

    Response add(BaseCode code);

    Response del(BaseCode code);

    Response update(BaseCode code);

    Response getBankList(String bankCode);
    Response getBank(String bankNo);
    Response Bank(String bankCode,String bankNo);
}
