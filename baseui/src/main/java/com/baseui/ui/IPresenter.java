package com.baseui.ui;

/**
 * Created by 王少岩 on 2016/10/8.
 */

public interface IPresenter<V extends IView> {
    void attachView(V view);
    void start();
    void detachView();
}
