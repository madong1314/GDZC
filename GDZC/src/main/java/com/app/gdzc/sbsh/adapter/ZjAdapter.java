package com.app.gdzc.sbsh.adapter;

import android.app.Activity;
import android.view.View;

import com.app.gdzc.R;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.recycleview.CommonAdapter;
import com.app.gdzc.recycleview.ViewHolder;

import java.util.List;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class ZjAdapter extends CommonAdapter<ZJBean> {
    private LydwDao mLydwDao;

    private shListener mShListener;

    public ZjAdapter(Activity activity, int layoutId, List<ZJBean> datas){
        super(activity, layoutId, datas);
        mLydwDao = new LydwDao(activity);
    }

    @Override
    public void convert(ViewHolder holder, final ZJBean zjBean) {
        holder.setText(R.id.tv_lydw, mLydwDao.getMcByBh(zjBean.getLydwh()));
        holder.setText(R.id.tv_yqmc, zjBean.getYqmc());
        holder.setText(R.id.tv_dj, zjBean.getDj());
        holder.setText(R.id.tv_xh, zjBean.getXh());
        holder.setVisible(R.id.tv_shenhe, zjBean.getCs().equals("1")?false:true);
        holder.setVisible(R.id.tv_yishenhe, zjBean.getCs().equals("1")?true:false);
        holder.setOnClickListener(R.id.tv_shenhe, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mShListener != null){
                    mShListener.onShListener(zjBean);
                }
            }
        });
    }

    public interface shListener{
        void onShListener(ZJBean zjBean);
    }

    public void setShListener(shListener shListener){
        mShListener = shListener;
    }
}
