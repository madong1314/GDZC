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
import com.app.gdzc.data.bean.MkBean;
import com.app.gdzc.sbdj.adapter.MkAdapter;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.presenter.SbdjPresenter;
import com.pulltofresh.PullToRefreshBase;
import com.pulltofresh.PullToRefreshRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/9/1.
 */
public class MkFragment extends BaseFragment<IEmptyInterFace, SbdjModel, SbdjPresenter> implements IMkView {
    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;
    LinearLayoutManager mLinearLayoutManager;
    MkAdapter mMkAdapter;
    List<MkBean> mList = new ArrayList<>();
    String title;

    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.layout_rv);
        showLeft();
        title = getArguments().getString("title", "");
        switch (title) {
            case "使用方向":
                mPresenter.getSyfx();
                break;
            case "经费科目":
                mPresenter.getJfkm();
                break;
            case "设备来源":
                mPresenter.getSbly();
                break;
            case "现状":
                mPresenter.getXz();
                break;
        }
        setTitle(title);
        initView();
    }

    private void initView() {
        String code = getArguments().getString("code", "");
        String value = getArguments().getString("value", "");
        MkBean mkBean = new MkBean();
        mkBean.setId(code);
        mkBean.setNr(value);
        mRecyclerView.setMode(PullToRefreshBase.Mode.DISABLED);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
        mMkAdapter = new MkAdapter(getActivity(), R.layout.adapter_item_rv, mList);
        mMkAdapter.setMkBean(mkBean);
        mRecyclerView.getRefreshableView().setAdapter(mMkAdapter);
    }

    @Override
    protected SbdjPresenter initPresenter() {
        return new SbdjPresenter(getActivity(), this);
    }

    @Override
    public void showView(List<MkBean> list) {
        mList.addAll(list);
        mMkAdapter.notifyDataSetChanged();
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
                Intent data = new Intent();
                data.putExtra("colName", title);
                data.putExtra("colCode", mMkAdapter.getMkBean().getId());
                data.putExtra("colValue", mMkAdapter.getMkBean().getNr().trim());
                getActivity().setResult(getActivity().RESULT_OK, data);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
