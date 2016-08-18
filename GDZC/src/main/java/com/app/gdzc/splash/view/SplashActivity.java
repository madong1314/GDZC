package com.app.gdzc.splash.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;

import com.app.gdzc.R;
import com.app.gdzc.splash.presenter.SplashPresenter;
import com.app.gdzc.utils.ActivityUtils;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_activity_base);
        findViewById(R.id.contentFrame).setBackgroundResource(R.mipmap.image_spalsh);
        SplashFragment splashFragment = (SplashFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if(splashFragment == null){
            splashFragment = SplashFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), splashFragment, R.id.contentFrame);
        }
        new SplashPresenter(splashFragment);
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
