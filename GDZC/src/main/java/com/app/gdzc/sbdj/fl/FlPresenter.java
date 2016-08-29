package com.app.gdzc.sbdj.fl;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.data.bean.SbmkBean;
import com.app.gdzc.data.source.DataSource;
import com.app.gdzc.data.source.local.SbmkDao;
import com.pulltofresh.PullToRefreshBase;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/29.
 */
public class FlPresenter implements FlContract.Presenter, DataSource.Callback<List<SbmkBean>> {
    private FlContract.FlView mFlView;
    private SbmkDao mSbmkDao;
    private int pageNo = 1;

    public FlPresenter(FlContract.FlView flView) {
        mFlView = flView;
        mFlView.setPresenter(this);
        mSbmkDao = new SbmkDao(BaseApplication.getAppContext());
    }

    @Override
    public void start() {
        getData();
    }

    @Override
    public void getData() {
        mSbmkDao.getData(1, 1, mFlView.getSearchContent(), this);
    }

    @Override
    public void onComplete(int tag, List<SbmkBean> list) {
        mFlView.showView(list);
        mFlView.onComplete();
    }

    @Override
    public void onError(int tag, String error) {
        mFlView.onComplete();
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pageNo = 1;
        mFlView.clearData();
        mSbmkDao.getData(1, pageNo, mFlView.getSearchContent(), this);
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        pageNo++;
        mSbmkDao.getData(1, pageNo, mFlView.getSearchContent(), this);
    }
}
