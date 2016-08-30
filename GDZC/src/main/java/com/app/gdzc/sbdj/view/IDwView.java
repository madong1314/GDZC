package com.app.gdzc.sbdj.view;

import com.app.gdzc.base.IBaseView;
import com.app.gdzc.data.bean.LydwBean;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public interface IDwView extends IBaseView {
    void showView(List<LydwBean> list);
}
