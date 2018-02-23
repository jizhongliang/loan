package com.hwc.framework.modules.third.business.zhengxin.repository;

import com.hwc.framework.modules.service.base.BaseDAO;
import com.hwc.framework.modules.third.business.zhengxin.entity.CreditRecordDetailEntity;
import com.hwc.framework.modules.third.util.DateUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * 这里用的是jdbctemplate，DAO层可以根据自己的业务来选择
 * ClassName: TaobaoDeliverAddressRepository
 * date: 2016年7月19日 下午6:29:23   
 * @author yuandong  
 * @version   
 * @since JDK 1.6
 */
@Repository
public class CreditRecordDetailRepository extends BaseDAO<CreditRecordDetailEntity> {
	
//	@Resource(name="templateZhengxin")
//    private JdbcTemplate jdbcTemplate;
//
//    public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}
    
    public void saveCreditRecordDetailEntity(CreditRecordDetailEntity entity)throws Exception  {
    	entity.setCreateTime(DateUtil.getCurrentDate());
    	entity.setLastModifyTime(DateUtil.getCurrentDate());
		this.add(entity);
    }
    
    

   public void deleteCreditRecordDetailEntity(String userId,String mappingId,String reportNo){
	   String tableName = "t_creditrecorddetail";
	        StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ").
	                append("userId='").append(userId).append("'").
	                append("and mappingId='").append(mappingId).append("'")
	                .append("and reportNo='").append(reportNo).append("'");

	         this.getJdbcTemplate().update(sql.toString());
   }
    
    
    
}
