package com.baseui.ui;

import android.os.Bundle;

/**
 * Created by 王少岩 on 2016/10/8.
 */

public abstract class IViewActivity<P extends IPresenter> extends BaseActivity implements IView {
    protected P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = onLoadPresenter();
        getPresenter().attachView(this);
        initEventAndData();
        if (getPresenter() != null) {
            getPresenter().start();
        }
    }

    public P getPresenter() {
        return mPresenter;
    }

    @Override
    protected void onDestroy() {
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
        super.onDestroy();
    }

    protected abstract P onLoadPresenter();

    protected abstract void initViews(Bundle savedInstanceState);

    protected abstract void initEventAndData();
}
