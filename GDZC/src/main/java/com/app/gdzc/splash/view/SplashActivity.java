package com.app.gdzc.splash.view;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.login.view.LoginActivity;
import com.app.gdzc.splash.contract.SplashContract;
import com.app.gdzc.splash.presenter.SplashPresenter;
import com.app.gdzc.utils.ENavigate;

import butterknife.InjectView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class SplashActivity extends BaseActivity implements SplashContract.View, View.OnClickListener {
    @InjectView(R.id.tv_go_main)
    TextView mTextView;
    private SplashContract.Presenter mPresenter;
    private SplashPresenter.MyCount mCount;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
        setOnclickListener(this, mTextView);
        new SplashPresenter(this);
        mCount = mPresenter.getCountTimer();
        mCount.start();
    }

    @Override
    public void setMainText(String mainText) {
        mTextView.setText(mainText);
    }

    @Override
    public void startMainActivity() {
        ENavigate.startActivity(this, LoginActivity.class);
        finish();
    }

    @Override
    public void startLoginActivity() {
        ENavigate.startActivity(this, LoginActivity.class);
        finish();
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_go_main:
                mCount.cancel();
                startLoginActivity();
            break;
        }
    }

    //屏蔽返回键的代码:
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_HOME:
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_CALL:
            case KeyEvent.KEYCODE_SYM:
            case KeyEvent.KEYCODE_VOLUME_DOWN:
            case KeyEvent.KEYCODE_VOLUME_UP:
            case KeyEvent.KEYCODE_STAR:
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
