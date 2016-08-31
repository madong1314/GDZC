package com.app.gdzc.base;

import android.app.Activity;

/**
 * Created by 王少岩 on 2016/8/16.
 */
public abstract class BasePresenter<V, M> {
    protected Activity mActivity;
    protected V mView;
    protected M mModel;

    /**
     * 关联view
     *
     * @param mView
     */
    public void attach(V mView) {
        this.mView = mView;
        mModel = initModel();
    }

    //针对不同的presenter 生成对应的model
    protected abstract M initModel();

    //释放资源
    public void dettach() {}
}
