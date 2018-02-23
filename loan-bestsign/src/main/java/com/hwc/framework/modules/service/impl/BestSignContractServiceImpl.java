/**
 * Copyright 杭州惠万村信息技术有限公司. All Rights Reserved.
 */
package com.hwc.framework.modules.service.impl;

import java.io.File;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

import com.hwc.framework.modules.third.BankCardService;
import com.hwc.loan.sdk.user.domain.BankCardBean;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.hwc.base.api.Response;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.common.HttpUtils;
import com.hwc.framework.common.NumToCapital;
import com.hwc.framework.modules.dao.BestSignContractMapper;
import com.hwc.framework.modules.domain.ContractDomian;
import com.hwc.framework.modules.domain.DContract;
import com.hwc.framework.modules.model.ArcSysConfig;
import com.hwc.framework.modules.model.BestSignContract;
import com.hwc.framework.modules.model.CLWContacts;
import com.hwc.framework.modules.service.ArcSysConfigService;
import com.hwc.framework.modules.service.BestSignContractService;
import com.hwc.framework.modules.service.CLWContactService;
import com.hwc.framework.modules.third.BestSignService;
import com.hwc.framework.modules.third.BorrowService;
import com.hwc.framework.modules.third.UserService;
import com.hwc.framework.modules.utils.DateUtils;
import com.hwc.framework.modules.utils.ResourceUtil;
import com.hwc.loan.sdk.borrow.domain.BorrowRepayRepayPlanListDomain;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.mybatis.core.AbstractService;

import cn.freesoft.utils.Base64;
import cn.freesoft.utils.FsUtils;
import net.dongliu.requests.Requests;

/**
 * 类说明
 *
 * @author HwcGenerator
 * @version 1.0.0
 * @since 2017/12/18
 */
@Service
public class BestSignContractServiceImpl extends AbstractService<BestSignContractMapper, BestSignContract>
		implements BestSignContractService {

	private static final String CONTRACT_ID = "contractId_";

	private static final String MONTH_SEQ_KEY = "monthSeq";

	private static final Logger logger = LoggerFactory.getLogger(BestSignContractServiceImpl.class);

	@Autowired
	private BestSignService bestSignService;

	@Autowired
	private UserService userService;

	@Autowired
	private IHwcCache cache;

    @Autowired
    private BankCardService bankCardService;


	@Value("${best.sign.fid}")
	private String fid;
	@Value("${best.sign.fid1}")
	private String fid1;
	@Value("${best.sign.fid2}")
	private String fid2;
	@Value("${best.sign.fid3}")
	private String fid3;
	@Value("${best.sign.fid4}")
	private String fid4;
	@Value("${best.sign.fid5}")
	private String fid5;
	@Value("${best.sign.fid6}")
	private String fid6;
	@Value("${best.sign.fid7}")
	private String fid7;
	@Value("${best.sign.fid8}")
	private String fid8;

	@Value("${best.sign.m_fid}")
	private String m_fid;
	// private String m_fid = "1898515743159353895";

	// private String m_fid = "1893309170917048872";

	@Value("${best.sign.m_ownId}")
	private String m_ownId;

	@Value("${best.sign.ownId}")
	private String ownId;
	@Autowired
	private BorrowService borrowService;
	@Autowired
	private CLWContactService clwContactService;
	@Autowired
	private ArcSysConfigService configService;

	@Override
	public Response createContract(ContractDomian domian, JSONArray jsonArray) {

        JSONObject contract = new JSONObject();
        contract.put("title", FsUtils.randomNumeric(10));
        contract.put("description", "信用分期协议");
        replaceCreditContract(domian, contract);
        contract.put("userId", ownId);
        // 添加合同元素
        JSONObject jsonContract = bestSignService.addPDFElements(contract);
        if (jsonContract.getInteger("errno").equals(0)) {
            // 获取新的文件号
            String newFid = jsonContract.getJSONObject("data").getString("fid");
            contract.put("fid", newFid);
            // 创建合同
            jsonContract = bestSignService.createContract(contract);
            if (jsonContract.getInteger("errno").equals(0)) {
                JSONObject jsonObject3 = jsonContract.getJSONObject("data");
                String contractId = jsonObject3.getString("contractId");
                JSONObject xx = new JSONObject();
                xx.put("contractId", contractId);
                xx.put("userId", domian.getUserId());
                xx.put("fid", newFid);
                DCloanUserDomain userDomain = userService.getUserInfo(domian.getUserId());
                // userDomain.setPhone("15355008306");
                JSONArray array = new JSONArray();
                array.add(domian.getUserId());
                array.add(ownId);
                xx.put("signers", array);
                xx.put("phone", userDomain.getPhone());
                // 添加签署者
                JSONObject jsonObject2 = bestSignService.addSigners(xx);
                xx.put("signType", "U");
                if (domian.getReturnUrl().contains("?")) {
                    xx.put("returnUrl", domian.getReturnUrl() + "&cid=" + contractId);
                } else {
                    xx.put("returnUrl", domian.getReturnUrl() + "?cid=" + contractId);
                }
                // 设置签署者位置
                xx.put("type", domian.getType());   //type表示是签署的哪一份合同
                setSignerConfig(xx, jsonArray);
                // 获取手工签署地址

				JSONObject jsonObject11 = bestSignService.getSignURL(xx);
				// JSONObject jsonObject11 = bestSignService.getPdfDownLoad(xx);
				if (jsonObject11.getInteger("errno").equals(0)) {
					DContract dContract = new DContract();
					dContract.setContractId(contractId);
					dContract.setUrl(jsonObject11.getJSONObject("data").getString("url"));
                    //存入缓存
                    cache.set("contractUrl:uid:" + domian.getUserId()+"_" + domian.getType(), 600L, JSONObject.toJSONString(dContract));
					logger.info("用户uid:{},第:{} 份合同存入缓存------", domian.getUserId(), domian.getType());
					BestSignContract contract1 = new BestSignContract();
					contract1.setContraceUrl(jsonObject11.getJSONObject("data").getString("url"));
					contract1.setCreated(new Date());
					contract1.setContractid(contractId);
					contract1.setFid(newFid);
					contract1.setUserId(domian.getUserId());
					contract1.setState("10");
					insert(contract1);
					return Response.success(dContract);
				} else {
					return Response.fail(jsonObject11.getString("errmsg"));
				}

			}

		}
		return Response.fail("错误");
	}

    @Override
    public Response createContract(ContractDomian domian) {

        JSONObject contract = new JSONObject();
        contract.put("title", FsUtils.randomNumeric(10));
        contract.put("description", "信用分期协议");
        replaceCreditContract(domian, contract);
        contract.put("userId", ownId);
        // 添加合同元素
        JSONObject jsonContract = bestSignService.addPDFElements(contract);
        if (jsonContract.getInteger("errno").equals(0)) {
            // 获取新的文件号
            String newFid = jsonContract.getJSONObject("data").getString("fid");
            contract.put("fid", newFid);
            // 创建合同
            jsonContract = bestSignService.createContract(contract);
            if (jsonContract.getInteger("errno").equals(0)) {
                JSONObject jsonObject3 = jsonContract.getJSONObject("data");
                String contractId = jsonObject3.getString("contractId");
                JSONObject xx = new JSONObject();
                xx.put("contractId", contractId);
                xx.put("userId", domian.getUserId());
                xx.put("fid", newFid);
                DCloanUserDomain userDomain = userService.getUserInfo(domian.getUserId());
                // userDomain.setPhone("15355008306");
                JSONArray array = new JSONArray();
                array.add(domian.getUserId());
                array.add(ownId);
                xx.put("signers", array);
                xx.put("phone", userDomain.getPhone());
                // 添加签署者
                JSONObject jsonObject2 = bestSignService.addSigners(xx);
                xx.put("signType", "U");
                if (domian.getReturnUrl().contains("?")) {
                    xx.put("returnUrl", domian.getReturnUrl() + "&cid=" + contractId);
                } else {
                    xx.put("returnUrl", domian.getReturnUrl() + "?cid=" + contractId);
                }
                // 设置签署者位置
                xx.put("type", domian.getType());   //type表示是签署的哪一份合同
                setSignerConfig(xx);
                // 获取手工签署地址

                JSONObject jsonObject11 = bestSignService.getSignURL(xx);
                // JSONObject jsonObject11 = bestSignService.getPdfDownLoad(xx);
                if (jsonObject11.getInteger("errno").equals(0)) {
                    DContract dContract = new DContract();
                    dContract.setContractId(contractId);
                    dContract.setUrl(jsonObject11.getJSONObject("data").getString("url"));

                    BestSignContract contract1 = new BestSignContract();
                    contract1.setContraceUrl(jsonObject11.getJSONObject("data").getString("url"));
                    contract1.setCreated(new Date());
                    contract1.setContractid(contractId);
                    contract1.setFid(newFid);
                    contract1.setUserId(domian.getUserId());
                    contract1.setState("10");
                    insert(contract1);
                    return Response.success(dContract);
                } else {
                    return Response.fail(jsonObject11.getString("errmsg"));
                }

            }

        }
        return Response.fail("错误");
    }

	private void replaceCreditContract(ContractDomian domian, JSONObject contract) {
		switch (domian.getType()) {
		case "1":
			contract.put("fid", fid1);
			contract.put("elements", replaceCreditArrayOne(domian));
			return;
		case "2":
			contract.put("fid", fid2);
			contract.put("elements", replaceCreditArrayTwo(domian));
			return;
		case "3":
			contract.put("fid", fid3);
			contract.put("elements", replaceCreditArrayThree(domian));
			return;
		case "4":
			contract.put("fid", fid4);
			contract.put("elements", replaceCreditArrayFour(domian));
			return;
		case "5":
			contract.put("fid", fid5);
			contract.put("elements", replaceCreditArrayFive(domian));
			return;
		case "6":
			contract.put("fid", fid6);
			contract.put("elements", replaceCreditArraySix(domian));
			return;
		case "7":
			contract.put("fid", fid7);
			contract.put("elements", replaceCreditArraySeven(domian));
			return;
		case "8":
			contract.put("fid", fid8);
			contract.put("elements", replaceCreditArrayEight(domian));
			return;
		default:
			contract.put("fid", fid8);
			contract.put("elements", replaceCreditArray(domian));
			return;

		}
	}

	@Override
	public Response finish(String contractId) {
		autoSignOther(contractId);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("contractId", contractId);
		JSONObject jsonObject1 = bestSignService.finish(jsonObject);
		if (jsonObject1.getInteger("errno").equals(0)) {
			return Response.success();
		} else {
			return Response.fail(jsonObject1.getString("errmsg"));
		}
	}

	/**
	 * 自动签署其他几份合同
	 *
	 * @param contractId
	 */
	private void autoSignOther(String contractId) {
		try {
			String waitSign = cache.get(CONTRACT_ID + contractId);
			if (waitSign == null || waitSign.trim().equals("null")) {
				return;
			}
			JSONArray contractArray = JSONArray.parseArray(waitSign);
			int size = contractArray.size();
			if (size == 0) {
				return;
			}
			for (int i = 0; i < size; i++) {
				JSONObject jsonObject = contractArray.getJSONObject(i);
				String cId = jsonObject.getString("contractId");
				cache.del(CONTRACT_ID + cId);
				if (cId.equals(contractId)) {
					continue;
				}
				try {
					bestSignService.applycert(jsonObject);
				} catch (Exception e) {
					logger.error("自动签署合同失败,contractId:" + cId + "," + e.getMessage(), e);
				}
			}
		} catch (Exception e) {
			logger.error("contractId:" + contractId + "," + e.getMessage(), e);
		}
	}

	@Override
	public Response createMortgageContract(ContractDomian domian) {

		DCloanUserDomain userDomain = userService.getUserInfo(domian.getUserId());
		domian.setMobile(userDomain.getPhone());

		JSONObject contract = new JSONObject();
		contract.put("fid", m_fid);
		contract.put("title", FsUtils.randomNumeric(10));
		contract.put("description", "车位分期协议");
		contract.put("elements", replaceMotgageArray(domian));
		contract.put("userId", ownId);
		contract.put("amount", domian.getAmount());
		contract.put("seq", cache.incr("mortgage_contract"));
		contract.put("p", "1");

		// JSONObject object = new JSONObject();
		// object.put("userId", COM1);
		// object.put("fid", "1893309170917048872");
		// object.put("idNo", "330327198801012365");
		// object.put("num", " HXYC201602003ZF-1");
		// object.put("seq", "HXYC201602003ZF");
		// contract.put("")
		// 添加合同元素
		JSONObject jsonContract = bestSignService.addPDFElements(contract);

		if (jsonContract.getInteger("errno").equals(0)) {
			// 获取新的文件号
			String newFid = jsonContract.getJSONObject("data").getString("fid");
			contract.put("fid", newFid);
			// 创建合同
			jsonContract = bestSignService.createContract(contract);
			if (jsonContract.getInteger("errno").equals(0)) {
				JSONObject jsonObject3 = jsonContract.getJSONObject("data");
				String contractId = jsonObject3.getString("contractId");
				JSONObject xx = new JSONObject();
				xx.put("contractId", contractId);
				xx.put("userId", domian.getUserId());
				xx.put("fid", newFid);

                // userDomain.setPhone("15355008306");
                JSONArray array = new JSONArray();
                array.add(String.valueOf(domian.getUserId()));
                array.add(m_ownId);
                xx.put("signers", array);
                xx.put("phone", userDomain.getPhone());
                // 添加签署者
                JSONObject jsonObject2 = bestSignService.addSigners(xx);
                xx.put("signType", "U");
                if (domian.getReturnUrl().contains("?")) {
                    xx.put("returnUrl", domian.getReturnUrl() + "&cid=" + contractId);
                } else {
                    xx.put("returnUrl", domian.getReturnUrl() + "?cid=" + contractId);
                }
                // 设置签署者位置
                setSignerConfig1(xx);

				JSONObject rzzl = new JSONObject();
				JSONObject p = new JSONObject();
				p.put("pageNum", "3");
				p.put("x", "0.241");
				p.put("y", "0.1303");

				JSONArray position = new JSONArray();
				position.add(p);
				rzzl.put("position", position);
				rzzl.put("userId", m_ownId);
				rzzl.put("contractId", contractId);
				bestSignService.signCert(rzzl);
				// 获取手工签署地址
				// JSONObject jsonObject11 = bestSignService.getPdfDownLoad(xx);
				JSONObject jsonObject11 = bestSignService.getSignURL(xx);
				if (jsonObject11.getInteger("errno").equals(0)) {
					DContract dContract = new DContract();
					dContract.setContractId(contractId);
					dContract.setUrl(jsonObject11.getJSONObject("data").getString("url"));

					BestSignContract contract1 = new BestSignContract();
					contract1.setContraceUrl(jsonObject11.getJSONObject("data").getString("url"));
					contract1.setCreated(new Date());
					contract1.setContractid(contractId);
					contract1.setFid(newFid);
					contract1.setUserId(domian.getUserId());
					contract1.setState("10");
					insert(contract1);
					return Response.success(dContract);
				} else {
					return Response.fail(jsonObject11.getString("errmsg"));
				}

			}

		}
		return Response.fail("错误");
	}

	@Override
	public String getCreditContractsListUrl() {
		ArcSysConfig config = configService.getSysConfigByCode("credit_contract_list_url");
		if (StringUtils.isEmpty(config)) {
			return "";
		}
		return config.getValue();
	}

	private JSONArray replaceMotgageArray(ContractDomian domian) {
		List<BorrowRepayRepayPlanListDomain> list = borrowService.getRepayPlan(domian);

		int periods = domian.getPeriods();
		if (FsUtils.strsNotEmpty(list) && !list.isEmpty()) {
			JSONObject object = new JSONObject();
			for (BorrowRepayRepayPlanListDomain listDomain : list) {
				if (listDomain.getPeriods() == domian.getPeriods()) {
					int i = 1;

					Double amount_total = 0d;
					Double interest_total = 0d;
					Double total_total = 0d;
					for (Object o : listDomain.getPlans()) {
						// JSONObject object = new JSONObject();
						JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(o));
						object.put("amount" + i, jsonObject.getString("realAmount"));
						object.put("interest" + i, jsonObject.getString("interest"));
						object.put("total" + i, jsonObject.getString("amount"));
						amount_total = FsUtils.addDouble(amount_total, jsonObject.getDouble("realAmount"));
						interest_total = FsUtils.addDouble(interest_total, jsonObject.getDouble("interest"));
						total_total = FsUtils.addDouble(total_total, jsonObject.getDouble("amount"));

						i++;
						// array.add(jsonObject);
					}
					object.put("amount_total", amount_total);
					object.put("interest_total", interest_total);
					object.put("total_total", total_total);
				} else {
					continue;
				}
			}

			int left = 30 - periods;
			if (left > 0) {
				for (int i = periods + 1; i < 31; i++) {
					// JSONObject object1 = new JSONObject();
					object.put("amount" + i, "/");
					object.put("interest" + i, "/");
					object.put("total" + i, "/");
					// array.add(object);
				}
			}
			Long monthSeq = cache.incr(MONTH_SEQ_KEY);
			String month = DateUtils.format("yyyyMM", new Date());
			if (monthSeq == null || monthSeq == 1) {
				monthSeq = Long.valueOf(month + "001");
				cache.set(MONTH_SEQ_KEY, monthSeq);
			}
			// 增加城市code
			/*
			 * String cityCode = ""; CLWContacts clwContacts =
			 * clwContactService.getContactsByPhone(domian.getMobile()); if (null !=
			 * clwContacts) { cityCode = clwContacts.getCityCode();
			 * logger.info("上上签合同城市区号:{},手机号:{}", cityCode, domian.getMobile()); }else {
			 * logger.info("上上签获取白名单为空，mobile:{}", domian.getMobile()); } String seq="H" +
			 * cityCode + "C"+monthSeq;
			 */
			CLWContacts clwContacts = clwContactService.getContactsByPhone(domian.getMobile());
			if (null == clwContacts) {
				// 没有白名单，借款失败
				logger.error("签合同没有白名单，返回空值");
				return null;
			}
			if (StringUtils.isEmpty(clwContacts.getBorrowId())) {
				logger.error("签合同白名单里没有合同号，不允许签合同，返回");
				return null;
			}
			String seq = clwContacts.getBorrowId();

			String MONTH_SEQ_USER_KEY = month + domian.getUserId();
			Long monthSeqUser = cache.incr(MONTH_SEQ_USER_KEY);

			object.put("seq", seq);
			object.put("num", seq + "ZF-" + monthSeqUser);
			object.put("amount", domian.getAmount());
			//object.put("rate", domian.getRate() + "%");
			object.put("rate", clwContacts.getBorrowRate() + "%");
			// object.put("seq", cache.incr("mortgage_contract"));
			object.put("p", "1");
			// object.put("rz_image", getImage("rzzl.png"));
			object.put("sign_date", new Date());
			object.put("sign_name", "吴成智");
			object.put("period", domian.getPeriods());

			String els = Requests.get("http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/mortgage_1.json").send()
					.readToText();

			for (String o : object.keySet()) {
				try {
					if (object.get(o) instanceof Date) {
						els = els.replace("${" + o + "}", FsUtils.formatDate(object.getDate(o)));
					} else {
						els = els.replace("${" + o + "}", object.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray array = JSONObject.parseArray(els);
			// JSONArray newArray = JSONObject.parseArray(ret);
			return array;
		}
		return null;

	}

	private JSONObject setSignerConfig(JSONObject jsonObject) {
		JSONObject object2 = new JSONObject();
		object2.put("userId", jsonObject.getString("userId"));
        String pageNum = "7";
        String x = "0.327";
        String y = "0.325";
        switch (jsonObject.getString("type")) {
            case "1":
                pageNum = "2";
                x = "0.700";
                y = "0.100";
                break;
            case "2":
                pageNum = "15";
                x = "0.700";
                y = "0.100";
                break;
            case "3":
                pageNum = "4";
                x = "0.2";
                y = "0.17";
                break;
            case "4":
                pageNum = "1";
                x = "0.60";
                y = "0.730";
                break;
            case "5":
                pageNum = "1";
                x = "0.76";
                y = "0.43";
                break;
            case "6":
                pageNum = "1";
                x = "0.69";
                y = "0.53";
                break;
            case "7":
                pageNum = "1";
                x = "0.69";
                y = "0.55";
                break;
            case "8":
				pageNum = "2";
				x = "0.700";
				y = "0.100";
                break;
            default:
                pageNum = "2";
                x = "0.327";
                y = "0.325";
                break;
        }

		JSONArray array_config = new JSONArray();
		// 用户签署位置
		if (jsonObject.getString("signType").equals("U")) {
			JSONObject p = new JSONObject();
			p.put("pageNum", pageNum);
			p.put("x", x);
			p.put("y", y);
			array_config.add(p);
		}
		// 财位签署位置
		else if (jsonObject.getString("signType").equals("C")) {
			JSONObject p = new JSONObject();
			p.put("pageNum", "7");
			p.put("x", "0.397");
			p.put("y", "0.736");
			array_config.add(p);
		} else if (jsonObject.getString("signType").equals("J")) {
			JSONObject p = new JSONObject();
			p.put("pageNum", "7");
			p.put("x", "0.427");
			p.put("y", "0.636");
			array_config.add(p);
		}
		object2.put("position", array_config);
		if (jsonObject.containsKey("phone")) {
			object2.put("phone", jsonObject.getString("phone"));
		}
		if (jsonObject.containsKey("returnUrl")) {
			object2.put("returnUrl", jsonObject.getString("returnUrl"));
		}
		object2.put("contractId", jsonObject.getString("contractId"));
		JSONObject jsonObject4 = bestSignService.setSignerConfig(object2);
		return jsonObject4;
	}

    private JSONObject setSignerConfig(JSONObject jsonObject, JSONArray array) {
        JSONObject object2 = new JSONObject();
        object2.put("userId", jsonObject.getString("userId"));
        String pageNum = "7";
        String x = "0.327";
        String y = "0.325";
        switch (jsonObject.getString("type")) {
            case "1":
                pageNum = "2";
                x = "0.700";
                y = "0.100";
                break;
            case "2":
                pageNum = "15";
                x = "0.700";
                y = "0.100";
                break;
            case "3":
                pageNum = "4";
                x = "0.25";
                y = "0.27";
                break;
            case "4":
                pageNum = "1";
                x = "0.60";
                y = "0.730";
                break;
            case "5":
                pageNum = "1";
                x = "0.76";
                y = "0.43";
                break;
            case "6":
                pageNum = "1";
                x = "0.69";
                y = "0.53";
                break;
            case "7":
                pageNum = "1";
                x = "0.69";
                y = "0.55";
                break;
            case "8":
                pageNum = "2";
				x = "0.700";
				y = "0.100";
                break;
            default:
                pageNum = "2";
                x = "0.327";
                y = "0.325";
                break;
        }

        JSONArray array_config = new JSONArray();
        // 用户签署位置
        if (jsonObject.getString("signType").equals("U")) {
            JSONObject p = new JSONObject();
            p.put("pageNum", pageNum);
            p.put("x", x);
            p.put("y", y);
            array_config.add(p);
        }
        // 财位签署位置
        else if (jsonObject.getString("signType").equals("C")) {
            JSONObject p = new JSONObject();
            p.put("pageNum", "7");
            p.put("x", "0.397");
            p.put("y", "0.736");
            array_config.add(p);
        } else if (jsonObject.getString("signType").equals("J")) {
            JSONObject p = new JSONObject();
            p.put("pageNum", "7");
            p.put("x", "0.427");
            p.put("y", "0.636");
            array_config.add(p);
        }
        object2.put("position", array_config);
        if (jsonObject.containsKey("phone")) {
            object2.put("phone", jsonObject.getString("phone"));
        }
        if (jsonObject.containsKey("returnUrl")) {
            object2.put("returnUrl", jsonObject.getString("returnUrl"));
        }
        object2.put("contractId", jsonObject.getString("contractId"));
        //增加自动签署所需的信息
        array.add(object2);
        JSONObject jsonObject4 = bestSignService.setSignerConfig(object2);
        return jsonObject4;
    }

	private JSONArray replaceCreditArray(ContractDomian domian) {
		try {
			String key = "contract:credit:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArrayOne(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit1.json"),"UTF-8");

			String key = "contract:credit1:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit1.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArrayTwo(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit2.json"),"UTF-8");
			String key = "contract:credit2:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit2.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArrayThree(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit3.json"),"UTF-8");
			String key = "contract:credit3:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit3.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArrayFour(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit4.json"),"UTF-8");
			String key = "contract:credit4:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit4.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArrayFive(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit5.json"),"UTF-8");
			String key = "contract:credit5:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit5.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArraySix(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit6.json"),"UTF-8");
			String key = "contract:credit6:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit6.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArraySeven(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit7.json"),"UTF-8");
			String key = "contract:credit7:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit7.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONArray replaceCreditArrayEight(ContractDomian domian) {
		try {
			//String ret = FileUtils.readFileToString(ResourceUtils.getFile("classpath:contractElement/credit8.json"),"UTF-8");
			String key = "contract:credit8:config";
			String ret = "";
			if (cache.exists(key)) {
				ret = cache.get(key);
			} else {
				String url = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/credit8.json";
				ret = Requests.get(url).send().readToText();
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, ret);
			}
			JSONObject jsonObject = getParams(domian);
			for (String o : jsonObject.keySet()) {
				try {
					if (jsonObject.get(o) instanceof Date) {
						ret = ret.replace("${" + o + "}", FsUtils.formatDate(jsonObject.getDate(o)));
					} else {
						ret = ret.replace("${" + o + "}", jsonObject.getString(o));
					}
				} catch (Exception ex) {
					logger.error("key", o);
					ex.printStackTrace();
				}

			}
			JSONArray newArray = JSONObject.parseArray(ret);
			return newArray;
		} catch (Exception ex) {
			logger.error("", ex);
		}
		return null;
	}

	private JSONObject getParams(ContractDomian domian) throws Exception {
		JSONObject object = new JSONObject();
		DCloanUserDomain userInfo = userService.getUserInfo(domian.getUserId());
        //获取银行卡信息
        BankCardBean bankCardInfo = bankCardService.getBankCardInfo(domian.getUserId());
		object.put("idNo", userInfo.getIdNo());
		//获取白名单
		CLWContacts clwContacts = clwContactService.getCreditContactsByPhone(userInfo.getPhone());
		//
//		String idNo = userInfo.getIdNo().substring(userInfo.getIdNo().length() - 6, userInfo.getIdNo().length());
//		String date = FsUtils.formatDateTime(new Date(), "yyMMdd");
//		String randNum = FsUtils.randomAlphabetic(4).toUpperCase();
		//object.put("num", "XYD" + idNo + date + randNum);
		//从缓存里取
		object.put("num", cache.get("contract_num_" + domian.getUserId() + "_" + domian.getType()));
        //缓存合同编号
        //cacheContractNum(object.getString("num"), domian);
		object.put("realName", userInfo.getRealName());
		object.put("address", userInfo.getLiveAddr());
		object.put("amount", domian.getAmount());
		object.put("amount_descript", NumToCapital.arabNumToChineseRMB(domian.getAmount()));
		object.put("begin_year", FsUtils.formatDateTime(domian.getCreateTime(), "yyyy"));
		object.put("begin_month", FsUtils.formatDateTime(domian.getCreateTime(), "MM"));
		object.put("begin_day", FsUtils.formatDateTime(domian.getCreateTime(), "dd"));
		Date end = FsUtils.addMonth(domian.getCreateTime(), domian.getPeriods());
		object.put("end_year", FsUtils.formatDateTime(end, "yyyy"));
		object.put("end_month", FsUtils.formatDateTime(end, "MM"));
		object.put("end_day", FsUtils.formatDateTime(end, "dd"));
		Double days = FsUtils.getDays(FsUtils.getInstanceOfDay(domian.getCreateTime()), FsUtils.getInstanceOfDay(end));
		object.put("borrow_day", days.intValue());

		object.put("first_repay_year", FsUtils.formatDateTime(domian.getCreateTime(), "yyyy"));
		object.put("first_repay_month", FsUtils.formatDateTime(domian.getCreateTime(), "MM"));
		object.put("first_repay_day", FsUtils.formatDateTime(domian.getCreateTime(), "dd"));
		object.put("rate", String.valueOf(clwContacts.getBorrowRate()) + "%");
		object.put("scene", domian.getScenes());
		object.put("loan_date", FsUtils.formatDate(new Date()));
		object.put("borrow_date", FsUtils.formatDate(new Date()));
		object.put("loan3_date", FsUtils.formatDate(new Date()));
		String jdImage = "XINHUAJINDIAN0101_780.png";
		object.put("loan3_iamge", getImage(jdImage));
		object.put("loan4_date", FsUtils.formatDate(new Date()));
		object.put("year",  FsUtils.formatDateTime(new Date(), "yyyy"));
		object.put("month", FsUtils.formatDateTime(new Date(), "MM"));
		object.put("day", FsUtils.formatDateTime(new Date(), "dd"));
        object.put("phone", userInfo.getPhone());
		if (!StringUtils.isEmpty(bankCardInfo)) {
			object.put("card_holder", bankCardInfo.getId_holder());	//持卡人
			object.put("bank", bankCardInfo.getBank());		//开户行
			object.put("card_no", bankCardInfo.getCardNo());//银行卡号
		}
		for (int i=1; i<=7; i++) {
			String _num = cache.get("contract_num_" + domian.getUserId() + "_" + i);
			object.put("num_" + i, _num);
		}

		if ("2".equals(domian.getType()) || "8".equals(domian.getType())) {
			String month = DateUtils.format("yyyyMM", new Date());
			String MONTH_SEQ_USER_KEY = month + domian.getUserId();
			Long monthSeqUser = cache.incr(MONTH_SEQ_USER_KEY);
			object.put("num_zf", object.getString("num") + "ZF-" + monthSeqUser);
		}
        object.put("original_price", String.valueOf(FsUtils.divDouble(clwContacts.getBorrowQuota().doubleValue(), 0.6D, 2)));
        object.put("period", domian.getPeriods());
		//增加额度属性
		object.put("quota", String.valueOf(clwContacts.getBorrowQuota()));
		object.put("quota_descript", NumToCapital.arabNumToChineseRMB(clwContacts.getBorrowQuota().doubleValue()));
        //增加偿付租金属性
        BorrowRepayRepayPlanListDomain list = borrowService.getCreditRepayPlan(domian);

		int j = 1;

		BigDecimal amount_total = BigDecimal.ZERO;
		for (Object o : list.getPlans()) {
			JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(o));
			String realAmount = jsonObject.getString("realAmount");
			String interest = jsonObject.getString("interest");
			String payAmount = String.valueOf(new BigDecimal(realAmount).add(new BigDecimal(interest)).setScale(2));
			object.put("pay_amount" + j, payAmount);
			amount_total = amount_total.add(new BigDecimal(realAmount).add(new BigDecimal(interest))).setScale(2);
			j++;
		}
		object.put("amount_total", String.valueOf(amount_total));

        int periods = domian.getPeriods();
        int left = 10 - periods;
        if (left > 0) {
            for (int i = periods + 1; i < 11; i++) {
                object.put("pay_amount" + i, "/");
            }
        }

		// String cwImage = "1877933099363336745.png";
		// object.put("loan4_image", getImage(cwImage));
		return object;
	}

    private void cacheContractNum(String num, ContractDomian domian) {
        switch (domian.getType()) {
            case "1":
                cache.set("contract_num_" + domian.getUserId() + "_1", 86400L, num);
                return;
            case "2":
                cache.set("contract_num_" + domian.getUserId() + "_2", 86400L, num);
                return;
            case "3":
                cache.set("contract_num_" + domian.getUserId() + "_3", 86400L, num);
                return;
            case "4":
                cache.set("contract_num_" + domian.getUserId() + "_4", 86400L, num);
                return;
            case "5":
                cache.set("contract_num_" + domian.getUserId() + "_5", 86400L, num);
                return;
            case "6":
                cache.set("contract_num_" + domian.getUserId() + "_6", 86400L, num);
                return;
            case "7":
                cache.set("contract_num_" + domian.getUserId() + "_7", 86400L, num);
                return;
            case "8":
                cache.set("contract_num_" + domian.getUserId() + "_8", 86400L, num);
                return;
        }
    }

    private String getImage(String fileName) {
		try {
			String prefix = "http://caiwei.oss-cn-hangzhou.aliyuncs.com/sign/";
			String key = "contract:sign:image:" + fileName;
			if (cache.exists(key)) {
				return cache.get(key);
			} else {
				String url = prefix + fileName;
				HttpURLConnection connection = HttpUtils.createHttpURLConnection("GET", url);
				byte[] imageByte = HttpUtils.getResponseBytes(connection);
				String imageData = Base64.encode(imageByte);
				Long expire = FsUtils.getDateSpan(new Date(), FsUtils.addDate(new Date(), 3));
				cache.set(key, expire, imageData);
				return imageData;
			}
		} catch (Exception ex) {
			logger.error("getimage", ex);
		}
		return "";
	}

	public JSONObject setSignerConfig1(JSONObject signerConfig1) {
		JSONObject object2 = new JSONObject();
		object2.put("userId", signerConfig1.getString("userId"));

		JSONArray array_config = new JSONArray();
		// 用户签署位置
		if (signerConfig1.getString("signType").equals("U")) {
			JSONObject p = new JSONObject();
			p.put("pageNum", "3");
			p.put("x", "0.741");
			p.put("y", "0.1333");
			array_config.add(p);
		}
		object2.put("position", array_config);
		if (signerConfig1.containsKey("phone")) {
			object2.put("phone", signerConfig1.getString("phone"));
		}
		if (signerConfig1.containsKey("returnUrl")) {
			object2.put("returnUrl", signerConfig1.getString("returnUrl"));
		}
		object2.put("contractId", signerConfig1.getString("contractId"));
		JSONObject jsonObject4 = bestSignService.setSignerConfig(object2);
		return jsonObject4;
	}
}
