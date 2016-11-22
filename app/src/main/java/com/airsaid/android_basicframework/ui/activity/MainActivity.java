package com.airsaid.android_basicframework.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.bean.ArticleBean;
import com.airsaid.android_basicframework.bean.ListBean;
import com.airsaid.android_basicframework.http.HttpClient;
import com.airsaid.android_basicframework.http.HttpParams;
import com.airsaid.android_basicframework.http.MyCallback;
import com.airsaid.android_basicframework.http.api.UserService;

import butterknife.BindView;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    @BindView(R.id.imageView)
    ImageView mImageView;
    @BindView(R.id.imageView2)
    ImageView mImageView2;


    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar("测试标题");

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
