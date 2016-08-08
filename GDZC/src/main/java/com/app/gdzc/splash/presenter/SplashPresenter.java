package com.app.gdzc.splash.presenter;

import android.app.Activity;
import android.os.CountDownTimer;

import com.app.gdzc.MainActivity;
import com.app.gdzc.baseui.IEmptyInterface;
import com.app.gdzc.baseui.presenter.BasePresenter;
import com.app.gdzc.login.view.LoginActivity;
import com.app.gdzc.splash.view.ISplashView;
import com.app.gdzc.utils.ENavigate;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class SplashPresenter extends BasePresenter<ISplashView, IEmptyInterface> {
    private ISplashView mSplashView;
    private Activity mActivity;

    public SplashPresenter(ISplashView splashView, Activity activity) {
        mSplashView = splashView;
        mActivity = activity;
    }

    @Override
    protected IEmptyInterface generateModel() {
        return null;
    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }
        @Override
        public void onFinish() {
            mSplashView.setMainText("跳过0s");
            //startMainActivity();
            startLoginActivity();
        }
        @Override
        public void onTick(long millisUntilFinished) {
            mSplashView.setMainText("跳过"+millisUntilFinished/1000+"s");
        }
    }

    public MyCount getMyCount(long millisInFuture, long countDownInterval){
        return new MyCount(millisInFuture, countDownInterval);
    }

    public void startMainActivity(){
        ENavigate.startActivity(mActivity, MainActivity.class);
        mActivity.finish();
    }
    public void startLoginActivity(){
        ENavigate.startActivity(mActivity, LoginActivity.class);
        mActivity.finish();
    }
}
