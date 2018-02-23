/**  
 * Project Name:api-webhook  
 * File Name:EmailClient.java  
 * Package Name:com.example.service.webhook.api  
 * Date:2016年7月19日下午5:17:42  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.callback.api;

import com.hwc.framework.modules.third.api.MoxieClient;
import com.hwc.framework.modules.third.callback.dto.ResultMsg;
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
public class CallbackClient extends MoxieClient {
	
	 private CallbackApi api;
	 
	 @Autowired
	public CallbackClient(@Value("${moxie.api.baseUrl}") String apiBaseUrl,
            @Value("${moxie.token}") String apiToken) {
		super(apiBaseUrl, apiToken);  
		api = retrofit.create(CallbackApi.class);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(CallbackApi.class);
	
	public ResultMsg recall(String taskId, String event) throws IOException {
        Call<ResultMsg> call = api.recall(taskId,event);
        Response<ResultMsg> response = call.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("recall error, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }

	
}
  
