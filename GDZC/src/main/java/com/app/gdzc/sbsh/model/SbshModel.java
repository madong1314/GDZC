package com.app.gdzc.sbsh.model;

import android.app.Activity;

import com.app.gdzc.data.source.local.ZJDao;
import com.app.gdzc.net.ResponseListener;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class SbshModel {
    private Activity mActivity;

    public SbshModel(Activity activity) {
        mActivity = activity;
    }

    public void getData(int pageNo, ResponseListener listener){
        new ZJDao(mActivity).getData(pageNo, listener);
    }
}
