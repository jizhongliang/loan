/**  
 * Project Name:api-webhook  
 * File Name:EmailClient.java  
 * Package Name:com.example.service.webhook.api  
 * Date:2016年7月19日下午5:17:42  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.api;

import com.hwc.framework.modules.third.api.MoxieClient;
import com.hwc.framework.modules.third.business.carrier.dto.BillDetail;
import com.hwc.framework.modules.third.business.carrier.dto.MobileBasic;
import com.hwc.framework.modules.third.business.carrier.dto.ShortMessageList;
import com.hwc.framework.modules.third.business.carrier.dto.VoiceCallList;
import com.hwc.framework.modules.third.business.carrier.dto.report.V3.ReportBasicV3;
import com.hwc.framework.modules.third.business.carrier.dto.union.UnionDataV3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

/**  
 * ClassName:EmailClient <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月19日 下午5:17:42 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
@Component
public class CarrierClient extends MoxieClient {
	
	 private CarrierApi api;
	 
	 @Autowired
	public CarrierClient(@Value("${moxie.api.baseUrl}") String apiBaseUrl,
            @Value("${moxie.token}") String apiToken) {
		super(apiBaseUrl, apiToken);  
		api = retrofit.create(CarrierApi.class);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(CarrierClient.class);
	
	public MobileBasic getMobileBasic(String mobile, String taskId) throws IOException {
        Call<MobileBasic> call = api.getBasic(mobile,taskId);
        Response<MobileBasic> response = call.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("getMobileBasic, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }
	
	
//	public PackageUsageDetail getPackageUsage(String mobile,String fromMonth,String toMonth) throws IOException {
//        Call<PackageUsageDetail> call = api.getPackage(mobile,fromMonth,toMonth);
//        Response<PackageUsageDetail> response = call.execute();
//        if (response.code() == 200) {
//            return response.body();
//        } else {
//            LOGGER.info("getPackageUsage, status:{}, message:{}", response.code(), response.message());
//        }
//        return null;
//    }
	
	
	public BillDetail getBill(String mobile, String fromMonth, String toMonth, String taskId) throws IOException {
        Call<BillDetail> call = api.getBill(mobile,fromMonth,toMonth,taskId);
        Response<BillDetail> response = call.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("getBill, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }

	
	public VoiceCallList getCall(String mobile, String month, String taskId) throws IOException {
        Call<VoiceCallList> call = api.getCall(mobile,month,taskId);
        Response<VoiceCallList> response = call.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("getCall, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }
	
	public ShortMessageList getSms(String mobile, String month, String taskId) throws IOException {
        Call<ShortMessageList> call = api.getSms(mobile,month,taskId);
        Response<ShortMessageList> response = call.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("getSms, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }

    public UnionDataV3 getMxData(String mobile, String taskId) throws IOException {
        Call<UnionDataV3> call = api.getMxData(mobile,taskId);
        Response<UnionDataV3> response = call.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("getMxData, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }

    public ReportBasicV3 getReportBasic(String mobile, String name, String idCard, String taskId, String contact) throws IOException {
        Call<ReportBasicV3> reportBasic = api.getReportBasic(mobile, name, idCard, taskId, contact);
        Response<ReportBasicV3> response = reportBasic.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("getReportBasic, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }


//	public MobileRechargeDetail getRecharge(String mobile,String month) throws IOException {
//        Call<MobileRechargeDetail> call = api.getRecharge(mobile,month);
//        Response<MobileRechargeDetail> response = call.execute();
//        if (response.code() == 200) {
//            return response.body();
//        } else {
//            LOGGER.info("getRecharge, status:{}, message:{}", response.code(), response.message());
//        }
//        return null;
//    }
	
//	public FamilyNetDetail getFamily(String mobile) throws IOException {
//        Call<FamilyNetDetail> call = api.getFamily(mobile);
//        Response<FamilyNetDetail> response = call.execute();
//        if (response.code() == 200) {
//            return response.body();
//        } else {
//            LOGGER.info("getFamily, status:{}, message:{}", response.code(), response.message());
//        }
//        return null;
//    }
	
}
  
