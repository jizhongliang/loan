package com.hwc.framework.modules.third.business.carrier.sevice;

import com.hwc.framework.modules.third.callback.api.CallbackClient;
import com.hwc.framework.modules.third.callback.dto.ResultMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CallbackService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CallbackService.class);
	
	@Autowired
	private CallbackClient callbackClient;

	public ResultMsg recall(String taskid, String event){
		try{
			return callbackClient.recall(taskid, event);
		}catch(Exception e){
			LOGGER.error("recall error",e);
		}
		return null;

	}
      
	
}
