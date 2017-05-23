package com.airsaid.android_basicframework.http.gson;

import android.text.TextUtils;

import com.airsaid.android_basicframework.R;
import com.airsaid.android_basicframework.base.BaseBean;
import com.airsaid.android_basicframework.utils.LogUtils;
import com.airsaid.android_basicframework.utils.UiUtils;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * @author Airsaid
 * @github https://github.com/airsaid
 * @date 2017/5/22
 * @desc 自己实现 Retrofit 的 json 解析过程。
 * 主要是判断返回状态码是否是成功，成功后再去解析 Json，避免了返回数据类型不一致导致的解析异常问题。
 */
public class CustomResponseConverter<T> implements Converter<ResponseBody, T> {

    public static final String TAG = "CustomResponseConverter";

    private final Gson mGson;
    private final TypeAdapter<T> mAdapter;

    public CustomResponseConverter(Gson gson, TypeAdapter<T> adapter) {
        this.mGson = gson;
        this.mAdapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        if(value == null) return null;

        String jsonString = value.string();
        LogUtils.json(TAG, jsonString);

        if(TextUtils.isEmpty(jsonString)) return null;

        try {
            JSONObject json = new JSONObject(jsonString);
            int status = json.getInt(BaseBean.STATUS);
            String msg = json.getString(BaseBean.MSG);
            if (0 == status) {// 请求成功，返回数据
                return mAdapter.fromJson(jsonString);
            } else {
                throw new RuntimeException(msg);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            throw new RuntimeException(UiUtils.getString(R.string.request_fail));
        } finally {
            value.close();
        }
    }

}
