package com.hwc.framework.modules.controller;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.domain.DUserEmerContacts;
import com.hwc.framework.modules.service.ClUserEmerContactsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/api/user/emer/contacts")
public class ClUserEmerContactsController {
    private static final Logger logger = LoggerFactory.getLogger(ClUserEmerContactsController.class);

    @Autowired
    private ClUserEmerContactsService clUserEmerContactsService;

    @PostMapping("/getList")
    public Response getList(@RequestBody IdRequest<Long> request) {
        List<DUserEmerContacts> list = clUserEmerContactsService.findUserEmerContactsByUserId(request.getId());
        return Response.success(list);
    }

}
