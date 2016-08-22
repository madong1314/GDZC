package com.app.gdzc.main;

import android.os.Bundle;

import com.app.gdzc.R;
import com.app.gdzc.base.BaseActivity;
import com.app.gdzc.utils.Utils;
import com.app.gdzc.widget.viewpager.AutoScrollViewPager;
import com.viewpagerindicator.LinePageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class MainActivity extends BaseActivity {
    @InjectView(R.id.top_pager)
    AutoScrollViewPager mViewPager;
    @InjectView(R.id.top_pager_indicator)
    LinePageIndicator mIndicator;

    private List<String> ads = new ArrayList<>();
    private ADAdapter mADAdapter;

    @Override
    protected void localOnCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        ads.add("http://pic2.ooopic.com/10/54/94/06b1OOOPICb9.jpg");
        ads.add("http://pic.58pic.com/58pic/12/95/27/03w58PICdsf.jpg");
        mADAdapter = new ADAdapter(this);
        mADAdapter.setAds(ads);
        mViewPager.setAdapter(mADAdapter);
        mIndicator.setViewPager(mViewPager);
        mIndicator.setPadding((int) (Utils.getWindowWidth(this) * 0.85), 0, 0, 20);
        mViewPager.startAutoScroll();
        mViewPager.setStopScrollWhenTouch(true);
        mViewPager.setInterval(5000);
    }
}
