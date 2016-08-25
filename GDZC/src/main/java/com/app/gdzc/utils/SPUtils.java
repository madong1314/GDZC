package com.app.gdzc.utils;

import com.app.gdzc.base.BaseApplication;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class SPUtils {
    private static final String USER_NAME = "user_name";
    private static final String PASS_WORD = "pass_word";
    private static final String REMB_PWD = "remb_pwd";
    private static final String IS_LOGIN = "is_login";

    //保存、获取用户名
    public static void setUserName(String userName){
        BaseApplication.getAppContext().getUserPreference().edit().putString(USER_NAME, userName).commit();
    }

    public static String getUserName(){
        return BaseApplication.getAppContext().getUserPreference().getString(USER_NAME, "");
    }

    //保存、获取密码
    public static void setPassWord(String passWord){
        BaseApplication.getAppContext().getUserPreference().edit().putString(PASS_WORD, passWord).commit();
    }

    public static String getPassWord() {
        return BaseApplication.getAppContext().getUserPreference().getString(PASS_WORD, "");
    }

    //记住密码
    public static void setRembPwd(boolean isRembPwd){
        BaseApplication.getAppContext().getUserPreference().edit().putBoolean(REMB_PWD, isRembPwd).commit();
    }

    public static boolean isRembPwd() {
        return BaseApplication.getAppContext().getUserPreference().getBoolean(REMB_PWD, false);
    }

    //判断登录状态
    public static void setIsLogin(boolean isLogin){
        BaseApplication.getAppContext().getUserPreference().edit().putBoolean(IS_LOGIN, isLogin).commit();
    }

    public static boolean isLogin(){
        return BaseApplication.getAppContext().getUserPreference().getBoolean(IS_LOGIN, false);
    }
}
