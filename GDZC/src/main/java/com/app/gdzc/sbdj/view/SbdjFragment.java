package com.app.gdzc.sbdj.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseFragment;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.TsxxBean;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.recycleview.DividerLinearItemDecoration;
import com.app.gdzc.sbdj.SbdjActivity;
import com.app.gdzc.sbdj.adapter.SbdjAdapter;
import com.app.gdzc.sbdj.model.SbdjModel;
import com.app.gdzc.sbdj.presenter.SbdjPresenter;
import com.app.gdzc.utils.ENavigate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/30.
 */
public class SbdjFragment extends BaseFragment<IEmptyInterFace, SbdjModel, SbdjPresenter> implements ISbdjView {

    @InjectView(R.id.rv)
    RecyclerView mRecyclerView;

    private LinearLayoutManager mLinearLayoutManager;
    private SbdjAdapter mSbdjAdapter;
    private List<TsxxBean> mList = new ArrayList<>();
    private Map<String, String> SbdeMap = new HashMap<>();

    @Override
    protected void localCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbdj);
        setTitle("设备登记");
        showLeft();
        initView();
        mPresenter.getTsxx();
    }

    private void initView(){
        mLinearLayoutManager = new LinearLayoutManager(getContext());
        mLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.addItemDecoration(new DividerLinearItemDecoration(getContext(),
                DividerLinearItemDecoration.VERTICAL_LIST));
        mRecyclerView.setHasFixedSize(true);
        mSbdjAdapter = new SbdjAdapter(getActivity(), R.layout.layout_input_sbdj, mList);
        mRecyclerView.setAdapter(mSbdjAdapter);

        mSbdjAdapter.setFlhListener(new SbdjAdapter.FlhListener() {
            @Override
            public void onFlhClickListener() {
                Bundle bundle = new Bundle();
                bundle.putString(SbdjActivity.FRAGMENT, SbdjActivity.FLHFRAGMENT);
                bundle.putString("dwmc", mSbdjAdapter.dwmc);
                ENavigate.startActivity(getActivity(), SbdjActivity.class, bundle);
            }
        });

        mSbdjAdapter.setLydwListener(new SbdjAdapter.LydwListener() {
            @Override
            public void onLydwClickListener() {
                ENavigate.startSbdjActivity(getActivity(), SbdjActivity.DWFRAGMENT);
            }
        });
    }

    @Override
    protected SbdjPresenter initPresenter() {
        return new SbdjPresenter(this);
    }

    @Override
    public void showView(List<TsxxBean> list) {
        mList.addAll(list);
        mSbdjAdapter.initEtVal(mList.size());
        mSbdjAdapter.setListData(mList);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("完成");
        super.onCreateOptionsMenu(menu, inflater);
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
}
