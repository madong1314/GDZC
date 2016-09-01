package com.app.gdzc.sbdj.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseFragment;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.FlhBean;
import com.app.gdzc.sbdj.adapter.FlAdapter;
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
public class FlhFragment extends BaseFragment<IEmptyInterFace, SbdjModel, SbdjPresenter> implements IFlView, PullToRefreshBase.OnRefreshListener, TextView.OnEditorActionListener {
    @InjectView(R.id.et_search)
    EditText mEditText;
    @InjectView(R.id.rv)
    PullToRefreshRecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private FlAdapter mFlAdapter;
    private List<FlhBean> mList = new ArrayList<>();
    private int pageNo = 1;
    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_fl);
        setTitle("分类号");
        showLeft();
        initView();
        setListener();
        mPresenter.getFlh(pageNo, getSearchContent());
    }

    private void initView() {
        String code = getArguments().getString("code", "");
        String value = getArguments().getString("value", "");
        FlhBean sbmkBean = new FlhBean();
        sbmkBean.setFlh(code);
        sbmkBean.setMc(value);
        mLinearLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.getRefreshableView().setLayoutManager(mLinearLayoutManager);
        mRecyclerView.getRefreshableView().setHasFixedSize(true);
        mFlAdapter = new FlAdapter(getActivity(), R.layout.adapter_item_rv, mList);
        mFlAdapter.setSbmkBean(sbmkBean);
        mRecyclerView.getRefreshableView().setAdapter(mFlAdapter);
    }

    private void setListener(){
        mRecyclerView.setOnRefreshListener(this);
        mEditText.setOnEditorActionListener(this);
    }

    @Override
    protected SbdjPresenter initPresenter() {
        return new SbdjPresenter(getActivity(), this);
    }

    @Override
    public void showView(List<FlhBean> list) {
        if(pageNo == 1){
            mList.clear();
        }
        mList.addAll(list);
        mFlAdapter.notifyDataSetChanged();
    }

    public String getSearchContent() {
        return mEditText.getText().toString();
    }

    @Override
    public void onComplete() {
        mRecyclerView.onRefreshComplete();
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
                data.putExtra("colName", "分类号");
                data.putExtra("colCode", mFlAdapter.getSbmkBean().getFlh());
                data.putExtra("colValue", mFlAdapter.getSbmkBean().getMc().trim());
                getActivity().setResult(getActivity().RESULT_OK, data);
                getActivity().finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        pageNo = 1;
        mPresenter.getFlh(pageNo, getSearchContent());
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        pageNo++;
        mPresenter.getFlh(pageNo, getSearchContent());
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEND
                || actionId == EditorInfo.IME_ACTION_DONE
                || actionId == EditorInfo.IME_ACTION_SEARCH
                || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
            // 先隐藏键盘
            ((InputMethodManager) mEditText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
            pageNo = 1;
            mPresenter.getFlh(pageNo, getSearchContent());
        }
        return false;
    }
}
