package com.hwc.framework.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

/**
 * Created by jzl on 2018/1/4.
 */
public class JSONObjectUtil {

    /**
     * JSONObject排序
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("all")
    public static JSONObject sortJsonObject(JSONObject obj) {
        if (null != obj) {
            Map map = new TreeMap();
            Iterator<String> it = obj.keys();
            while (it.hasNext()) {
                String key = it.next();
                Object value = obj.get(key);
                if (null != value) {
                    if (value instanceof JSONObject) {
                        JSONObject jSONObject = JSONObject.fromObject(value);
                        if (null != jSONObject) {
                            map.put(key, sortJsonObject(jSONObject));
                        }
                    } else if (value instanceof JSONArray) {
                        JSONArray jSONArray = JSONArray.fromObject(value);
                        if (null != jSONArray && jSONArray.size() > 0) {
                            map.put(key, sortJsonArray(jSONArray));
                        }
                    } else {
                        map.put(key, value);
                    }
                }
            }
            return JSONObject.fromObject(map);
        }
        return null;
    }

    /**
     * JSONArray排序
     *
     * @param array
     * @return
     */
    @SuppressWarnings("all")
    public static JSONArray sortJsonArray(JSONArray array) {
        if (null != array && array.size() > 0) {
            List<String> list = new ArrayList<String>();
            for (int i = 0; i < array.size(); i++) {
                Object obj = array.get(i);
                if (null != obj) {
                    if (obj instanceof JSONObject) {
                        JSONObject jSONObject = JSONObject.fromObject(obj);
                        if (null != jSONObject) {
                            list.add(sortJsonObject(jSONObject).toString());
                        }
                    } else if (obj instanceof JSONArray) {
                        JSONArray jSONArray = JSONArray.fromObject(obj);
                        if (null != jSONArray && jSONArray.size() > 0) {
                            list.add(sortJsonArray(JSONArray.fromObject(obj)).toString());
                        }
                    } else {
                        list.add(obj.toString());
                    }
                }
            }
            if (null != list && !list.isEmpty()) {
                Collections.sort(list, new Comparator<String>() {

                    @Override
                    public int compare(String s1, String s2) {
                        return s1.compareToIgnoreCase(s2);
                    }
                });
                return JSONArray.fromObject(list);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        try {
            /*String jsong = "{\"interest\":\"370.00\",\"isLastStages\":0,\"isOverdue\":1,\"overdues\":[{\"expiryDate\":\"Nov 22, 2017 11:23:59 PM\",\"overdueAmount\":1000.00,\"overdueFine\":430.00,\"overdueInterest\":220.00,\"overdueStages\":\"136\"}],\"payMentTransaction\":\"DK01201712299090303523500001\",\"repayAmount\":\"1000.00\",\"stages\":\"136\",\"totalAmount\":\"1800.00\"}";
            JSONObject jSONObject = JSONObject.fromObject(jsong);
            System.out.println("111====" + JsonStringUtils.sortJsonObject(jSONObject));*/

            String jsong = "[{\"interest\":\"370.00\",\"isLastStages\":0,\"isOverdue\":1,\"overdues\":[{\"expiryDate\":\"Nov 22, 2017 11:23:59 PM\",\"overdueAmount\":1000.00,\"overdueFine\":430.00,\"overdueInterest\":220.00,\"overdueStages\":\"136\"}],\"payMentTransaction\":\"DK01201712299090303523500001\",\"repayAmount\":\"1000.00\",\"stages\":\"136\",\"totalAmount\":\"1800.00\"},{\"interest\":\"370.00\",\"isLastStages\":0,\"isOverdue\":1,\"overdues\":[{\"expiryDate\":\"Nov 22, 2017 11:23:59 PM\",\"overdueAmount\":1000.00,\"overdueFine\":430.00,\"overdueInterest\":220.00,\"overdueStages\":\"136\"}],\"payMentTransaction\":\"DK01201712299090303523500001\",\"repayAmount\":\"1000.00\",\"stages\":\"136\",\"totalAmount\":\"1800.00\"}]";
            //String jsong = "[{\\\"interest\\\":\\\"370.00\\\",\\\"isLastStages\\\":0,\\\"isOverdue\\\":1,\\\"overdues\\\":[{\\\"expiryDate\\\":\\\"Nov 22, 2017 11:23:59 PM\\\",\\\"overdueAmount\\\":1000.00,\\\"overdueFine\\\":430.00,\\\"overdueInterest\\\":220.00,\\\"overdueStages\\\":\\\"136\\\"}],\\\"payMentTransaction\\\":\\\"DK01201712299090303523500001\\\",\\\"repayAmount\\\":\\\"1000.00\\\",\\\"stages\\\":\\\"136\\\",\\\"totalAmount\\\":\\\"1800.00\\\"},{\\\"interest\\\":\\\"370.00\\\",\\\"isLastStages\\\":0,\\\"isOverdue\\\":1,\\\"overdues\\\":[{\\\"expiryDate\\\":\\\"Nov 22, 2017 11:23:59 PM\\\",\\\"overdueAmount\\\":1000.00,\\\"overdueFine\\\":430.00,\\\"overdueInterest\\\":220.00,\\\"overdueStages\\\":\\\"136\\\"}],\\\"payMentTransaction\\\":\\\"DK01201712299090303523500001\\\",\\\"repayAmount\\\":\\\"1000.00\\\",\\\"stages\\\":\\\"136\\\",\\\"totalAmount\\\":\\\"1800.00\\\"}]\"";
            JSONArray jSONArray = JSONArray.fromObject(jsong);
            System.out.println("111====" + sortJsonArray(jSONArray));
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
