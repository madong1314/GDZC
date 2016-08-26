package com.app.gdzc.sbdj.dw;

import android.content.Context;

import com.app.gdzc.R;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.recycleview.CommonAdapter;
import com.app.gdzc.recycleview.ViewHolder;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class DwAdapter extends CommonAdapter<LydwBean> {

    public DwAdapter(Context context, int layoutId, List<LydwBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, LydwBean lydwBean) {
        holder.setText(R.id.tv_dwmc, lydwBean.getDwmc());
        holder.setText(R.id.tv_dwjm, lydwBean.getDwjm());
    }
}
