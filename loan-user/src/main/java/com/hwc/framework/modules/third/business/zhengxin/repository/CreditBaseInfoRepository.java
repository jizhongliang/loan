package com.hwc.framework.modules.third.business.zhengxin.repository;

import com.hwc.framework.modules.service.base.BaseDAO;
import com.hwc.framework.modules.third.business.zhengxin.entity.CreditBaseInfoEntity;
import com.hwc.framework.modules.third.util.DateUtil;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 这里用的是jdbctemplate，DAO层可以根据自己的业务来选择
 * ClassName: UserInfoRepository
 * date: 2016年7月19日 下午6:29:23   
 * @author yuandong  
 * @version   
 * @since JDK 1.6
 */
@Repository
public class CreditBaseInfoRepository extends BaseDAO<CreditBaseInfoEntity> {
	
//	@Resource(name="templateZhengxin")
//    private JdbcTemplate jdbcTemplate;
//
//    public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
    
    public void saveCreditBaseInfo(CreditBaseInfoEntity entity) throws Exception  {
    	entity.setCreateTime(DateUtil.getCurrentDate());
    	entity.setLastModifyTime(DateUtil.getCurrentDate());
		this.add(entity);
    }
    
    public void updateCreditBaseInfo(CreditBaseInfoEntity entity) throws Exception  {
    	entity.setLastModifyTime(DateUtil.getCurrentDate());
		this.modify(entity);
    }
    
    public CreditBaseInfoEntity getCreditBaseInfo(String userId,String mappingId,String reportNo) throws Exception {
        String sql = "";
        String tableName = "t_creditbaseinfo";
        sql = "select * from " + tableName + " where userId=? and mappingId=? and reportNo=?";
        CreditBaseInfoEntity pojo = null;
        try{
         pojo = (CreditBaseInfoEntity) queryOne(sql, new Object[] { userId,mappingId,reportNo }, new CreditBaseInfoEntity());	
        }catch(EmptyResultDataAccessException e){
        	return null;
        }
        
        return pojo;
    }
    
    
}
