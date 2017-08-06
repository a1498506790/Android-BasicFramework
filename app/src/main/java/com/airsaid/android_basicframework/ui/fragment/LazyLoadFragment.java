package com.airsaid.android_basicframework.ui.fragment;
;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.callback.LazyLoadCallback;

/**
 * @author Airsaid
 * @Date 2017/8/6 17:15
 * @Blog http://blog.csdn.net/airsaid
 * @Desc 懒加载示例 Fragment
 */
public class LazyLoadFragment extends Fragment implements LazyLoadCallback {

    private TextView mTextView;
    private ProgressBar mLoading;

    public static LazyLoadFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("content", content);
        LazyLoadFragment fragment = new LazyLoadFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lazy_laod, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mTextView = (TextView) view.findViewById(R.id.textView);
        mLoading = (ProgressBar) view.findViewById(R.id.loading);
        mTextView.setText(getArguments().getString("content"));
    }

    @Override
    public void onLoad() {
        mTextView.setVisibility(View.GONE);
        mLoading.setVisibility(View.VISIBLE);
        mTextView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoading.setVisibility(View.GONE);
                mTextView.setVisibility(View.VISIBLE);
            }
        }, 1000);
    }
}
