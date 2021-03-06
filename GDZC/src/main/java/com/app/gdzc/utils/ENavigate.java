package com.app.gdzc.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.app.gdzc.sbdj.SbdjActivity;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class ENavigate {
    /**
     * 普通跳转
     * @param activity
     * @param activityClass
     */
    public static void startActivity(Activity activity, Class<? extends Activity> activityClass){
        Intent intent= new Intent(activity,activityClass);
        activity.startActivity(intent);
    }

    public static void startActivity(Activity activity, Class<? extends Activity> activityClass, Bundle bundle){
        Intent intent= new Intent(activity,activityClass);
        intent.putExtras(bundle);
        activity.startActivity(intent);
    }

    /**
     * 带返回值的跳转
     * @param activity
     * @param activityClass
     * @param requestCode
     */
    public static void startActivityForResult(Activity activity, Class<? extends Activity> activityClass, int requestCode){
        Intent intent= new Intent(activity,activityClass);
        activity.startActivityForResult(intent,requestCode);
    }

    public static void startActivityForResult(Activity activity, Class<? extends Activity> activityClass, int requestCode, Bundle bundle){
        Intent intent= new Intent(activity,activityClass);
        intent.putExtras(bundle);
        activity.startActivityForResult(intent,requestCode);
    }

    public static void startSbdjActivity(Activity activity, String tag){
        Bundle bundle = new Bundle();
        bundle.putString(SbdjActivity.FRAGMENT, tag);
        startActivity(activity, SbdjActivity.class, bundle);
    }
}
