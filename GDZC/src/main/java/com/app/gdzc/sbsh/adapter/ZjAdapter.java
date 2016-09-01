package com.app.gdzc.sbsh.adapter;

import android.content.Context;

import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.recycleview.CommonAdapter;
import com.app.gdzc.recycleview.ViewHolder;

import java.util.List;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class ZjAdapter extends CommonAdapter<ZJBean> {
    public ZjAdapter(Context context, int layoutId, List<ZJBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, ZJBean zjBean) {

    }
}
