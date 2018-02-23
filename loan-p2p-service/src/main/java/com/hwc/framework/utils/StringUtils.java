package com.hwc.framework.utils;
 
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 
public class StringUtils extends org.apache.commons.lang.StringUtils {
	public static char TEN = '十';
 
 
	public static String firstLetterToUpperCase(String str) {
		if (str == null) {
			return str;
		}
		char upperCase = Character.toUpperCase(str.charAt(0));
		return (String) (str.length() > 1 ? upperCase + str.substring(1) : upperCase);
	}

	public static String sub(String str, int length) {
		if (str == null) {
			return str;
		}
		str = str.trim();
		if (str.length() <= length) {
			return str;
		}
		return str.substring(0, length);
	} 

	/**
	 * 根据原图url获取scale(比如197x136)缩略图url
	 * 
	 * @param url
	 * @param scale
	 * @return
	 */
	public static String getThumbnail(String url, String scale) {
		int index = url.lastIndexOf(".");
		if (index == -1) {
			return url;
		}
		scale = "_" + scale;
		return url.substring(0, index) + scale + url.substring(index);
	}

	/**
	 * 根据url获取该url所属的group
	 * 
	 * @param url
	 * @return
	 */
	public static String getGroup(String url) {
		if (org.apache.commons.lang.StringUtils.isBlank(url)) {
			return null;
		}
		int start = url.indexOf("group");
		int end = url.indexOf("/", start);
		return url.substring(start, end);
	}

 
	/**
	 * 替换最后一个点号后面的字符串为*
	 * 
	 * @param remoteAddr
	 * @return
	 */
	public static String mosaic(String str) {
		if (str == null) {
			return str;
		}
		int lastIndex = str.lastIndexOf(".");
		if (lastIndex == -1) {
			return str;
		}

		return str.substring(0, lastIndex) + ".*";
	}
 
	
	
	/**
     * 查看该字符串数组是否有重复数据 用户处理字符串数组里面存在重复数据和一些非数字数据
     * <功能详细描述>
     * @param str：用，隔开的字符串
     * @author  xuyh
     * @version  1.0, 2013-7-9
     * @return String 返回去除重复和去除非数字字符串
     * @exception throws [违例类型] [违例说明]
     */
    public static String removeRepeatData(String str){
        
        //字符串不为空
        if(!isEmpty(str)){
            String [] s =str.split(",");
            //数组必须大于1才处理去重
            if(s.length > 1){
                StringBuffer sb = new StringBuffer();//返回字符串
                for(int i=0; i< s.length; i++){  
                    
                    //字符串转换成数字，如果不是数字，则移除
                    if(stringToInteger(s[i]) == null) continue;
                    
                    //如果查找到有重复的，直接去掉
                    if(sb.indexOf(s[i]+",") == -1){
                        
                        sb.append(s[i]).append(",");
                    } 
                }
                return sb.toString().substring(0, sb.length()-1);
            }else{
                return str;
            }
        }
        return "";
    }
    
    /**
     * String转换成为Integer类型
     * <功能详细描述>
     * @param 
     * @author  xuyh
     * @version  1.0, 2013-3-4
     * @return Integer [返回类型说明]
     * @exception throws [违例类型] [违例说明]
     */
    public static Integer stringToInteger(String str) {
        if (str == null) {
            return null;
        }
        else {
            try {
                Integer i = Integer.valueOf(str);
                return i;
            }
            catch (Exception ex) {
                return null;
            }
        }
    }
 
	/**
	 * md5加密
	 * <功能详细描述>
	 * @param 
	 * @author  zenglm
	 * @version  4.0, 2017年6月14日
	 * @return String [返回类型说明]
	 * @exception throws [违例类型] [违例说明]
	 */
	   public static String MD5(String sourcein) {
	        String str = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            md.update(sourcein.getBytes());
	            byte b[] = md.digest();
	            int i;
	            StringBuffer buf = new StringBuffer("");
	            for (int offset = 0; offset < b.length; offset++) {
	                i = b[offset];
	                if (i < 0)
	                    i += 256;
	                if (i < 16)
	                    buf.append("0");
	                buf.append(Integer.toHexString(i));
	            }
	            str = buf.toString().toLowerCase();
	        }
	        catch (NoSuchAlgorithmException e) { // TODO Auto-generated catch block  
	            e.printStackTrace();
	        }
	        return str;
	    }

	/**
	 * 去除手机号内部的乱码
	 * @param resource
	 * @return
	 */
	public static String remove(String resource){
		StringBuffer buffer=new StringBuffer();
		int position=0;
		char currentChar;
		while(position<resource.length())
		{
			currentChar=resource.charAt(position++);
			if(Character.isDigit(currentChar))
				buffer.append(currentChar);
		}
		return buffer.toString();

	}
}