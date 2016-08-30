package com.app.gdzc.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gdzc.R;
import com.app.gdzc.utils.StatusBarUtil;

import butterknife.ButterKnife;

/**
 * Created by 王少岩 on 2016/8/17.
 */
public abstract class BaseActivity<V, M, P extends BasePresenter<V, M> > extends AppCompatActivity {

    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected FrameLayout mLayout;
    private long mClickTime = 0l;
    private static int EXIT_TIMEOUT = 2500;
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter != null) mPresenter.attach((V) this);
        StatusBarUtil.transparencyBar(this);
        StatusBarUtil.setStatusBarColor(this,R.color.black);
        setCustomerView(R.layout.activity_base);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setOnclickListener(View.OnClickListener listener, View...views ){
        if(listener!=null){
            int len = views.length;
            for(int i=0;i<len;i++){
                View view = views[i];
                if(view != null){
                    view.setOnClickListener(listener);
                }
            }
        }
    }

    private boolean isRoot() {
        return isTaskRoot() || (getParent() != null && getParent().isTaskRoot());
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (isRoot() && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            long second = System.currentTimeMillis();
            if (second - mClickTime < EXIT_TIMEOUT) {
                finish();
                return true;
            } else {
                mClickTime = second;
                Toast.makeText(this, "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyUp(keyCode, event);
    }

    protected abstract P initPresenter();
}
