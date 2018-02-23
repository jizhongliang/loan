package com.hwc.framework.modules.service.impl;

import cn.freesoft.utils.FsUtils;
import com.hwc.base.exception.ServiceException;
import com.hwc.common.aliyun.IHwcCache;
import com.hwc.framework.modules.dao.ArcCreditMapper;
import com.hwc.framework.modules.model.ArcCredit;
import com.hwc.framework.modules.service.ArcCreditService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jzl on 2018/1/8.
 */
@Service
public class ArcCreditServiceImpl implements ArcCreditService {
    private static Logger logger = LoggerFactory.getLogger(ArcCreditServiceImpl.class);

    private static final String key = "arc:credit:user_id:";

    @Autowired
    private ArcCreditMapper arcCreditMapper;
    @Autowired
    IHwcCache cache;

    /**
     * 还款增加额度
     *
     * @param userId
     * @param amount
     */
    @Override
    public void increaseQuota(Long userId, Double amount) {
        ArcCredit credit = getCreditByUserid(userId);
        if (FsUtils.strsEmpty(credit)) {
            throw new ServiceException("未找到额度信息");
        }
        Map<String, Object> map = new HashMap();
        map.put("used", -amount);
        map.put("unuse", amount);
        map.put("id", credit.getId());
        arcCreditMapper.updateAmount(map);
        String newkey = key + userId;
        cache.del(newkey);
        getCreditByUserid(userId);
    }

    public ArcCredit getCreditByUserid(Long userId) throws ServiceException {
        String newkey = key + userId;
        if (cache.exists(newkey)) {
            return cache.get(newkey, ArcCredit.class);
        } else {
            ArcCredit credit = arcCreditMapper.getCreditById(userId);
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
}
