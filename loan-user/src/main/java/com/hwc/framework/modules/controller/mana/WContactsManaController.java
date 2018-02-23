package com.hwc.framework.modules.controller.mana;

import cn.freesoft.utils.FsUtils;
import com.github.pagehelper.PageHelper;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.domain.request.WContactsImportListRequest;
import com.hwc.framework.modules.domain.request.WContactsListRequest;
import com.hwc.framework.modules.service.ClWContactsFailService;
import com.hwc.framework.modules.service.ClWContactsService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/mana/user/wContacts")
public class WContactsManaController {
    private static final Logger logger = LoggerFactory.getLogger(WContactsManaController.class);

    @Autowired
    private ClWContactsService clWContactsService;
    
    @Autowired
    private ClWContactsFailService clWContactsFailService;
    
    @PostMapping("/listPage")
    public Response listPage(@RequestBody WContactsListRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Map<String,Object> map = new HashMap<String, Object>();
        String cat = request.getCat();
        if (!FsUtils.strsEmpty(cat)){
            if (cat.equals("10")){
                map.put("isCredit","T");
                map.put("isDy","F");
            }else {
                map.put("isDy","T");
                map.put("isCredit","F");
            }
        }
        map.put("isAvailability",request.getState());
        map.put("idNo",request.getIdNo());
        map.put("phone",request.getPhone());
        map.put("name",request.getRealName());
        if(request.getState().equals("1")){
             List<DWContacts> list = clWContactsService.listWContactsPage(map);	
             return Response.success(list);
        }else if(request.getState().equals("2")){
        	List<DWContacts> list = clWContactsFailService.listWContactsFailPage(map);
        	return Response.success(list);
        }else if(request.getState().equals("3")){
        	List<DWContacts> list = clWContactsFailService.listWContactsFailPage(map);
        	return Response.success(list);
        }else{
        	logger.info("前端页面*_list.jsp  state值不正确！");
        	return Response.success();
        }
        
    }

    @PostMapping("/importList")
    public Response importList(@RequestBody WContactsImportListRequest request) {
        Response response = clWContactsService.importWContactsList(request);
        return response;
    }

    @PostMapping("/importOne")
    public Response importOne(@RequestBody DWContacts dwContacts) {
        Response response = clWContactsService.importWContactsOne(dwContacts);
        return response;
    }

    @PostMapping("/updateOne")
    public Response updateOne(@RequestBody DWContacts dwContacts) {
        Response response = clWContactsService.updateWContactsOne(dwContacts);
        return response;
    }

    @PostMapping("/getOne")
    public Response getOne(@RequestBody IdRequest<Long> request) {
        Response response = clWContactsService.getWContactsOne(request.getId());
        return response;
    }
}
