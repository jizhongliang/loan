package com.hwc.framework.modules.controller;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.domain.ContractDomian;
import com.hwc.framework.modules.domain.DContract;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.BestSignContractService;

import com.hwc.framework.modules.third.UserService;
import com.hwc.framework.modules.threadPool.BestContractTaskExecutor;
import com.hwc.framework.modules.utils.DateUtils;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by on 2017/12/18.
 */
@RestController
@RequestMapping("/api/contract")
public class BestContractController {

	@Autowired
	private BestSignContractService signContractService;
	@Autowired
	private UserService userService;
	@Autowired
	IHwcCache cache;
	@Autowired
	private BestContractTaskExecutor bestContractTaskExecutor;


	@PostMapping("/creditList")
	public Response getCreditList(@RequestBody ContractDomian domian) {
		domian.setCreateTime(new Date());
		JSONArray array = new JSONArray();
		DCloanUserDomain userInfo = userService.getUserInfo(domian.getUserId());
		String idNo = userInfo.getIdNo().substring(userInfo.getIdNo().length() - 6, userInfo.getIdNo().length());
		String date = FsUtils.formatDateTime(new Date(), "yyMMdd");
		String randNum = FsUtils.randomAlphabetic(4).toUpperCase();
		String num = "XYD" + idNo + date + randNum;	//第二份，主合同
		//缓存合同统一编号
		cache.set("contract_num_" + domian.getUserId(), 86400L, num);
		//缓存其它附属编号
		cache.set("contract_num_" + domian.getUserId() + "_1", 86400L, num + "SQ");
		cache.set("contract_num_" + domian.getUserId() + "_2", 86400L, num);
		cache.set("contract_num_" + domian.getUserId() + "_3", 86400L, num + "MM");
		cache.set("contract_num_" + domian.getUserId() + "_4", 86400L, num + "HK");
		cache.set("contract_num_" + domian.getUserId() + "_5", 86400L, num + "JF");
		cache.set("contract_num_" + domian.getUserId() + "_6", 86400L, num + "QH");
		cache.set("contract_num_" + domian.getUserId() + "_7", 86400L, num + "YS");


//		long start = System.currentTimeMillis();
//		System.out.println("开始时间=======" + start);
//		for (int i=1; i<=7; i++) {
//			domian.setType(String.valueOf(i));
//			signContractService.createContract(domian, array);
			bestContractTaskExecutor.handleBestContract(domian, array);

//		}
//		long end = System.currentTimeMillis();
//		System.out.println("结束时间=======" + end);
//		System.out.println("签完七份合同总共耗时ms:" + (end - start));
//		System.out.println("签完七份合同总共耗时s:" + (end - start)/1000);
		for (int j=0; j<array.size(); j++) {
			String key = "contractId_" + array.getJSONObject(j).getString("contractId");
			String value = array.toJSONString();
			cache.set(key, 86400L, value);
		}
		//
		String url = signContractService.getCreditContractsListUrl();
		domian.setContractListUrl(url);
		return Response.success(domian);
	}

	/**
	 * h5上选择具体的合同时请求的接口
	 * @param domian
	 * @return
	 */
	@PostMapping("/getContractUrl")
	public Response getContractUrl(@RequestBody  ContractDomian domian) {
		String res = cache.get("contractUrl:uid:" + domian.getUserId()+"_" + domian.getType());
		JSONObject jsonObject = JSONObject.fromObject(res);

		return Response.success(jsonObject);
	}

	@PostMapping("/credit")
	public Response<DContract> getCreditContract(@RequestBody ContractDomian domian) {
		domian.setCreateTime(new Date());
		if (StringUtils.isEmpty(domian.getType())) {
			domian.setType("8");
		}
		DCloanUserDomain userInfo = userService.getUserInfo(domian.getUserId());
		String idNo = userInfo.getIdNo().substring(userInfo.getIdNo().length() - 6, userInfo.getIdNo().length());
		String date = FsUtils.formatDateTime(new Date(), "yyMMdd");
		String randNum = FsUtils.randomAlphabetic(4).toUpperCase();
		String num = "XYD" + idNo + date + randNum;
		//缓存合同统一编号
		cache.set("contract_num_" + domian.getUserId() + "_8",86400L, num);
		return signContractService.createContract(domian);
	}

	@PostMapping("/finish")
	public Response finish(@RequestBody String request) {
		JSONObject body = JSONObject.fromObject(request);
		String contractId = String.valueOf(body.getJSONObject("params").get("contractId"));
		return signContractService.finish(contractId);
	}

	@PostMapping("/oldFinish")
	public Response oldFinish(@RequestBody IdRequest<String> request) {
		return signContractService.finish(request.getId());
	}

	@PostMapping("/mortgage")
	public Response getMortgageContract(@RequestBody ContractDomian domian) {
		domian.setCreateTime(new Date());
		return signContractService.createMortgageContract(domian);

	}
}
