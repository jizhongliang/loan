package com.hwc.framework.modules.third.callback.api;

import com.hwc.framework.modules.third.callback.dto.ResultMsg;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CallbackApi {

    @GET("/gateway/callback/v1/recall")
    Call<ResultMsg> recall(@Query("taskId") String taskId, @Query("event") String event);



}
