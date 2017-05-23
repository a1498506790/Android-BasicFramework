package com.airsaid.android_basicframework.http.api;


import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.bean.TestBean;
import com.airsaid.android_basicframework.bean.ListBean;
import com.airsaid.android_basicframework.constants.Api;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * @author Airsaid
 * @github https://github.com/airsaid
 * @date 2017/5/22
 * @desc user service interface
 */
public interface UserService {

    @GET(Api.TEST_URL)
    Call<BaseBean<ListBean<TestBean>>> testData(@QueryMap() Map<String, String> params);

}
