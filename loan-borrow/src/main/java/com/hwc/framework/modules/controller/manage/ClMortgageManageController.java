package com.hwc.framework.modules.controller.manage;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.framework.common.MortgageImgTypeConstant;
import com.hwc.framework.modules.domain.*;
import com.hwc.framework.modules.model.CLWContacts;
import com.hwc.framework.modules.service.CLWContactService;
import com.hwc.framework.modules.service.ClMortgageService;
import com.hwc.loan.sdk.user.domain.DWContactsDomain;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by   on 2017/11/6.
 * 管理后台处理抵押申请
 */
@RestController
@RequestMapping("/manage/mortgage")
@Api(tags = "管理后台处理抵押申请")
public class ClMortgageManageController {
    /**
     * 审核抵押申请
     *
     * @return
     */
    @Autowired
    private ClMortgageService mortgageService;
    @Autowired
    private CLWContactService cLWContactService;
    @PostMapping("/trial")
    @ApiOperation(value = "初审")
    public Response authMortgageApply(@RequestBody BorrowAuditBean bean) {
        return mortgageService.trialAudit(bean.getState(), bean.getId(), bean.getRemark(), null);
    }

    @PostMapping("/reviewAuth")
    @ApiOperation(value = "复审")
    public Response reviewAuth(@RequestBody BorrowAuditBean bean) {
        return mortgageService.auditMortgage(bean);
    }

    @PostMapping("/finalAuth")
    @ApiOperation(value = "终审")
    public Response finalAuth(@RequestBody BorrowAuditBean bean) {
        return mortgageService.auditFinalMortgage(bean);
    }

    @PostMapping("/upImage")
    @ApiOperation(value = "上传图片")
    public Response uploadImage(@RequestBody MortgageImge bean) {
        return mortgageService.uploadOtherImg(bean.getMid(), bean.getUrl(), bean.getCat());
    }

    @PostMapping("/setQuota")
    @ApiOperation(value = "设置额度")
    public Response setMortgageQuota(@RequestBody MortgageBean bean) {
        return mortgageService.setMortgageQuota(bean);
    }

    @PostMapping("/list")
    @ApiOperation(value = "抵押审核列表")
    public Response<List<MortgageBean>> mortgageList(@RequestBody BorrowQueryRequest bean) {
        return mortgageService.getMortgageList(bean);
    }

    @PostMapping("/detail")
    @ApiOperation(value = "抵押审核明细")
    public Response<MortgageBean> mortgageDetail(@RequestBody IdRequest<Long> mid) {
        return Response.success(mortgageService.getMortgagBean(mid.getId()));
    }

    @PostMapping("/updateMortgageByPhone")
    @ApiOperation(value = "通过手机号更新抵押表")
    public Response<MortgageBean> updateMortgageByPhone(@RequestBody MortgageBean bean) {
        return Response.success(mortgageService.updateMortgageByMobile(bean));
    }
    @PostMapping("/mobile")
    @ApiOperation(value = "抵押审核明细")
    public Response<CLWContacts> mortgageMobile(@RequestBody DWContactsDomain bean) {
    	return Response.success(cLWContactService.getByPhones(bean.getPhone()));
    }
}
