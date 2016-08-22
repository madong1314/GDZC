package com.app.gdzc.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

/**
 * Created by 王少岩 on 2016/8/16.
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private RequestQueue mQueue;
    private SharedPreferences mPreferences;
    private SharedPreferences mUserPreference;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mUserPreference = mInstance.getSharedPreferences("gdzc_user_pref", Context.MODE_PRIVATE);

        if (mQueue == null) {
            mQueue = Volley.newRequestQueue(this);
        }

        initImageLoader();

    }

    public static BaseApplication getAppContext() {
        return mInstance;
    }

    public SharedPreferences getPreferences() {
        return mPreferences;
    }

    public SharedPreferences getUserPreference() {
        return mUserPreference;
    }

    // 返回volley队列
    public RequestQueue getRequestQueue() {
        return mQueue;
    }

    /**
     * 初始化图画缓存
     */
    protected void initImageLoader() {
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
    }
}
