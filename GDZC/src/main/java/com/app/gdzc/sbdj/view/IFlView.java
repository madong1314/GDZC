package com.app.gdzc.sbdj.view;

import com.app.gdzc.base.IBaseView;
import com.app.gdzc.data.bean.SbmkBean;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public interface IFlView extends IBaseView {
    String getSearchContent();
    void onComplete();
    void showView(List<SbmkBean> list);
    void clearData();
}
