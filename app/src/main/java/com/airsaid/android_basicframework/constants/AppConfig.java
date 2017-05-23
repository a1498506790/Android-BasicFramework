package com.airsaid.android_basicframework.constants;

import android.os.Environment;

import java.io.File;


/**
 * @author Airsaid
 * @github https://github.com/airsaid
 * @date 2017/5/22
 * @desc  APP 配置信息类，用与存储配置 APP 中的配置。
 */
public class AppConfig {

    /** SD卡目录 */
    public static final String SDCARD_PATH = Environment.getExternalStorageDirectory() + File.separator;

    /** 缓存目录名字 */
    public static final String CACHE_DIR_NAME = "cache";

    /** 缓存目录路径 */
    public static final String CACHE_PATH = SDCARD_PATH.concat(CACHE_DIR_NAME.concat(File.separator));

    /** 文件缓存目录路径 */
    public static final String CACHE_FILE_PATH = CACHE_PATH.concat("file");

    /** 图片缓存目录路径 */
    public static final String CACHE_IMAGE_PATH =  CACHE_PATH.concat("image");

    /** 缓存大小 */
    public static final int CACHE_SIZE = 104857600;// 100MB

    /** 缓存时间 */
    public static final int CACHE_DATE = 60 * 60 * 24;// 一天总秒数

}
