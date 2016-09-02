package com.app.gdzc.sbsh.presenter;

import android.app.Activity;

import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.net.ResponseListener;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.view.ISbshView;
import com.app.gdzc.utils.Utils;

import org.json.JSONException;

import java.util.List;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class SbshPresenter extends BasePresenter<ISbshView, SbshModel> implements ResponseListener<List<ZJBean>> {
    private Activity mActivity;

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

    @Override
    public void requestCompleted(String tag, List<ZJBean> list) throws JSONException {
        mView.showView(list);
    }

    @Override
    public void requestError(String tag, String error) {
        Utils.showToast(mActivity, error);
    }
}
