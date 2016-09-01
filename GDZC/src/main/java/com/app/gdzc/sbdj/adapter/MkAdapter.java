package com.app.gdzc.sbdj.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;

import com.app.gdzc.R;
import com.app.gdzc.data.bean.MkBean;
import com.app.gdzc.recycleview.CommonAdapter;
import com.app.gdzc.recycleview.ViewHolder;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class MkAdapter extends CommonAdapter<MkBean> {

    private MkBean mMkBean = new MkBean();

    public MkAdapter(Context context, int layoutId, List<MkBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(ViewHolder holder, final MkBean mkBean) {
        holder.setText(R.id.tv_name, mkBean.getNr().trim());
        holder.setText(R.id.tv_code, mkBean.getId());
        CheckBox checkBox = holder.getView(R.id.cb);
        if (mkBean.getId().equals(mMkBean.getId())) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
        holder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMkBean = mkBean;
                notifyDataSetChanged();
            }
        });
    }

    public MkBean getMkBean() {
        return mMkBean;
    }

    public void setMkBean(MkBean mkBean) {
        mMkBean = mkBean;
    }
}
