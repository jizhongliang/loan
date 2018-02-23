package com.hwc.framework.modules.service;

import com.hwc.framework.modules.domain.Param;
import com.hwc.framework.modules.domain.Params;
import com.hwc.framework.modules.domain.Response;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitService {

    @POST("huoqService/assetSide/synBorrowerUserinfo")
    Call<Response> synBorrowerUserinfo(@Body String param);


    @POST("assetSide/queryBorrowerUserinfo")
    Call<Response> queryBorrowerUserinfo(@Body String param);

}
