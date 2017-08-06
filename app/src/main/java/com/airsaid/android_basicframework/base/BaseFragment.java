package com.airsaid.android_basicframework.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.widget.status.MultiStatusLayout;

import butterknife.ButterKnife;

/**
 * @author Airsaid
 * @github https://github.com/airsaid
 * @date 2017/5/22
 * @desc Fragment 基类
 */
public abstract class BaseFragment extends Fragment {

    protected static String TAG;

    private View mView;
    private Toolbar mToolbar;
    private Activity mActivity;
    protected Context mContext;
    protected LayoutInflater mLayoutInflater;
    private AppCompatActivity mCompatActivity;

    protected boolean mIsVisiable; // 是否可见
    protected boolean mIsViewCreate; // 是否已经调用了 onCreateView

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
        mActivity = getActivity();
        mContext = mActivity;
        mCompatActivity = (AppCompatActivity) mActivity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mLayoutInflater = getLayoutInflater(savedInstanceState);
        mView = getLayout(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIsViewCreate = true;
        // 绑定依赖注入框架
        ButterKnife.bind(this, mView);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onCreateFragment(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    /**
     * 初始化标题栏
     */
    protected Toolbar initToolbar(String title){
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCompatActivity.setSupportActionBar(mToolbar);
        ActionBar actionBar = mCompatActivity.getSupportActionBar();
        if(actionBar != null){
            // 取消原有左侧标题
            actionBar.setDisplayShowTitleEnabled(false);
        }
        // 设置标题
        TextView txtTitle = (TextView) findViewById(R.id.txt_title_title);
        txtTitle.setText(title);
        // 设置左侧图标
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBack();
            }
        });
        return mToolbar;
    }

    /**
     * 设置标题栏标题
     */
    public void setTitle(String title){
        if(mToolbar != null){
            TextView txtTitle = (TextView) findViewById(R.id.txt_title_title);
            txtTitle.setText(title);
        }
    }

    /**
     * 设置标题栏标题颜色
     */
    public void setTitleTextColor(int id) {
        if (mToolbar != null) {
            TextView txtTitle = (TextView) findViewById(R.id.txt_title_title);
            txtTitle.setTextColor(id);
        }
    }

    /**
     * 设置左侧标题
     */
    public void setLeftTitle(String leftTitle){
        if(mToolbar != null && leftTitle != null){
            mToolbar.setNavigationIcon(null);
            TextView txtLeftTitle = (TextView) findViewById(R.id.txt_title_left);
            txtLeftTitle.setVisibility(View.VISIBLE);
            txtLeftTitle.setText(leftTitle);
            txtLeftTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onBack();
                }
            });
        }
    }

    /**
     * 设置左侧标题颜色
     */
    public void setLeftTitleColor(int id){
        if(mToolbar != null){
            TextView txtLeftTitle = (TextView) findViewById(R.id.txt_title_left);
            txtLeftTitle.setTextColor(id);
        }
    }

    /**
     * 设置左侧标题字体大小
     */
    public void setLeftTitleSize(float size){
        if(mToolbar != null){
            TextView txtLeftTitle = (TextView) findViewById(R.id.txt_title_left);
            txtLeftTitle.setTextSize(size);
        }
    }

    /**
     * 显示加载中布局
     */
    public void showLoading(){
        MultiStatusLayout multiStatusLayout = (MultiStatusLayout) findViewById(R.id.layout_multi_status);
        if(multiStatusLayout != null){
            multiStatusLayout.setStatus(MultiStatusLayout.STATUS_LOADING);
        }
    }

    /**
     * 显示加载失败布局
     */
    public void showFail(View.OnClickListener listener){
        MultiStatusLayout multiStatusLayout = (MultiStatusLayout) findViewById(R.id.layout_multi_status);
        if(multiStatusLayout != null){
            multiStatusLayout.setStatus(MultiStatusLayout.STATUS_LOAD_FAIL, listener);
        }
    }

    /**
     * 显示内容
     */
    public void showContent(){
        MultiStatusLayout multiStatusLayout = (MultiStatusLayout) findViewById(R.id.layout_multi_status);
        if(multiStatusLayout != null){
            multiStatusLayout.setStatus(MultiStatusLayout.STATUS_HIDE);
        }
    }

    /**
     * 返回，默认退出当前 activity
     */
    protected void onBack(){
        finish();
    }

    /**
     * 销毁当前挂载的 activity
     */
    protected void finish(){
        mActivity.finish();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        mIsVisiable = isVisibleToUser;
        if(mIsVisiable && mIsViewCreate){
            onLazyLoadData();
        }
    }

    protected void onLazyLoadData(){}

    /**
     * 查找当前控件
     */
    protected View findViewById(int id){
        return mView.findViewById(id);
    }

    /**
     * 获取 Layout 布局
     */
    public abstract View getLayout(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);

    public abstract void onCreateFragment(@Nullable Bundle savedInstanceState);

}
