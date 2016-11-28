package com.airsaid.android_basicframework.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.constants.AppConstants;
import com.airsaid.android_basicframework.widget.ProgressWebView;

import butterknife.BindView;

/**
 * Created by zhouyou on 2016/8/4.
 * Class desc: 浏览页 Activity
 */
public class BrowserActivity extends BaseActivity {

    @BindView(R.id.webView)
    ProgressWebView mWebView;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_browser;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        if (getIntent() != null) {
            // 设置标题
            String title = getIntent().getExtras().getString(AppConstants.EXTRA_TITLE, "");
            initToolbar(title);

            // 设置加载url
            if (!TextUtils.isEmpty(getIntent().getExtras().getString(AppConstants.EXTRA_URL, ""))) {
                mWebView.loadUrl(getIntent().getStringExtra(AppConstants.EXTRA_URL));
            }
        }
    }
}
