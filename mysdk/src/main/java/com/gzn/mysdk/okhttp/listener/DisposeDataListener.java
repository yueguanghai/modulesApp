package com.gzn.mysdk.okhttp.listener;

/**
 * @author guozhennan8791@gmail.com
 * @version V1.0
 * @Description:
 * @Date 2018/9/24 下午9:40
 */
public interface DisposeDataListener {

    void onSuccess(Object responseObj);


    void onFailure(Object responseObj);
}
