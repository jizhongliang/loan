package com.hwc.framework.common;
/**
 * 用户类型
 * @author Administrator
 *
 */
public enum UserType {
	CREDIT("信用类", "10"),MORTGAGE("信用类", "10");
	private String description;
	private String type;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	UserType(String description, String type) {
		this.description = description;
		this.type = type;
	}
}
