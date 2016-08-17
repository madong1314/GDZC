package com.app.gdzc.base;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by 王少岩 on 2016/8/16.
 */
public class BaseApplication extends Application {
    private static BaseApplication mInstance;
    private RequestQueue mQueue;

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
    }

    public static BaseApplication getAppContext() {
        return mInstance;
    }
    // 返回volley队列
    public RequestQueue getRequestQueue() {
        return mQueue;
    }
}
