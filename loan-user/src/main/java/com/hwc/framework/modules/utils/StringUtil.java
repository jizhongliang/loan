package com.hwc.framework.modules.utils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 工具类-字符串处理
 *
 * @author xx
 * @version 2.0
 * @since 2014年1月28日
 */
public final class StringUtil {

    // 每位加权因子  
    private static int power[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 构造函数
     */
    private StringUtil() {

    }



    /**
     * 根据身份证Id获取性别
     *
     * @param cardId
     * @return 性别: '男' / '女'
     */
    public static String getSex(String cardId) {
        int sexNum;
        // 15位的最后一位代表性别，18位的第17位代表性别，奇数为男，偶数为女
        if (cardId.length() == 15) {
            sexNum = cardId.charAt(cardId.length() - 1);
        } else {
            sexNum = cardId.charAt(cardId.length() - 2);
        }

        if (sexNum % 2 == 1) {
            return "男";
        } else {
            return "女";
        }
    }

    public static String toString(String separate, int... objs) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < objs.length; i++) {
            if (i > 0)
                sb.append(separate);
            sb.append(objs[i]);
        }
        return sb.toString();
    }


    public static String toStringArray(Object... list) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (Object o : list) {
            if (index > 0) sb.append(",");
            sb.append(o.toString());
            index++;
        }
        return sb.toString();
    }

    @SuppressWarnings("rawtypes")
    public static String toString(Collection list) {
        return toString(list, ",");
    }

    @SuppressWarnings("rawtypes")
    public static String toString(Collection list, String delim) {
        StringBuilder sb = new StringBuilder();
        int index = 0;

        for (Object o : list) {
            if (index > 0) sb.append(delim);
            sb.append(o.toString());
            index++;
        }
        return sb.toString();
    }

    public static String getRelativePath(File file, File parentFile) {
        return file.getAbsolutePath().replaceFirst("^\\Q" + parentFile.getAbsolutePath() + "\\E", "").replace("\\", "/");
    }

    @SuppressWarnings("deprecation")
    public static String getFileUri(HttpServletRequest request, File file) {
        String pre = request.getRealPath("/");
        String fullpath = file.getAbsolutePath();
        return fullpath.replace(pre.replaceFirst("[\\\\/]$", ""), "").replace("\\", "/");
    }

    ;

    public static String getRepairedFileUri(String fullpath) {
        return fullpath.replaceFirst("[\\\\/]$", "").replace("\\", "/").replace("//", "/");
    }

    ;

    /**
     * 格式化金额数字为千分位显示；
     *
     * @param str
     * @return
     */
    public static String fmtMicrometer(String str) {
        DecimalFormat df;
        if (str.indexOf(".") > -1) {
            if (str.length() - str.indexOf(".") - 1 == 0) {
                df = new DecimalFormat("###,##0.");
            } else if (str.length() - str.indexOf(".") - 1 == 1) {
                df = new DecimalFormat("###,##0.0");
            } else {
                df = new DecimalFormat("###,##0.00");
            }
        } else {
            df = new DecimalFormat("###,##0");
        }
        double number = Double.parseDouble(str);
        return df.format(number);
    }

    /**
     * 根据身份证获取年龄
     *
     * @param idNo
     * @return
     * @throws ParseException
     */
    public static Integer getAge(String idNo) throws ParseException {
        String dates;
        if (idNo != null && idNo.length() == 15) {
            idNo = convertIdcarBy15bit(idNo);
        }
        if (idNo != null && idNo.length() == 18) {
            dates = idNo.substring(6, 14);
//            int year = DateUtil.daysBetween(DateUtil.valueOf(dates, DateUtil.DATEFORMAT_STR_012), DateUtil.getNow()) / 365;
            int year = 0;
            return year;
        }
        return 0;
    }

    /**
     * 将15位的身份证转成18位身份证
     *
     * @param idcard
     * @return
     * @throws ParseException
     */
    public static String convertIdcarBy15bit(String idcard) throws ParseException {
        String idcard17;
        // 非15位身份证  
        if (idcard.length() != 15) {
            return null;
        }

        if (isDigital(idcard)) {
            // 获取出生年月日  
            String birthday = idcard.substring(6, 12);
            Date birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            Calendar cday = Calendar.getInstance();
            cday.setTime(birthdate);
            String year = String.valueOf(cday.get(Calendar.YEAR));

            idcard17 = idcard.substring(0, 6) + year + idcard.substring(8);

            char c[] = idcard17.toCharArray();
            String checkCode;

            if (null != c) {
//                int bit[] = new int[idcard17.length()];
                int bit[];
                // 将字符数组转为整型数组  
                bit = converCharToInt(c);
                int sum17 = getPowerSum(bit);

                // 获取和值与11取模得到余数进行校验码  
                checkCode = getCheckCodeBySum(sum17);
                // 获取不到校验位  
                if (null == checkCode) {
                    return null;
                }

                // 将前17位与第18位校验码拼接  
                idcard17 += checkCode;
            }
        } else { // 身份证包含数字  
            return null;
        }
        return idcard17;
    }

    /**
     * 将和值与11取模得到余数进行校验码判断
     *
     * @param sum17
     * @return 校验位
     */
    public static String getCheckCodeBySum(int sum17) {
        String checkCode = null;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
        }
        return checkCode;
    }

    /**
     * 将身份证的每位和对应位的加权因子相乘之后，再得到和值
     *
     * @param bit
     * @return
     */
    public static int getPowerSum(int[] bit) {

        int sum = 0;

        if (power.length != bit.length) {
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < power.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * power[j];
                }
            }
        }
        return sum;
    }

    /**
     * 数字验证
     *
     * @param str
     * @return
     */
    public static boolean isDigital(String str) {
        return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
    }

    /**
     * 将字符数组转为整型数组
     *
     * @param c
     * @return
     * @throws NumberFormatException
     */
    public static int[] converCharToInt(char[] c) throws NumberFormatException {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * 字符转成map类型的
     * 字符串："current=1&mobileType=1&pageSize=10"
     */
    public static Map<String, Object> convertStringToMap(String s) {
        Map<String, Object> m = new HashMap<String, Object>();
        String[] couple = s.split("&");
        for (int i = 0; i < couple.length; i++) {
            String[] single = couple[i].split("=");
            if (single.length < 2) {
                m.put(single[0], "");
                continue;
            }
            m.put(single[0], single[1]);
        }
        return m;
    }

    /**
     * 比较版本号的大小,前者大则返回一个正数,后者大返回一个负数,相等则返回0
     *
     * @param version1
     * @param version2
     * @return
     */
    public static int compareVersion(String version1, String version2) throws Exception {
        if (version1 == null || version2 == null) {
            throw new Exception("compareVersion error:illegal params.");
        }
        String[] versionArray1 = version1.split("\\.");//注意此处为正则匹配，不能用"."；
        String[] versionArray2 = version2.split("\\.");
        int idx = 0;
        int minLength = Math.min(versionArray1.length, versionArray2.length);//取最小长度值
        int diff = 0;
        while (idx < minLength
                && (diff = versionArray1[idx].length() - versionArray2[idx].length()) == 0//先比较长度
                && (diff = versionArray1[idx].compareTo(versionArray2[idx])) == 0) {//再比较字符
            ++idx;
        }
        //如果已经分出大小，则直接返回，如果未分出大小，则再比较位数，有子版本的为大；
        diff = (diff != 0) ? diff : versionArray1.length - versionArray2.length;
        return diff;
    }

    static int[] DAYS = {0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     * @param date yyyy-MM-dd HH:mm:ss  / yyyy-MM-dd
     * @return
     */
    public static boolean isValidDate(String date) {
        try {
            int year = 0;
            int month = 0;
            int day = 0;
            if (date.length() > 5) {
                year = Integer.parseInt(date.substring(0, 4));
                if (year <= 0) return false;
            } else {
                return false;
            }
            if (date.length() > 8) {
                month = Integer.parseInt(date.substring(5, 7));
                if (month <= 0 || month > 12)
                    return false;
            } else {
                return false;
            }
            if (date.length() > 11) {
                day = Integer.parseInt(date.substring(8, 10));
                if (day <= 0 || day > DAYS[month])
                    return false;
            } else {
                return false;
            }
            if (month == 2 && day == 29 && !isGregorianLeapYear(year)) {
                return false;
            }
            if (date.length() > 20) {
                int hour = Integer.parseInt(date.substring(11, 13));
                if (hour < 0 || hour > 23)
                    return false;
                int minute = Integer.parseInt(date.substring(14, 16));
                if (minute < 0 || minute > 59)
                    return false;
                int second = Integer.parseInt(date.substring(17, 19));
                if (second < 0 || second > 59)
                    return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static final boolean isGregorianLeapYear(int year) {
        return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
    }

    public static String getPhoneNum(String phone) {
        char[] chars = phone.toCharArray();
        StringBuffer buffer = new StringBuffer();
        for (char c : chars) {
            if (isDigital(String.valueOf(c))) {
                buffer.append(String.valueOf(c));
            }
        }
        return buffer.toString();
    }

    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return
     */
    public static String subZeroAndDot(String s){
        if(s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(subZeroAndDot("1.01000"));;  // 转换后为1.01
    }

}
