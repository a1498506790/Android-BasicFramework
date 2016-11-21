package com.airsaid.android_basicframework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.constants.AppConstants;
import com.airsaid.android_basicframework.ui.activity.IndexActivity;
import com.airsaid.android_basicframework.ui.activity.MainActivity;
import com.airsaid.android_basicframework.utils.SPUtils;

/**
 * Created by zhouyou on 2016/9/1.
 * Class desc: 闪屏页 Activity
 */
public class SplashActivity extends BaseActivity {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 判断是否是第一次登录
                boolean isFirstLogin = (boolean) SPUtils.getSP(mContext
                        , AppConstants.KEY_IS_FIRST_LOGIN, true);
                Intent intent = new Intent();
                if(isFirstLogin){
                    intent.setClass(mContext, IndexActivity.class);
                }else{
                    intent.setClass(mContext, MainActivity.class);
                }
                startActivity(intent);
                finish();
            }
        }, 2000);
    }
}
