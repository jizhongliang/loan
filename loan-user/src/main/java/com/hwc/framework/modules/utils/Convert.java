package com.hwc.framework.modules.utils;

import cn.freesoft.utils.FsUtils;


/**
 * 类型转换器
 * 
 *
 */
public class Convert {
	

	private Convert() {
		// 静态类不可实例化
	}

	/**
	 * 转换为Integer数组<br>
	 *
	 * @param
	 * @return 结果
	 */
	public static Integer[] toIntArray(String str) {
		return toIntArray(",", str);
	}

	/**
	 * 转换为Integer数组<br>
	 *
	 * @param split 分隔符
	 * @param split 被转换的值
	 * @return 结果
	 */
	public static Integer[] toIntArray(String split, String str) {
		if (FsUtils.strsEmpty(str)) {
			return new Integer[] {};
		}
		String[] arr = str.split(split);
		final Integer[] ints = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			final Integer v = FsUtils.i(arr[i]);
			ints[i] = v;
		}
		return ints;
	}
}
