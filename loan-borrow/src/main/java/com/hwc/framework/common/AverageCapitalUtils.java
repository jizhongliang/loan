package com.hwc.framework.common;


import cn.freesoft.utils.FsUtils;
import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by   on 2017/10/30.
 * 等额本金算法
 */
public class AverageCapitalUtils {
    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金和利息
     * <p>
     * 公式：每月偿还本金=(贷款本金÷还款月数)+(贷款本金-已归还本金累计额)×月利率
     *
     * @param invest     总借款额（贷款本金）
     * @param
     * @param totalMonth 还款总月数
     * @return 每月偿还本金和利息, 不四舍五入，直接截取小数点最后两位
     */
    public static List<JSONObject> getPerMonthPrincipalInterest(double invest, Date startDate, double dateRate, int totalMonth) {
        //  Map<Integer, String> map = new HashMap<Integer, String>();
        List<JSONObject> jsonObjects = new ArrayList<>();
        Double sub_amount = 0D;
        for (int i = 1; i <= totalMonth; i++) {
            JSONObject jsonObject = new JSONObject();

            double monthPri = getPerMonthPrincipal(invest, totalMonth);
            /**
             * 如果等额本金除不尽，则第一期本金=全部本金-后几期本金之和
             */
            if (FsUtils.mulDouble(monthPri, totalMonth) < invest) {
                if (i == 1) {
                    monthPri = FsUtils.subDouble(invest, FsUtils.mulDouble(monthPri, totalMonth - 1));
                }
            }

            jsonObject.put("monthPri", monthPri);   //本金
            sub_amount = FsUtils.addDouble(sub_amount, monthPri);  //累积已还的本金，用于计算未还剩余本金

            jsonObject.put("seq", i);   //第几期
            Date lastRepayDate = FsUtils.getInstanceOfDay(FsUtils.addMonth(startDate, i - 1));
            Date repayDate = FsUtils.getInstanceOfDay(FsUtils.addMonth(startDate, i));
            jsonObject.put("date", FsUtils.formatDate(repayDate));    //应换日期

            //Double days = FsUtils.getDays(lastRepayDate, repayDate);
            int days = Math.abs(DateUtil.daysBetween(repayDate, lastRepayDate));
            jsonObject.put("days", days);   //本期持有的天数
            double newRate = RateUtils.getRate(dateRate);   //利率
            double daysRate = FsUtils.mulDouble(newRate, FsUtils.d(days));
            Double balance = FsUtils.roundDouble(FsUtils.subDouble(invest, sub_amount), 2);  //剩余本金
            //剩余本金
            jsonObject.put("balance", balance);
            //计算利息的金额（本期本金+剩余本金）
            jsonObject.put("rest", balance);
            Double interest = FsUtils.roundUpDouble(FsUtils.mulDouble(FsUtils.addDouble(balance, monthPri), daysRate), 2);
            //利息
            jsonObject.put("interest", interest);
            jsonObject.put("rate", dateRate);   //利率
            double dayRes = FsUtils.addDouble(monthPri, interest);

            //应还金额
            jsonObject.put("dayRes", FsUtils.roundUpDouble(dayRes, 2));
            jsonObjects.add(jsonObject);


        }
        return jsonObjects;
    }


    /**
     * 等额本金计算获取还款方式为等额本金的每月偿还本金
     * <p>
     * 公式：每月应还本金=贷款本金÷还款月数
     *
     * @param invest     总借款额（贷款本金）
     * @param totalMonth 还款总月数
     * @return 每月偿还本金
     */
    public static double getPerMonthPrincipal(double invest, int totalMonth) {

        BigDecimal monthIncome = new BigDecimal(invest).divide(new BigDecimal(totalMonth), 2, BigDecimal.ROUND_DOWN);

        return monthIncome.doubleValue();
    }


    /**
     * @param args
     */
    public static void main(String[] args) throws ParseException {
        double invest = 10000; // 本金
        int month = 120;
        double yearRate = 0.05; // 年利率
        Date date = FsUtils.addDate(FsUtils.getInstanceOfDay(new Date()), -2);
        List<JSONObject> jsonObjects = getPerMonthPrincipalInterest(invest, date, yearRate, month);
        System.out.println("等额本金---每月本息：" + jsonObjects.toString());
        /*double benjin = getPerMonthPrincipal(invest, month, 0);
        System.out.println("等额本金---每月本金:" + benjin);
        double benjin2 = getPerMonthPrincipal(invest, month, 1);
        System.out.println("等额本金2---每月本金:" + benjin2);*/

        String patten = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(patten);

        Date date1 = sdf.parse("2018-01-15 00:00:01");
        Date date2 = FsUtils.getInstanceOfDay(FsUtils.addMonth(date1, 1));
        String res = sdf.format(date2);
        System.out.println("=====" + res);

        int days = DateUtil.daysBetween(date1, new Date());
        System.out.println("xiangcha==" + days);


    }
}
