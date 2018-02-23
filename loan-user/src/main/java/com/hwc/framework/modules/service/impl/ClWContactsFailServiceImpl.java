package com.hwc.framework.modules.service.impl;

import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.PageUtils;

import cn.freesoft.utils.FsUtils;

import com.hwc.base.api.Response;
import com.hwc.framework.modules.dao.ClWContactsFailMapper;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.domain.request.WContactsImportListRequest;
import com.hwc.framework.modules.domain.response.WContactsFailImportListResponse;
import com.hwc.framework.modules.domain.response.WContactsImportListResponse;
import com.hwc.framework.modules.model.ClWContacts;
import com.hwc.framework.modules.model.ClWContactsFail;
import com.hwc.framework.modules.service.ClWContactsFailService;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 失效白名单保存
 * 2018/01/26.
 */
@Service
public class ClWContactsFailServiceImpl extends AbstractService<ClWContactsFailMapper, ClWContactsFail> implements ClWContactsFailService {
    private static final Logger logger = LoggerFactory.getLogger(ClWContactsFailServiceImpl.class);

    
    //插入重复的
    @Override
    public int insert(DWContacts dwContacts) {
    	 dwContacts.setState("T");
         dwContacts.setIsBorrow("F");
         dwContacts.setIsAvailability("3");
         dwContacts.setUpdated(new Date());
         dwContacts.setCreated(new Date());
         int insert = this.mapper.insert(convertToClWContacts(dwContacts));
    	return insert;
    }
    
    //插入格式不正确  或 字段缺失的 
    @Override
    public int insertAbnormal(DWContacts dwContacts) {
    	 dwContacts.setState("T");
         dwContacts.setIsBorrow("F");
         dwContacts.setIsAvailability("2");
         dwContacts.setUpdated(new Date());
         dwContacts.setCreated(new Date());
         int insert = this.mapper.insert(convertToClWContacts(dwContacts));
    	return insert;
    }
    
    //条件查询   失败白名单
    @Override
    public List<DWContacts> listWContactsFailPage(Map<String, Object> params) {
        List<DWContacts> responseList = null;
        List<ClWContactsFail> clWContactsFailList = this.mapper.selectBySelective(params);
        if (clWContactsFailList != null) {
            responseList = new ArrayList<DWContacts>();
            for (int i = 0; i < clWContactsFailList.size(); i++) {
                responseList.add(convertToDWContacts(clWContactsFailList.get(i)));
            }
        }
        List<DWContacts> list = PageUtils.convert(clWContactsFailList, responseList);
        return list;
    }
    
    private DWContacts convertToDWContacts(ClWContactsFail clWContacts) {
        DWContacts dwContacts = new DWContacts();
        if (clWContacts != null) {
            dwContacts.setId(clWContacts.getId());
            dwContacts.setIdNo(clWContacts.getIdNo());
            dwContacts.setPhone(clWContacts.getPhone());
            dwContacts.setTipsPhone(clWContacts.getTipsPhone());
            dwContacts.setIsCredit(clWContacts.getIsCredit());
            dwContacts.setIsDy(clWContacts.getIsDy());
            dwContacts.setPhone(clWContacts.getPhone());
            dwContacts.setBorrowQuota(clWContacts.getBorrowQuota());
            dwContacts.setCompanyAddr(clWContacts.getCompanyAddr());
            dwContacts.setCompanyName(clWContacts.getCompanyName());
            dwContacts.setCompanyType(clWContacts.getCompanyType());
            dwContacts.setCreated(clWContacts.getCreated());
            dwContacts.setDyCity(clWContacts.getDyCity());
            dwContacts.setDyValue(clWContacts.getDyValue());
            dwContacts.setDyValueDiscount(clWContacts.getDyValueDiscount());
            dwContacts.setEducation(clWContacts.getEducation());
            dwContacts.setIsBorrow(clWContacts.getIsBorrow());
            dwContacts.setLiveAddr(clWContacts.getLiveAddr());
            dwContacts.setName(clWContacts.getName());
            dwContacts.setLiveCommunity(clWContacts.getLiveCommunity());
            dwContacts.setPersonIncome(clWContacts.getPersonIncome());
            dwContacts.setSrc(clWContacts.getSrc());
            dwContacts.setState(clWContacts.getState());
            dwContacts.setUpdated(clWContacts.getUpdated());
            dwContacts.setQuotaExpire(clWContacts.getQuotaExpire());
            dwContacts.setBorrowRate(clWContacts.getBorrowRate());
            dwContacts.setCreditLines(clWContacts.getCreditLines());
            dwContacts.setVocation(clWContacts.getVocation());
            dwContacts.setRiskLevel(clWContacts.getRiskLevel());
            dwContacts.setUnuseBorrowBalance(clWContacts.getUnuseBorrowBalance());
            dwContacts.setOldRepayRate(clWContacts.getOldRepayRate());
            dwContacts.setMonthRepayBalance(clWContacts.getMonthRepayBalance());
            //TODO
            dwContacts.setOriginalLimit(clWContacts.getOriginalLimit());
            dwContacts.setSurplusLimit(clWContacts.getSurplusLimit());
            dwContacts.setDyTerm(clWContacts.getDyTerm());
            dwContacts.setBorrowId(clWContacts.getBorrowid());
            dwContacts.setCityCode(clWContacts.getCityCode());
            dwContacts.setDeclarationCompany(clWContacts.getDeclarationCompany());
            dwContacts.setDeclarationPeople(clWContacts.getDeclarationPeople());
            dwContacts.setMortgageTestify(clWContacts.getMortgageTestify());
            dwContacts.setIsAvailability(clWContacts.getIsAvailability());
            dwContacts.setParkingPosition(clWContacts.getParkingPosition());
            dwContacts.setMsg(clWContacts.getMsg());
            return dwContacts;
        } else {
            return null;
        }
    }
    
	private ClWContactsFail convertToClWContacts(DWContacts dwContacts) {
		ClWContactsFail clWContactsFail = new ClWContactsFail();
	    if (dwContacts != null) {
	    	clWContactsFail.setId(dwContacts.getId());
	    	clWContactsFail.setIdNo(dwContacts.getIdNo());
	    	clWContactsFail.setPhone(dwContacts.getPhone());
	    	clWContactsFail.setTipsPhone(dwContacts.getTipsPhone());
	    	clWContactsFail.setIsCredit(dwContacts.getIsCredit());
	    	clWContactsFail.setIsDy(dwContacts.getIsDy());
	    	clWContactsFail.setPhone(dwContacts.getPhone());
	    	clWContactsFail.setBorrowQuota(dwContacts.getBorrowQuota());
	    	clWContactsFail.setCompanyAddr(dwContacts.getCompanyAddr());
	    	clWContactsFail.setCompanyName(dwContacts.getCompanyName());
	    	clWContactsFail.setCompanyType(dwContacts.getCompanyType());
	        clWContactsFail.setCreated(dwContacts.getCreated());
	        clWContactsFail.setDyCity(dwContacts.getDyCity());
	        clWContactsFail.setDyValue(dwContacts.getDyValue());
	        clWContactsFail.setDyValueDiscount(dwContacts.getDyValueDiscount());
	        clWContactsFail.setEducation(dwContacts.getEducation());
	        clWContactsFail.setIsBorrow(dwContacts.getIsBorrow());
	        clWContactsFail.setLiveAddr(dwContacts.getLiveAddr());
	        clWContactsFail.setName(dwContacts.getName());
	        clWContactsFail.setLiveCommunity(dwContacts.getLiveCommunity());
	        clWContactsFail.setPersonIncome(dwContacts.getPersonIncome());
	        clWContactsFail.setSrc(dwContacts.getSrc());
	        clWContactsFail.setState(dwContacts.getState());
	        clWContactsFail.setUpdated(dwContacts.getUpdated());
	        clWContactsFail.setQuotaExpire(dwContacts.getQuotaExpire());
	        clWContactsFail.setBorrowRate(dwContacts.getBorrowRate());
	        clWContactsFail.setCreditLines(dwContacts.getCreditLines());
	        clWContactsFail.setVocation(dwContacts.getVocation());
	        clWContactsFail.setRiskLevel(dwContacts.getRiskLevel());
	        clWContactsFail.setUnuseBorrowBalance(dwContacts.getUnuseBorrowBalance());
	        clWContactsFail.setOldRepayRate(dwContacts.getOldRepayRate());
	        clWContactsFail.setMonthRepayBalance(dwContacts.getMonthRepayBalance());
	        //TODO
	        clWContactsFail.setOriginalLimit(dwContacts.getOriginalLimit());
	        clWContactsFail.setSurplusLimit(dwContacts.getSurplusLimit());
	        clWContactsFail.setDyTerm(dwContacts.getDyTerm());
	        clWContactsFail.setBorrowid(dwContacts.getBorrowId());
	        clWContactsFail.setCityCode(dwContacts.getCityCode());
	        clWContactsFail.setDeclarationCompany(dwContacts.getDeclarationCompany());
	        clWContactsFail.setDeclarationPeople(dwContacts.getDeclarationPeople());
	        clWContactsFail.setMortgageTestify(dwContacts.getMortgageTestify());
	        clWContactsFail.setIsAvailability(dwContacts.getIsAvailability());
	        clWContactsFail.setParkingPosition(dwContacts.getParkingPosition());
	        clWContactsFail.setMsg(dwContacts.getMsg());
	        return clWContactsFail;
	    } else {
	        return null;
	    }
	}




}
