package com.app.gdzc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.gdzc.R;

import butterknife.ButterKnife;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public abstract class BaseFragment<V, M, P extends BasePresenter<V, M>> extends Fragment {

    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected P mPresenter;
    private View mView;
    private FrameLayout mFrameLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mPresenter = initPresenter();
        if (mPresenter != null) mPresenter.attach((V) this);
        setHasOptionsMenu(true);
        if (mView == null) {
            mView = inflater.inflate(R.layout.fragment_base, container, false);
            mFrameLayout = (FrameLayout) findViewById(R.id.contentFrame);
            mToolbar = (Toolbar) findViewById(R.id.toolbar);
            ((AppCompatActivity)getActivity()).setSupportActionBar(mToolbar);
            mTitle = (TextView) findViewById(R.id.tv_title);
            localCreateView(savedInstanceState);
        } else {
            ButterKnife.inject(this, mView);
        }
        return mView;
    }

    protected abstract void localCreateView(Bundle savedInstanceState);

    //实例化presenter
    protected abstract P initPresenter();

    public void setContentView(int resid) {
        View view = View.inflate(getContext(), resid, mFrameLayout);
        view.setClickable(true);
        ButterKnife.inject(this, mView);
    }

    protected View findViewById(int id) {
        return mView.findViewById(id);
    }

    public void setTitle(CharSequence title) {
        mToolbar.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        mTitle.setText(title);
    }

    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    protected void showLeft(){
        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setOnclickListener(View.OnClickListener listener, View... views) {
        if (listener != null) {
            int len = views.length;
            for (int i = 0; i < len; i++) {
                View view = views[i];
                if (view != null) {
                    view.setOnClickListener(listener);
                }
            }
        }
    }
}
