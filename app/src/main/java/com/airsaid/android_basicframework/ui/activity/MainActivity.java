package com.airsaid.android_basicframework.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.TestActivity;
import com.airsaid.android_basicframework.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity {


    private List<Integer> mList;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar("测试标题");
        mList = new ArrayList<>();
        mList.add(R.id.toolbar);
    }

    public void next(View v){
        startActivity(new Intent(this, TestActivity.class));
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
                mStatusLayout.showContent();
            }
        });
    }
}
