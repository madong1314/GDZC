package com.app.gdzc.login;

import android.text.TextUtils;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.data.bean.LoginBean;
import com.app.gdzc.data.source.local.LoginDao;
import com.app.gdzc.utils.SPUtils;
import com.app.gdzc.utils.Utils;

import static com.google.common.base.Preconditions.checkNotNull;
/**
 * Created by 王少岩 on 2016/8/22.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.LoginView mLoginView;

    public LoginPresenter(LoginContract.LoginView loginView) {
        mLoginView = checkNotNull(loginView, "loginView can not be null");
        mLoginView.setPresenter(this);
    }

    @Override
    public void start() {
        mLoginView.setUserName(SPUtils.getUserName());
        mLoginView.setPassWord(SPUtils.getPassWord());
        mLoginView.setRembPwd(SPUtils.isRembPwd());
    }

    @Override
    public void login() {
        if(TextUtils.isEmpty(mLoginView.getUserName())){
            Utils.showToast(BaseApplication.getAppContext(), "用户名不能为空");
            return;
        }
        if(TextUtils.isEmpty(mLoginView.getPassWord())){
            Utils.showToast(BaseApplication.getAppContext(), "密码不能为空");
            return;
        }
        SPUtils.setUserName(mLoginView.getUserName());
        SPUtils.setRembPwd(mLoginView.isRembPwd());
        if(mLoginView.isRembPwd()) SPUtils.setPassWord(mLoginView.getPassWord());

        LoginBean loginBean = new LoginBean();
        loginBean.setUserName(mLoginView.getUserName());
        loginBean.setPassWord(mLoginView.getPassWord());
        LoginDao loginDao = new LoginDao(BaseApplication.getAppContext());
        if(loginDao.queryUser(loginBean)) mLoginView.startMainActivity();
    }
}
