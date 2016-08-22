package com.app.gdzc.login;

import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.BaseView;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class LoginContract {
    interface LoginView extends BaseView<Presenter>{
        String getUserName();
        String getPassWord();
        void setUserName(String userName);
        void setPassWord(String passWord);
        void setRembPwd(boolean isRembPwd);
        boolean isRembPwd();
        void startMainActivity();
    }

    interface Presenter extends BasePresenter {
        void login();
    }
}
