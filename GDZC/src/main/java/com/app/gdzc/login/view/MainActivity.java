package com.app.gdzc.login.view;

import android.os.Bundle;
import android.view.Menu;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.utils.Utils;

public class MainActivity extends BaseActivity {

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setTitle("登录");
    }

    @Override
    protected boolean onLeftClick() {
        Utils.showToast(MainActivity.this, "返回");
        return true;
    }

    @Override
    protected void onRigthClick() {
        Utils.showToast(MainActivity.this, "分享");
    }
}
