package com.app.gdzc.sbsh.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.presenter.SbshPresenter;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/9/2.
 */
public class SbshDetailsActivity extends BaseActivity<IEmptyInterFace, SbshModel, SbshPresenter>{

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

    private ZJBean zjBean;
    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_sbsh_details);
        setTitle("设备详情");
        showLeft();
        zjBean = (ZJBean) getIntent().getExtras().getSerializable("zjBean");
        showView(zjBean);
    }

    @Override
    protected SbshPresenter initPresenter() {
        return new SbshPresenter(this);
    }

    public void showView(ZJBean zjBean) {
        mTvLydw.setText(new LydwDao(this).getMcByBh(zjBean.getLydwh()));
        mTvYqmc.setText(zjBean.getYqmc());
        mTvDj.setText(zjBean.getDj());
        mTvXh.setText(zjBean.getXh());
        mTvGg.setText(zjBean.getGg());
        mTvLyr.setText(zjBean.getLyr());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        menu.findItem(R.id.action_right).setTitle("审核");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_right:
                zjBean.setCs("1");
                mPresenter.shenHe(zjBean);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
