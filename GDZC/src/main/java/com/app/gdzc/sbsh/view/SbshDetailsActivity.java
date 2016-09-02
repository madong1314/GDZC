package com.app.gdzc.sbsh.view;

import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.presenter.SbshPresenter;
import com.app.gdzc.utils.Utils;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/9/2.
 */
public class SbshDetailsActivity extends BaseActivity<IEmptyInterFace, SbshModel, SbshPresenter> implements ISbshDetailView {

    @InjectView(R.id.tv_lydw)
    TextView mTvLydw;
    @InjectView(R.id.tv_yqmc)
    TextView mTvYqmc;
    @InjectView(R.id.tv_dj)
    TextView mTvDj;
    @InjectView(R.id.tv_xh)
    TextView mTvXh;
    @InjectView(R.id.tv_gg)
    TextView mTvGg;
    @InjectView(R.id.tv_lyr)
    TextView mTvLyr;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbsh_details);
        setTitle("设备详情");
        showLeft();
    }

    @Override
    protected SbshPresenter initPresenter() {
        return new SbshPresenter(this, this);
    }

    @Override
    public void showView(ZJBean zjBean) {
        mTvLydw.setText(zjBean.getLydwh());
        mTvYqmc.setText(zjBean.getYqmc());
        mTvDj.setText(zjBean.getDj());
        mTvXh.setText(zjBean.getXh());
        mTvGg.setText(zjBean.getGg());
        mTvLyr.setText(zjBean.getLyr());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setIcon(R.mipmap.ic_overflow);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_right:
                showPopupWindow();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private PopupWindow popupWindow;
    private void showPopupWindow() {
        if (popupWindow == null) {
            View contentView = LayoutInflater.from(this).inflate(R.layout.popup_window_sbsh, null);
            TextView tvEdit = (TextView) contentView.findViewById(R.id.tv_edit);
            TextView tvSh = (TextView) contentView.findViewById(R.id.tv_sh);
            popupWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
            popupWindow.setOutsideTouchable(true);
            popupWindow.setBackgroundDrawable(new BitmapDrawable());
        }

        if (popupWindow.isShowing()) {
            popupWindow.dismiss();
        } else {
            popupWindow.showAsDropDown(mToolbar, Utils.getWindowWidth(this), 0);
        }
    }
}
