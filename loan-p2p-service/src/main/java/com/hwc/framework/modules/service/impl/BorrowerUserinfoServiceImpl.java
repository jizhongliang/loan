package com.hwc.framework.modules.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.hwc.framework.common.Constant;
import com.hwc.framework.modules.dao.CLBorrowMapper;
import com.hwc.framework.modules.dao.ClBankCardMapper;
import com.hwc.framework.modules.dao.SysConfMapper;
import com.hwc.framework.modules.domain.*;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.model.CLBorrow;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.service.BorrowerUserinfoService;
import com.hwc.framework.utils.*;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BorrowerUserinfoServiceImpl extends AbstractService<ClBankCardMapper, ClBankCard>
        implements BorrowerUserinfoService {
    private static final Logger logger = LoggerFactory.getLogger(BorrowerUserinfoServiceImpl.class);
    @Value("${sync.url}")
    private String syncUrl;
    @Value("${query.url}")
    private String queryUrl;
    @Value("${source.code}")
    private String source;
    @Value("${idCard.url}")
    private String idCardUrl;
    @Value("${notify.url}")
    private String notifyUrl;
    @Value("${subject.attribute}")
    private String subjectAttribute;
    @Value("${usage.loan}")
    private String usageLoan;
    @Value("${repayment.method}")
    private Integer repaymentMethod;

    @Value("${best.sign.developerId}")
    private  String developerId;
    @Value("${best.sign.host}")
    private  String host;
    @Value("${best.sign.pem}")
    private  String pem;

    @Autowired
    private SysConfMapper sysConfMapper;

    @Autowired
    private CLBorrowMapper clBorrowMapper;

    @Override
    public List<BorrowerUserinfo> findBorrowerUserinfos() {
        return this.mapper.findBorrowerUserinfos();
    }

    @Override
    public ClQuartzInfo synBorrowerUserinfo() {
        ClQuartzInfo result = new ClQuartzInfo();
        List<BorrowerUserinfo> list = this.mapper.findBorrowerUserinfos();
        if (list == null || list.isEmpty()) {
            logger.info("本批次无申请人员，放款数据同步终止");
            result.setSucceed(0);
            result.setFail(0);
        }
        ArcSysConfig sysConfig = new ArcSysConfig();
        sysConfig.setCode(Constant.LOAN_LIMIT);
        sysConfig = sysConfMapper.selectOne(sysConfig);
        int loan_limit = sysConfig != null ? Integer.parseInt(sysConfig.getValue()) : 50;
        logger.info("loan_limit:"+loan_limit);
        if (list.size() >= loan_limit) {
            // TODO: 2017/12/26 通知运维人员
            SmsUtil.sendMsg("15902962530",String.format("本批次借款申请达到%s条",list.size()));
            logger.info("本批次申请人员超过50人，放款数据同步终止");
            result.setSucceed(0);
            result.setFail(0);
        } else {
            long time;
            long time2;
            int succeed = 0;
            int fail = 0;
            String crossToken;
            CLBorrow clBorrow;
            CLBorrow borrow;
            for (BorrowerUserinfo info:
                list) {
                try {
                    crossToken = BestSignUtils.getCrossToken(host,developerId,pem,info.getBoContractNo());
                    info.setCrossToken(crossToken);
                    try{
                        clBorrow = clBorrowMapper.findBorrowByOrderNo(info.getThirdTransaction());
                        borrow = new CLBorrow();
                        borrow.setId(clBorrow.getId());
                        borrow.setCrossToken(crossToken);
                        clBorrowMapper.updateByPrimaryKeySelective(borrow);
                        logger.info("保存token成功");
                    }catch (Throwable throwable){
                        logger.warn("保存token报错",throwable);
                    }
                    time = System.currentTimeMillis();
                    boolean bool = queryBorrowerUserinfo(info.getThirdTransaction());
                    time2 = System.currentTimeMillis();
                    logger.info("查询放款状态耗时{}结果{}", time2-time,bool);
                    if(!bool){
                        continue;
                    }
                    List params = new ArrayList();
                    info.setBosource(source);
                    info.setIdCardUrl(idCardUrl+info.getIdCardUrl());
                    info.setContactPhone1(StringUtils.remove(info.getContactPhone1()));
                    info.setContactPhone2(StringUtils.remove(info.getContactPhone2()));
                    info.setNotifyUrl(notifyUrl);
                    info.setSubjectAttribute(subjectAttribute);
                    info.setUsageLoan(usageLoan);
                    info.setRepaymentMethod(repaymentMethod);
                    info.setBoBankNo(BankCodingUtil.getPay_code(info.getBoBankName()));
                    params.add(info);
                    Params vo = new Params();
                    vo.setParams(params);
                    vo.setSource(source);
                    vo.setSign(RSAUtil.encoderByMd5(JSONObject.toJSONString(params, SerializerFeature.SortField)));
                    logger.info("明文-{}",JSON.toJSONString(vo));
                    String resultStr = HttpClientUtils.postJson(syncUrl,
                            Des.encode(Des.secretKey,JSON.toJSONString(vo)),"utf8",true);
                    logger.info("订单{}同步耗时{}", info.getThirdTransaction(),System.currentTimeMillis() - time);
                    JsonUtil.getData(resultStr);
                    succeed++;
                } catch (Exception e) {
                    logger.warn("调用第三方接口报错{}",e.getMessage());
                    fail++;
                }
            }
            result.setSucceed(succeed);
            result.setFail(fail);
        }
        return result;
    }

    @Override
    public boolean queryBorrowerUserinfo(String orderId) throws Exception {
        Map<String,String> map = new HashMap<>();
        map.put("thirdTransaction",orderId);
        Param param = new Param();
        param.setParams(map);
        param.setSource(source);
        param.setSign(RSAUtil.encoderByMd5(JSONObject.toJSONString(map, SerializerFeature.SortField)));
        logger.info("params-{}",JSON.toJSONString(param));
        logger.info("订单{}发起查询", orderId);
        String result = HttpClientUtils.postJson(queryUrl,
                Des.encode(Des.secretKey,JSON.toJSONString(param)),"utf8",true);
        String data = JsonUtil.getData(result);
        ResDatas datas = JSON.parseObject(data,ResDatas.class);
        List<ResResult> list = datas.getResult();
        if (list == null || list.isEmpty()) {
            return true;
        }
        ResResult re = list.get(0);
        if (re.getStatus() != null && re.getStatus() == 1) {
            return true;
        }
        return false;
    }

}
