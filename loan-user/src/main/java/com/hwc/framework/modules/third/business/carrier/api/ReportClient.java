/**  
 * Project Name:api-webhook  
 * File Name:EmailClient.java  
 * Package Name:com.example.service.webhook.api  
 * Date:2016年7月19日下午5:17:42  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.business.carrier.api;

import com.hwc.framework.modules.third.api.MoxieReportClient;
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
public class ReportClient extends MoxieReportClient {
	
	 private ReportApi api;
	 
	 @Autowired
	public ReportClient(@Value("${moxie.api.baseUrl}") String apiBaseUrl,
            @Value("${moxie.token}") String apiToken) {
		super(apiBaseUrl, apiToken);  
		api = retrofit.create(ReportApi.class);
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(ReportClient.class);
	
    public String getReportBasic(String mobile, String taskId) throws IOException {
        Call<String> reportBasic = api.getReportBasic(mobile, taskId);
        Response<String> response = reportBasic.execute();
        if (response.code() == 200) {
            return response.body();
        } else {
            LOGGER.info("getReportBasic, status:{}, message:{}", response.code(), response.message());
        }
        return null;
    }

	
}
  
