package com.app.gdzc.sbdj.presenter;

import android.app.Activity;

import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.data.bean.SbmkBean;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.data.source.local.SbmkDao;
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
                List<SbmkBean> list = (List<SbmkBean>) response;
                mFlView.showView(list);
                mFlView.onComplete();
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

    public void getFlh(int pageNo, String keyWord) {
        mModel.getFlh(pageNo, keyWord, this);
    }
}
