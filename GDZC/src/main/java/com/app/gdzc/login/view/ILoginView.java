package com.app.gdzc.login.view;

import com.app.gdzc.base.IBaseView;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public interface ILoginView extends IBaseView {
    String getUserName();
    String getPassWord();
    void setUserName(String userName);
    void setPassWord(String passWord);
    void setRembPwd(boolean isRembPwd);
    boolean isRembPwd();
    void startMainActivity();
}
