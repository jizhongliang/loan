/**  
 * Project Name:api-webhook  
 * File Name:EventTypeUtil.java  
 * Package Name:com.example.service.webhook.util  
 * Date:2016年7月19日下午3:33:05  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.util;

import com.hwc.framework.modules.third.business.carrier.billitem.CarrierBillTask;
import com.hwc.framework.modules.third.business.carrier.billitem.CarrierReportTask;
import com.hwc.framework.modules.third.business.zhengxin.billitem.ZhengxinTask;

import java.util.HashMap;

/**  
 * ClassName:EventTypeUtil <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月19日 下午3:33:05 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class EventTypeUtil {
	
	private static HashMap<String, Class> eventMap = new HashMap<String, Class>() {
        {
//        	put("email",EmailBillTask.class);
//        	put("bank",BankBillTask.class);
        	put("carrier",CarrierBillTask.class);
//        	put("chsi",ChsiBillTask.class);
//        	put("taobao",TaobaoTask.class);
//        	put("fund",FundTask.class);
//        	put("alipay",AlipayBillTask.class);
//        	put("jingdong",JdBillTask.class);
//        	put("qq",QqBillTask.class);
//        	put("insurance",InsuranceBillTask.class);
        	put("zhengxin",ZhengxinTask.class);
//			put("security",SecurityTask.class);
//			put("zhixingcourt", ZhixingCourtBillTask.class);
        	
        }
    };
    
    
    
    private static HashMap<String, Class> reportMap = new HashMap<String, Class>() {
        {
			put("carrier",CarrierReportTask.class);
        }
    };
    
    public static Class getEventType(String type){
    	  return  eventMap.get(type);
    	  
    }
    

    public static Class getReportEventType(String type){
  	  return  reportMap.get(type);

  }

}
  
