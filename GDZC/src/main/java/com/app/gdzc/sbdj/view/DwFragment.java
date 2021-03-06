package com.app.gdzc.sbdj.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseFragment;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.LydwBean;
import com.app.gdzc.sbdj.adapter.DwAdapter;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.presenter.SbdjPresenter;
import com.pulltofresh.PullToRefreshBase;
import com.pulltofresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class DwFragment extends BaseFragment<IEmptyInterFace, SbdjModel, SbdjPresenter> implements IDwView {

    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    DwAdapter mDwAdapter;
    List<LydwBean> mList = new ArrayList<>();

    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_rv);
        setTitle("领用单位");
        showLeft();
        initView();
        mPresenter.getDw();
    }

    private void initView(){
        String code = getArguments().getString("code", "");
        String value = getArguments().getString("value", "");
        LydwBean lydwBean = new LydwBean();
        lydwBean.setDwbh(code);
        lydwBean.setDwmc(value);
        mRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
        mDwAdapter = new DwAdapter(getActivity(), R.layout.adapter_item_rv, mList);
        mDwAdapter.setLydwBean(lydwBean);
        mRecyclerView.getRefreshableView().setAdapter(mDwAdapter);
    }

    @Override
    protected SbdjPresenter initPresenter() {
        return new SbdjPresenter(getActivity(), this);
    }

    @Override
    public void showView(List<LydwBean> list) {
        mList.addAll(list);
        mDwAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("确定");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_right:
                Intent data = new Intent();
                data.putExtra("colName", "领用单位号");
                data.putExtra("colCode", mDwAdapter.getLydwBean().getDwbh());
                data.putExtra("colValue", mDwAdapter.getLydwBean().getDwmc());
                getActivity().setResult(getActivity().RESULT_OK, data);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
