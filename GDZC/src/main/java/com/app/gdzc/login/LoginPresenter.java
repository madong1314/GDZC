package com.app.gdzc.login;

import android.text.TextUtils;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.data.bean.LoginBean;
import com.app.gdzc.data.source.DataSource;
import com.app.gdzc.data.source.local.LoginDao;
import com.app.gdzc.utils.SPUtils;
import com.app.gdzc.utils.Utils;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class LoginPresenter implements LoginContract.Presenter, DataSource.Callback<List<LoginBean>> {

    private LoginContract.LoginView mLoginView;
    private LoginDao mLoginDao;

    public LoginPresenter(LoginContract.LoginView loginView) {
        mLoginView = checkNotNull(loginView, "loginView can not be null");
        mLoginView.setPresenter(this);
        mLoginDao = new LoginDao(BaseApplication.getAppContext());
    }

    @Override
    public void start() {
        mLoginView.setUserName(SPUtils.getUserName());
        mLoginView.setPassWord(SPUtils.getPassWord());
        mLoginView.setRembPwd(SPUtils.isRembPwd());
    }

    @Override
    public void login() {
        if (TextUtils.isEmpty(mLoginView.getUserName())) {
            Utils.showToast(BaseApplication.getAppContext(), BaseApplication.getAppContext().getString(R.string.empty_username));
            return;
        }
        if (TextUtils.isEmpty(mLoginView.getPassWord())) {
            Utils.showToast(BaseApplication.getAppContext(), BaseApplication.getAppContext().getString(R.string.empty_password));
            return;
        }

        //创建loginBean
        LoginBean loginBean = new LoginBean();
        loginBean.setUserName(mLoginView.getUserName());
        loginBean.setPassWord(mLoginView.getPassWord());

        //根据输入的用户名和密码查询数据库
        mLoginDao.getData(0, loginBean, this);
    }

    @Override
    public void onComplete(int tag, List<LoginBean> list) {
        switch (tag) {
            case 0:
                if (list.size() > 0) {
                    //保存用户名、密码
                    SPUtils.setUserName(mLoginView.getUserName());
                    SPUtils.setRembPwd(mLoginView.isRembPwd());
                    //保存记住密码
                    if (mLoginView.isRembPwd())
                        SPUtils.setPassWord(mLoginView.getPassWord());
                    SPUtils.setIsLogin(true);
                    //跳转到首页
                    mLoginView.startMainActivity();
                }
                break;
        }
    }

    @Override
    public void onError(int tag, String error) {
        switch (tag) {
            case 0:
                Utils.showToast(BaseApplication.getAppContext(), error);
                break;
        }
    }
}
