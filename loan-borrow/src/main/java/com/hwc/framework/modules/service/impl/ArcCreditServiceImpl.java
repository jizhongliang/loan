package com.hwc.framework.modules.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwc.framework.modules.third.UserService;
import com.hwc.loan.sdk.user.domain.DCloanUserDomain;
import com.hwc.loan.sdk.user.domain.DUserStateDomian;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwc.base.api.IdRequest;
import com.hwc.base.api.Response;
import com.hwc.base.exception.ServiceException;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.dao.ArcCreditMapper;
import com.hwc.framework.modules.model.ArcCredit;
import com.hwc.framework.modules.model.UserBorrowQuota;
import com.hwc.framework.modules.service.ArcCreditService;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.web.core.RequestUtils;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/23.
 */
@Service
public class ArcCreditServiceImpl extends AbstractService<ArcCreditMapper, ArcCredit>
                                  implements ArcCreditService {
    private static final Logger logger = LoggerFactory.getLogger(ArcCreditServiceImpl.class);

    private static final String key    = "arc:credit:user_id:";
    @Autowired
    private IHwcCache           cache;
    @Autowired
    private UserService userService;

    /**
     * 借款减去额度
     *
     * @param userId
     * @param amount
     */
    public void subtractQuota(Long userId, Double amount) {
        ArcCredit credit = getCreditByUserid(userId);
        if (FsUtils.strsEmpty(credit)) {
            throw new ServiceException("未找到额度信息");
        }
        Map<String, Object> map = new HashMap();
        map.put("used", amount);
        map.put("unuse", -amount);
        map.put("id", credit.getId());
        mapper.updateAmount(map);
        String newkey = key + userId;

        cache.del(newkey);
        getCreditByUserid(userId);
    }

    /**
     * 还款增加额度
     *
     * @param userId
     * @param amount
     */
    public void increaseQuota(Long userId, Double amount) {
        ArcCredit credit = getCreditByUserid(userId);
        if (FsUtils.strsEmpty(credit)) {
            throw new ServiceException("未找到额度信息");
        }
        Map<String, Object> map = new HashMap();
        map.put("used", credit.getUsed().doubleValue() >amount ? credit.getUsed().doubleValue() - amount : 0);
        map.put("unuse",credit.getUnuse().doubleValue()+amount);
        map.put("id", credit.getId());
        mapper.updates(map);
        logger.info("increaseQuota,userId=>" + userId + ",amount=>" + amount);
        String newkey = key + userId;
        cache.del(newkey);
        getCreditByUserid(userId);
    }

    /**
     * 检查额度是否大于借款额度
     *
     * @param userId
     * @param borrow_amount
     * @return
     */
    public boolean isQuota(Long userId, Double borrow_amount) {
        ArcCredit credit = getCreditByUserid(userId);
        if (FsUtils.strsNotEmpty(credit)) {
            if (FsUtils.strsEmpty(credit.getUnuse())) {
                credit.setUnuse(FsUtils.bigdec(0));
            }
            if ("20".equals(credit.getState())) {
                //额度被冻结
                throw new ServiceException("该账号无法借款,请联系客服人员");
            }
            Double left_amount = FsUtils.subDouble(credit.getUnuse().doubleValue(), borrow_amount);
            if (left_amount >= 0D) {
                return true;
            } else {
                return false;
            }
        } else {
            throw new ServiceException("未找到额度信息");
        }

    }

    public boolean unFreezeQuota(Long userId) {
        return createQuotaState(userId, "10");
    }

    public boolean freezeQuota(Long userId) {
        return createQuotaState(userId, "20");
    }

    private boolean createQuotaState(Long userId, String state) {
        ArcCredit credit = getCreditByUserid(userId);
        if (FsUtils.strsEmpty(credit)) {
            throw new ServiceException("未找到额度信息");
        }
        credit.setState(state);
        int cnt = this.mapper.updateByPrimaryKeySelective(credit);
        String newkey = key + userId;
        cache.del(newkey);
        if (cnt == 1)
            return true;
        else
            return false;
    }

    /**
     * 设置用户额度
     *
     * @param userId
     * @param quota
     */
    public Response setUserQuota(Long userId, Double quota) {
        logger.info("设置额度{}_{}", userId, quota);
        if (FsUtils.strsEmpty(userId)) {
            return Response.fail("userId不能为空");
        }
        ArcCredit credit = getCreditByUserid(userId);
        if (FsUtils.strsNotEmpty(credit)) {
            if (credit.getUsed().doubleValue() != 0D) {
                return Response.fail("额度已经被使用，不能覆盖");
            } else {
                credit.setUserId(userId);
                credit.setUnuse(FsUtils.bigdec(quota));
                credit.setTotal(credit.getUnuse());
                credit.setCreditType(0L);
                credit.setState("10");
                this.update(credit);
                String newkey = key + userId;
                cache.del(newkey);
            }
        } else {
            credit = new ArcCredit();
            credit.setUserId(userId);
            credit.setUnuse(FsUtils.bigdec(quota));
            credit.setUsed(FsUtils.bigdec(0));
            credit.setTotal(credit.getUnuse());
            credit.setCreditType(0L);
            credit.setState("10");
            int cnt = insert(credit);
        }
        return Response.success(credit);
    }

    public ArcCredit getCreditByUserid(Long userId) throws ServiceException {
        String newkey = key + userId;
        if (cache.exists(newkey)) {
            return cache.get(newkey, ArcCredit.class);
        } else {
            ArcCredit credit = mapper.getCreditById(userId);
            if (FsUtils.strsNotEmpty(credit)) {
                Date endDate = FsUtils.addDate(new Date(), 7);
                Long expire = FsUtils.getDateSpan(new Date(), endDate);
                cache.set(newkey, expire, credit);
                return credit;
            } else {
                return null;
            }
        }

    }

    @Override
    public List<Double> getQuotaRang(Long userId, Double min, Double step) {
        ArcCredit credit = getCreditByUserid(userId);
        if (FsUtils.strsEmpty(credit)) {
            throw new ServiceException("未找到额度信息");
        }
        Double left = credit.getUnuse().doubleValue();
        double nstep = FsUtils.divDouble(left, 10);
        if (step < nstep) {
            step = FsUtils.roundCeilingDouble(nstep, 0);
        }

        List<Double> quotas = new ArrayList<>();
        if (left <= min) {
            quotas.add(left);
            quotas.add(left);
            return quotas;
        } else {
            quotas.add(min);
            while (FsUtils.addDouble(min, step) <= left) {
                quotas.add(FsUtils.addDouble(min, step));
                min = FsUtils.addDouble(min, step);
            }
            if (!quotas.get(quotas.size() - 1).equals(left)) {
                quotas.add(left);
            }
            return quotas;
        }
    }

    @Override
    public Response getQuota(IdRequest<Long> request) {
        Object uid = RequestUtils.getUserId();
        if (FsUtils.strsEmpty(uid)) {
            uid = request.getId();
        }
        Long userId = Long.parseLong(uid.toString());
        DCloanUserDomain userInfo = userService.getUserInfo(userId);
        UserBorrowQuota quota = new UserBorrowQuota();
        if ("10".equalsIgnoreCase(userInfo.getCat()) ) {
            DUserStateDomian domian = userService.userIsAuth(userId);
            if (!domian.isAuth()){
                quota.setQuota("0");
                return Response.success(quota);
            }
        }
        if (FsUtils.strsEmpty(uid)) {
            quota.setQuota("0");
        } else {
            userId = FsUtils.l(uid);
            ArcCredit credit = getCreditByUserid(userId);
            if (FsUtils.strsEmpty(credit)) {
                quota.setQuota("0");
            } else {
                Double unUse = credit.getUnuse().doubleValue();
                if (unUse >= 10000) {
                    unUse = FsUtils.divDouble(unUse, 10000D);
                    quota.setQuota(FsUtils.roundDouble(unUse, 2) + "万");
                } else
                    quota.setQuota(FsUtils.roundDouble(unUse, 2) + "");

            }
        }
        return Response.success(quota);
    }
}
