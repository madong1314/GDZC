package com.app.gdzc.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.gdzc.R;
import com.app.gdzc.baseui.presenter.BasePresenter;
import com.app.gdzc.utils.Utils;

import butterknife.ButterKnife;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public abstract class BaseActivity<V, M, P extends BasePresenter<V,M>> extends AppCompatActivity {
    protected ViewGroup mTitleBarView;
    protected TextView mLeftImg;
    protected TextView mRightImg;
    protected FrameLayout mContentFlayout;
    protected RelativeLayout mTabNameFlayout;
    protected P mPresenter;
    protected FragmentManager mFragmentManager;
    private long mClickTime = 0l;
    private int EXIT_TIMEOUT = 2500;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = initPresenter();
        if (mPresenter != null) mPresenter.attach((V) this);
        setContentView(R.layout.activity_base);
        mContentFlayout = (FrameLayout) findViewById(R.id.flayout_content);
        initTitleBarView();
        localCreateView(savedInstanceState);
    }

    //实例化presenter
    protected abstract P initPresenter();
    protected void initTitleBarView() {}
    protected abstract void localCreateView(Bundle savedInstanceState);

    public void setMyContentView(int resId){
        mContentFlayout.removeAllViews();
        View view = View.inflate(this, resId, null);
        mContentFlayout.addView(view);
        ButterKnife.inject(this);
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

    @Override
    protected void onDestroy() {
        if (mPresenter != null)
            mPresenter.dettach();
        super.onDestroy();
        ButterKnife.reset(this);
    }

    protected boolean isRoot() {
        return isTaskRoot() || (getParent() != null && getParent().isTaskRoot());
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        if (isRoot() && event.getKeyCode() == KeyEvent.KEYCODE_BACK && mFragmentManager.getBackStackEntryCount() <= 0) {
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            Utils.hideLoading();
        }
        return super.onKeyDown(keyCode, event);
    }

}
