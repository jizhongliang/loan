package com.hwc.framework.common;

import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;

import java.lang.reflect.*;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by   on 2017/11/1.
 */
public class ReflectUtil {
    private static final Logger logger = Logger.getLogger(ReflectUtil.class);

    public static List<?> PRIMITIVE_TYPES = Arrays.asList(new Class[]{
            char.class, short.class, byte.class, int.class, long.class,
            float.class, double.class, boolean.class, Short.class, Byte.class,
            Integer.class, Long.class, Float.class, Double.class,
            Boolean.class, String.class, Date.class});

    public static boolean isPrimitive(Class<?> type) {
        return PRIMITIVE_TYPES.contains(type);
    }

    public static Object invokeGetMethod(Class<?> claszz, Object o, String name) {
        Object ret = null;
        try {
            Method method = claszz.getMethod("get"
                    + FsUtils.firstCharUpperCase(name));
            ret = method.invoke(o);
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            logger.error("claszz:" + claszz + ",name:" + name, e);
        }
        return ret;
    }

    public static Object invokeSetMethod(Class<?> claszz, Object o,
                                         String name, Class<?>[] argTypes, Object[] args) {
        Object ret = null;
        // 非 常量 进行反射
        try {
            if (!checkModifiers(claszz, name)) {
                Method method = claszz.getMethod(
                        "set" + FsUtils.firstCharUpperCase(name), argTypes);
                ret = method.invoke(o, args);
            }
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            logger.error("claszz:" + claszz + ",name:" + name + ",argType:"
                    + argTypes + ",args:" + args, e);
        }
        return ret;
    }

    public static Object invokeSetMethod(Class<?> claszz, Object o,
                                         String name, Class<?> argType, Object args) {
        Object ret = null;
        // 非 常量 进行反射
        try {
            if (!checkModifiers(claszz, name)) {
                Method method = claszz.getMethod(
                        "set" + FsUtils.firstCharUpperCase(name),
                        new Class[]{argType});
                ret = method.invoke(o, new Object[]{args});
            }
        } catch (NoSuchMethodException | SecurityException
                | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            logger.error("claszz:" + claszz + ",name:" + name + ",argType:"
                    + argType + ",args:" + args);
        }
        return ret;
    }

    /**
     * 校验参数类型 目前只校验是否为 常量
     *
     * @param claszz
     * @param name
     * @return 常量返回true，非常量返回false
     */
    private static boolean checkModifiers(Class<?> claszz, String name) {
        try {
            Field field = claszz.getField(name);
            if (isConstant(field.getModifiers())) {
                return true;
            }
        } catch (NoSuchFieldException e) {
            logger.error(e);
            return false;
        }
        return false;
    }

    /**
     * 是否为常量
     *
     * @param modifiers
     * @return 常量返回true，非常量返回false
     */
    private static boolean isConstant(int modifiers) {
        // static 和 final修饰
        if (Modifier.isStatic(modifiers) && Modifier.isFinal(modifiers)) {
            return true;
        }
        return false;
    }

    @SuppressWarnings("rawtypes")
    public static Class<?> getSuperClassGenricType(Class clazz, int index) throws Exception {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if ((index >= params.length) || (index < 0)) {
            throw new Exception("你输入的索引不能小于0 超出了参数的总数") ;
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }

    @SuppressWarnings("rawtypes")
    public static Class getSuperClassGenricType(Class clazz) throws Exception {
        return getSuperClassGenricType(clazz, 0);
    }

    public static Method getDeclaredMethod(Object object, String methodName,
                                           Class<?>... parameterTypes) {
        Method method = null;
        Class<?> clazz = object.getClass();
        while (clazz != Object.class) {
            try {
                method = clazz.getDeclaredMethod(methodName, parameterTypes);
            } catch (Exception e) {
                // 这里异常不能抛出去。
                // 如果这里的异常打印或者往外抛，就不会执行clazz = clazz.getSuperclass(),
                // 最后就不会进入到父类中了
            }
            if (method != null)
                break;
            clazz = clazz.getSuperclass();
        }
        return method;
    }

    public static Map<String, Field> getClassField(Class<?> clazz) {
        Field[] declaredFields = clazz.getDeclaredFields();
        Map<String, Field> fieldMap = new HashMap<String, Field>();
        Map<String, Field> superFieldMap = new HashMap<String, Field>();
        for (Field field : declaredFields) {
            fieldMap.put(field.getName(), field);
        }
        if (clazz.getSuperclass() != null) {
            superFieldMap = getClassField(clazz.getSuperclass());
        }
        fieldMap.putAll(superFieldMap);
        return fieldMap;
    }

    /**
     * object 属性名称及属性值组装为 Map，再用Map转Json字符串。 组装规则： 只组装String类型，且不为常量的字段，
     * 组装时若属性值为空或为null，则不加入Json
     *
     * @param object
     * @return
     */
    public static String fieldValueToJson(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fss = new Field[0];
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fs = clazz.getDeclaredFields();
                fss = ArrayUtil.concat(fss, fs);
            } catch (Exception e) {
                // 这里异常不能抛出去。
                // 如果这里的异常打印或者往外抛，就不会执行clazz = clazz.getSuperclass(),
                // 最后就不会进入到父类中了
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        for (Field f : fss) {
            // 反射对象中String类型，且不为常量的字段
            if (String.class.equals(f.getType())
                    && !isConstant(f.getModifiers())) {
                String fieldName = f.getName();
                Object o = ReflectUtil.invokeGetMethod(f.getDeclaringClass(),
                        object, fieldName);
                String value = FsUtils.s(o);
                if (value == "") {
                    continue;
                }
                map.put(fieldName, value);
            }
        }
        String str = JSONObject.toJSONString(map);
        return str;
    }

    /**
     * object 属性名称及属性值组装为 Map。 组装规则： 只组装String类型，且不为常量的字段，
     * 组装时若属性值为空或为null，则不加入Json
     *
     * @param object
     * @return
     */
    public static Map<String, Object> fieldValueToMap(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fss = new Field[0];
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fs = clazz.getDeclaredFields();
                fss = ArrayUtil.concat(fss, fs);
            } catch (Exception e) {
                // 这里异常不能抛出去。
                // 如果这里的异常打印或者往外抛，就不会执行clazz = clazz.getSuperclass(),
                // 最后就不会进入到父类中了
            }
        }

        Map<String, Object> map = new HashMap<String, Object>();
        for (Field f : fss) {
            // 反射对象中String类型，且不为常量的字段
            if (String.class.equals(f.getType())
                    && !isConstant(f.getModifiers())) {
                String fieldName = f.getName();
                Object o = ReflectUtil.invokeGetMethod(f.getDeclaringClass(),
                        object, fieldName);
                String value = FsUtils.s(o);
                if (value == "") {
                    continue;
                }
                map.put(fieldName, value);
            }
        }
        return map;
    }

    /**
     * object 属性名称及属性值组装为String字符串。 组装规则：
     * field.name1=field.value1&field.name2=field.value2 ...
     *
     * @param object
     * @return
     */
    public static String fieldValueToString(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fss = new Field[0];
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                Field[] fs = clazz.getDeclaredFields();
                fss = ArrayUtil.concat(fss, fs);
            } catch (Exception e) {
                // 这里异常不能抛出去。
                // 如果这里的异常打印或者往外抛，就不会执行clazz = clazz.getSuperclass(),
                // 最后就不会进入到父类中了
            }
        }
        StringBuilder sb = new StringBuilder(50);
        for (Field f : fss) {
            // 反射对象中String类型，且不为常量的字段
            if (String.class.equals(f.getType())
                    && !isConstant(f.getModifiers())) {
                String fieldName = f.getName();
                Object o = ReflectUtil.invokeGetMethod(f.getDeclaringClass(),
                        object, fieldName);
                String value = FsUtils.s(o);
                if (value == "") {
                    continue;
                }
                sb.append(fieldName + "=" + value + "&");
            }
        }
        logger.debug("请求TPP参数：" + sb.toString());
        return sb.toString();
    }

    /**
     * 根据paramsName数组定义内容，反射出Object对应Field的值，然后拼接成字符串返回 拼接规则value1+value2+value3
     * 例：value1=a,value2=b,value3=c,那么方法返回结果为：abc
     *
     * @param object
     * @param paramNames
     * @return
     */
    public static String fieldValueToString(Object object, String[] paramNames) {
        if (object == null || paramNames == null || paramNames.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder detail = new StringBuilder();
        for (String name : paramNames) {
            Object o = ReflectUtil.invokeGetMethod(object.getClass(), object,
                    name);
            String value = FsUtils.s(o);
            sb.append(value);
            detail.append(name + "=" + value + "&");
        }
        logger.debug("参数拼接明细：" + detail.toString());
        logger.debug("参数拼接结果：" + sb.toString());
        return sb.toString();
    }

    public static String fieldValueToString(Map<String, String> data,
                                            String[] paramNames) {
        if (data.isEmpty() || paramNames == null || paramNames.length <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        StringBuilder detail = new StringBuilder();
        for (String name : paramNames) {
            String value = data.get(name);
            value = FsUtils.s(value);
            sb.append(value);
            detail.append(name + "=" + value + "&");
        }
        logger.debug("参数拼接明细：" + detail.toString());
        logger.debug("参数拼接结果：" + sb.toString());
        return sb.toString();
    }

    /**
     * object 属性名称及属性值组装为 Map，再用Map转Json字符串。 组装规则： 只组装String类型，且不为常量的字段，
     * 组装时若属性值为空或为null，则不加入Json
     *
     * @param object
     * @return
     */
    public static String fieldValueToJson(Object object, String[] paramNames) {
        Map<String, String> map = fieldValueToMap(object, paramNames);
        String str = JSONObject.toJSONString(map);
        return str;
    }

    /**
     * object 属性名称及属性值组装为 Map，再用Map转Json字符串。 组装规则： 只组装String类型，且不为常量的字段，
     * 组装时若属性值为空或为null，则不加入Map
     *
     * @param object
     * @return
     */
    public static Map<String, String> fieldValueToMap(Object object,
                                                      String[] paramNames) {
        return fieldValueToMap(object, paramNames, true);
    }

    /**
     * object 属性名称及属性值组装为 Map，再用Map转Json字符串。 组装规则： 只组装String类型，且不为常量的字段，
     * 组装时若isTrim为true 且 属性值为空或为null，则不加入Map
     *
     * @param object
     * @param paramNames
     * @param isTrim
     * @return
     */
    public static Map<String, String> fieldValueToMap(Object object,
                                                      String[] paramNames, boolean is_trim) {
        Map<String, String> map = new HashMap<String, String>();
        for (String name : paramNames) {
            Object o = ReflectUtil.invokeGetMethod(object.getClass(), object,
                    name);
            String value = FsUtils.s(o);
            // 是否去空
            if (is_trim && "".equals(value)) {
                continue;
            }
            map.put(name, value);
        }
        logger.debug("数组反射结果：" + map.toString());
        return map;
    }
}