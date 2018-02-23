package com.hwc.framework.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据银行的名称获得银行编码
 * 
 * @author 王能顺       2017年9月13日09:32:04
 *
 */
public class BankCodingUtil {
	
	public static String getPay_code(String parameter) {
		Map<String, String> map = new HashMap<String, String>();
		
		map.put("中国工商银行", "ICBC");
		map.put("工商银行", "ICBC");
		map.put("中国农业银行", "ABC");
		map.put("农业银行", "ABC");
		map.put("中国建设银行", "CCB");
		map.put("建设银行", "CCB");
		map.put("中国银行", "BOC");
		map.put("中国交通银行", "BCOM");
		map.put("交通银行", "BCOM");
		map.put("中国兴业银行", "CIB");
		map.put("兴业银行", "CIB");
		map.put("中信银行", "CITIC");
		map.put("中国光大银行", "CEB");
		map.put("光大银行", "CEB");
		map.put("中国平安银行", "PAB");
		map.put("平安银行", "PAB");
		map.put("中国邮政储蓄银行", "PSBC");
		map.put("邮政储蓄银行", "PSBC");
		map.put("上海银行", "SHB");
		map.put("浦东发展银行", "SPDB");
		map.put("浦东银行", "SPDB");
		map.put("中国民生银行", "CMBC");
		map.put("民生银行", "CMBC");
		map.put("中国招商银行", "CMB");
		map.put("招商银行", "CMB");
		map.put("中国广发银行", "GDB");
		map.put("广发银行", "GDB");
		map.put("中国华夏银行", "HXB");
		map.put("华夏银行", "HXB");
		map.put("杭州银行", "HZB");
		map.put("北京银行", "BOB");
		map.put("宁波银行", "NBCB");
		map.put("江苏银行", "JSB");
		map.put("浙商银行", "ZSB");
		
		
		map.put("邮储银行广东分行", "PSBC");
		map.put("邮储银行河南分行", "PSBC");
		map.put("邮储银行成都分行", "PSBC");
		//map.put("黑龙江省农村信用社联合社", "");
		//map.put("广州农村商业银行", "");
		map.put("邮储银行福建分行", "PSBC");
		map.put("邮储银行上海分行", "PSBC");
		
		
		
		
		if (map.containsKey(parameter)) {
			return map.get(parameter).trim();
		}else {	
			return null;
		}
		
		
	}
}
