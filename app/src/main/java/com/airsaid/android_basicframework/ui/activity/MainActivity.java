package com.airsaid.android_basicframework.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.TestActivity;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.bean.ArticleBean;
import com.airsaid.android_basicframework.bean.ListBean;
import com.airsaid.android_basicframework.http.HttpClient;
import com.airsaid.android_basicframework.http.HttpParams;
import com.airsaid.android_basicframework.http.MyCallback;
import com.airsaid.android_basicframework.http.api.UserService;

import retrofit2.Response;

public class MainActivity extends BaseActivity {


    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar("测试标题");
        
    }

    public void next(View v){
        startActivity(new Intent(this, TestActivity.class));
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

                    }

                    @Override
                    public void onFail(String message) {

                    }
                });
    }

}
