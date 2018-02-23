package com.hwc.framework.modules.service.base;


import java.lang.reflect.Field;

public class ReflectUtil {

	/**
	 * 判断是否为静态字段
	 * @param field
	 * @return
	 */
	public static boolean isStaticField(Field field) {
		if (field.toGenericString().contains("static")) {
			return true;
		}
		return false;
	}

}
