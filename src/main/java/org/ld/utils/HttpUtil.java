package org.ld.utils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * http工具未完善
 * https://blog.csdn.net/xiaojin21cen/article/details/86713894
 */
@SuppressWarnings("unused")
public class HttpUtil {

    private static final Logger LOG = Logger.newInstance(HttpUtil.class);
    public static String JSON_TYPE = "application/json";
    public static String STREAM_TYPE = "application/octet-stream";

    /**
     * 根据url 获取 Http 连接默认30s超时
     */
    private static HttpURLConnection getUrlConnection(String url) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(30000);
        conn.setReadTimeout(30000);
        conn.setRequestProperty("Content-Type", JSON_TYPE);
        conn.setDoOutput(true);
        conn.setUseCaches(false);
        return conn;
    }

    public OutputStream put(HttpURLConnection conn) throws IOException {
        conn.setRequestMethod("PUT");
        conn.setInstanceFollowRedirects(false);
        conn.setChunkedStreamingMode(32 << 10); //32kB-chunk
        if (conn.getResponseCode() == 307) {
            String location = conn.getHeaderField("location");
            conn.disconnect();
            conn = getUrlConnection(location);
            conn.setInstanceFollowRedirects(false);
            conn.setChunkedStreamingMode(32 << 10); //32kB-chunk
        }
        HttpURLConnection conn1 = conn;
        return new BufferedOutputStream(conn1.getOutputStream(), 10 * 1024 * 1024) {
            @Override
            public void close() throws IOException {
                try {
                    super.close();
                } finally {
                    try {
                        conn1.getInputStream();
                        if (!((conn1.getResponseCode() == 200)
                                || (conn1.getResponseCode() == 201)
                                || (conn1.getResponseCode() == 202))) {
                            LOG.error(() -> "请求失败----Code:" + conn1.getResponseCode() + "Message:" + conn1.getResponseMessage());
                        }
                    } finally {
                        conn1.disconnect();
                    }
                }
            }
        };
    }
}