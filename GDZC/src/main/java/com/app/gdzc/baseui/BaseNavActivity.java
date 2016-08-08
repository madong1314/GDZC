package com.app.gdzc.baseui;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.baseui.BaseActivity;
import com.app.gdzc.baseui.bean.BaseNavView;
import com.app.gdzc.baseui.presenter.BasePresenter;
import com.app.gdzc.utils.NavViewUtils;
import com.app.gdzc.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王少岩 on 2016/8/5.
 */
public abstract class BaseNavActivity<V, M, P extends BasePresenter<V,M>> extends BaseActivity<V,M,P> {

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(v == mLeftImg){
                if(!onLeftClick(v))
                    finish();
            }else if(v == mRightImg){
                onRightClick(v);
            }
        }
    };

    protected void initTitleBarView() {
        mContentFlayout = (FrameLayout) findViewById(R.id.flayout_content);
        mTitleBarView = (ViewGroup) findViewById(R.id.title_bar);
        mLeftImg = (TextView) mTitleBarView.findViewById(R.id.iv_left);
        mRightImg = (TextView) mTitleBarView.findViewById(R.id.iv_right);
        mTabNameFlayout = (RelativeLayout) mTitleBarView.findViewById(R.id.rlayout_tabname_container);
        mLeftImg.setOnClickListener(mClickListener);
        mRightImg.setOnClickListener(mClickListener);
    }

    /**
     * 左侧Button点击时回调,如果返回true不会执行onback
     *
     * @param view
     */
    protected boolean onLeftClick(View view) {
        Utils.hideLoading();
        return false;
    }

    /**
     * 右侧Button点击时回调
     *
     * @param view
     */
    protected void onRightClick(View view) {}

    /**
     *
     *  @param resId 左上角图标id
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void setLeft(int resId){
        showLeftBtn();
        mLeftImg.setCompoundDrawablesRelativeWithIntrinsicBounds(getResources().getDrawable(resId), null, null, null);
    }

    protected void showLeftBtn(){
        mTitleBarView.setVisibility(View.VISIBLE);
        mLeftImg.setVisibility(View.VISIBLE);
    }

    protected void setLeftText(String leftTxt){
        if(TextUtils.isEmpty(leftTxt)){
            hideLeftBtn();
        }else {
            showLeftBtn();
            mLeftImg.setText(leftTxt);
        }
    }

    private void hideLeftBtn(){
        mLeftImg.setVisibility(View.GONE);
    }

    /**
     *
     * @param resId 右上角图标id
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    protected void setRight(int resId){
        Drawable rightDrawable =null;
        showRightBtn();
        if (resId != 0){//0为null
            rightDrawable = getResources().getDrawable(resId);
        }

        mRightImg.setCompoundDrawablesRelativeWithIntrinsicBounds(null, null, rightDrawable, null);
    }

    private void showRightBtn(){
        mTitleBarView.setVisibility(View.VISIBLE);
        mRightImg.setVisibility(View.VISIBLE);
    }

    private void hideRightBtn(){
        mRightImg.setVisibility(View.GONE);
    }

    protected void setRightText(String rightText){
        if(TextUtils.isEmpty(rightText)){
            hideRightBtn();
        }else{
            showRightBtn();
            mRightImg.setText(rightText);
        }
    }

    /**
     *
     * @param tabNames 标签名称
     * @param listener 点击标签监听器
     */
    protected BaseNavView setTabNames(List<String> tabNames, View.OnClickListener listener){
        BaseNavView baseNavView = NavViewUtils.addTabViews(this, mTabNameFlayout,tabNames, listener);
        mTitleBarView.setVisibility(View.VISIBLE);
        return baseNavView;
    }

    /**
     * 设置页面标题
     * @param title
     */
    protected void setTitle(String title){
        List<String> tabNames = new ArrayList<String>();
        tabNames.add(title);
        NavViewUtils.addTabViews(this, mTabNameFlayout,tabNames, null);
        mTitleBarView.setVisibility(View.VISIBLE);
    }
}
