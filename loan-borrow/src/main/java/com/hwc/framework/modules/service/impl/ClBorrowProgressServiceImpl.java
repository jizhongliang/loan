package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.framework.modules.dao.ClBorrowProgressMapper;
import com.hwc.framework.modules.model.ClBorrow;
import com.hwc.framework.modules.model.ClBorrowProgress;
import com.hwc.framework.modules.service.ClBorrowProgressService;
import com.hwc.mybatis.core.AbstractService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 2017/10/23.
 */
@Service
public class ClBorrowProgressServiceImpl extends AbstractService<ClBorrowProgressMapper, ClBorrowProgress> implements ClBorrowProgressService {
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowProgressServiceImpl.class);

    public void borrowProgress(ClBorrow borrow) {
        try {
            ClBorrowProgress progress = new ClBorrowProgress();
            progress.setState(borrow.getState());
            progress.setBorrowId(borrow.getId());
            progress.setCreateTime(new Date());
            progress.setUserId(borrow.getUserId());
            progress.setRemark(getremark(borrow.getState()));
            insert(progress);
        } catch (Exception ex) {
            FsUtils.log_error(ex);
        }


    }

    private String getremark(String state) {
        if (state.equals("22")) {
            return "系统复审中,请耐心等待";
        } else if (state.equals("26")) {
            return "恭喜通过审核";
        } else if (state.equals("30")) {
            return "已打款,请查看您的提现银行卡";
        } else if (state.equals("40")) {
            return "还款成功";
        } else if (state.equals("50")) {
            return "订单已逾期";
        } else
            return "";
    }

}
