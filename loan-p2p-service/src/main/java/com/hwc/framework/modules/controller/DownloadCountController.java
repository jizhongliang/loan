package com.hwc.framework.modules.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.dao.CLDownloadCountMapper;
import com.hwc.framework.modules.model.CLDownloadCount;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/p2p/downloadCount")
public class DownloadCountController {
	
	@Autowired
	private CLDownloadCountMapper cLDownloadCountMapper;
	
	/**
	 * 
	 * @param response
	 * @param type    0浏览，1 关注， 2下载，3注册
	 * @param equipment  设备 Android/ios
	 * @param state  合作渠道
	 * @param ip     用户ip
	 * @param url    本页网站地址
	 * @param city   ip对应城市
	 * @param phone  注册是手机号
	 * @return
	 */
	@RequestMapping("/insert")
    @ApiOperation(value = "用户点击信息保存")
    public Response insertDownloadInfo(
    		HttpServletResponse response,
    		@RequestParam(value = "type", required = true) String type,
    		@RequestParam(value = "equipment", required = true) String equipment,
    		@RequestParam(value = "state", required = false) String state,
    		@RequestParam(value = "ip", required = true) String ip,
    		@RequestParam(value = "url", required = false) String url,
    		@RequestParam(value = "city", required = true) String city,
    		@RequestParam(value = "phone", required = false) String phone
    		) {
		response.setHeader("Access-Control-Allow-Origin", "*");
		CLDownloadCount record = new CLDownloadCount();
		record.setType(type);
		record.setEquipment(equipment);
		record.setState(state);
		record.setIp(ip);
		if(url == null){
			return Response.fail();
		}
		record.setUrl(url);
		record.setIpCity(city);
		record.setPhone(phone);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    String date = sdf.format(new Date());
	    record.setCreatetime(date);
		cLDownloadCountMapper.insert(record);
        return Response.success();
    }
}
