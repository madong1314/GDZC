package com.app.gdzc.sbdj;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.recycleview.OnItemClickListener;
import com.app.gdzc.utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjActivity extends BaseActivity implements SbdjContract.SbdjView {

    @InjectView(R.id.llayout_root)
    LinearLayout mLayoutRoot;

    Map<String, String> SbdeMap = new HashMap<>();
    private List<TsxxBean> mList;

    private SbdjContract.Presenter mPresenter;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbdj);
        setTitle("设备登记");
        showLeft();
        new SbdjPresenter(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showView(List<TsxxBean> list) {
        mList = list;
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(final TsxxBean tsxxBean : list){
            View view = inflater.inflate(R.layout.layout_input_sbdj, null);
            TextView tv = (TextView) view.findViewById(R.id.tv_label);
            EditText et_input = (EditText) view.findViewById(R.id.et_input);
            TextView tv_input = (TextView) view.findViewById(R.id.tv_input);
            TextView canbenull = (TextView) view.findViewById(R.id.tv_canbenull);
            tv.setText(tsxxBean.getShowContent());
            if(tsxxBean.getColNameEng().equals("lydwh")){
                et_input.setVisibility(View.GONE);
                tv_input.setVisibility(View.VISIBLE);
                tv_input.setHint(tsxxBean.getHintContent());
                tv_input.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showDw(v, tsxxBean);
                    }
                });
            }else{
                et_input.setVisibility(View.VISIBLE);
                tv_input.setVisibility(View.GONE);
                et_input.setHint(tsxxBean.getHintContent());
                et_input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {}

                    @Override
                    public void afterTextChanged(Editable s) {
                        SbdeMap.remove(tsxxBean.getShowContent());
                        SbdeMap.put(tsxxBean.getShowContent(), s.toString());
                    }
                });
            }
            canbenull.setVisibility(tsxxBean.getCanBeNull().equals("1")?View.VISIBLE:View.GONE);
            mLayoutRoot.addView(view);
        }

    }

    @Override
    public void setPresenter(SbdjContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("完成");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_right:
                if(ZJBean.isCorrect(SbdeMap, mList)){
                    ZJBean zjBean = ZJBean.mapToZJBean(SbdeMap);
                }
            break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showDw(final View v, final TsxxBean tsxxBean){
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.layout_rv, null);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.rv);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        SbdjDwAdapter adapter = new SbdjDwAdapter(this, R.layout.adapter_item_dw, new LydwDao(this).getData());
        recyclerView.setAdapter(adapter);
        final Dialog dialog = Utils.showBottomDialog(this, view);
        adapter.setOnItemClickListener(new OnItemClickListener<LydwBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, LydwBean lydwBean, int position) {
                ((TextView)v).setText(lydwBean.getDwmc());
                SbdeMap.remove(tsxxBean.getShowContent());
                SbdeMap.put(tsxxBean.getShowContent(), lydwBean.getDwbh());
                dialog.dismiss();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, LydwBean lydwBean, int position) {
                return false;
            }
        });
    }
}
