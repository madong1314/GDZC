package com.app.gdzc.sbdj;

import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.base.BaseView;
import com.app.gdzc.data.bean.TsxxBean;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjContract {
    interface SbdjView extends BaseView<Presenter>{
        void showView(List<TsxxBean> list);
    }

    interface Presenter extends BasePresenter{

    }
}
