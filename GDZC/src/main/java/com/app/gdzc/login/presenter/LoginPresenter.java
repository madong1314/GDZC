package com.app.gdzc.login.presenter;

import android.text.TextUtils;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.data.source.local.LoginDao;
import com.app.gdzc.login.bean.LoginBean;
import com.app.gdzc.login.model.LoginModel;
import com.app.gdzc.login.view.ILoginView;
import com.app.gdzc.net.ResponseListener;
import com.app.gdzc.utils.SPUtils;
import com.app.gdzc.utils.Utils;

import org.json.JSONException;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class LoginPresenter extends BasePresenter<ILoginView, LoginModel> implements ResponseListener<List<LoginBean>> {

    public void login() {
        if (TextUtils.isEmpty(mView.getUserName())) {
            Utils.showToast(BaseApplication.getAppContext(), BaseApplication.getAppContext().getString(R.string.empty_username));
            return;
        }
        if (TextUtils.isEmpty(mView.getPassWord())) {
            Utils.showToast(BaseApplication.getAppContext(), BaseApplication.getAppContext().getString(R.string.empty_password));
            return;
        }

        //创建loginBean
        LoginBean loginBean = new LoginBean();
        loginBean.setUserName(mView.getUserName());
        loginBean.setPassWord(mView.getPassWord());

        //根据输入的用户名和密码查询数据库
        mView.showDialog();
        mModel.login(loginBean, this);
    }

    @Override
    protected LoginModel initModel() {
        return new LoginModel(BaseApplication.getAppContext());
    }

    @Override
    public void requestCompleted(String tag, List<LoginBean> list) throws JSONException {
        mView.hideDialog();
        switch (tag) {
            case LoginDao.LOGIN_TAG:
                if (list.size() > 0) {
                    //保存用户名、密码
                    SPUtils.setUserName(mView.getUserName());
                    SPUtils.setRembPwd(mView.isRembPwd());
                    //保存记住密码
                    if (mView.isRembPwd()) SPUtils.setPassWord(mView.getPassWord());
                    SPUtils.setIsLogin(true);
                    //跳转到首页
                    mView.startMainActivity();
                }
                break;
        }
    }

    @Override
    public void requestError(String tag, String error) {
        mView.hideDialog();
        switch (tag) {
            case LoginDao.LOGIN_TAG:
                Utils.showToast(BaseApplication.getAppContext(), error);
                break;
        }
    }
}
