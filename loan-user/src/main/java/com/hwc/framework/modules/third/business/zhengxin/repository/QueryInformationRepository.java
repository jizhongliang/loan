package com.hwc.framework.modules.third.business.zhengxin.repository;

import com.hwc.framework.modules.service.base.BaseDAO;
import com.hwc.framework.modules.third.business.zhengxin.entity.QueryInformationEntity;
import com.hwc.framework.modules.third.util.DateUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class QueryInformationRepository extends BaseDAO<QueryInformationEntity> {
	
//	@Resource(name="templateZhengxin")
//    private JdbcTemplate jdbcTemplate;
//
//    public JdbcTemplate getJdbcTemplate() {
//		return jdbcTemplate;
//	}

    public void savePublicInformationEntity(QueryInformationEntity entity)throws Exception  {
    	entity.setCreateTime(DateUtil.getCurrentDate());
    	entity.setLastModifyTime(DateUtil.getCurrentDate());
		this.add(entity);
    }
    
    

   public void deletePublicInformationEntity(String userId,String mappingId,String reportNo){
	   String tableName = "t_queryinformationdetail";
	        StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName).append(" WHERE ").
	                append("userId='").append(userId).append("'").
	                append("and mappingId='").append(mappingId).append("'")
	                .append("and reportNo='").append(reportNo).append("'");

	         this.getJdbcTemplate().update(sql.toString());
   }
    
    
    
}
