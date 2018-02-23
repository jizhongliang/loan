package com.hwc.framework.modules.dao;

import com.hwc.framework.modules.domain.BorrowerUserinfo;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.mybatis.core.Mapper;

import java.util.List;

public interface ClBankCardMapper extends Mapper<ClBankCard> {

    List<BorrowerUserinfo> findBorrowerUserinfos();
}