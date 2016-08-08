package com.app.gdzc.login.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.gdzc.R;
import com.app.gdzc.baseui.BaseNavActivity;
import com.app.gdzc.login.model.ILoginModel;
import com.app.gdzc.login.presenter.LoginPresenter;

import butterknife.InjectView;

/**
 * Created by ASUS on 2016/8/6.
 */
public class LoginActivity extends BaseNavActivity<ILoginView, ILoginModel, LoginPresenter> implements ILoginView, View.OnClickListener {

    @InjectView(R.id.et_username)
    EditText mEtUserName;
    @InjectView(R.id.et_password)
    EditText mEtPassWord;
    @InjectView(R.id.bt_login)
    Button mButton;

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setMyContentView(R.layout.activity_login);
        setLeftText("");
        setTitle("登录");
        setRightText("");
        setOnclickListener(this, mButton);
    }

    @Override
    public String getUserName() {
        return mEtUserName.getText().toString();
    }

    @Override
    public String getPassWord() {
        return mEtPassWord.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_login:
                mPresenter.login();
                break;
        }
    }
}
