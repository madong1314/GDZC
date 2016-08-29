package com.app.gdzc.sbdj.fl;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.data.bean.SbmkBean;
import com.pulltofresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/29.
 */
public class FlActivity extends BaseActivity implements FlContract.FlView {
    @InjectView(R.id.et_search)
    EditText mEditText;
    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private FlAdapter mFlAdapter;
    private List<SbmkBean> mList = new ArrayList<>();

    private FlContract.Presenter mPresenter;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fl);
        setTitle("分类号");
        showLeft();
        new FlPresenter(this);
        initView();
    }

    private void initView() {
        String flmc = getIntent().getExtras().getString("flmc");
        SbmkBean sbmkBean = new SbmkBean();
        sbmkBean.setMc(flmc);
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
        mFlAdapter = new FlAdapter(this, R.layout.adapter_item_rv, mList);
        mFlAdapter.setSbmkBean(sbmkBean);
        mRecyclerView.getRefreshableView().setAdapter(mFlAdapter);
        mRecyclerView.setOnRefreshListener(mPresenter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("确定");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_right:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getSearchContent() {
        return mEditText.getText().toString();
    }

    @Override
    public void onComplete() {
        mRecyclerView.onRefreshComplete();
    }

    @Override
    public void showView(List<SbmkBean> list) {
        mList.addAll(list);
        mFlAdapter.notifyDataSetChanged();
    }

    @Override
    public void clearData() {
        mList.clear();
    }

    @Override
    public void setPresenter(FlContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
