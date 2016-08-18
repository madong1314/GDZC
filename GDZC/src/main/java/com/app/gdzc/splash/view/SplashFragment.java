package com.app.gdzc.splash.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.gdzc.login.view.MainActivity;
import com.app.gdzc.R;
import com.app.gdzc.splash.contract.SplashContract;
import com.app.gdzc.utils.ENavigate;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by 王少岩 on 2016/8/16.
 */
public class SplashFragment extends Fragment implements SplashContract.View, View.OnClickListener {
    @InjectView(R.id.tv_go_main)
    TextView mTextView;

    private SplashContract.Presenter mPresenter;
    public SplashFragment() {
    }

    public static SplashFragment newInstance(){
        return new SplashFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.activity_splash, container, false);
        ButterKnife.inject(this, root);
        initView();
        return root;
    }

    private void initView(){
        mTextView.setOnClickListener(this);
    }

    @Override
    public void setMainText(String mainText) {
        mTextView.setText(mainText);
    }

    @Override
    public void startMainActivity() {
        ENavigate.startActivity(getActivity(), MainActivity.class);
        getActivity().finish();
    }

    @Override
    public void startLoginActivity() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_go_main:
                startMainActivity();
            break;
        }
    }

    @Override
    public void setPresenter(SplashContract.Presenter presenter) {
        mPresenter = checkNotNull(presenter);
    }
}
