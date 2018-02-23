package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.dao.SmsTplMapper;
import com.hwc.framework.modules.domain.DSmsTpl;
import com.hwc.framework.modules.model.ClSmsTpl;
import com.hwc.framework.modules.service.SmsTplService;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 2017/10/18.
 */
@Service
public class SmsTplServiceImpl extends AbstractService<SmsTplMapper, ClSmsTpl> implements SmsTplService {
    private static final Logger logger = LoggerFactory.getLogger(SmsTplServiceImpl.class);

    @Override
    public DSmsTpl findSmsTplByType(String type) {
        if (FsUtils.strsEmpty(type)){
            return null;
        }
        ClSmsTpl select = new ClSmsTpl();
        select.setType(type);
        select.setState("10");
        ClSmsTpl clSmsTpl = this.mapper.selectOne(select);
        if (clSmsTpl != null){
            return convertToDSmsTpl(clSmsTpl);
        }
        return null;
    }


    public String ret(String type) {
        DSmsTpl tpl = this.findSmsTplByType(type);
        return tpl.getTpl();
    }

    public String change(String code) {
        String message = null;
        if ("register".equals(code)) {
            message = ret("register");
        } else if ("findReg".equals(code)) {
            message = ret("findReg");
        } else if ("bindCard".equals(code)) {
            message = ret("bindCard");
        } else if ("findPay".equals(code)) {
            message = ret("findPay");
        } else if ("overdue".equals(code)) {
            message = ret("overdue");
        } else if ("loanInform".equals(code)) {
            message = ret("loanInform");
        } else if ("repayInform".equals(code)) {
            message = ret("repayInform");
        } else if ("login".equals(code)) {
            message = ret("login");
        }else if ("manageLogin".equals(code)) {
            message = ret("manageLogin");
        }
        return message;
    }

    protected String changeMessage(String smsType, Map<String, Object> map) {
        String message = "";
        if ("overdue".equals(smsType) || "repaying".equals(smsType) || "overdueing".equals(smsType)) {
            message = ret(smsType);
            message = message
                    .replace(
                            "{$platform}",
                            isNull(map.get("platform")))
                    .replace("{$loan}", isNull(map.get("loan")))
                    .replace(
                            "{$time}",
                            isNull(map.get("time")))
                    .replace("{$overdueday}",
                            isNull(map.get("overdueday")))
                    .replace("{$amercement}",
                            isNull(map.get("amercement")));
        }
        if ("loanInform".equals(smsType) || "borrowDeny".equals(smsType) || "borrowApply".equals(smsType)) {
            message = ret(smsType);
            message = message.replace("{$time}",
                    isNull(map.get("time")))
                    .replace(
                            "{$platform}",
                            isNull(map.get("platform")));
        }
        if ("repayInform".equals(smsType)) {
            message = ret(smsType);
            message = message.replace("{$time}",
                    isNull(map.get("time")))
                    .replace("{$loan}", isNull(map.get("loan")))
                    .replace(
                            "{$platform}",
                            isNull(map.get("platform")));
        }

        return message;
    }

    private DSmsTpl convertToDSmsTpl(ClSmsTpl clSmsTpl){
        DSmsTpl dSmsTpl = new DSmsTpl();
        dSmsTpl.setId(clSmsTpl.getId());
        dSmsTpl.setNumber(clSmsTpl.getNumber());
        dSmsTpl.setState(clSmsTpl.getState());
        dSmsTpl.setTpl(clSmsTpl.getTpl());
        dSmsTpl.setType(clSmsTpl.getType());
        dSmsTpl.setTypeName(clSmsTpl.getTypeName());
        return dSmsTpl;
    }

    public static String isNull(Object o) {
        if (o == null) {
            return "";
        } else {
            String str = "";
            if (o instanceof String) {
                str = (String)o;
            } else {
                str = o.toString();
            }

            return str.trim();
        }
    }
    
}
