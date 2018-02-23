package com.hwc.framework.modules.controller;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.common.MortgageImgTypeConstant;
import com.hwc.framework.modules.domain.MortgageBean;
import com.hwc.framework.modules.domain.MortgageImge;
import com.hwc.framework.modules.service.ClMortgageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 2017/10/23.
 */
@RestController
@RequestMapping("/api/mortgage")
@Api(value = "车位分期申请")
public class ClMortgageController {
    private static final Logger logger = LoggerFactory.getLogger(ClMortgageController.class);

    @Autowired
    private ClMortgageService clMortgageService;

    /**
     * 提交抵押申请
     *
     * @return
     */
    @PostMapping("/apply")
    @ApiOperation(value = "车位分期申请")
    public Response borrowApply(@RequestBody MortgageBean bean) {
        return clMortgageService.mortgageApply(bean);
    }

    @PostMapping("/info")
    @ApiOperation(value = "车位分期詳細信息")
    public Response getMorgageInfo(@RequestBody IdRequest<Long> request) {
        if (FsUtils.strsEmpty(request.getId())) {
            return Response.fail("Id不能为空");
        }
        return Response.success(clMortgageService.getMortgagBean(request.getId()));
    }

    @PostMapping("/upImage")
    @ApiOperation(value = "上传图片")
    public Response upMortgageImage(@RequestBody MortgageImge imge) {
        return clMortgageService.uploadOtherImg(imge.getMid(), imge.getUrl(), MortgageImgTypeConstant.ASSETS);
    }
}
