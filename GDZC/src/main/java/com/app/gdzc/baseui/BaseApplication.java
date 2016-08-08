package com.app.gdzc.baseui;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.gdzc.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private static SharedPreferences mPreference;
    private RequestQueue mQueue;
    public static DisplayImageOptions imgoptions;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        /*
         * 初始化Volley
         */
        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(this);
        }
        /*
         *  初始化默认SharedPreferences
         */
        mPreference = PreferenceManager.getDefaultSharedPreferences(this);
        /*
         *  初始化图画缓存
         */
        initImageLoader();
    }

    public static BaseApplication getAppContext() {
        return mInstance;
    }
    // 返回volley队列
    public RequestQueue getRequestQueue() {
        return mQueue;
    }
    public static SharedPreferences getAppPreferences(){
        return mPreference;
    }
    /**
     * 初始化图画缓存
     */
    protected void initImageLoader(){
        //defalut cache
        DisplayImageOptions needCacheOption = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .build();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .denyCacheImageMultipleSizesInMemory()
                .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                .memoryCacheSize(2 * 1024 * 1024)
                .discCacheSize(50 * 1024 * 1024)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)
                .discCacheFileCount(100)
                .writeDebugLogs()
                .defaultDisplayImageOptions(needCacheOption) // cache
                .build();
        ImageLoader.getInstance().init(config);

        imgoptions = new DisplayImageOptions.Builder()
//				.showImageOnLoading(R.drawable.loading)
                .showImageForEmptyUri(R.mipmap.image_default)
                .showImageOnFail(R.mipmap.image_default)
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.EXACTLY_STRETCHED)
                .considerExifParams(true).bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }
}
