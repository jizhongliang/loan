package com.hwc.framework.modules.third.business.zhengxin.api;

import com.hwc.framework.modules.third.business.zhengxin.dto.CreditInformation;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ZhengxinApi {
	      
    @GET("/gateway/zhengxin/v2/zhengxin/{taskId}/{reportNo}")
    Call<CreditInformation> getCreditInfo(@Path("taskId") String taskId, @Path("reportNo") String reportNo);
    
}
