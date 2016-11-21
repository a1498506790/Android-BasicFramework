package com.airsaid.android_basicframework;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.airsaid.android_basicframework.base.BaseFragment;

/**
 * Created by zhouyou on 2016/11/18.
 * Class desc:
 */
public class TestFragment extends BaseFragment {

    @Override
    public View getLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, null);
    }

    @Override
    public void onCreateFragment(@Nullable Bundle savedInstanceState) {
        initToolbar("Fragment标题");
        setLeftTitle("左侧");
        setLeftTitleColor(Color.parseColor("#ffff00"));
        setLeftTitleSize(18f);
    }
}
