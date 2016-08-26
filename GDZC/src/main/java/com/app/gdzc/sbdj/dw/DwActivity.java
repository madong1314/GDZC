package com.app.gdzc.sbdj.dw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.recycleview.OnItemClickListener;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class DwActivity extends BaseActivity {
    @InjectView(R.id.rv)
    RecyclerView mRecyclerView;

    LinearLayoutManager mLinearLayoutManager;
    DwAdapter mDwAdapter;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_rv);
        setTitle("领用单位");
        showLeft();
        initView();
    }

    private void initView(){
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mDwAdapter = new DwAdapter(this, R.layout.adapter_item_dw, new LydwDao(this).getData());
        mRecyclerView.setAdapter(mDwAdapter);
        mDwAdapter.setOnItemClickListener(new OnItemClickListener<LydwBean>() {
            @Override
            public void onItemClick(ViewGroup parent, View view, LydwBean lydwBean, int position) {
                Intent data = new Intent();
                data.putExtra("colName", "领用单位号");
                data.putExtra("colCode", lydwBean.getDwbh());
                data.putExtra("colValue", lydwBean.getDwmc());
                setResult(RESULT_OK, data);
                finish();
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, LydwBean lydwBean, int position) {
                return false;
            }
        });
    }
}
