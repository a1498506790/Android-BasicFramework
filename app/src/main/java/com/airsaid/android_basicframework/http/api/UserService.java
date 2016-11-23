package com.airsaid.android_basicframework.http.api;


import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.bean.TestBean;
import com.airsaid.android_basicframework.bean.ListBean;
import com.airsaid.android_basicframework.constants.ApiConstant;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by zhouyou on 2016/6/24.
 * Class desc: user service interface
 */
public interface UserService {

    @GET(ApiConstant.TEST_URL)
    Call<BaseBean<ListBean<TestBean>>> testData(@QueryMap() Map<String, String> params);

}
