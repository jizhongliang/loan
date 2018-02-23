package com.hwc.framework.modules.service.base;


import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 数据转换工具
 * ClassName: ConvertUtils    
 * date: 2016年7月20日 上午11:41:26   
 * @author yuandong  
 * @version   
 * @since JDK 1.6
 */
public class ConvertUtils {

	public static final String setMethodModify = "set";

	/**
	 * Map to bean
	 * 
	 * @param map
	 * @param obj
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> T convert2Bean(Map<String, Object> map, Object obj)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {
		if (map == null || obj == null)
			return null;
		Class class1 = obj.getClass();
		Method[] methods = class1.getMethods();
		for (Method method : methods) {
			String methodName = method.getName();
			if (methodName.startsWith(ConvertUtils.setMethodModify)) {
				String propertyName = methodName.substring(3).toUpperCase(
						Locale.getDefault());
				Object value = map.get(propertyName);
				if (value != null) {
					method.invoke(obj, value);
				}
			}
		}
		return (T) obj;
	}

	/**
	 * List<Map<String,Object>> to List<Object>
	 * 
	 * @param list
	 * @param obj
	 * @return
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> convert2List(List<Map<String, Object>> list,
			Object obj) throws IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		if (list == null || obj == null)
			return null;
		List<T> beans = new ArrayList<T>();
		for (int i = 0; i < list.size(); i++) {

			beans.add((T) convert2Bean(list.get(i), obj.getClass()
					.newInstance()));
		}
		return beans;
	}

	/**
	 * 将实体转成 Map<String, Object>
	 * 
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> convert2Map(Object obj) {
		Map<String, Object> reMap = new HashMap<String, Object>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for (int i = 0; i < fields.length; i++) {
				Field f = obj.getClass().getDeclaredField(fields[i].getName());
				f.setAccessible(true);
				Object o = f.get(obj);
				reMap.put(fields[i].getName(), o);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reMap;
	}

	private static Object convertType(Object value) {
		if (value == null)
			return value;
		String clazz = value.getClass().getName();
		if (clazz.equals("java.math.BigDecimal")) {
			return Double.parseDouble(value.toString());
		}
		return value;
	}

}
