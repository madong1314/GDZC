package com.app.gdzc.sbsh.view;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.base.IEmptyInterFace;
import com.app.gdzc.data.bean.ZJBean;
import com.app.gdzc.data.source.local.LydwDao;
import com.app.gdzc.data.source.local.MkDao;
import com.app.gdzc.data.source.local.SbmkDao;
import com.app.gdzc.photo.SelectPhotoActivity;
import com.app.gdzc.sbsh.model.SbshModel;
import com.app.gdzc.sbsh.presenter.SbshPresenter;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/9/2.
 */
public class SbshDetailsActivity extends BaseActivity<IEmptyInterFace, SbshModel, SbshPresenter>{

    @InjectView(R.id.tv_yqmc)
    TextView mTvYqmc;
    @InjectView(R.id.tv_flmc)
    TextView mTvFlmc;
    @InjectView(R.id.tv_lydw)
    TextView mTvLydw;
    @InjectView(R.id.tv_xh)
    TextView mTvXh;
    @InjectView(R.id.tv_gg)
    TextView mTvGg;
    @InjectView(R.id.tv_cj)
    TextView mTvCj;
    @InjectView(R.id.tv_cch)
    TextView mTvCch;
    @InjectView(R.id.tv_gzrq)
    TextView mTvGzrq;
    @InjectView(R.id.tv_ccrq)
    TextView mTvCcrq;
    @InjectView(R.id.tv_bxqx)
    TextView mTvBxqx;
    @InjectView(R.id.tv_cfd)
    TextView mTvCfd;
    @InjectView(R.id.tv_jfkm)
    TextView mTvJfkm;
    @InjectView(R.id.tv_syfx)
    TextView mTvSyfx;
    @InjectView(R.id.tv_lyr)
    TextView mTvLyr;
    @InjectView(R.id.tv_jsr)
    TextView mTvJsr;
    @InjectView(R.id.tv_kyh)
    TextView mTvKyh;
    @InjectView(R.id.tv_gbm)
    TextView mTvGbm;
    @InjectView(R.id.tv_ghs)
    TextView mTvGhs;
    @InjectView(R.id.tv_xz)
    TextView mTvXz;
    @InjectView(R.id.tv_sbly)
    TextView mTvSbly;
    @InjectView(R.id.tv_dj)
    TextView mTvDj;
    @InjectView(R.id.iv_yq)
    ImageView mIvYq;
    @InjectView(R.id.iv_fp)
    ImageView mIvFp;

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
        mTvYqmc.setText(zjBean.getYqmc());
        mTvFlmc.setText(new SbmkDao(this).getMcByBh(zjBean.getFlh()));
        mTvLydw.setText(new LydwDao(this).getMcByBh(zjBean.getLydwh()));
        mTvXh.setText(zjBean.getXh());
        mTvGg.setText(zjBean.getGg());
        mTvCj.setText(zjBean.getCj());
        mTvCch.setText(zjBean.getCch());
        mTvGzrq.setText(zjBean.getGzrq());
        mTvCcrq.setText(zjBean.getCcrq());
        mTvBxqx.setText(zjBean.getBxqx());
        mTvCfd.setText(zjBean.getCfdmc());
        mTvJfkm.setText(new MkDao(this).getMcByBh(zjBean.getJfkm()));
        mTvSyfx.setText(new MkDao(this).getMcByBh(zjBean.getSyfx()));
        mTvLyr.setText(zjBean.getLyr());
        mTvJsr.setText(zjBean.getJsr());
        mTvKyh.setText(zjBean.getKyh());
        mTvGbm.setText(zjBean.getGbm());
        mTvGhs.setText(zjBean.getGhs());
        mTvXz.setText(new MkDao(this).getMcByBh(zjBean.getXz()));
        mTvSbly.setText(new MkDao(this).getMcByBh(zjBean.getSbly()));
        mTvDj.setText(zjBean.getDj());
        ImageLoader.getInstance().displayImage("file://" + SelectPhotoActivity.SD_CACHE_DIR + File.separator + SelectPhotoActivity.PATH_IMAGE_SHOT + File.separator + zjBean.getYqzp(), mIvYq, BaseApplication.imgoptions);
        ImageLoader.getInstance().displayImage("file://" + SelectPhotoActivity.SD_CACHE_DIR + File.separator + SelectPhotoActivity.PATH_IMAGE_SHOT + File.separator + zjBean.getFpzp(), mIvFp, BaseApplication.imgoptions);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.meun_main, menu);
        if(zjBean.getCs().equals("1"))
            menu.findItem(R.id.action_right).setVisible(false);
        else
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
