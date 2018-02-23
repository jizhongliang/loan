package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.dao.ClH5UserInfoMapper;
import com.hwc.framework.modules.domain.ClH5UserInfo;
import com.hwc.framework.modules.model.ClUserOtherInfo;
import com.hwc.framework.modules.service.ClUserOtherInfoService;

import io.swagger.annotations.ApiOperation;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/api/user/other/info")
public class ClUserOtherInfoController {
    private static final Logger logger = LoggerFactory.getLogger(ClUserOtherInfoController.class);

    @Autowired
    private ClH5UserInfoMapper clH5UserInfoMapper;
    
    
    /** 
     * 从H5获取用户信息（不是身份证信息）
     * @param response
     * @param userName
     * @param userPhone
     * @param credit
     * @param moneyUse
     * @param borrowTime
     * @param url
     * @param ip
     * @param city
     * @param equipment
     * @param Inviter
     * @return
     */
    @RequestMapping("/saveUserInfo")
    @ApiOperation(value = "H5页面获取用户信息")
    public Response saveUserInfo(
    		HttpServletResponse response,
    		@RequestParam(value = "userName", required = false) String userName,
    		@RequestParam(value = "userPhone", required = false) String userPhone,
    		@RequestParam(value = "credit", required = false) String credit,
    		@RequestParam(value = "moneyUse", required = false) String moneyUse,
    		@RequestParam(value = "borrowTime", required = false) Date borrowTime,
    		@RequestParam(value = "url", required = false) String url,
    		@RequestParam(value = "ip", required = false) String ip,
    		@RequestParam(value = "city", required = false) String city,
    		@RequestParam(value = "equipment", required = false) String equipment,
    		@RequestParam(value = "Inviter", required = false) String Inviter
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		if(userPhone!=null &&userPhone!=""){
			Map<String, Object> param=new HashMap<String, Object>();
			param.put("userphone", userPhone);
			List<ClH5UserInfo> list= clH5UserInfoMapper.selectBySelective(param);
			logger.info("List"+list.size());
			if(list.size()>0){
				return Response.fail("请勿重复提交");
			}
		}
		ClH5UserInfo clH5UserInfo = new ClH5UserInfo();
		clH5UserInfo.setUsername(userName);
		clH5UserInfo.setUserphone(userPhone);
		clH5UserInfo.setCredit(credit);
		clH5UserInfo.setMoneyuse(moneyUse);
		clH5UserInfo.setBorrowtime(borrowTime);
		clH5UserInfo.setUrl(url);
		clH5UserInfo.setIp(ip);
		clH5UserInfo.setCity(city);
		clH5UserInfo.setEquipment(equipment);
		clH5UserInfo.setInviter(Inviter);
		clH5UserInfo.setCreateTime(new Date());
		int i=clH5UserInfoMapper.save(clH5UserInfo);
		if(i>0){
			return Response.success("提交成功，我们的客服人员会联系您");
		}
		return Response.fail("请重新填写");
    }


}
