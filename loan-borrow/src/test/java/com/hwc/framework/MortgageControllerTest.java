package com.hwc.framework;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSON;
import com.hwc.base.api.Response;
import com.hwc.framework.common.ClMortgageOrderState;
import com.hwc.framework.common.MortgageImgTypeConstant;
import com.hwc.framework.modules.domain.BorrowAuditBean;
import com.hwc.framework.modules.domain.BorrowBean;
import com.hwc.framework.modules.domain.MortgageBean;
import com.hwc.framework.modules.service.ClMortgageBorrowService;
import com.hwc.framework.modules.service.ClMortgageService;
import com.sun.tools.javac.tree.JCTree;
import org.hibernate.validator.constraints.EAN;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by   on 2017/11/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MortgageControllerTest {
    @Autowired
    private ClMortgageService clMortgageService;

    @Test
    public void saveMortgage() {
        MortgageBean bean = new MortgageBean();
        bean.setBorrowAmount(100000D);
        bean.setDescript("车位");
        bean.setDyAddress("杭州市拱墅区九龙仓碧玺2幢2004室");
        bean.setDyBuyPrice(2000000D);
        bean.setDyArea(20.92D);
        bean.setDyBuyYear("2017");
        bean.setDyCity("杭州");
        bean.setDyCommunity("九龙仓碧玺");
        bean.setUserId(22L);
        List<String> list = new ArrayList<>();
        list.add("http://www.afa1");
        list.add("http://www.afa2");
        list.add("http://www.afa3");
        list.add("http://www.afa4");
        bean.setAssetsImg(list);
        //bean.setUserName();
        clMortgageService.mortgageApply(bean);
    }

    @Test
    public void trialAudit() {
        Response response = clMortgageService.trialAudit(ClMortgageOrderState.STATE_TRIAL, 4L, "", 0L);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void uploadImg() {
        Long mid = 2L;
        List<String> list = new ArrayList<>();
        list.add("http://x.afa1");
        list.add("http://3.afa2");
        list.add("http://5.afa3");
        list.add("http://7.afa4");
        clMortgageService.uploadOtherImg(mid, list, MortgageImgTypeConstant.USERIMG);
    }

    @Test
    public void setMortgageQuota() {
        MortgageBean bean = new MortgageBean();
        bean.setRealQuota(350000D);
        //bean.setRealRate("0.05");
        bean.setId(4L);
        bean.setUserId(22L);
        Response response = clMortgageService.setMortgageQuota(bean);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void auditMortgage() {
        BorrowAuditBean bean = new BorrowAuditBean();


        bean.setId(4L);
        // bean.set(2L);
        bean.setState(ClMortgageOrderState.STATE_V_PASS);
        Response response = clMortgageService.auditMortgage(bean);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void auditFinalMortgage() {
        BorrowAuditBean bean = new BorrowAuditBean();
        bean.setId(4L);
        // bean.setUserId(2L);
        bean.setState(ClMortgageOrderState.STATE_PASS);
        Response response = clMortgageService.auditFinalMortgage(bean);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void getmortgageDetail() {
        clMortgageService.getMortgagBean(10L);
    }
}
