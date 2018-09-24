package com.gzn.mysdk.okhttp;

import com.gzn.mysdk.okhttp.cookie.SimpleCookieJar;
import com.gzn.mysdk.okhttp.https.HttpsUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OkHttpClient 封装类
 *
 * @author guozhennan
 */
public class CommonOkHttpClient {
    private static final int TIME_OUT = 30;
    private static OkHttpClient sOkHttpClient;


    static {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        /**
         * 设置对 HTTPS 的支持
         */
        okHttpClientBuilder.hostnameVerifier(new HostnameVerifier() {
            @Override
            public boolean verify(String hostname, SSLSession session) {
                return true;
            }

        });
        /**
         * 添加请求头,按照个人的需要来添加
         */
        okHttpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                request.newBuilder()
                        .addHeader("User-Agent", "gzn")
                        .build();
                return chain.proceed(request);
            }
        });

        okHttpClientBuilder.cookieJar(new SimpleCookieJar());

        okHttpClientBuilder.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        okHttpClientBuilder.followRedirects(true);

        okHttpClientBuilder.sslSocketFactory(HttpsUtils.initSSLSocketFactory(), HttpsUtils.initTrustManager());

        sOkHttpClient = okHttpClientBuilder.build();

    }

    /**
     * 发送 get 请求
     * @param request
     * @param callback
     * @return
     */
    public static Call get(Request request, Callback callback) {
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }
    /**
     * 发送 post 请求
     * @param request
     * @param callback
     * @return
     */
    public static Call post(Request request, Callback callback) {
        Call call = sOkHttpClient.newCall(request);
        call.enqueue(callback);
        return call;
    }
}
