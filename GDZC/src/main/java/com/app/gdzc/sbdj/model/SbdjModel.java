package com.app.gdzc.sbdj.model;

import android.content.Context;

import com.app.gdzc.data.source.local.TsxxDao;
import com.app.gdzc.net.ResponseListener;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class SbdjModel {
    private Context mContext;

    public SbdjModel(Context context) {
        mContext = context;
    }

    public void getTsxx(ResponseListener listener){
        new TsxxDao(mContext).getData(TsxxDao.TSXX_TAG, listener);
    }

    public void createZJ(){

    }
}
