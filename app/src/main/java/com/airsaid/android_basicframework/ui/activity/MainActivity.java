package com.airsaid.android_basicframework.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.bean.ArticleBean;
import com.airsaid.android_basicframework.bean.ListBean;
import com.airsaid.android_basicframework.http.HttpClient;
import com.airsaid.android_basicframework.http.HttpParams;
import com.airsaid.android_basicframework.http.MyCallback;
import com.airsaid.android_basicframework.http.api.UserService;
import com.airsaid.android_basicframework.utils.LogUtils;

import butterknife.BindView;
import retrofit2.Response;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout mRefreshLayout;

    private int mPage = 1;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar("测试标题");

        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    @Override
    public void onRefresh() {
        mPage = 121;
        getHomeArticles(mPage);
    }

    /**
     * 请求网络，获取首页文章列表
     */
    private void getHomeArticles(int page){
        HttpClient.getIns().createService(UserService.class)
                .homeArticles(HttpParams.getIns().putPage(page))
                .enqueue(new MyCallback<BaseBean<ListBean<ArticleBean>>>() {
                    @Override
                    public void onSucc(Response<BaseBean<ListBean<ArticleBean>>> response) {
                        BaseBean<ListBean<ArticleBean>> body = response.body();
                        LogUtils.e("test", "body: " + body.toString() );
                    }

                    @Override
                    public void onFail(String message) {

                    }
                });
    }

}
