package com.app.gdzc.sbsh.presenter;

import android.app.Activity;

import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.local.ZJDao;
import com.app.gdzc.net.ResponseListener;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.view.ISbshView;
import com.app.gdzc.utils.Utils;

import org.json.JSONException;

import java.util.List;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class SbshPresenter extends BasePresenter<IEmptyInterFace, SbshModel> implements ResponseListener {
    private Activity mActivity;
    private ISbshView mSbshView;
    public SbshPresenter(Activity activity, ISbshView sbshView) {
        mActivity = activity;
        mSbshView = sbshView;
    }

    public SbshPresenter(Activity activity) {
        mActivity = activity;
    }

    @Override
    protected SbshModel initModel() {
        return new SbshModel(mActivity);
    }

    public void getData(int pageNo){
        mModel.getData(pageNo, this);
    }

    public void shenHe(ZJBean zjBean){
        mModel.shenHeZJ(zjBean, this);
    }

    @Override
    public void requestCompleted(String tag, Object response) throws JSONException {
        switch (tag){
            case ZJDao.ZJ_SEARCH:
                mSbshView.showView((List<ZJBean>) response);
            break;
            case ZJDao.ZJ_SHENHE:
                if(mSbshView != null){
                    mSbshView.refreshView();
                }else {
                    mActivity.setResult(Activity.RESULT_OK);
                    mActivity.finish();
                }
            break;
        }
    }

    @Override
    public void requestError(String tag, String error) {
        Utils.showToast(mActivity, error);
    }
}
