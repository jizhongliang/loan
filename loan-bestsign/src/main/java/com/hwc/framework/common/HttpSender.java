package com.hwc.framework.common;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class HttpSender {

    public static Map<String, Object> getResponseString(String method, String url, String sendData, Map<String, String> headers) throws IOException {
        Map<String, Object> response = HttpRequester.request(method, url, sendData, headers);
        int responseCode = (int) response.get("responseCode");
        byte[] responseBytes = (byte[]) response.get("responseData");
        String responseString;
        try {
            responseString = new String(responseBytes, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            responseString = new String(responseBytes);
        }
        Map<String, Object> result = new HashMap<>();
        result.put("responseCode", responseCode);
        result.put("responseData", responseString);
        return result;
    }
    
    public static Map<String, Object> getResponseBytes(String method, String url, String sendData, Map<String, String> headers) throws IOException {
        return HttpRequester.request(method, url, sendData, headers);
    }
    
    public static String urlencode(String data) {
        return urlencode(data, "UTF-8");
    }
    
    public static String urlencode(String data, String charset) {
        try {
            return URLEncoder.encode(data, charset);
        }
        catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
    
    private static class HttpRequester {
        public static Map<String, Object> request(String method, String url, Object sendData, Map<String, String> headers) throws IOException {
            String tag = "[HttpRequester] [" + method + " " + url + "]";

            HttpURLConnection connection;
            try {
                connection = HttpUtils.createHttpURLConnection(method, url);
            }
            catch (IOException e) {
                throw e;
            }

            if (headers != null && headers.size() > 0) {
                for (String name : headers.keySet()) {
                    String value = headers.get(name);
                }
                HttpUtils.setHeaders(connection, headers);
            }
            
            if (sendData != null) {
                byte[] sendDataBytes = null;
                if (sendData instanceof String) {
                    String strSendData = (String) sendData;
                    if (strSendData.length() > 0) {
                        sendDataBytes = strSendData.getBytes("UTF-8");
                    }
                }
                else if (sendData instanceof byte[]) {
                    sendDataBytes = (byte[]) sendData;
                }
                else {
                    throw new RuntimeException("sendData not support for this Class, only byte[] or string");
                }
                
                if (sendDataBytes != null && sendDataBytes.length > 0) {
                    String loogerSendData = getLoggerString(sendDataBytes, 256);
                    try {
                        HttpUtils.sendRequest(connection, sendDataBytes);
                    }
                    catch (IOException e) {
                        throw e;
                    }
                }
                
            }
            

            int responseCode;
            try {
                responseCode = HttpUtils.getResponseCode(connection);
            }
            catch (IOException e) {
                throw e;
            }

            byte[] responseBytes;
            try {
                responseBytes = HttpUtils.getResponseBytes(connection);
            }
            catch (IOException e) {
                throw e;
            }
            
            Map<String, Object> response = new HashMap<>();
            response.put("responseCode", responseCode);
            response.put("responseData", responseBytes);

            String loggerResponseString = getLoggerString(responseBytes, 256);

            return response;
        }

        private static String getLoggerString(final byte[] data, int maxLength) {
            String loggerString;
            if (data.length > maxLength) {
                byte[] shortData = new byte[maxLength];
                System.arraycopy(data, 0, shortData, 0, shortData.length);
                try {
                    loggerString = new String(shortData, "UTF-8") + "...";
                }
                catch (UnsupportedEncodingException e) {
                    loggerString = new String(shortData) + "...";
                }
            }
            else {
                try {
                    loggerString = new String(data, "UTF-8");
                }
                catch (UnsupportedEncodingException e) {
                    loggerString = new String(data);
                }
            }
            
            char[] chars = new char[loggerString.length()];
            loggerString.getChars(0, loggerString.length(), chars, 0);
            for (int i = 0; i < chars.length; i++) {
                char c = chars[i];
                if (c == '\n' || c == '\r') {
                    chars[i] = ' ';
                }
            }
            return new String(chars);
        }
        
    }
}
