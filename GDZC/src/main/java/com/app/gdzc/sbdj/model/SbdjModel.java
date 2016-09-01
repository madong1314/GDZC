package com.app.gdzc.sbdj.model;

import android.app.Activity;

import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.data.source.local.MkDao;
import com.app.gdzc.data.source.local.SbmkDao;
import com.app.gdzc.data.source.local.TsxxDao;
import com.app.gdzc.data.source.local.ZJDao;
import com.app.gdzc.net.ResponseListener;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class SbdjModel {
    private Activity mActivity;

    public SbdjModel(Activity activity) {
        mActivity = activity;
    }

    public void getTsxx(ResponseListener listener) {
        new TsxxDao(mActivity).getData(TsxxDao.TSXX_TAG, listener);
    }

    public void createZJ(ZJBean zjBean, ResponseListener listener) {
        new ZJDao(mActivity).createZJ(zjBean, listener);
    }

    public void getDw(ResponseListener listener) {
        new LydwDao(mActivity).getData(LydwDao.LYDWDAO_TAG, listener);
    }

    public void getFlh(int pageNo, String keyWord, ResponseListener listener) {
        new SbmkDao(mActivity).getData(pageNo, keyWord, listener);
    }

    public void getXz(ResponseListener listener) {
        new MkDao(mActivity).getXz(listener);
    }

    public void getSbly(ResponseListener listener) {
        new MkDao(mActivity).getSbly(listener);
    }

    public void getSyfx(ResponseListener listener) {
        new MkDao(mActivity).getSyfx(listener);
    }

    public void getJfkm(ResponseListener listener) {
        new MkDao(mActivity).getJfkm(listener);
    }
}
