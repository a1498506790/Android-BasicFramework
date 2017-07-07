package com.airsaid.android_basicframework.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseActivity;
import com.airsaid.android_basicframework.constants.AppConstants;
import com.airsaid.android_basicframework.utils.SPUtils;

import butterknife.BindView;

/**
 * @author Airsaid
 * @github https://github.com/airsaid
 * @date 2017/5/22
 * @desc 引导页 Activity
 */
public class IndexActivity extends BaseActivity {

    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private int[] drawables = {R.mipmap.ic_splash, R.mipmap.ic_splash, R.mipmap.ic_splash};
    private WindowManager mWindowManager;
    private int mCurrentItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setSlideable(false);
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.activity_index;
    }

    @Override
    public void onCreateActivity(@Nullable Bundle savedInstanceState) {
        initView();
        mWindowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        SPUtils.setSP(mContext, AppConstants.KEY_IS_FIRST_LOGIN, false);
    }

    private void initView() {
        View[] pageViews = new View[drawables.length];
        for (int i = 0; i < pageViews.length; i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(drawables[i]);
            pageViews[i] = imageView;
        }
        mViewPager.setAdapter(new ViewPagerAdapter(pageViews));

        // 设置触摸监听
        mViewPager.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float endX;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = motionEvent.getX();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = motionEvent.getX();
                        // 获取屏幕的宽度
                        Point size = new Point();
                        mWindowManager.getDefaultDisplay().getSize(size);
                        int width = size.x;

                        // 判断是否到了最后一页并且是否是向左滑动，并且滑动距离是否符合屏幕宽度的4分之一
                        if (mCurrentItem == (drawables.length - 1) && startX - endX >= (width / 4)) {
                            // 跳转到主页
                            startActivity(new Intent(mContext, MainActivity.class));
                            finish();
                        }
                        break;
                }
                return false;
            }
        });

        // 设置滑动监听
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                mCurrentItem = position;
            }

            @Override
            public void onPageSelected(int position) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public class ViewPagerAdapter extends PagerAdapter{

        private View[] pagerViews;

        public ViewPagerAdapter(View[] pagerViews){
            this.pagerViews = pagerViews;
        }

        @Override
        public int getCount() {
            return pagerViews.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(pagerViews[position]);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(pagerViews[position]);
            return pagerViews[position];
        }
    }
}
