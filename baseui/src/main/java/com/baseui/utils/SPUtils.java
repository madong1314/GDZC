package com.baseui.utils;


import com.baseui.ui.BaseApplication;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class SPUtils {
    public static final String kUser_login = "login_flag";
    public static final String kUser_token = "token";
    public static final String kUser_account = "account";
    public static final String kUser_password = "password";
    public static final String kUser_remember = "remember";

    public static boolean isLogin() {
        return BaseApplication.getAppContext().getUserPreference().getBoolean(kUser_login, false);
    }

    public static void setLoginStatus(boolean isLogin){
        BaseApplication.getAppContext().getUserPreference().edit().putBoolean(kUser_login, isLogin).commit();
    }

    public static String getUserToken() {
        return BaseApplication.getAppContext().getUserPreference().getString(kUser_token, "");
    }

    public static void setUserToken(String userToken) {
        BaseApplication.getAppContext().getUserPreference().edit().putString(kUser_token, userToken).commit();
    }

    public static void setUserAccount(String account){
        BaseApplication.getAppContext().getUserPreference().edit().putString(kUser_account, account).commit();
    }

    public static String getUserAccount(){
        return BaseApplication.getAppContext().getUserPreference().getString(kUser_account, "");
    }

    public static void setUserPassword(String password){
        BaseApplication.getAppContext().getUserPreference().edit().putString(kUser_password, password).commit();
    }

    public static String getUserPassword(){
        return BaseApplication.getAppContext().getUserPreference().getString(kUser_password, "");
    }

    public static boolean isRemember() {
        return BaseApplication.getAppContext().getUserPreference().getBoolean(kUser_remember, false);
    }

    public static void setRemember(boolean remember){
        BaseApplication.getAppContext().getUserPreference().edit().putBoolean(kUser_remember, remember).commit();
    }

    /**
     * 用户登录，保持必要的数据
     */
    public static void onLogin(String token, String account, String password, boolean remember) {
        setUserToken(token);
        setUserAccount(account);
        setUserPassword(password);
        setRemember(remember);
        setLoginStatus(true);
    }
}
