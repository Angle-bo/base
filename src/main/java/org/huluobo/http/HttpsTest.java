package org.huluobo.http;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.URL;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 *
 * java原生类，不依赖三方库
 * 使用HttpsURLConnection 发送Https请求
 */
public class HttpsTest {

    public final static String URL = "https://xxxxxxxxx/heartbeat/device";
    public final static String PFX_PATH = "C:/Users/G8670/Desktop/deviceapi.visionlives.com/client.p12";
    public final static String PFX_PASSWORD = "888888";

    public static void main(String[] args) throws Exception {

        String body = "{\n" +
                "    \"serialNo\":\"JK2106010006\",\n" +
                "    \"model\":\"设备型号\",\n" +
                "    \"sumber\":\"CPU-ID\"\n" +
                "}";

        X509TrustManager[] tm = new X509TrustManager[]{new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
            @Override
            public X509Certificate[] getAcceptedIssuers( ) {
                return new X509Certificate[0];
            }
        }};

        java.net.URL url = new URL(URL);
        InputStream in = new FileInputStream(PFX_PATH);
        KeyStore keyStore = KeyStore.getInstance("PKCS12");
        keyStore.load(in, PFX_PASSWORD.toCharArray());
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, PFX_PASSWORD.toCharArray());
        SSLContext context = SSLContext.getInstance("TLS");
        context.init(keyManagerFactory.getKeyManagers(), tm, null);
        HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);
        HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
        urlConnection.setSSLSocketFactory(context.getSocketFactory());
        urlConnection.setRequestMethod("POST");
        urlConnection.setRequestProperty("Content-Type", "application/json");
        // 发送POST请求必须设置如下两行
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        // 获取URLConnection对象对应的输出流
        PrintWriter out = new PrintWriter(urlConnection.getOutputStream());

        // 发送请求参数
        out.print(body);
        // flush输出流的缓冲
        out.flush();

        urlConnection.connect();
        int responseCode = urlConnection.getResponseCode();
        System.out.println(responseCode);
        //获取返回结果body
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                urlConnection.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        // 断开连接
        urlConnection.disconnect();
    }
}
