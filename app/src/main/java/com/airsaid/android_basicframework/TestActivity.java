package com.airsaid.android_basicframework;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.bean.ArticleBean;
import com.airsaid.android_basicframework.bean.ListBean;
import com.airsaid.android_basicframework.http.HttpClient;
import com.airsaid.android_basicframework.http.HttpParams;
import com.airsaid.android_basicframework.http.MyCallback;
import com.airsaid.android_basicframework.http.api.UserService;

import retrofit2.Response;

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
