package com.airsaid.android_basicframework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.airsaid.android_basicframework.base.BaseActivity;

/**
 * Created by zhouyou on 2016/11/22.
 * Class desc:
 */
public class TestActivity extends BaseActivity {
    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar("测试 Activity");
    }

    public void next(View v){
        startActivity(new Intent(this, TestActivity.class));
    }
}
