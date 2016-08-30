package com.app.gdzc.login.view;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.login.model.LoginModel;
import com.app.gdzc.login.presenter.LoginPresenter;
import com.app.gdzc.main.MainActivity;
import com.app.gdzc.utils.ENavigate;
import com.app.gdzc.utils.Utils;

import butterknife.InjectView;

public class LoginActivity extends BaseActivity<ILoginView, LoginModel, LoginPresenter> implements ILoginView, View.OnClickListener {
    @InjectView(R.id.et_username)
    EditText mEtUserName;
    @InjectView(R.id.et_password)
    EditText mEtPassWord;
    @InjectView(R.id.tv_forget)
    TextView mTvForget;
    @InjectView(R.id.cb_remember)
    CheckBox mCbRemember;
    @InjectView(R.id.tv_register)
    TextView mTvRegister;
    @InjectView(R.id.tv_login)
    TextView mTvLogin;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_login);
        init();
    }

    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    private void init() {
        setOnclickListener(this, mTvLogin, mTvRegister, mTvForget);
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
    public void setUserName(String userName) {
        mEtUserName.setText(userName);
    }

    @Override
    public void setPassWord(String passWord) {
        mEtPassWord.setText(passWord);
    }

    @Override
    public void setRembPwd(boolean isRembPwd) {
        mCbRemember.setChecked(isRembPwd);
    }

    @Override
    public boolean isRembPwd() {
        return mCbRemember.isChecked();
    }

    @Override
    public void startMainActivity() {
        ENavigate.startActivity(this, MainActivity.class);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                mPresenter.login();
                break;
            case R.id.tv_register:
                break;
            case R.id.tv_forget:
                break;
        }
    }

    @Override
    public void showDialog() {
        Utils.showLoading(this);
    }

    @Override
    public void hideDialog() {
        Utils.hideLoading();
    }
}
