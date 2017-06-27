package com.airsaid.android_basicframework.ui.activity.test;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.bean.TestBean;
import com.airsaid.android_basicframework.bean.ListBean;
import com.airsaid.android_basicframework.http.HttpClient;
import com.airsaid.android_basicframework.http.HttpParams;
import com.airsaid.android_basicframework.http.MyCallback;
import com.airsaid.android_basicframework.http.api.UserService;
import com.airsaid.android_basicframework.widget.refresh.PullToRefreshLayout;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Response;

/**
 * Created by zhouyou on 2016/11/23.
 * Class desc:
 * <p>
 * 演示下拉刷新/上拉加载更多
 */
public class RefreshActivity extends BaseActivity implements PullToRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.refreshView)
    PullToRefreshLayout mRefreshView;

    private List<TestBean> mList = new ArrayList<>();
    private TestAdapter mAdapter;
    private int mPage = 1;

    @Override
    public int getLayoutRes() {
        return R.layout.activity_test_refresh;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initToolbar("演示刷新");
        // 设置刷新监听
        mRefreshView.setOnRefreshListener(this);
        // 初始化 Adapter
        initAdapter();
        // 默认获取数据
        showLoading();

        mRefreshView.postDelayed(new Runnable() {
            @Override
            public void run() {
                onRefresh();
            }
        }, 1000);

    }

    private void initAdapter() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new TestAdapter(android.R.layout.simple_list_item_1, mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onRefresh() {
        mPage = 1;
        getData(mPage);
    }

    @Override
    public void onLoadMore() {
        mPage ++;
        getData(mPage);
    }

    /**
     * 请求网络，演示数据
     */
    private void getData(int page){
        HttpClient.getIns().createService(UserService.class)
                .testData(HttpParams.getIns().putPage(page))
                .enqueue(new MyCallback<BaseBean<ListBean<TestBean>>>() {
                    @Override
                    public void onSuccess(Response<BaseBean<ListBean<TestBean>>> response) {
                        showContent();
                        mRefreshView.onFinishLoading();
                        ListBean<TestBean> data = response.body().getData();

                        // 设置数据为空时显示空布局
                        if(data.getLists().size() <= 0){
                            showEmpty();
                            return;
                        }

                        if(mPage == 1){
                            // 下拉刷新
                            mAdapter.setNewData(data.getLists());
                            // 判断是否开启上拉加载更多
                            if(data.getPageIndex() < data.getPageAll()) {
                                mRefreshView.setPullUpEnable(true);
                            } else{
                                mRefreshView.setPullUpEnable(false);
                            }
                        }else{
                            // 上拉加载
                            mAdapter.addData(data.getLists());
                            if(data.getPageIndex() >= data.getPageAll()){
                                mRefreshView.setPullUpEnable(false);
                            }
                        }
                    }

                    @Override
                    public void onFail(String message) {
                        mRefreshView.onFinishLoading();
                        showError(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                showLoading();
                                onRefresh();
                            }
                        });
                    }
                });
    }

    public class TestAdapter extends BaseQuickAdapter<TestBean, BaseViewHolder>{


        public TestAdapter(int layoutResId, List<TestBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder baseViewHolder, TestBean bean) {
            baseViewHolder.setText(android.R.id.text1, bean.getTitle());
        }
    }

}
