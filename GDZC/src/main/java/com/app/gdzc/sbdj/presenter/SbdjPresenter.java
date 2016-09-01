package com.app.gdzc.sbdj.presenter;

import android.app.Activity;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.data.bean.FlhBean;
import com.app.gdzc.data.bean.MkBean;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.data.source.local.MkDao;
import com.app.gdzc.data.source.local.SbmkDao;
import com.app.gdzc.data.source.local.TsxxDao;
import com.app.gdzc.data.source.local.ZJDao;
import com.app.gdzc.net.ResponseListener;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.view.IDwView;
import com.app.gdzc.sbdj.view.IFlView;
import com.app.gdzc.sbdj.view.IMkView;
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
    private IMkView mMkView;

    public SbdjPresenter(Activity activity, ISbdjView sbdjView) {
        mActivity = activity;
        mSbdjView = sbdjView;
    }

    public SbdjPresenter(Activity activity, IDwView dwView) {
        mActivity = activity;
        mDwView = dwView;
    }

    public SbdjPresenter(Activity activity, IFlView flView) {
        mActivity = activity;
        mFlView = flView;
    }

    public SbdjPresenter(Activity activity, IMkView mkView) {
        mMkView = mkView;
        mActivity = activity;
    }

    @Override
    protected SbdjModel initModel() {
        return new SbdjModel(mActivity);
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
            case SbmkDao.SBMKDAO_TAG: {
                List<FlhBean> list = (List<FlhBean>) response;
                mFlView.showView(list);
                mFlView.onComplete();
                break;
            }
            case MkDao.MKDAO_TAG_SBLY:
            case MkDao.MKDAO_TAG_SYFX:
            case MkDao.MKDAO_TAG_JFKM:
            case MkDao.MKDAO_TAG_XZ: {
                List<MkBean> list = (List<MkBean>) response;
                mMkView.showView(list);
                break;
            }
            case ZJDao.ZJ_CREATE:
                Utils.showToast(BaseApplication.getAppContext(), "保存成功");
                mActivity.finish();
                break;
        }
    }

    @Override
    public void requestError(String tag, String error) {
        Utils.showToast(BaseApplication.getAppContext(), error);
    }

    public void getTsxx() {
        mModel.getTsxx(this);
    }

    public void createZJ(ZJBean zjBean) {
        mModel.createZJ(zjBean, this);
    }

    public void getDw() {
        mModel.getDw(this);
    }

    public void getFlh(int pageNo, String keyWord) {
        mModel.getFlh(pageNo, keyWord, this);
    }

    public void getXz() {
        mModel.getXz(this);
    }

    public void getSbly() {
        mModel.getSbly(this);
    }

    public void getSyfx() {
        mModel.getSyfx(this);
    }

    public void getJfkm() {
        mModel.getJfkm(this);
    }
}
