package com.app.gdzc.login.model;

import android.app.Activity;

import com.app.gdzc.data.source.local.LoginDao;
import com.app.gdzc.login.bean.LoginBean;
import com.app.gdzc.net.ResponseListener;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class LoginModel {
    private Activity mActivity;

    public LoginModel(Activity activity) {
        mActivity = activity;
    }

    public void login(LoginBean loginBean, ResponseListener listener){
        new LoginDao(mActivity).getData(LoginDao.LOGIN_TAG, loginBean, listener);
    }
}
