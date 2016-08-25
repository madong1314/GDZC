package com.app.gdzc.splash;

import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.login.LoginActivity;
import com.app.gdzc.main.MainActivity;
import com.app.gdzc.utils.ENavigate;
import com.app.gdzc.utils.SPUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class SplashActivity extends Activity implements View.OnClickListener {
    @InjectView(R.id.tv_go_main)
    TextView mTextView;
    private MyCount mCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        mTextView.setOnClickListener(this);
        mCount = new MyCount(5000, 1000);
        mCount.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_go_main:
                mCount.cancel();
                if(SPUtils.isLogin())
                    startMainActivity();
                else
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

    public void startMainActivity() {
        ENavigate.startActivity(this, MainActivity.class);
        finish();
    }

    public void startLoginActivity() {
        ENavigate.startActivity(this, LoginActivity.class);
        finish();
    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mTextView.setText("跳过0s");
            if(SPUtils.isLogin())
                startMainActivity();
            else
                startLoginActivity();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mTextView.setText("跳过" + millisUntilFinished / 1000 + "s");
        }
    }
}
