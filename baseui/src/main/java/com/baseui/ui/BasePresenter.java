package com.baseui.ui;

/**
 * Created by 王少岩 on 2016/10/8.
 */

public abstract class BasePresenter<V extends IView, M extends IModel> implements IPresenter<V> {
    protected static final String TAG = "BasePresenter";
    protected V mView;
    protected M mModel;

    @Override
    public void attachView(V view) {
        mView = view;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }

    public M getModel() {
        return mModel;
    }
}
