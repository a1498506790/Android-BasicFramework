package com.airsaid.android_basicframework.ui.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.adapter.LazyLoadFragmentPagerAdapter;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.ui.fragment.LazyLoadFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Airsaid
 * @Date 2017/8/6 17:54
 * @Blog http://blog.csdn.net/airsaid
 * @Desc
 */
public class LazyActivity extends BaseActivity{

    private ViewPager mViewPager;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_lazy;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar(getIntent().getStringExtra(Intent.EXTRA_TITLE));
        mViewPager = (ViewPager) findViewById(R.id.viewPager);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(LazyLoadFragment.newInstance("这是第一个 fragment"));
        fragments.add(LazyLoadFragment.newInstance("这是第二个 fragment"));
        fragments.add(LazyLoadFragment.newInstance("这是第三个 fragment"));
        fragments.add(LazyLoadFragment.newInstance("这是第四个 fragment"));
        mViewPager.setAdapter(new LazyLoadFragmentPagerAdapter(getSupportFragmentManager()
                , fragments));
    }
}
