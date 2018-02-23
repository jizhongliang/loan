/**  
 * Project Name:api-webhook  
 * File Name:BaseDAO.java  
 * Package Name:com.example.service.webhook.repository  
 * Date:2016年7月19日下午8:32:56  
 * Copyright (c) 2016, yuandong@51dojo.com All Rights Reserved.  
 *  
*/  
  
package com.hwc.framework.modules.service.base;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/**  
 * ClassName:BaseDAO <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2016年7月19日 下午8:32:56 <br/>  
 * @author   yuandong  
 * @version    
 * @param <T>
 * @since    JDK 1.6  
 * @see        
 */
public abstract class BaseDAO<T> {
	
	private Class<T> entityClass;

	public JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@SuppressWarnings("unchecked")
	public BaseDAO() {
		ParameterizedType type = (ParameterizedType) getClass()
				.getGenericSuperclass();
		entityClass = (Class<T>) type.getActualTypeArguments()[0];
	}

	public static final String SQL_INSERT = "insert";
	public static final String SQL_UPDATE = "update";
	public static final String SQL_DELETE = "delete";

	/**
	 * 修改数据
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public int modify(T entity) throws Exception {
		Saveable objForSave = (Saveable) entity;
		Field[] fields = entityClass.getDeclaredFields();
		String sql = this.makeSql(objForSave, SQL_UPDATE, null, fields);
		Object[] args = this.setArgs(objForSave, SQL_UPDATE, fields);
		int[] argTypes = this.setArgTypes(objForSave, SQL_UPDATE, fields);
		return getJdbcTemplate().update(sql, args, argTypes);
	}
	
	public void add(T entity)throws Exception{
    	Saveable objForSave = (Saveable) entity;
        Field[] fields = entityClass.getDeclaredFields();
        final String sql = this.makeSql(objForSave, SQL_INSERT, null, fields);
        final Object[] args = this.setArgs(objForSave, SQL_INSERT, fields);
        int[] argTypes = this.setArgTypes(objForSave, SQL_INSERT, fields);
        getJdbcTemplate().update(sql, args, argTypes);
    }

	/**
	 * 按表修改数据
	 * 
	 * @param entity
	 * @param table
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public int modify(T entity, String table) throws Exception {
		Saveable saveable = (Saveable) entity;
		Field[] fields = entityClass.getDeclaredFields();
		String sql = this.makeSql(saveable, SQL_UPDATE, table, fields);
		Object[] args = this.setArgs(saveable, SQL_UPDATE, fields);
		int[] argTypes = this.setArgTypes(saveable, SQL_UPDATE, fields);
		return getJdbcTemplate().update(sql, args, argTypes);
	}

	/**
	 * 按自定义SQL修改数据
	 * 
	 * @param sql
	 *            预定义sql
	 * @param args
	 *            参数列表
	 * @return
	 */
	public int modify(String sql, Object[] args) {
		return getJdbcTemplate().update(sql, args);
	}

	/**
	 * 删除数据
	 * 
	 * @param entity
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public int delete(T entity) throws Exception {
		Saveable objForSave = (Saveable) entity;
		Field[] fields = entityClass.getDeclaredFields();
		String sql = this.makeSql(objForSave, SQL_DELETE,
				objForSave.getTableName(), fields);
		Object[] args = this.setArgs(objForSave, SQL_DELETE, fields);
		int[] argTypes = this.setArgTypes(objForSave, SQL_DELETE, fields);
		return getJdbcTemplate().update(sql, args, argTypes);
	}

	/**
	 * 删除数据
	 * 
	 * @param sql
	 * @param args
	 * @param readOnly
	 * @return
	 */
	public int delete(String sql, Object[] args) {
		return getJdbcTemplate().update(sql, args);
	}

	/**
	 * 按表删除数据
	 * 
	 * @param entity
	 * @param table
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	public int deleteByTable(T entity, String table) throws Exception {
		Saveable objForSave = (Saveable) entity;
		Field[] fields = entityClass.getDeclaredFields();
		String sql = this.makeSql(objForSave, SQL_DELETE, table, fields);
		Object[] args = this.setArgs(objForSave, SQL_DELETE, fields);
		int[] argTypes = this.setArgTypes(objForSave, SQL_DELETE, fields);
		return getJdbcTemplate().update(sql, args, argTypes);
	}

	/**
	 * 查询数据集合
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 * @throws InstantiationException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws DataAccessException
	 */
	public List<T> query(String sql, Object[] args, Object obj)
			throws DataAccessException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException,
			InstantiationException {
		return ConvertUtils.convert2List(
				getJdbcTemplate().queryForList(sql, args), obj);

	}

	/**
	 * 查询单表数据
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 * @throws DataAccessException
	 */
	@SuppressWarnings("unchecked")
	public T queryOne(String sql, Object[] args, Object obj)
			throws DataAccessException, IllegalArgumentException,
			IllegalAccessException, InvocationTargetException {
		return (T) ConvertUtils.convert2Bean(
				getJdbcTemplate().queryForMap(sql, args), obj);

	}

	

	/**
	 * 保存并返回主键
	 * 
	 * @param objForSave
	 * @param readOnly
	 * @return
	 */
	public Number saveAndReturnKey(Saveable objForSave) {
		return saveAndReturnKey(objForSave, objForSave.getTableName(),
				objForSave.getKeyColumns());
	}

	/**
	 * 保存并返回主键
	 * 
	 * @param objForSave
	 * @param tableName
	 * @param keyColumns
	 * @return
	 */
	private Number saveAndReturnKey(Object objForSave, String tableName,
			String... keyColumns) {
		SimpleJdbcInsert insertActor = getSimpleJdbcInsert(getJdbcTemplate());
		insertActor.setTableName(tableName);
		insertActor.usingGeneratedKeyColumns(keyColumns);
		Number newId = insertActor
				.executeAndReturnKey(new BeanPropertySqlParameterSource(
						objForSave));
		traceSql(insertActor.getInsertString());
		return newId;
	}
	
	/**
	 * 保存并返回主键
	 * 
	 * @param objForSave
	 * @param tableName
	 * @param keyColumns
	 * @return
	 */
	public Number saveAndReturnKey(Saveable objForSave, String tableName) {
		return saveAndReturnKey(objForSave, tableName,objForSave.getKeyColumns());
	}

	/**
	 * 保存数据
	 * 
	 * @param objForSave
	 */
	public void save(Saveable objForSave) {
		SimpleJdbcInsert insertActor = getSimpleJdbcInsert(getJdbcTemplate());
		insertActor.setTableName(objForSave.getTableName());
		insertActor.execute(new BeanPropertySqlParameterSource(objForSave));
		traceSql(insertActor.getInsertString());
	}

	/**
	 * 指定表保存数据
	 * 
	 * @param objForSave
	 * @param tableName
	 */
	public void save(Object objForSave, String tableName) {
		SimpleJdbcInsert insertActor = getSimpleJdbcInsert(getJdbcTemplate());
		insertActor.setTableName(tableName);
		insertActor.execute(new BeanPropertySqlParameterSource(objForSave));
		traceSql(insertActor.getInsertString());
	}

	/**
	 * 转换为添加工具 jdbcTemplate to SimpleJdbcInsert
	 * 
	 * @param jdbcTemplate
	 * @return
	 */
	public SimpleJdbcInsert getSimpleJdbcInsert(JdbcTemplate jdbcTemplate) {
		return new SimpleJdbcInsert(jdbcTemplate);
	}

	/**
	 * 拼装Sql
	 * 
	 * @param sqlFlag
	 * @return
	 */
	private String makeSql(Saveable entity, String sqlFlag, String table,
			Field[] fields) {
		StringBuffer sql = new StringBuffer();
		if (sqlFlag.equals(SQL_INSERT)) {
			if (table != null) {
				sql.append(" INSERT INTO " + table);
			} else {
				sql.append(" INSERT INTO " + entity.getTableName());

			}
			sql.append("(");
			StringBuilder sbValue = new StringBuilder();
			for (Field field : fields) {
				field.setAccessible(true);
				if (!ReflectUtil.isStaticField(field)) {
					sql.append(field.getName()).append(",");
					sbValue.append("?,");
				}
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(") VALUES (");
			sbValue = sbValue.deleteCharAt(sbValue.length() - 1);
			sql.append(sbValue);
			sql.append(")");
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			if (table != null) {
				sql.append(" UPDATE " + table + " SET ");
			} else {
				sql.append(" UPDATE " + entity.getTableName() + " SET ");
			}
			for (Field field : fields) {
				field.setAccessible(true);
				if (!ReflectUtil.isStaticField(field)) {
					String columnName = field.getName().toLowerCase();
					if (!columnName.equals(entity.getKeyColumns()[0]
							.toLowerCase())) {
						sql.append(columnName).append("=").append("?,");
					}
				}
			}
			sql = sql.deleteCharAt(sql.length() - 1);
			sql.append(" WHERE " + entity.getKeyColumns()[0] + "=?");
		} else if (sqlFlag.equals(SQL_DELETE)) {
			if (table != null) {
				sql.append(" DELETE FROM " + table + " WHERE "
						+ entity.getKeyColumns()[0] + "=?");
			} else {
				sql.append(" DELETE FROM " + entity.getTableName() + " WHERE "
						+ entity.getKeyColumns()[0] + "=?");
			}
		}
		traceSql(sql.toString());
		return sql.toString();
	}

	/**
	 * 设置参数
	 * 
	 * @param entity
	 * @param sqlFlag
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private Object[] setArgs(Saveable entity, String sqlFlag, Field[] fields)
			throws Exception {
		List<Object> args = new ArrayList<Object>();
		if (sqlFlag.equals(SQL_INSERT)) {
			for (Field field : fields) {
				if (!ReflectUtil.isStaticField(field)) {
					field.setAccessible(true);
					args.add(field.get(entity));
				}
			}
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			Object val = null;
			for (Field field : fields) {
				if (!ReflectUtil.isStaticField(field)) {
					field.setAccessible(true);
					if (!field.getName().toLowerCase()
							.equals(entity.getKeyColumns()[0].toLowerCase())) {
						args.add(field.get(entity));
					} else {
						val = field.get(entity);
					}
				}
			}
			args.add(val);
		} else if (sqlFlag.equals(SQL_DELETE)) {
			Field field = entityClass
					.getDeclaredField(entity.getKeyColumns()[0]);
			field.setAccessible(true);
			args.add(field.get(entity));
		}
		if (args.size() <= 0)
			return null;
		return args.toArray();
	}

	private int getArgTyte(Field field) {
		String clazz = field.getType().getName();
		if (clazz.equals("java.lang.String")) {
			return Types.VARCHAR;
		} else if (clazz.equals("java.lang.Double") || clazz.equals("double")) {
			return Types.DECIMAL;
		} else if (clazz.equals("java.lang.Integer") || clazz.equals("int")) {
			return Types.INTEGER;
		} else if (clazz.equals("java.util.Date")) {
			return Types.TIMESTAMP;
			// return Types.DATE;
		} else if (clazz.equals("java.lang.Long") || clazz.equals("long")) {
			return Types.BIGINT;
		} else if (clazz.equals("java.lang.Boolean") || clazz.equals("boolean")) {
			return Types.BIT;
		} else if (clazz.equals("java.lang.Float") || clazz.equals("float")) {
			return Types.FLOAT;
		} else if (clazz.equals("java.lang.Short") || clazz.equals("short")) {
			return Types.SMALLINT;
		} else if (clazz.equals("java.lang.Char") || clazz.equals("char")) {
			return Types.CHAR;
		} else if (clazz.equals("java.sql.Timestamp")) {
			return Types.TIMESTAMP;
		} else if (clazz.equals("java.math.BigDecimal")
				|| clazz.equals("bigdecimal")) {
			return Types.DECIMAL;
		} else {
			throw new RuntimeException("无效的数据类型:" + field.getName() + ":clazz:"
					+ clazz);
		}
	}

	/**
	 * 设置参数类型
	 * 
	 * @param entity
	 * @param sqlFlag
	 * @return
	 * @throws IllegalAccessException
	 * @throws IllegalArgumentException
	 */
	private int[] setArgTypes(Saveable entity, String sqlFlag, Field[] fields)
			throws Exception {
		List<Integer> argTypes = new ArrayList<Integer>();
		if (sqlFlag.equals(SQL_INSERT)) {
			for (Field field : fields) {
				if (!ReflectUtil.isStaticField(field)) {
					field.setAccessible(true);
					argTypes.add(getArgTyte(field));
				}
			}
		} else if (sqlFlag.equals(SQL_UPDATE)) {
			Integer valType = null;
			for (Field field : fields) {
				if (!ReflectUtil.isStaticField(field)) {
					if (!field.getName().toLowerCase()
							.equals(entity.getKeyColumns()[0].toLowerCase())) {
						field.setAccessible(true);
						argTypes.add(getArgTyte(field));
					} else {
						valType = getArgTyte(field);
					}
				}
			}
			argTypes.add(valType);

		} else if (sqlFlag.equals(SQL_DELETE)) {
			Field field = entityClass
					.getDeclaredField(entity.getKeyColumns()[0]);
			field.setAccessible(true);
			argTypes.add(getArgTyte(field));
		}
		if (argTypes.size() <= 0)
			return null;
		int[] argTypeResult = new int[argTypes.size()];
		int idx = 0;
		for (Integer type : argTypes) {
			argTypeResult[idx] = type;
			idx++;
		}
		argTypes = null;
		return argTypeResult;
	}

	/**
	 * 打印语句
	 * 
	 * @param sql
	 */
	public void traceSql(String sql) {
	//	System.out.println("JDBC:" + sql);
	}

	/**
	 * 拼装分页语句
	 * 
	 * @param pageSize
	 * @param currentPage
	 * @return
	 */
	public String pageCondition(Integer pageSize, int currentPage) {
		if (currentPage < 1) {
			currentPage = 1;
		}
		return " LIMIT " + pageSize * (currentPage - 1) + "," + pageSize + "";
	}

	/**
	 * 清空表
	 * 
	 * @param tableName
	 */
	public void truncateTable(String tableName) {
		getJdbcTemplate().update("TRUNCATE TABLE " + tableName);
	}
}
  
