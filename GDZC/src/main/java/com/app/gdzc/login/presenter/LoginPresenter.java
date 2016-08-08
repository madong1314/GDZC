package com.app.gdzc.login.presenter;

import android.app.Activity;
import android.text.TextUtils;
import android.widget.Toast;

import com.app.gdzc.MainActivity;
import com.app.gdzc.baseui.presenter.BasePresenter;
import com.app.gdzc.login.model.ILoginModel;
import com.app.gdzc.login.model.LoginModel;
import com.app.gdzc.login.view.ILoginView;
import com.app.gdzc.net.HttpPath;
import com.app.gdzc.net.ResponseListener;
import com.app.gdzc.utils.ENavigate;
import com.app.gdzc.utils.Utils;

import org.json.JSONException;

/**
 * Created by ASUS on 2016/8/6.
 */
public class LoginPresenter extends BasePresenter<ILoginView, ILoginModel> implements ResponseListener {
    private Activity mActivity;

    public LoginPresenter(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected ILoginModel generateModel() {
        return new LoginModel(mActivity, this);
    }

    public void login(){
       if (TextUtils.isEmpty(mView.getUserName())){
           Toast.makeText(mActivity,"请输入用户名",Toast.LENGTH_SHORT).show();
           return;
       }
        if (TextUtils.isEmpty(mView.getPassWord())){
            Toast.makeText(mActivity,"请输入密码",Toast.LENGTH_SHORT).show();
            return;
        }
        mModel.login(mView.getUserName(), mView.getPassWord());
    }

    @Override
    public void requestCompleted(int tag, Object response) throws JSONException {
        switch (tag){
            case HttpPath.LOGIN_TYPE:
                ENavigate.startActivity(mActivity, MainActivity.class);
                mActivity.finish();
                break;
        }
    }

    @Override
    public void requestError(int tag, String error) {
        Utils.showToast(mActivity, error);
    }
}
