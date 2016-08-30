package com.app.gdzc.sbdj.view;

import com.app.gdzc.base.IBaseView;
import com.app.gdzc.data.bean.TsxxBean;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public interface ISbdjView extends IBaseView {
    void showView(List<TsxxBean> list);
}
