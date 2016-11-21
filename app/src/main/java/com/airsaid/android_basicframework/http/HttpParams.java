package com.airsaid.android_basicframework.http;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhouyou on 2016/10/18.
 * Class desc: 请求参数封装类。
 */
public class HttpParams {

	private static HttpParams mInstance;
	private static Map<String, String> mParams;

	public static HttpParams getIns() {
		if(mParams == null){
			mParams = new HashMap<>();
		}else{
			mParams.clear();
		}

		if (mInstance == null) {
			mInstance = new HttpParams();
		}
		return mInstance;
	}

	/**
	 * 存储用户id和code
	 * @param uid	用户ID
	 * @param ucode	用户code
     */
	public void putUserInfo(String uid, String ucode){
		mParams.put("uid", uid);
		mParams.put("ucode", ucode);
	}

	/**
	 * 请求不带用户信息的分页数据
	 * @param page	页码
     */
	public Map<String, String> putPage(int page){
		mParams.put("page", String.valueOf(page));
		return mParams;
	}


}
