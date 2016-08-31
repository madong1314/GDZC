package com.app.gdzc.sbdj.model;

import android.app.Activity;

import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.data.source.local.SbmkDao;
import com.app.gdzc.data.source.local.TsxxDao;
import com.app.gdzc.net.ResponseListener;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class SbdjModel {
    private Activity mActivity;

    public SbdjModel(Activity activity) {
        mActivity = activity;
    }

    public void getTsxx(ResponseListener listener){
        new TsxxDao(mActivity).getData(TsxxDao.TSXX_TAG, listener);
    }

    public void createZJ(){

    }

    public void getDw(ResponseListener listener){
        new LydwDao(mActivity).getData(LydwDao.LYDWDAO_TAG, listener);
    }

    public void getFlh(int pageNo, String keyWord,ResponseListener listener){
        new SbmkDao(mActivity).getData(pageNo, keyWord, listener);
    }
}
