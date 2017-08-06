package com.airsaid.android_basicframework.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.constants.AppConstants;
import com.airsaid.android_basicframework.ui.activity.test.LazyActivity;
import com.airsaid.android_basicframework.ui.activity.test.RefreshActivity;
import com.airsaid.android_basicframework.ui.activity.test.StatusActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private String[] mTitles = new String[]{
              "带进度条 WebView 示例"
            , "多状态布局示例"
            , "下拉刷新,上拉加载示例"
            , "懒加载示例"
    };
    private Class[] mClasses = {
              BrowserActivity.class
            , StatusActivity.class
            , RefreshActivity.class
            , LazyActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setSlideable(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        Toolbar toolbar = initToolbar("基础框架");
        toolbar.setNavigationIcon(null);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(android.R.layout.simple_list_item_1, Arrays.asList(mTitles)));
        mRecyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(MainActivity.this, mClasses[position]);
                intent.putExtra(Intent.EXTRA_TITLE, mTitles[position]);
                if(position == 0){
                    intent.putExtra(AppConstants.EXTRA_URL, "https://github.com/Airsaid/Android-BasicFramework");
                }
                startActivity(intent);
            }
        });
    }

    public class MyAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

        public MyAdapter(int layoutResId, List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, String s) {
            baseViewHolder.setText(android.R.id.text1, s);
        }
    }
}
