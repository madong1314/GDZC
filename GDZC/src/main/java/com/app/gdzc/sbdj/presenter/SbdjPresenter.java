package com.app.gdzc.sbdj.presenter;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.data.source.local.TsxxDao;
import com.app.gdzc.data.source.local.ZJDao;
import com.app.gdzc.net.ResponseListener;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.view.IDwView;
import com.app.gdzc.sbdj.view.IFlView;
import com.app.gdzc.sbdj.view.ISbdjView;
import com.app.gdzc.utils.Utils;

import org.json.JSONException;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjPresenter extends BasePresenter<IEmptyInterFace, SbdjModel> implements ResponseListener {

    private ISbdjView mSbdjView;
    private IDwView mDwView;
    private IFlView mFlView;

    public SbdjPresenter(ISbdjView sbdjView) {
        mSbdjView = sbdjView;
    }

    public SbdjPresenter(IDwView dwView) {
        mDwView = dwView;
    }

    public SbdjPresenter(IFlView flView) {
        mFlView = flView;
    }

    @Override
    protected SbdjModel initModel() {
        return new SbdjModel(BaseApplication.getAppContext());
    }

    @Override
    public void requestCompleted(String tag, Object response) throws JSONException {
        switch (tag) {
            case TsxxDao.TSXX_TAG: {
                List<TsxxBean> list = (List<TsxxBean>) response;
                mSbdjView.showView(list);
                break;
            }
            case LydwDao.LYDWDAO_TAG: {
                List<LydwBean> list = (List<LydwBean>) response;
                mDwView.showView(list);
                break;
            }
            case ZJDao.ZJ_CREATE:
                Utils.showToast(BaseApplication.getAppContext(), "保存成功");
                break;
        }
    }

    @Override
    public void requestError(String tag, String error) {

    }

    public void getTsxx() {
        mModel.getTsxx(this);
    }

    public void createZJ() {
        mModel.createZJ();
    }

    public void getDw() {
        mModel.getDw(this);
    }
}