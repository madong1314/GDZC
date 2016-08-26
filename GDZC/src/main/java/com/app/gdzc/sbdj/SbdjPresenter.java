package com.app.gdzc.sbdj;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.DataSource;
import com.app.gdzc.data.source.local.TsxxDao;
import com.app.gdzc.data.source.local.ZJDao;
import com.app.gdzc.utils.Utils;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjPresenter implements SbdjContract.Presenter, DataSource.Callback {
    private SbdjContract.SbdjView mSbdjView;
    private TsxxDao mTsxxDao;
    private ZJDao mZJDao;
    private final int tag_tsxxDao = 0;
    private final int tag_zjDao = 1;

    public SbdjPresenter(SbdjContract.SbdjView sbdjView) {
        mSbdjView = checkNotNull(sbdjView, "loginView can not be null");
        mSbdjView.setPresenter(this);
        mTsxxDao = new TsxxDao(BaseApplication.getAppContext());
        mZJDao = new ZJDao(BaseApplication.getAppContext());
    }

    @Override
    public void start() {
        mTsxxDao.getData(tag_tsxxDao, this);
    }

    @Override
    public void onComplete(int tag, Object object) {
        switch (tag) {
            case tag_tsxxDao:
                List<TsxxBean> list = (List<TsxxBean>) object;
                mSbdjView.showView(list);
                break;
            case tag_zjDao:
                Utils.showToast(BaseApplication.getAppContext(), "保存成功");
                break;
        }
    }

    @Override
    public void onError(int tag, String error) {
        Utils.showToast(BaseApplication.getAppContext(), error);
    }

    @Override
    public void saveSbdj(ZJBean zjBean) {
        mZJDao.createZJ(tag_zjDao, zjBean, this);
    }
}
