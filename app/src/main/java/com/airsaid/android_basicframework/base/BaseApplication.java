package com.airsaid.android_basicframework.base;

import android.app.Application;
import android.content.Context;

/**
 * Created by zhouyou on 2016/6/23.
 * Class desc:
 *
 */
public class BaseApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * 获取上下文
     * @return
     */
    public static Context getContext(){
        return mContext;
    }
}
