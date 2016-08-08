package com.app.gdzc.login.model;

import android.app.Activity;

import com.app.gdzc.baseui.bean.BaseBean;
import com.app.gdzc.net.HttpPath;
import com.app.gdzc.net.HttpPostParams;
import com.app.gdzc.net.HttpResponseUtils;
import com.app.gdzc.net.ResponseListener;

/**
 * Created by ASUS on 2016/8/6.
 */
public class LoginModel implements ILoginModel {
    private Activity mActivity;
    private ResponseListener mListener;

    public LoginModel(Activity mActivity, ResponseListener mListener) {
        this.mActivity = mActivity;
        this.mListener = mListener;
    }

    @Override
    public void login(String username, String password) {
        HttpResponseUtils.getInstance(mActivity).sendPost(HttpPath.LOGIN_TYPE, HttpPath.LOGIN, HttpPostParams.getInstance().BaseParams(), BaseBean.class, mListener, true);
    }
}
