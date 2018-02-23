package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.BankCardAuthoriztionRespBean;
import com.hwc.framework.modules.domain.BankCardBean;
import com.hwc.framework.modules.domain.BindCardDomain;
import com.hwc.framework.modules.domain.CardAuthDataBean;
import com.hwc.framework.modules.model.ArcBasecode;
import com.hwc.framework.modules.model.BankListModel;
import com.hwc.framework.modules.model.BankModel;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.service.ArcBasecodeService;
import com.hwc.framework.modules.service.ClBankCardService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 2017/11/02.
 */
@RestController
@RequestMapping("/api/bank/card")
@Api(tags = "银行卡管理")
public class ClBankCardController {
    private static final Logger logger = LoggerFactory.getLogger(ClBankCardController.class);

    @Autowired
    private ClBankCardService clBankCardService;
    @Autowired
    private ArcBasecodeService arcBasecodeService;

    @PostMapping("/authSignReturn")
    @ApiOperation(value = "绑卡和获取短信验证码")
    public Response<BankCardAuthoriztionRespBean> authSignReturn(@RequestBody BindCardDomain card) {
        logger.info("绑卡和获取短信验证码接口收到的参数：{}", card.logString());
        return clBankCardService.authSignReturn(card);
    }

    @PostMapping("/getBankCardInfo")
    @ApiOperation(value = "获取银行卡信息")
    public Response<BankCardBean> getBankCardInfo(@RequestBody IdRequest<Long> userId) {
        BankCardBean bean = clBankCardService.getBankCardBean(userId.getId());

        return Response.success(bean);
    }
    @PostMapping("/getAuthData")
    @ApiOperation(value = "获取绑定卡需要的数据")
    public Response<BankCardBean> getAuthData(@RequestBody CardAuthDataBean card){
        return clBankCardService.authData(card);
    }
    
    /** 
     * 车位绑卡接口
     * @param card
     * @return
     */
    @PostMapping("/authSignReturnCW")
    @ApiOperation(value = "绑卡和获取短信验证码")
    public Response<BankCardAuthoriztionRespBean> authSignReturnCW(@RequestBody BindCardDomain card) {
        logger.info("绑卡和获取短信验证码接口收到的参数：{}", card.logString());
        return clBankCardService.authSignReturnCW(card);
    }
    
    /** 
     * 获取银行信息
     * @param request
     * @return
     */
    @PostMapping("/getBankList")
    @ApiOperation(value = "获取银行列表")
    public Response<ArcBasecode> getBankList(@RequestBody BankListModel bean) {
        return arcBasecodeService.getBankList(bean.getBankCode());
    }
    /** 
     * 获取银行信息
     * @param request
     * @return
     */
    @PostMapping("/getBank")
    @ApiOperation(value = "获取银行信息")
    public Response getBank(@RequestBody BankModel bean) {
    	Response re=arcBasecodeService.getBank(bean.getBankNo());
    	logger.info("银行信息------------------>"+re.getData());
    	logger.info("银行msg------------------>"+re.getMessage());
    	logger.info("银行success------------------>"+re.getSuccess());
        return re;
    }
    @PostMapping("/Bank")
    @ApiOperation(value = "检测一致性")
    public Response Bank(@RequestBody BankModel bean) {
    	Response re=arcBasecodeService.Bank(bean.getBankCode(),bean.getBankNo());
    	logger.info("银行信息1------------------>"+re.getData());
    	logger.info("银行msg1------------------>"+re.getMessage());
    	logger.info("银行success1------------------>"+re.getSuccess());
    	return re;
    }

}
