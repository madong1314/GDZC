package com.app.gdzc.login.model;

import android.content.Context;

import com.app.gdzc.data.source.local.LoginDao;
import com.app.gdzc.login.bean.LoginBean;
import com.app.gdzc.net.ResponseListener;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class LoginModel {
    private Context mContext;

    public LoginModel(Context context) {
        mContext = context;
    }

    public void login(LoginBean loginBean, ResponseListener listener){
        new LoginDao(mContext).getData(LoginDao.LOGIN_TAG, loginBean, listener);
    }
}
