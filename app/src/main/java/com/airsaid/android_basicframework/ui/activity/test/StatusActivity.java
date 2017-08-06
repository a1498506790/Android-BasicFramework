package com.airsaid.android_basicframework.ui.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.RadioGroup;

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
        initToolbar(getIntent().getStringExtra(Intent.EXTRA_TITLE));
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == 1){
                    showLoading();
                }else if(checkedId == 2){
                    showFail(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            showLoading();
                        }
                    });
                }else if(checkedId == 3){
                    showContent();
                }
            }
        });
    }
}
