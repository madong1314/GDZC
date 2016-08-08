package com.app.gdzc.splash.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.splash.presenter.SplashPresenter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class SplashActivity extends AppCompatActivity implements ISplashView, View.OnClickListener {
    @InjectView(R.id.rlayout_root)
    RelativeLayout rootRlayout;
    @InjectView(R.id.image_splash)
    ImageView splashImg;
    @InjectView(R.id.tv_go_main)
    TextView goMainTxt;
    @InjectView(R.id.tv_go_adver)
    TextView goAdverTxt;

    private SplashPresenter.MyCount timerCount;
    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setBackgroundDrawableResource(R.mipmap.image_spalsh);
        setContentView(R.layout.activity_splash);
        ButterKnife.inject(this);
        goMainTxt.setOnClickListener(this);
        mSplashPresenter = new SplashPresenter(this, this);
        timerCount = mSplashPresenter.getMyCount(3000, 1000);
        timerCount.start();
    }

    @Override
    public void setMainText(String mainText) {
        rootRlayout.setVisibility(View.VISIBLE);
        goMainTxt.setVisibility(View.VISIBLE);
        goMainTxt.setText(mainText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_go_main:
                timerCount.cancel();
                mSplashPresenter.startMainActivity();
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
