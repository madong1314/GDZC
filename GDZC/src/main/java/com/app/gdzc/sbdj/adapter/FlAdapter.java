package com.app.gdzc.sbdj.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.app.gdzc.R;
import com.app.gdzc.data.bean.SbmkBean;
import com.app.gdzc.recycleview.CommonAdapter;
import com.app.gdzc.recycleview.ViewHolder;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/29.
 */
public class FlAdapter extends CommonAdapter<SbmkBean> {
    private SbmkBean mSbmkBean = new SbmkBean();

    public FlAdapter(Context context, int layoutId, List<SbmkBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final SbmkBean sbmkBean) {
        holder.setText(R.id.tv_name, sbmkBean.getMc().trim());
        holder.setText(R.id.tv_code, sbmkBean.getFlh());
        CheckBox checkBox = holder.getView(R.id.cb);
        if (sbmkBean.getFlh().equals(mSbmkBean.getFlh())) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSbmkBean = sbmkBean;
                notifyDataSetChanged();
            }
        });
    }

    public SbmkBean getSbmkBean() {
        return mSbmkBean;
    }

    public void setSbmkBean(SbmkBean sbmkBean) {
        mSbmkBean = sbmkBean;
    }
}
