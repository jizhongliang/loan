package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.dao.ClBankCardMapper;
import com.hwc.framework.modules.domain.DBankCard;
import com.hwc.framework.modules.domain.DUser;
import com.hwc.framework.modules.domain.DUserBaseInfo;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.service.ClBankCardService;
import com.hwc.framework.modules.service.ClUserBaseInfoService;
import com.hwc.framework.modules.service.ClUserService;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 2017/10/23.
 */
@Service
public class ClBankCardServiceImpl extends AbstractService<ClBankCardMapper, ClBankCard> implements ClBankCardService {
    private static final Logger logger = LoggerFactory.getLogger(ClBankCardServiceImpl.class);

    @Autowired
    private ClUserBaseInfoService clUserBaseInfoService;

    @Autowired
    private ClUserService clUserService;

    @Override
    public Response getBankCardList(Long userId) {
        if (FsUtils.strsEmpty(userId)){
            return Response.fail("参数错误");
        }
        ClBankCard select =  new ClBankCard();
        select.setUserId(userId);
        ClBankCard clBankCard = this.mapper.selectOne(select);
        if (clBankCard != null){
            DBankCard dBankCard = convertToDBankCard(clBankCard);
            // 校验是否能更换银行卡
            if(true){
                dBankCard.setState("10");
            }else {
                dBankCard.setState("20");
            }
            DUserBaseInfo userBaseInfo = this.clUserBaseInfoService.getUserBaseInfo(userId);
            dBankCard.setRealName(userBaseInfo.getRealName());
            return  Response.success(dBankCard);
        }
        return Response.fail();
    }

    @Override
    public Response updateUserBankCar(DBankCard dBankCard) {
        if (FsUtils.strsEmpty(dBankCard.getUserId(), dBankCard.getCardNo(), dBankCard.getBank())){
            return Response.fail("参数错误");
        }
        // 校验是否能更换银行卡
        if(true){
            return Response.fail("您有未完成的分期，无法换卡");
        }
        if (FsUtils.l(dBankCard.getCardNo()) == null || dBankCard.getCardNo().length() < 15 || dBankCard.getCardNo().length() > 30){
            return Response.fail("银行卡号格式有误");
        }

        DUser dUser = this.clUserService.getUserById(dBankCard.getUserId());
        DUserBaseInfo dUserBaseInfo = clUserBaseInfoService.getUserBaseInfo(dBankCard.getUserId());

        // 接银行卡

        return Response.fail("未接银行卡");
    }

    @Override
    public DBankCard getBankCardByUserId(Long userId) {
       if(FsUtils.strsEmpty(userId)){
            return null;
        }
        ClBankCard select =  new ClBankCard();
        select.setUserId(userId);
        ClBankCard clBankCard = this.mapper.selectOne(select);
        if (clBankCard != null) {
            DBankCard dBankCard = convertToDBankCard(clBankCard);
            return dBankCard;
        }else {
            return null;
        }

    }


    private DBankCard convertToDBankCard(ClBankCard clBankCard){
        DBankCard dBankCard = new DBankCard();
        dBankCard.setId(clBankCard.getId());
        dBankCard.setUserId(clBankCard.getUserId());
        dBankCard.setPhone(clBankCard.getPhone());
        dBankCard.setCardNo(clBankCard.getCardNo());
        dBankCard.setBank(clBankCard.getBank());
        dBankCard.setBindTime(clBankCard.getBindTime());
        dBankCard.setAgreeNo(clBankCard.getAgreeNo());
        return dBankCard;
    }
}
