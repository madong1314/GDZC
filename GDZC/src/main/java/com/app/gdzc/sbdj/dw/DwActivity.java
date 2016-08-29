package com.app.gdzc.sbdj.dw;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.pulltofresh.PullToRefreshRecyclerView;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/26.
 */
public class DwActivity extends BaseActivity {
    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;

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
        String dwmc = getIntent().getExtras().getString("dwmc");
        LydwBean lydwBean = new LydwBean();
        lydwBean.setDwmc(dwmc);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
        mDwAdapter = new DwAdapter(this, R.layout.adapter_item_rv, new LydwDao(this).getData());
        mDwAdapter.setLydwBean(lydwBean);
        mRecyclerView.getRefreshableView().setAdapter(mDwAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("确定");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_right:
                Intent data = new Intent();
                data.putExtra("colName", "领用单位号");
                data.putExtra("colCode", mDwAdapter.getLydwBean().getDwbh());
                data.putExtra("colValue", mDwAdapter.getLydwBean().getDwmc());
                setResult(RESULT_OK, data);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
