package com.app.gdzc.sbdj.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.recycleview.CommonAdapter;
import com.app.gdzc.recycleview.ViewHolder;

import java.util.List;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class SbdjAdapter extends CommonAdapter<TsxxBean> {
    private String[] etValArr;
    private FlhListener mFlhListener;
    private LydwListener mLydwListener;
    public String dwmc;

    public SbdjAdapter(Context context, int layoutId, List<TsxxBean> datas) {
        super(context, layoutId, datas);
    }

    @Override
    public void convert(final ViewHolder holder, TsxxBean tsxxBean) {
        holder.setText(R.id.tv_label, tsxxBean.getShowContent());
        holder.setHintText(R.id.et_input, tsxxBean.getHintContent());
        holder.setHintText(R.id.tv_input, tsxxBean.getHintContent());
        holder.setText(R.id.et_input, etValArr[holder.mPosition]);
        holder.setVisible(R.id.tv_input, false);

        if (tsxxBean.getCanBeNull().equals("1")) {
            holder.setVisible(R.id.tv_canbenull, true);
        } else {
            holder.setVisible(R.id.tv_canbenull, false);
        }

        EditText et = holder.getView(R.id.et_input);
        TextView tv = holder.getView(R.id.tv_input);
        switch (tsxxBean.getColNameEng()) {
            case "flh":
                et.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mFlhListener.onFlhClickListener();
                    }
                });
                break;
            case "lydwh":
                dwmc = tv.getText().toString();
                et.setVisibility(View.GONE);
                tv.setVisibility(View.VISIBLE);
                tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mLydwListener.onLydwClickListener();
                    }
                });
                break;
            default:
                et.setVisibility(View.VISIBLE);
                tv.setVisibility(View.GONE);
                et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        etValArr[holder.mPosition] = s.toString();
                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                break;
        }

    }

    public void initEtVal(int size) {
        etValArr = new String[size];
    }

    public interface FlhListener{
        void onFlhClickListener();
    }

    public interface LydwListener{
        void onLydwClickListener();
    }

    public void setFlhListener(FlhListener flhListener) {
        mFlhListener = flhListener;
    }

    public void setLydwListener(LydwListener lydwListener) {
        mLydwListener = lydwListener;
    }
}
