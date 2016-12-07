package com.airsaid.android_basicframework.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.utils.ActivityManager;
import com.airsaid.android_basicframework.widget.StatusLayout;
import com.airsaid.android_basicframework.widget.slideback.SlideBackActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by zhouyou on 2016/6/23.
 * Class desc: activity base class
 */
public abstract class BaseActivity extends SlideBackActivity {

    protected static String TAG;
    protected Activity mContext;
    private Toolbar mToolbar;
    protected StatusLayout mStatusLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 设置 Activity 屏幕方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 隐藏 ActionBar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置 TAG
        TAG = this.getClass().getSimpleName();

        super.onCreate(savedInstanceState);
        this.mContext = this;

        // 设置布局
        setContentView(getStatusLayoutView());

        // 绑定依赖注入框架
        ButterKnife.bind(this);

        onCreateActivity(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        ActivityManager.getInstance().pushActivity(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ActivityManager.getInstance().popActivity(this);
    }

    /**
     * 初始化标题栏
     */
    public Toolbar initToolbar(String title) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
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
     * 返回
     */
    protected void onBack() {
        finish();
    }

    /**
     * 获取多状态布局 View
     */
    protected View getStatusLayoutView() {
        mStatusLayout = (StatusLayout) LayoutInflater.from(this).inflate(R.layout.layout_status_view, null);
        if (getLayoutRes() != 0) {
            View contentView = LayoutInflater.from(this).inflate(getLayoutRes(), null);
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT
                    , ViewGroup.LayoutParams.MATCH_PARENT);
            mStatusLayout.addView(contentView, params);
        }
        return mStatusLayout;
    }

    /**
     * 显示内容布局
     */
    protected void showContent() {
        showContent(null);
    }

    /**
     * 显示内容布局
     * @param skipIds 不显示的 View ID 集合
     */
    protected void showContent(List<Integer> skipIds) {
        if (skipIds == null || skipIds.size() <= 0) {
            mStatusLayout.showContent();
        } else {
            mStatusLayout.showContent(skipIds);
        }
    }

    /**
     * 显示加载中布局
     */
    protected void showLoading() {
        // 如果有 Toolbar 则默认不隐藏
        showLoading(getToolBarVisibleIds());
    }

    /**
     * 显示加载中布局
     *
     * @param skipIds 不隐藏的 View ID 集合
     */
    protected void showLoading(List<Integer> skipIds) {
        if(skipIds == null || skipIds.size() <= 0){
            mStatusLayout.showLoading();
        }else{
            mStatusLayout.showLoading(skipIds);
        }
    }

    /**
     * 显示没有数据布局
     */
    protected void showEmpty() {
        // 如果有 Toolbar 则默认不隐藏
       showEmpty(null, null, null, getToolBarVisibleIds());
    }

    /**
     * 显示没有数据布局
     * @param emptyDrawable 图片
     * @param emptyTitle    标题
     * @param emptyMessage  提示文字
     * @param skipIds       不隐藏的 View ID 集合
     */
    protected void showEmpty(Drawable emptyDrawable, String emptyTitle, String emptyMessage, List<Integer> skipIds){
        if (skipIds == null || skipIds.size() <= 0) {
            mStatusLayout.showEmpty();
        } else {
            mStatusLayout.showEmpty(emptyDrawable, emptyTitle, emptyMessage, skipIds);
        }
    }

    /**
     * 显示加载错误布局
     */
    protected void showError(View.OnClickListener onClickListener) {
        // 如果有 Toolbar 则默认不隐藏
        showError(null, null, null, null, onClickListener, getToolBarVisibleIds());
    }

    /**
     * 显示加载错误布局
     * @param errorDrawable     图片
     * @param errorTitle        标题
     * @param errorContext      提示内容
     * @param errorButtonText   按钮文字
     * @param onClickListener   按钮点击事件
     * @param skipIds           不隐藏的 View ID 集合
     */
    protected void showError(Drawable errorDrawable, String errorTitle, String errorContext
            , String errorButtonText, View.OnClickListener onClickListener, List<Integer> skipIds) {
        if (skipIds == null || skipIds.size() <= 0) {
            mStatusLayout.showError(onClickListener);
        } else {
            mStatusLayout.showError(errorDrawable, errorTitle, errorContext, errorButtonText, onClickListener, skipIds);
        }
    }

    /**
     * 获取 Toolbar 所有显示的 View ID 集合
     * @return
     */
    private List<Integer> getToolBarVisibleIds(){
        List<Integer> skipIds = null;
        if(mToolbar != null){
            skipIds = new ArrayList<>();
            skipIds.add(R.id.toolbar);
            TextView txtTitle = (TextView) findViewById(R.id.txt_title_title);
            if(View.VISIBLE == txtTitle.getVisibility()){
                skipIds.add(R.id.txt_title_title);
            }
            TextView txtLeftTitle = (TextView) findViewById(R.id.txt_title_left);
            if(View.VISIBLE == txtLeftTitle.getVisibility()){
                skipIds.add(R.id.txt_title_left);
            }
        }
        return skipIds;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        MPermissionUtils.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    /**
     * 获取布局
     */
    public abstract int getLayoutRes();

    public abstract void onCreateActivity(@Nullable Bundle savedInstanceState);
}
