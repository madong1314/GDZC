package com.app.gdzc.sbdj.fl;

import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.BaseView;
import com.app.gdzc.data.bean.SbmkBean;
import com.pulltofresh.PullToRefreshBase;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/29.
 */
public class FlContract {
    interface FlView extends BaseView<Presenter>{
        String getSearchContent();
        void onComplete();
        void showView(List<SbmkBean> list);
        void clearData();
    }
    interface Presenter extends BasePresenter, PullToRefreshBase.OnRefreshListener{
        void getData();
    }
}
