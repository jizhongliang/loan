package com.hwc.framework.modules.third.business.carrier.api;

import com.hwc.framework.modules.third.business.carrier.dto.BillDetail;
import com.hwc.framework.modules.third.business.carrier.dto.MobileBasic;
import com.hwc.framework.modules.third.business.carrier.dto.ShortMessageList;
import com.hwc.framework.modules.third.business.carrier.dto.VoiceCallList;
import com.hwc.framework.modules.third.business.carrier.dto.report.V3.ReportBasicV3;
import com.hwc.framework.modules.third.business.carrier.dto.union.UnionDataV3;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CarrierApi {
	      
    @GET("/carrier/v3/mobiles/{mobile}/basic")
    Call<MobileBasic> getBasic(@Path("mobile") String mobile, @Query("task_id") String taskId);

//    @GET("/carrier/v2/mobiles/{mobile}/package-usage")
//    Call<PackageUsageDetail> getPackage(@Path("mobile") String mobile,@Query("from_month") String fromMonth,@Query("to_month") String toMonth);
    
    @GET("/carrier/v3/mobiles/{mobile}/bill")
    Call<BillDetail> getBill(@Path("mobile") String mobile, @Query("from_month") String fromMonth, @Query("to_month") String toMonth, @Query("task_id") String taskId);

    @GET("/carrier/v3/mobiles/{mobile}/call")
    Call<VoiceCallList> getCall(@Path("mobile") String mobile, @Query("month") String month, @Query("task_id") String taskId);
    
    @GET("/carrier/v3/mobiles/{mobile}/sms")
    Call<ShortMessageList> getSms(@Path("mobile") String mobile, @Query("month") String month, @Query("task_id") String taskId);
    
   
    
//    @GET("/carrier/v2/mobiles/{mobile}/recharge")
//    Call<MobileRechargeDetail> getRecharge(@Path("mobile") String mobile,@Query("month") String month);
//
//    @GET("/carrier/v2/mobiles/{mobile}/family")
//    Call<FamilyNetDetail> getFamily(@Path("mobile") String mobile);

    @GET("/carrier/v3/mobiles/{mobile}/mxdata")
    Call<UnionDataV3> getMxData(@Path("mobile") String mobile, @Query("task_id") String taskId);

    @GET("/carrier/v3/mobiles/{mobile}/mxreport")
    Call<ReportBasicV3> getReportBasic(@Path("mobile") String mobile, @Query("name") String name, @Query("idcard") String idCard, @Query("task_id") String taskId, @Query("contact") String contact);




}
