package com.hwc.framework.modules.service;

import com.hwc.framework.modules.domain.BorrowerUserinfo;
import com.hwc.framework.modules.domain.Response;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.mybatis.core.Service;

import java.io.IOException;
import java.util.List;

public interface BorrowerUserinfoService extends Service<ClBankCard> {

    List<BorrowerUserinfo> findBorrowerUserinfos();

    ClQuartzInfo synBorrowerUserinfo();

    boolean queryBorrowerUserinfo(String orderId) throws Exception;
}
