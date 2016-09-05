package com.app.gdzc.sbsh.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.recycleview.DividerLinearItemDecoration;
import com.app.gdzc.recycleview.OnItemClickListener;
import com.app.gdzc.sbsh.adapter.ZjAdapter;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.presenter.SbshPresenter;
import com.app.gdzc.utils.ENavigate;
import com.pulltofresh.PullToRefreshBase;
import com.pulltofresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class SbshActivity extends BaseActivity<IEmptyInterFace, SbshModel, SbshPresenter> implements ISbshView {
    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private ZjAdapter mAdapter;
    private List<ZJBean> mList = new ArrayList<>();
    private int pageNo = 1;
    private ZJBean mZJBean;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.layout_rv);
        setTitle("设备审核");
        showLeft();
        initView();
        mPresenter.getData(pageNo);
    }

    private void initView() {
        mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
        mRecyclerView.getRefreshableView().addItemDecoration(new DividerLinearItemDecoration(this, DividerLinearItemDecoration.VERTICAL_LIST));
        mAdapter = new ZjAdapter(this, R.layout.adapter_item_sbsh, mList);
        mRecyclerView.getRefreshableView().setAdapter(mAdapter);
        mAdapter.setShListener(new ZjAdapter.shListener() {
            @Override
            public void onShListener(ZJBean zjBean) {
                zjBean.setCs("1");
                mPresenter.shenHe(zjBean);
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener<ZJBean>() {

            @Override
            public void onItemClick(ViewGroup parent, View view, ZJBean zjBean, int position) {
                mZJBean = zjBean;
                Bundle bundle = new Bundle();
                bundle.putSerializable("zjBean", mZJBean);
                ENavigate.startActivityForResult(SbshActivity.this, SbshDetailsActivity.class, 1000, bundle);
            }

            @Override
            public boolean onItemLongClick(ViewGroup parent, View view, ZJBean zjBean, int position) {
                return false;
            }
        });
        mRecyclerView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<RecyclerView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pageNo = 1;
                mList.clear();
                mPresenter.getData(pageNo);
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<RecyclerView> refreshView) {
                pageNo++;
                mPresenter.getData(pageNo);
            }
        });
    }

    @Override
    protected SbshPresenter initPresenter() {
        return new SbshPresenter(this, this);
    }

    @Override
    public void showView(List<ZJBean> list) {
        mList.addAll(list);
        mAdapter.notifyDataSetChanged();
        mRecyclerView.onRefreshComplete();
    }

    @Override
    public void refreshView() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            mZJBean.setCs("1");
            refreshView();
        }
    }
}
