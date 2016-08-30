package com.app.gdzc.sbdj.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseFragment;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.SbmkBean;
import com.app.gdzc.sbdj.adapter.FlAdapter;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.presenter.SbdjPresenter;
import com.pulltofresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class FlhFragment extends BaseFragment<IEmptyInterFace, SbdjModel, SbdjPresenter> implements IFlView {
    @InjectView(R.id.et_search)
    EditText mEditText;
    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private FlAdapter mFlAdapter;
    private List<SbmkBean> mList = new ArrayList<>();

    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fl);
        setTitle("分类号");
        showLeft();
        initView();
    }

    private void initView() {
        String flmc = getArguments().getString("flmc", "");
        SbmkBean sbmkBean = new SbmkBean();
        sbmkBean.setMc(flmc);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
        mFlAdapter = new FlAdapter(getActivity(), R.layout.adapter_item_rv, mList);
        mFlAdapter.setSbmkBean(sbmkBean);
        mRecyclerView.getRefreshableView().setAdapter(mFlAdapter);
    }

    @Override
    protected SbdjPresenter initPresenter() {
        return new SbdjPresenter(this);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public String getSearchContent() {
        return mEditText.getText().toString();
    }

    @Override
    public void onComplete() {

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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("确定");
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_right:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
