package com.hwc.framework.modules.controller;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.domain.request.WContactsImportListRequest;
import com.hwc.framework.modules.model.ClWContacts;
import com.hwc.framework.modules.service.ClWContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/api/user/wContacts")
public class ClWContactsController {
    private static final Logger logger = LoggerFactory.getLogger(ClWContactsController.class);

    @Autowired
    private ClWContactsService clWContactsService;

    @PostMapping("/getOne")
    public Response getOne(@RequestBody DWContacts dwContacts) {
        Response response = clWContactsService.getWContactsWithPhone(dwContacts);
        return response;
    }

    @PostMapping("/importWContactsOne")
    public Response importWContactsOne(@RequestBody DWContacts dwContacts) {
        Response response = clWContactsService.importWContactsOne(dwContacts);
        return response;
    }

    @PostMapping("/importWContactsList")
    public Response importWContactsList(@RequestBody WContactsImportListRequest requestList) {
        Response response = clWContactsService.importWContactsList(requestList);
        return response;
    }

}
