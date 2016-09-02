package com.app.gdzc.sbsh.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.sbsh.adapter.ZjAdapter;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.presenter.SbshPresenter;
import com.pulltofresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class SbshActivity extends BaseActivity<ISbshView, SbshModel, SbshPresenter> implements ISbshView {
    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ZjAdapter mAdapter;
    private List<ZJBean> mList = new ArrayList<>();
    private int pageNo = 1;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_rv);
        setTitle("设备审核");
        showLeft();
        initView();
        mPresenter.getData(pageNo);
    }

    private void initView(){
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
    }

    @Override
    protected SbshPresenter initPresenter() {
        return new SbshPresenter(this);
    }

    @Override
    public void showView(List<ZJBean> list) {

    }
}
