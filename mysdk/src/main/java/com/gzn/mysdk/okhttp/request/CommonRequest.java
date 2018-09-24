package com.gzn.mysdk.okhttp.request;

import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Request;

public class CommonRequest {

    /**
     * 返回一个创建好的POST Request对象
     * @param url
     * @param params
     * @return
     */
    public static Request createPostRequest(String url,RequestParams params){

        FormBody.Builder mFormBodyBuilder = new FormBody.Builder();
        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                mFormBodyBuilder.add(entry.getKey(), entry.getValue());
            }
        }

        FormBody mFormBody = mFormBodyBuilder.build();

        return new Request.Builder().url(url).post(mFormBody).build();

    }
    /**
     * 返回一个创建好的GET Request对象
     * @param url
     * @param params
     * @return
     */
    public static Request createGetRequest(String url,RequestParams params){

        StringBuilder builder = new StringBuilder(url).append("?");

        if (params != null) {
            for (Map.Entry<String, String> entry : params.urlParams.entrySet()) {
                builder.append(entry.getKey()).append("=").append(entry.getValue())
                        .append("&");
            }
        }
        return new Request.Builder().url(builder.substring(0, builder.length() - 1)).get().build();

    }

}
