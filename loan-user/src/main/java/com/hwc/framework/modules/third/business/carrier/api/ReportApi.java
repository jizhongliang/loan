package com.hwc.framework.modules.third.business.carrier.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReportApi {
	      
   

    @GET("/carrier/v3/mobiles/{mobile}/mxreport")
    Call<String> getReportBasic(@Path("mobile") String mobile, @Query("task_id") String taskId);



}
