package com.app.gdzc.login.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;

import butterknife.InjectView;

public class LoginActivity extends BaseActivity {
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
    }
}
