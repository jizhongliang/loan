package com.hwc.framework.modules.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.hwc.framework.modules.dao.ClBankCardMapper;
import com.hwc.framework.modules.dao.ClQuartzInfoMapper;
import com.hwc.framework.modules.dao.ClQuartzLogMapper;
import com.hwc.framework.modules.model.ClBankCard;
import com.hwc.framework.modules.model.ClQuartzInfo;
import com.hwc.framework.modules.model.QuartzInfoModel;
import com.hwc.framework.modules.service.QuartzInfoService;
import com.hwc.framework.utils.DateUtil;
import com.hwc.mybatis.core.AbstractService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务详情ServiceImpl
 * 
 */
@Service
public class QuartzInfoServiceImpl extends AbstractService<ClQuartzInfoMapper, ClQuartzInfo> implements QuartzInfoService {
	
	private static final Logger logger = LoggerFactory.getLogger(QuartzInfoServiceImpl.class);
   

    @Resource
    private ClQuartzLogMapper quartzLogMapper;


	@Override
	public boolean save(ClQuartzInfo qi) {
		int result = this.mapper.insert(qi);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Map<String, Object> search) {
		int result = this.mapper.updateSelective(search);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<ClQuartzInfo> list(Map<String, Object> result) {
		return this.mapper.listSelective(result);
	}

	@Override
	public Page<QuartzInfoModel> page(Map<String, Object> searchMap,
									  int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<QuartzInfoModel> list = this.mapper.page(searchMap);
		
		for (QuartzInfoModel quartzInfoModel : list) {
			String lastStartTime = quartzLogMapper.findLastTimeByQzInfoId(quartzInfoModel.getId());
			if(StringUtils.isNotBlank(lastStartTime)){
				quartzInfoModel.setLastStartTime(DateUtil.valueOf(lastStartTime));
			}
			
		}
		
		return (Page<QuartzInfoModel>) list;
	}

	@Override
	public ClQuartzInfo findByCode(String code){
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", code);
			ClQuartzInfo quartzInfo = this.mapper.findSelective(paramMap);
			if (null != quartzInfo) {
				return quartzInfo;
			}
		} catch (Exception e) {
			logger.error("查询定时任务异常", e);
		}
		return null;
	}
	
	@Override
	public ClQuartzInfo findSelective(Map<String, Object> paramMap) {
		try {
			ClQuartzInfo quartzInfo = this.mapper.findSelective(paramMap);
			if (null != quartzInfo) {
				return quartzInfo;
			}
		} catch (Exception e) {
			logger.error("查询定时任务异常", e);
		}
		return null;
	}

	@Override
	public ClQuartzInfo getById(Long id) {
		return this.mapper.selectByPrimaryKey(id);
	}

}