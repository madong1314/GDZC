package com.app.gdzc.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

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
}
