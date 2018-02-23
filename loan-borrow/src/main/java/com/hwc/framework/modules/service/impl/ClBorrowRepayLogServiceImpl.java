package com.hwc.framework.modules.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.hwc.base.api.Response;
import com.hwc.framework.common.ParamUtil;
import com.hwc.framework.modules.dao.ClBorrowRepayLogMapper;
import com.hwc.framework.modules.domain.PayLogBean;
import com.hwc.framework.modules.domain.RepayLogBean;
import com.hwc.framework.modules.domain.RepayQueryRequest;
import com.hwc.framework.modules.model.ClBorrowRepay;
import com.hwc.framework.modules.model.ClBorrowRepayLog;
import com.hwc.framework.modules.service.ClBorrowRepayLogService;
import com.hwc.framework.modules.service.ClBorrowService;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.DataObjectConverter;
import com.hwc.mybatis.util.PageUtils;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/31.
 */
@Service
public class ClBorrowRepayLogServiceImpl extends
                                         AbstractService<ClBorrowRepayLogMapper, ClBorrowRepayLog>
                                         implements ClBorrowRepayLogService {
    private static final Logger logger = LoggerFactory.getLogger(ClBorrowRepayLogServiceImpl.class);

    @Autowired
    private ClBorrowService     borrowService;

    /**
     * 还款成功后 记录还款状态
     *
     * @param repay
     * @param param
     */
    public void doSave(ClBorrowRepay repay, Map<String, Object> param) {
        ClBorrowRepayLog log = new ClBorrowRepayLog();

        log.setBorrowId(repay.getBorrowId());
        log.setRepayId(repay.getId());
        log.setUserId(repay.getUserId());
        log.setAmount(FsUtils.bigdec(repay.getTotalAmount()));// 实际还款金额
        log.setRepayTime(repay.getRepayTime());// 实际还款时间
        log.setPenaltyDay(repay.getPenaltyDay());
        // 实际还款时间在应还款时间之前或当天（不对比时分秒），重置逾期金额和天数
        //        if (!repay_time.after(repayPlanTime)) {
        //            log.setPenaltyAmout(FsUtils.bigdec(0.00));
        //            log.setPenaltyDay(0);
        //        } else {
        // 金额减免时 罚金可页面填写
        String penaltyAmout = FsUtils.s(param.get("penaltyAmout"));
        if (FsUtils.strsNotEmpty(penaltyAmout)) {
            log.setPenaltyAmout(FsUtils.bigdec(penaltyAmout));
        } else {
            log.setPenaltyAmout(repay.getPenaltyAmout());
        }
        //  }

        log.setSerialNumber((String) param.get("serialNumber"));
        log.setRepayAccount((String) param.get("repayAccount"));
        log.setRepayWay((String) param.get("repayWay"));
        log.setPayTime(new Date());
        log.setCreateTime(new Date());
        logger.info("还款成功后 记录还款状态,borrowId=>" + repay.getBorrowId() + ",data=>"
                    + JSONObject.toJSONString(log));
        this.insert(log);
    }

    /**
     * app 上呈现的 还款记录
     *
     * @param userId
     * @return
     */
    public List<PayLogBean> getPayLogList(Long userId) {
        ClBorrowRepayLog repayLog = new ClBorrowRepayLog();
        repayLog.setUserId(userId);
        List<ClBorrowRepayLog> logs = this.mapper.select(repayLog);
        List<PayLogBean> payLogBeans = PageUtils.convert(logs,
            new DataObjectConverter<ClBorrowRepayLog, PayLogBean>() {
                @Override
                public PayLogBean convert(ClBorrowRepayLog log) {
                    PayLogBean bean = new PayLogBean();
                    bean.setBorrowId(log.getBorrowId());
                    bean.setPayTime(ParamUtil.isEmpty(log.getRepayTime()) ? log.getPayTime()
                        : log.getRepayTime());
                    bean.setAmount(log.getAmount().doubleValue());
                    bean.setRemark("归还成功");
                    return bean;
                }
            });

        return payLogBeans;
    }

    public Response getPayLogList(RepayQueryRequest request) {
        PageHelper.startPage(request.getPage(), request.getPageSize());
        Map<String, Object> map = new HashMap();
        if (FsUtils.strsNotEmpty(request.getStart())) {
            map.put("start", FsUtils.formatDate(request.getStart()));
        }
        if (FsUtils.strsNotEmpty(request.getEnd())) {
            map.put("end", FsUtils.formatDate(FsUtils.addDate(request.getEnd(), 1)));
        }
        if (FsUtils.strsNotEmpty(request.getMobile())) {
            map.put("mobile", request.getMobile());
        }
        if (FsUtils.strsNotEmpty(request.getOrderNo())) {
            map.put("orderNo", request.getOrderNo());
        }
        if (FsUtils.strsNotEmpty(request.getRepayWay())) {
            map.put("repayWay", request.getRepayWay());
        }
        map.put("borrow_type", request.getBorrow_type());
        List<RepayLogBean> beans = mapper.payLogList(map);
        return Response.success(beans);

    }

}
