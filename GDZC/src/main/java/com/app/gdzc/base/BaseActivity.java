package com.app.gdzc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.app.gdzc.R;

import butterknife.ButterKnife;

/**
 * Created by 王少岩 on 2016/8/17.
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected FrameLayout mLayout;
    private Menu mMenu;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomerView(R.layout.layout_activity_base);
        localOnCreate(savedInstanceState);
    }

    protected void setCustomerView(int res) {
        super.setContentView(res);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mLayout = (FrameLayout) findViewById(R.id.contentFrame);
    }

    /**
     * 重写此方法而不再重写{@link  # onCreate(Bundle)};
     *
     * @param savedInstanceState
     */
    protected abstract void localOnCreate(Bundle savedInstanceState);

    @Override
    public void setContentView(int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID,
                mLayout, false);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        mLayout.addView(view);
        ButterKnife.inject(this);
    }

    @Override
    public void setTitle(CharSequence title) {
        mToolbar.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        mTitle.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    protected void showLeft(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.icon_back);
    }

    protected boolean onLeftClick() {
        return false;
    }

    protected void onRigthClick(){}

    protected void setRight(){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        mMenu = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (!onLeftClick()) onBackPressed();
                break;
            case R.id.action_right:
                onRigthClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
