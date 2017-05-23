package com.airsaid.android_basicframework.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;
import com.tencent.smtt.sdk.TbsListener;

/**
 * @author Airsaid
 * @github https://github.com/airsaid
 * @date 2017/5/22
 * @desc Application 基类
 */
public class BaseApplication extends Application {

    public static final String TAG = "BaseApplication";

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        initX5WebView();
    }

    /**
     * 获取上下文
     * @return Context
     */
    public static Context getContext(){
        return mContext;
    }

    /**
     * 初始化 x5 内核
     */
    private void initX5WebView() {
        QbSdk.setTbsListener(new TbsListener() {
            @Override
            public void onDownloadFinish(int i) {
                Log.d(TAG, "onDownloadFinish is " + i);
            }

            @Override
            public void onInstallFinish(int i) {
                Log.d(TAG, "onInstallFinish is " + i);
            }

            @Override
            public void onDownloadProgress(int i) {
                Log.d(TAG, "onDownloadProgress:"+i);
            }
        });
        QbSdk.initX5Environment(this, new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.d(TAG, "onCoreInitFinished: 初始化完成");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                Log.d(TAG, "onCoreInitFinished: 初始化失败 " + b);
            }
        });
    }

}
