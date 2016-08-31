package com.app.gdzc.login.view;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public interface ILoginView{
    String getUserName();
    String getPassWord();
    void setUserName(String userName);
    void setPassWord(String passWord);
    void setRembPwd(boolean isRembPwd);
    boolean isRembPwd();
    void startMainActivity();
}
