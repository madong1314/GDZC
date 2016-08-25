package com.app.gdzc.sbdj;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.source.DataSource;
import com.app.gdzc.data.source.local.TsxxDao;
import com.app.gdzc.utils.Utils;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjPresenter implements SbdjContract.Presenter, DataSource.Callback<List<TsxxBean>> {
    private SbdjContract.SbdjView mSbdjView;
    private TsxxDao mTsxxDao;

    public SbdjPresenter(SbdjContract.SbdjView sbdjView) {
        mSbdjView = checkNotNull(sbdjView, "loginView can not be null");
        mSbdjView.setPresenter(this);
        mTsxxDao = new TsxxDao(BaseApplication.getAppContext());
    }

    @Override
    public void start() {
        mTsxxDao.getData(0, this);
    }

    @Override
    public void onComplete(int tag, List<TsxxBean> list) {
        mSbdjView.showView(list);
    }

    @Override
    public void onError(int tag, String error) {
        Utils.showToast(BaseApplication.getAppContext(), error);
    }
}
