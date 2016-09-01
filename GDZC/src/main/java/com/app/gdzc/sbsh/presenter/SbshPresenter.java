package com.app.gdzc.sbsh.presenter;

import android.app.Activity;

import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.view.ISbshView;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class SbshPresenter extends BasePresenter<ISbshView, SbshModel> {
    private Activity mActivity;

    public SbshPresenter(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected SbshModel initModel() {
        return new SbshModel(mActivity);
    }
}
