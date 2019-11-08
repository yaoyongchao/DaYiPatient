package com.fh.baselib.net;



import com.fh.baselib.utils.LogUtil;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * 忽略https验证
 * Created by p on 2017/08/07.
 */

public class SSLUtils {

    private static final int DEFAULT_TIMEOUT = 60;
    public static OkHttpClient getClient() {
        TrustManager[] trustManager = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws
                            CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws
                            CertificateException {
                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        X509Certificate[] x509Certificates = new X509Certificate[0];
                        return x509Certificates;
                    }
                }
        };
        OkHttpClient.Builder client = new OkHttpClient.Builder();

        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor(
                new HttpLoggingInterceptor.Logger() {
                    @Override
                    public void log(String message) {
                        LogUtil.Companion.e("OkHTTP--" + message);

                    }
                }
        );
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        try {
            SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustManager, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            client.addInterceptor(interceptor);
            client.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            client.readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            client.writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
            client.sslSocketFactory(sslSocketFactory);
            client.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }
        return client.build();
    }
}
