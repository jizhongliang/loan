package com.hwc.framework.modules.service.base;


/**
 * 存储序列化
 * 
 * @author hc
 * 
 */
public interface Saveable {

	public String getTableName();

	public String[] getKeyColumns();

	//获取主键值
	public String getPrimaryVal();

}