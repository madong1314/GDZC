package com.app.gdzc.splash.presenter;

import android.os.CountDownTimer;

import com.app.gdzc.splash.contract.SplashContract;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class SplashPresenter implements SplashContract.Presenter {
    private SplashContract.View mSplashView;

    public SplashPresenter(SplashContract.View view) {
        mSplashView = checkNotNull(view, "mSplashView can not be null");
        mSplashView.setPresenter(this);
    }

    @Override
    public void start() {
        new MyCount(5000, 1000).start();
    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onFinish() {
            mSplashView.setMainText("跳过0s");
            mSplashView.startMainActivity();
        }

        @Override
        public void onTick(long millisUntilFinished) {
            mSplashView.setMainText("跳过" + millisUntilFinished / 1000 + "s");
        }
    }
}
