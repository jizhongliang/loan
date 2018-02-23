package com.hwc.framework.modules.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hwc.base.sdk.core.Client;
import com.hwc.base.sdk.core.ItemData;
import com.hwc.loan.sdk.borrow.domain.ManageMortgageListDomain;
import com.hwc.loan.sdk.borrow.request.ManageMortgageUpdateByPhoneRequest;
import com.hwc.loan.sdk.borrow.response.ManageMortgageUpdateByPhoneResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.druid.sql.ast.statement.SQLIfStatement.Else;
import com.hwc.base.api.Response;
import com.hwc.framework.modules.dao.ClWContactsMapper;
import com.hwc.framework.modules.domain.DWContacts;
import com.hwc.framework.modules.domain.request.WContactsImportListRequest;
import com.hwc.framework.modules.domain.response.WContactsImportListResponse;
import com.hwc.framework.modules.model.ClWContacts;
import com.hwc.framework.modules.service.ClWContactsFailService;
import com.hwc.framework.modules.service.ClWContactsService;
import com.hwc.mybatis.core.AbstractService;
import com.hwc.mybatis.util.PageUtils;

import cn.freesoft.utils.FsUtils;

/**
 * 2017/10/23.
 */
@Service
public class ClWContactsServiceImpl extends AbstractService<ClWContactsMapper, ClWContacts>
                                    implements ClWContactsService {
    private static final Logger logger = LoggerFactory.getLogger(ClWContactsServiceImpl.class);

    @Autowired
    private Client borrowClient;
    
    @Autowired
    private ClWContactsFailService wContactsFailService;
    
    @Override
    public Response getWContactsWithPhone(DWContacts request) {
        if (FsUtils.strsEmpty(request) || FsUtils.strsEmpty(request.getPhone())) {
            return Response.fail("参数错误");
        }
        DWContacts dwContacts = this.getWContactsWithPhone(request.getPhone());
        if (dwContacts != null) {
            return Response.success(dwContacts);
        }
        return Response.fail("该用户不是白名单");
    }

    //比较私人号码是否重复
    @Override
    public DWContacts getWContactsWithPhone(String phone) {
        Map<String, Object> params = new HashMap<>();
        params.put("phone", phone);
        List<ClWContacts> selectBySelective = this.mapper.selectBySelective(params);
        if (selectBySelective != null && selectBySelective.size() > 0) {
            return convertToDWContacts(selectBySelective.get(0));
        }
        return null;
    }
    //比较提示号码是否重复
    public DWContacts getWContactsWithTisPhone(String tipsPhone) {
        Map<String, Object> params = new HashMap<>();
        params.put("tipsPhone", tipsPhone);
        List<ClWContacts> selectBySelective = this.mapper.selectBySelective(params);
        if (selectBySelective != null && selectBySelective.size() > 0) {
            return convertToDWContacts(selectBySelective.get(0));
        }
        return null;
    }
    //比较身份证号是否重复
    public DWContacts getWContactsWithidNo(String idNo) {
        Map<String, Object> params = new HashMap<>();
        params.put("idNo", idNo);
        List<ClWContacts> selectBySelective = this.mapper.selectBySelective(params);
        if (selectBySelective != null && selectBySelective.size() > 0) {
            return convertToDWContacts(selectBySelective.get(0));
        }
        return null;
    }
    /** 
     * @see com.hwc.framework.modules.service.ClWContactsService#getWContactsWithTipsPhone(java.lang.String)
     */
    @Override
    public DWContacts getWContactsWithTipsPhone(String tipsPhone) {
        ClWContacts select = new ClWContacts();
        select.setTipsPhone(tipsPhone);
        select.setIsAvailability("1");
        List<ClWContacts> clWContacts = this.mapper.select(select);
        if (clWContacts == null || clWContacts.isEmpty()) {
            return null;
        }
        DWContacts dwContacts = convertToDWContacts(clWContacts.get(0));
        return dwContacts;
    }

    @Override
    public DWContacts getWContactsWithIdNo(String idNO) {
        ClWContacts select = new ClWContacts();
        select.setIdNo(idNO);
        ClWContacts clWContacts = this.mapper.selectOne(select);
        DWContacts dwContacts = convertToDWContacts(clWContacts);
        return dwContacts;
    }
    
    //查正常白名单
    @Override
    public List<DWContacts> listWContactsPage(Map<String, Object> params) {
        List<DWContacts> responseList = null;
        List<ClWContacts> clWContactsList = this.mapper.selectBySelective(params);
        if (clWContactsList != null) {
            responseList = new ArrayList<DWContacts>();
            for (int i = 0; i < clWContactsList.size(); i++) {
                responseList.add(convertToDWContacts(clWContactsList.get(i)));
            }
        }
        List<DWContacts> list = PageUtils.convert(clWContactsList, responseList);
        return list;
    }

    @Override
    public Response<WContactsImportListResponse> importWContactsList(WContactsImportListRequest request) {
        WContactsImportListResponse wContactsImportListResponse = new WContactsImportListResponse();
        List<DWContacts> list = request.getList();
        if (FsUtils.strsEmpty(list)) {
            return Response.fail("数据为空，无法导入");
        }
        int size = list.size(), failNum = 0, successNum = 0;
        for (int i = 0; i < size; i++) {
            DWContacts dwContacts = list.get(i);
            
     
            if (dwContacts != null && !FsUtils.strsEmpty(dwContacts.getPhone())
                || !FsUtils.isMobileNum(dwContacts.getPhone())) {
                if(dwContacts.getIsAvailability().equals("2")){//字段缺失,格式不正确
                	wContactsFailService.insertAbnormal(dwContacts);
                	failNum++;
                }else if(dwContacts.getIsAvailability().equals("1")){
                	 DWContacts findOne = this.getWContactsWithPhone(dwContacts.getPhone());//查电话重复
                	 DWContacts findOne1 = this.getWContactsWithTisPhone(dwContacts.getPhone());//查是否和提示号码重复
                	 DWContacts findOne2 = null;
                	 DWContacts findOne3 = null;
                	 if (null != dwContacts.getTipsPhone()&&!"".equals(dwContacts.getTipsPhone())) {
                		 findOne2 = this.getWContactsWithPhone(dwContacts.getTipsPhone());//查电话重复
                    	 findOne3 = this.getWContactsWithTisPhone(dwContacts.getTipsPhone());//查是否和提示号码重复
					}
                     if (null == findOne && null == findOne1 && null == findOne2 && null == findOne3) {//正常
                    	 if(null != dwContacts.getIdNo()&&!"".equals(dwContacts.getIdNo())){
                    		 DWContacts wContactsWithidNo = getWContactsWithidNo(dwContacts.getIdNo());
                        	 if (null == wContactsWithidNo) {//身份证号没重复
    	                		 dwContacts.setState("T");
    	                         dwContacts.setIsBorrow("F");
    	                         dwContacts.setUpdated(new Date());
    	                         dwContacts.setCreated(new Date());
    	                         this.mapper.insert(convertToClWContacts(dwContacts));
    	                         successNum++;
    						 }else{//重复
    							 if(null != dwContacts.getMsg()){
    	                    		 dwContacts.setMsg(dwContacts.getMsg()+",身份证号重复");
    	                     	}else{
    	                     		dwContacts.setMsg("身份证号重复");
    	                     	}
    	                         wContactsFailService.insert(dwContacts);
    	                         failNum++; 
    						 }
                    	 }else{
                    		 dwContacts.setState("T");
	                         dwContacts.setIsBorrow("F");
	                         dwContacts.setUpdated(new Date());
	                         dwContacts.setCreated(new Date());
	                         this.mapper.insert(convertToClWContacts(dwContacts));
	                         successNum++;
                    	 }
                     } else {//重复
                    	 if(null != dwContacts.getMsg()){
                    		 dwContacts.setMsg(dwContacts.getMsg()+",手机号码重复");
                     	}else{
                     		dwContacts.setMsg("手机号码重复");
                     	}
                         wContactsFailService.insert(dwContacts);
                         failNum++;
                     }
                }else{
                	logger.info("字段“IsAvailability”值不是1或者2");
                	failNum++;
                }
            } else {
                failNum++;
            }
        }
        wContactsImportListResponse.setSuccessNum(successNum);
        wContactsImportListResponse.setFailNum(failNum);
        return Response.success(wContactsImportListResponse);
    }

    @Override
    public Response importWContactsOne(DWContacts dwContacts) {
        if (dwContacts == null || FsUtils.strsEmpty(dwContacts.getPhone())) {
            return Response.fail("数据为空，无法导入");
        }
        DWContacts findOne = this.getWContactsWithPhone(dwContacts.getPhone());
        if (findOne == null) {
            dwContacts.setUpdated(new Date());
            dwContacts.setCreated(new Date());
            this.mapper.insert(convertToClWContacts(dwContacts));
            return Response.success();
        } else {
            return Response.fail("该用户已存在");
        }
    }

    @Override
    public Response updateWContactsOne(DWContacts request) {
        if (request == null || FsUtils.strsEmpty(request.getId(), request.getPhone(),
                request.getBorrowQuota(), request.getBorrowRate(), request.getName())) {
            return Response.fail("参数错误");
        }
        request.setUpdated(new Date());
        this.update(convertToClWContacts(request));

        if(("F").equals(request.getIsCredit())&&("T").equals(request.getIsDy())) {
            //修改cl_mortgage对应字段

            ManageMortgageUpdateByPhoneRequest manageMortgageGetByPhoneRequest = new ManageMortgageUpdateByPhoneRequest();
            manageMortgageGetByPhoneRequest.setMobile(request.getPhone());
            manageMortgageGetByPhoneRequest.setRealQuota(Double.valueOf(request.getBorrowQuota().toString()));
            manageMortgageGetByPhoneRequest.setRealRate(request.getBorrowRate());
            ManageMortgageUpdateByPhoneResponse manageMortgageUpdateByPhoneResponse = borrowClient.invoke(manageMortgageGetByPhoneRequest);

            if (manageMortgageUpdateByPhoneResponse.getSuccess()) {

                return Response.success();
            } else {
                return Response.fail("修改抵押物记录失败");
            }
        }
        return Response.success();
    }


    @Override
    public Response getWContactsOne(Long id) {
        if (FsUtils.strsEmpty(id)) {
            return Response.fail("参数错误");
        }
        ClWContacts clWContacts = this.mapper.selectByPrimaryKey(id);
        if (clWContacts != null) {
            DWContacts dwContacts = convertToDWContacts(clWContacts);
            return Response.success(dwContacts);
        } else {
            return Response.fail("该白名单不存在");
        }

    }

    private DWContacts convertToDWContacts(ClWContacts clWContacts) {
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
            dwContacts.setBorrowId(clWContacts.getBorrowId());
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

    private ClWContacts convertToClWContacts(DWContacts dwContacts) {
        ClWContacts clWContacts = new ClWContacts();
        if (dwContacts != null) {
            clWContacts.setId(dwContacts.getId());
            clWContacts.setIdNo(dwContacts.getIdNo());
            clWContacts.setPhone(dwContacts.getPhone());
            clWContacts.setTipsPhone(dwContacts.getTipsPhone());
            clWContacts.setIsCredit(dwContacts.getIsCredit());
            clWContacts.setIsDy(dwContacts.getIsDy());
            clWContacts.setPhone(dwContacts.getPhone());
            clWContacts.setBorrowQuota(dwContacts.getBorrowQuota());
            clWContacts.setCompanyAddr(dwContacts.getCompanyAddr());
            clWContacts.setCompanyName(dwContacts.getCompanyName());
            clWContacts.setCompanyType(dwContacts.getCompanyType());
            clWContacts.setCreated(dwContacts.getCreated());
            clWContacts.setDyCity(dwContacts.getDyCity());
            clWContacts.setDyValue(dwContacts.getDyValue());
            clWContacts.setDyValueDiscount(dwContacts.getDyValueDiscount());
            clWContacts.setEducation(dwContacts.getEducation());
            clWContacts.setIsBorrow(dwContacts.getIsBorrow());
            clWContacts.setLiveAddr(dwContacts.getLiveAddr());
            clWContacts.setName(dwContacts.getName());
            clWContacts.setLiveCommunity(dwContacts.getLiveCommunity());
            clWContacts.setPersonIncome(dwContacts.getPersonIncome());
            clWContacts.setSrc(dwContacts.getSrc());
            clWContacts.setState(dwContacts.getState());
            clWContacts.setUpdated(dwContacts.getUpdated());
            clWContacts.setQuotaExpire(dwContacts.getQuotaExpire());
            clWContacts.setBorrowRate(dwContacts.getBorrowRate());
            clWContacts.setCreditLines(dwContacts.getCreditLines());
            clWContacts.setVocation(dwContacts.getVocation());
            clWContacts.setRiskLevel(dwContacts.getRiskLevel());
            clWContacts.setUnuseBorrowBalance(dwContacts.getUnuseBorrowBalance());
            clWContacts.setOldRepayRate(dwContacts.getOldRepayRate());
            clWContacts.setMonthRepayBalance(dwContacts.getMonthRepayBalance());
            //TODO
            clWContacts.setOriginalLimit(dwContacts.getOriginalLimit());
            clWContacts.setSurplusLimit(dwContacts.getSurplusLimit());
            clWContacts.setDyTerm(dwContacts.getDyTerm());
            clWContacts.setBorrowId(dwContacts.getBorrowId());
            clWContacts.setCityCode(dwContacts.getCityCode());
            clWContacts.setDeclarationCompany(dwContacts.getDeclarationCompany());
            clWContacts.setDeclarationPeople(dwContacts.getDeclarationPeople());
            clWContacts.setMortgageTestify(dwContacts.getMortgageTestify());
            clWContacts.setIsAvailability(dwContacts.getIsAvailability());
            clWContacts.setParkingPosition(dwContacts.getParkingPosition());
            dwContacts.setMsg(clWContacts.getMsg());
            return clWContacts;
        } else {
            return null;
        }
    }
    @Override
	public DWContacts getByP(Map<String, String> map) {
        ClWContacts obj = this.mapper.getByP(map);
        if (obj == null) {
            return  null;
        }
        return convertToDWContacts(obj);
	}
}
