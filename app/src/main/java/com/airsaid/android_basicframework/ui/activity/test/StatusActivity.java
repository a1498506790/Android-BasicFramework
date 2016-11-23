package com.airsaid.android_basicframework.ui.activity.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseActivity;

/**
 * Created by zhouyou on 2016/11/22.
 * Class desc:
 *
 * 演示多状态布局使用
 */
public class StatusActivity extends BaseActivity{

    @Override
    public int getLayoutRes() {
        return R.layout.activity_test_status;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar("多状态布局");
    }

    public void load(View v){
        showLoading();
    }

    public void empty(View v){
        showEmpty();
    }

    public void error(View v){
        showError(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showContent();
            }
        });
    }
}
