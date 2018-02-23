/**  
 * Project Name:ofs-service-server  
 * File Name:SequenceUtil.java  
 * Package Name:com.moxie.cloud.services.ofs.util  
 * Date:2016年5月30日下午4:57:00  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.third.util;

import com.alibaba.druid.util.StringUtils;
import com.google.common.hash.HashCode;
import org.apache.commons.codec.digest.DigestUtils;

/**  
 * ClassName:SequenceUtil <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年5月30日 下午4:57:00 <br/>  
 * @author   yuandong  
 * @version    
 * @since    JDK 1.6  
 * @see        
 */
public class SequenceUtil {
	
	private static String getSequenceId(String userid) {
        byte[] bytes = DigestUtils.sha256(userid.toLowerCase());
        Long value = HashCode.fromBytes(bytes).asLong();
        return value.toString();
    }
	
   public static String getTableSuffix(String userid){
	   if(StringUtils.isEmpty(userid)){
		   return "";
	   }
	   
	   
	   //按照userid的hashcode来分表，适用于userid为非数字的情况
//	   String hashcode = getSequenceId(userid);
//	   return hashcode.substring(hashcode.length()-2);
	   
	   //按照userid来分表，适用于userid是数字的情况
	   if(userid.length()==1){
		   return "0"+userid;
	   }
	   return userid.substring(userid.length()-2);
   }
   


}
  
