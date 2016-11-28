package com.airsaid.android_basicframework.http;


import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.utils.LogUtils;
import com.airsaid.android_basicframework.utils.ToastUtils;
import com.airsaid.android_basicframework.utils.UiUtils;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by zhouyou on 2016/6/22.
 * Class desc: 对 retrofit 的 callback 进行二次封装
 */
public abstract class MyCallback<T extends BaseBean> implements Callback<T> {

    private static final String TAG = "MyCallback";

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        LogUtils.e(TAG, "request success:  " + response.raw().toString());
        if(response.raw().code() == 200){
            // 在这里对服务器规定的状态码进行相应判断
            if(response.body().status == 0){
                onSucc(response);
            }else{
                ToastUtils.show(UiUtils.getContext(), response.body().msg);
                onFail(response.body().msg);
            }
        }else{
            onFailure(call, new RuntimeException("response error, detail=" + response.raw().toString()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        LogUtils.e(TAG, "request failure:  " + call.request().toString());
        LogUtils.e(TAG, "request failure:  " + t.getMessage() + " ==== " + t.toString());

        // 对不同网络错误做不同处理
        if(t instanceof SocketTimeoutException){
            // 连接超时
            ToastUtils.show(UiUtils.getContext(), "网络连接超时，请稍后再试");
        }else if(t instanceof ConnectException){
            // 网络连接错误
            ToastUtils.show(UiUtils.getContext(), "网络连接错误，请检查您的网络是否正常");
        }else if(t instanceof UnknownHostException){
            // DNS解析错误
            ToastUtils.show(UiUtils.getContext(), "请求失败，请检查您的网络是否正常");
        }else if(t instanceof IllegalStateException){
            // 参数解析失败
            ToastUtils.show(UiUtils.getContext(), "请求失败(数据解析错误)");
        }else if(t instanceof RuntimeException){
            // 运行时异常
            ToastUtils.show(UiUtils.getContext(), "请求失败");
        }
        onFail(t.getMessage() + " ==== " + t.toString());
    }

    /**
     * 请求成功
     * @param response
     */
    public abstract void onSucc(Response<T> response);

    /**
     * 请求失败
     * @param message
     */
    public abstract void onFail(String message);

}
