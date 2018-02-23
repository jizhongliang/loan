package com.hwc.framework.modules.utils;

import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;

public class ResourceUtil {

	private static final String UTF8 = "utf8";

	public static InputStream getInputStream(String path) throws Exception {
		return new ClassPathResource(path).getInputStream();
	}

	public static String getString(String path) throws Exception {
		try {
			return IOUtils.toString(getInputStream(path), UTF8);
		} catch (Exception e) {
			return FileUtils.readFileToString(ResourceUtils.getFile(path), UTF8);
		}
	}

	public static boolean isWindows() {
		String os = System.getProperty("os.name");
		return os.toLowerCase().startsWith("win");
	}

}
