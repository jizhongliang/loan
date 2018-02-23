package com.hwc.framework.common;

import javax.imageio.ImageIO;
import javax.net.ssl.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Map;

public class HttpUtils {
    public static int defaultConnectTimeout = 5000; // 默认连接超时
    public static int defaultReadTimeout = 30000; // 默认读取超时

    static {
        CookieHandler.setDefault(new CookieManager());
        HttpsURLConnection.setDefaultSSLSocketFactory(createSSLSocketFactory());
    }

    public static int getResponseCode(HttpURLConnection connection) throws IOException {
        try {
            return connection.getResponseCode();
        } catch (IOException e) {
            throw e;
        }
    }


    public static byte[] getResponseBytes(HttpURLConnection connection) throws IOException {

        InputStream inputStream;
        try {
            inputStream = connection.getInputStream();
        } catch (IOException e) {
            throw e;
        }

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];

        while (true) {
            int read = 0;
            IOException exception = null;
            try {
                read = inputStream.read(buffer);
                if (read < 1) {
                    break;
                }
            } catch (IOException e) {
                exception = e;
            }

            if (exception != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }

                try {
                    outputStream.close();
                } catch (IOException e) {

                }

                throw exception;
            }

            outputStream.write(buffer, 0, read);
        }

        try {
            outputStream.close();
        } catch (IOException e) {

        }

        return outputStream.toByteArray();

    }

    public static void sendRequest(HttpURLConnection connection, byte[] sendData) throws IOException {
        if (sendData == null || sendData.length < 1) {
            return;
        }

        OutputStream outputStream;
        try {
            outputStream = connection.getOutputStream();
        } catch (IOException e) {
            throw e;
        }

        try {
            outputStream.write(sendData);
        } catch (IOException e) {
            throw e;
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {

            }
        }
    }

    public static void setHeaders(HttpURLConnection connection, Map<String, String> headers) {
        for (String name : headers.keySet()) {
            String value = headers.get(name);
            connection.setRequestProperty(name, value);
        }
    }

    public static HttpURLConnection createHttpURLConnection(String method, String url) throws IOException {
        HttpURLConnection connection = createHttpURLConnection(url);

        try {
            connection.setRequestMethod(method);
        } catch (ProtocolException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        connection.setConnectTimeout(defaultConnectTimeout);
        connection.setReadTimeout(defaultReadTimeout);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(true);
        connection.setUseCaches(false);

        return connection;
    }

    private static HttpURLConnection createHttpURLConnection(String url) throws IOException {
        HttpURLConnection connection = null;

        URL httpUrl;
        try {
            httpUrl = new URL(url);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        if (url.substring(0, 8).equalsIgnoreCase("https://")) {
            try {
                connection = (HttpsURLConnection) httpUrl.openConnection();
            } catch (IOException e) {
                throw e;
            }
        } else {
            try {
                connection = (HttpURLConnection) httpUrl.openConnection();
            } catch (IOException e) {
                throw e;
            }
        }

        return connection;
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLContext sslContext = createSSLContext();
        return sslContext.getSocketFactory();
    }

    private static SSLContext createSSLContext() {
        SSLContext sslContext;
        try {
            sslContext = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage(), e);
        }

        TrustManager[] trustManageres = new TrustManager[1];
        trustManageres[0] = new MyX509TrustManager();

        try {
            sslContext.init(null, trustManageres, null);
        } catch (KeyManagementException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return sslContext;
    }

    private static class MyX509TrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] paramArrayOfX509Certificate, String paramString) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }
}
