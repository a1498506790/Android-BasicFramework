package com.airsaid.android_basicframework.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Airsaid
 * @github https://github.com/airsaid
 * @date 2017/5/22
 * @desc 请求参数封装类
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
	 * 请求用户 id 和 code
	 * @param uid	用户 id
	 * @param ucode	用户 code
     */
	public Map<String, String> putUserInfo(String uid, String ucode){
		mParams.put("uid", uid);
		mParams.put("ucode", ucode);
		return mParams;
	}

	/**
	 * 请求不带用户信息的分页数据
	 * @param page	页码
	 */
	public Map<String, String> putPage(int page){
		mParams.put("page", String.valueOf(page));
		return mParams;
	}

	/**
	 * 请求带用户信息的分页数据
	 * @param page 页码
	 */
	public Map<String, String> putPage(String uid, String ucode, int page){
		putUserInfo(uid, ucode);
		mParams.put("page", String.valueOf(page));
		return mParams;
	}

}
