package com.app.gdzc.sbdj.view;

import com.app.gdzc.data.bean.FlhBean;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public interface IFlView {
    void onComplete();
    void showView(List<FlhBean> list);
}
