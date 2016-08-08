package com.app.gdzc.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;

import com.app.gdzc.BuildConfig;
import com.app.gdzc.baseui.BaseApplication;

import java.util.List;

/**
 * @author huiyh
 *
 * 判断APK状态
 */
public class AppState {

    /**
     * <font color=red>需要使用同一的keystory<font/>
     */
	private static final int SIGNATURE_CODE_DEBUG = 197583638,
                             SIGNATURE_CODE_RELEASE = -624722122;

    private static int signatureCode;
	
	static{
		initAppState();
	}

    /**
     * 开发测试(根据BuildConfig)
     * 用于判断是否打开测试功能
     * @return
     */
    public static boolean isDeveloping(){
        return BuildConfig.DEBUG;
    }
    /**
     * 正式发布(根据签名)
     * 用于判断签名等
     * @return
     */
    public static boolean isReleased(){
        if(signatureCode == 0){
            initAppState();
        }
        //正式版签名为负,开发版签名为正
        return signatureCode < 0;
    }
    /**
     * 篡改
     * @return
     */
    public static boolean isTampered(){
        if(signatureCode == 0 ){
            initAppState();
        }
        return (signatureCode == SIGNATURE_CODE_DEBUG || signatureCode == SIGNATURE_CODE_RELEASE);
    }


    /**
	 * 初始化App签名Hash值
	 * */
	private static void initAppState() {
		try {
			Context context = getAppContext();
			PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
			Signature sigs = packageInfo.signatures[0];
            signatureCode =  sigs.hashCode();
		} catch (Exception e) {
            signatureCode = 0;
			handleException(e);
		}
	}
	
	/**
     *  获取AppConteaxt
     * */
	private static Context getAppContext() {
		return BaseApplication.getAppContext();
	}

	/**
	 * 捕获异常的处理
	 */
	private static void handleException(Exception e) {
		// 内容可以为空 , 也可替换为相应的异常处理方法
		ExceptionHandler.handle(e);
	}
	public static boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		// device

		ActivityManager activityManager = (ActivityManager) getAppContext().getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = getAppContext().getPackageName();

		List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}

		return false;
	}
}

