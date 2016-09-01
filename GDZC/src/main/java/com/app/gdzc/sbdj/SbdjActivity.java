package com.app.gdzc.sbdj;

import android.content.Intent;
import android.os.Bundle;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.sbdj.view.DwFragment;
import com.app.gdzc.sbdj.view.FlhFragment;
import com.app.gdzc.sbdj.view.MkFragment;
import com.app.gdzc.sbdj.view.SbdjFragment;
import com.app.gdzc.utils.ActivityUtils;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjActivity extends BaseActivity {

    private SbdjFragment mSbdjFragment;
    private DwFragment mDwFragment;
    private FlhFragment mFlhFragment;
    private MkFragment mMkFragment;

    public static final String FRAGMENT = "fragment";
    public static final String SBDJFRAGMENT = "SbdjFragment";
    public static final String DWFRAGMENT = "DwFragment";
    public static final String FLHFRAGMENT = "FlhFragment";
    public static final String MKFRAGMENT = "MKFRAGMENT";

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base);
        String fragment = getIntent().getExtras().getString(FRAGMENT);
        Bundle bundle = new Bundle();
        switch (fragment) {
            case SBDJFRAGMENT:
                mSbdjFragment = new SbdjFragment();
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mSbdjFragment, R.id.contentFrame);
                break;
            case DWFRAGMENT:
                mDwFragment = new DwFragment();
                bundle.putString("code", getIntent().getExtras().getString("code"));
                bundle.putString("value", getIntent().getExtras().getString("value"));
                mDwFragment.setArguments(bundle);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mDwFragment, R.id.contentFrame);
                break;
            case FLHFRAGMENT:
                mFlhFragment = new FlhFragment();
                bundle.putString("code", getIntent().getExtras().getString("code"));
                bundle.putString("value", getIntent().getExtras().getString("value"));
                mFlhFragment.setArguments(bundle);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mFlhFragment, R.id.contentFrame);
                break;
            case MKFRAGMENT:
                mMkFragment = new MkFragment();
                bundle.putString("title", getIntent().getExtras().getString("title"));
                bundle.putString("code", getIntent().getExtras().getString("code"));
                bundle.putString("value", getIntent().getExtras().getString("value"));
                mMkFragment.setArguments(bundle);
                ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mMkFragment, R.id.contentFrame);
                break;
        }
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) return;
        mSbdjFragment.onActivityResult(requestCode, resultCode, data);
    }
}
