package com.app.gdzc.sbdj.dw;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.app.gdzc.R;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.recycleview.CommonAdapter;
import com.app.gdzc.recycleview.ViewHolder;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class DwAdapter extends CommonAdapter<LydwBean> {

    private LydwBean mLydwBean = new LydwBean();

    public DwAdapter(Context context, int layoutId, List<LydwBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final LydwBean lydwBean) {
        holder.setText(R.id.tv_name, lydwBean.getDwmc());
        holder.setText(R.id.tv_code, lydwBean.getDwjm());
        CheckBox checkBox = holder.getView(R.id.cb);
        if (lydwBean.getDwmc().equals(mLydwBean.getDwmc())) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLydwBean = lydwBean;
                notifyDataSetChanged();
            }
        });
    }

    public void setLydwBean(LydwBean lydwBean){
        mLydwBean = lydwBean;
    }

    public LydwBean getLydwBean() {
        return mLydwBean;
    }
}
