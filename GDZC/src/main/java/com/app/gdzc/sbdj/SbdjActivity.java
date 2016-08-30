package com.app.gdzc.sbdj;

import android.content.Intent;
import android.os.Bundle;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.base.BasePresenter;
import com.app.gdzc.sbdj.view.SbdjFragment;
import com.app.gdzc.utils.ActivityUtils;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public class SbdjActivity extends BaseActivity {

    private SbdjFragment mSbdjFragment;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_base);
        mSbdjFragment = new SbdjFragment();
        ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mSbdjFragment, R.id.contentFrame);
    }

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != RESULT_OK) return;
        switch (requestCode){
            case 1000:
            break;
        }
    }
}
