package com.hwc.framework.utils;

import java.io.File;

public class FileUtils extends org.apache.commons.io.FileUtils {
	/**
	 * 获取文件扩展名
	 *
	 * @param file
	 * @return
	 */
	public static String getExtension(File file) {
		String extension = "";
		String fileName = file.getName();
		int lastIndex = fileName.lastIndexOf(".");
		if (lastIndex != -1 && lastIndex != 0) {
			extension = fileName.substring(lastIndex + 1).toLowerCase();
		}
		return extension;
	}

	/**
	 * 转换成kb单位
	 *
	 * @param slaveFile
	 * @return
	 */
	public static float convertKbSize(File slaveFile) {
		return (float) slaveFile.length() / 1024;
	}

	/**
	 * 根据全路径创建文件
	 * @param fullPath
	 * @return
	 */
	public static File createFile(String fullPath) {
		String path = null;
		int index = fullPath.lastIndexOf("/");
		if (index == -1) {
			path = fullPath;
		} else {
			path = fullPath.substring(0, index);
		}
		if (path != null) {
			new File(path).mkdirs();
		}
		return new File(fullPath);
	}
}
