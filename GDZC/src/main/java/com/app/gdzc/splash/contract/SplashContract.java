package com.app.gdzc.splash.contract;

import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.BaseView;

/**
 * Created by 王少岩 on 2016/8/16.
 */
public interface SplashContract {
    interface View extends BaseView<Presenter>{
        void setMainText(String mainText);
        void startMainActivity();
        void startLoginActivity();

    }

    interface Presenter extends BasePresenter{
    }
}
