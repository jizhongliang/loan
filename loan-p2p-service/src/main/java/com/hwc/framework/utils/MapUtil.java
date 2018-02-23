package com.hwc.framework.utils;

import org.apache.commons.lang.StringUtils;
import org.jsoup.helper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by Administrator on 2015/4/22.
 */
@SuppressWarnings({ "unchecked", "rawtypes" })
public class MapUtil {
	
	public static final Logger logger = LoggerFactory.getLogger(MapUtil.class);

	public static List collectProperty(List<Map<String, Object>> list, String property) {
        return collectProperty(list, property, true);
    }

    public static List collectProperty(List<Map<String, Object>> list, String property, boolean acceptNull) {
        List rlist = new ArrayList();
        for (Map rec : list) {
            Object o = rec.get(property);
            if (o == null && !acceptNull) continue;
            rlist.add(o);
        }
        return rlist;
    }

    /**
     * @param type 要转化的类型
     * @param map  包含属性值的 map
     * @return 转化出来的 JavaBean 对象
     * @description 将一个 Map 对象转化为一个 JavaBean
     * @author 孙凯伦
     * @since 1.0.0
     */
	public static Object convertMap(Class type, Map map) {
        BeanInfo beanInfo;
        try {
            beanInfo = Introspector.getBeanInfo(type);
            Object obj = type.newInstance(); // 创建 JavaBean 对象

            // 给 JavaBean 对象的属性赋值
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();

                if (map.containsKey(propertyName)) {
                    // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                    Object value = map.get(propertyName);

                    Object[] args = new Object[1];
                    args[0] = value;

                    descriptor.getWriteMethod().invoke(obj, args);
                }
            }
            return obj;
        } catch (IntrospectionException e) {
            logger.error(e.getMessage(), e);
        } // 获取类属性
        catch (InstantiationException e) {
        	logger.error(e.getMessage(), e);
        } catch (IllegalAccessException e) {
        	logger.error(e.getMessage(), e);
        } catch (IllegalArgumentException e) {
        	logger.error(e.getMessage(), e);
        } catch (InvocationTargetException e) {
        	logger.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * @param bean bean 要转化的JavaBean 对象
     * @return 转化出来的 Map 对象
     * @throws IntrospectionException    IntrospectionException 如果分析类属性失败
     * @throws IllegalAccessException    IllegalAccessException 如果实例化 JavaBean 失败
     * @throws InvocationTargetException InvocationTargetException 如果调用属性的 setter 方法失败
     * @description 将一个 JavaBean 对象转化为一个  Map
     * @author 孙凯伦
     * @since 1.0.0
     */
    public static Map convertBean(Object bean)
        throws IntrospectionException, IllegalAccessException, InvocationTargetException {
        Class type = bean.getClass();
        Map returnMap = new HashMap();
        BeanInfo beanInfo = Introspector.getBeanInfo(type);

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();
            if (!"class".equals(propertyName)) {
                Method readMethod = descriptor.getReadMethod();
                Object result = readMethod.invoke(bean, new Object[0]);
                if (result != null) {
                    returnMap.put(propertyName, result);
                } else {
                    returnMap.put(propertyName, "");
                }
            }
        }
        return returnMap;
    }

    public static Map array2Map(Object[][] values) {
        LinkedHashMap rec = new LinkedHashMap();
        Object[][] arr = values;
        int len = values.length;

        for (int i = 0; i < len; ++i) {
            Object[] o = arr[i];
            Object value = o[1];
            if (value instanceof Object[][]) {
                value = array2Map((Object[][]) ((Object[][]) value));
            } else if (value instanceof List) {
                ArrayList children = new ArrayList();
                Iterator i$1 = ((List) value).iterator();

                while (i$1.hasNext()) {
                    Object item = i$1.next();
                    if (item instanceof Object[][]) {
                        children.add(array2Map((Object[][]) ((Object[][]) item)));
                    } else if (item instanceof Map) {
                        children.add((Map) item);
                    } else {
                        children.add(item);
                    }
                }

                value = children;
            }

            rec.put(o[0], value);
        }

        return rec;
    }

    public static Map<String, Object> replaceNullValue2EmptyStr(Map<String, Object> rec) {
        Iterator i$ = rec.keySet().iterator();

        while (i$.hasNext()) {
            String key = (String) i$.next();
            if (rec.get(key) == null) {
                rec.put(key, "");
            }
        }

        return rec;
    }
    
    /**
     * 去除map中的空值
     * @param rec
     * @return
     */
    public static Map<String, Object> removeEmptyStr(Map<String, Object> rec) {
        Iterator i$ = rec.keySet().iterator();

        while (i$.hasNext()) {
            String key = (String) i$.next();
            Object value = rec.get(key);
            if (value == null) {
            	i$.remove();
            }
        }

        return rec;
    }

    public static <T> List<T> toList(Collection<T> collection) {
        ArrayList rlist = new ArrayList();
        Iterator it = collection.iterator();

        while (it.hasNext()) {
            Object t = it.next();
            rlist.add(t);
        }

        return rlist;
    }

	public static Map<String, Object> simpleSort(Map params) {
        List<String> list = toList(params.keySet());
        Collections.sort(list);
        Map<String, Object> result = new LinkedHashMap();
        for (String name : list) {
            result.put(name, params.get(name));
        }
        return result;
    }
}
