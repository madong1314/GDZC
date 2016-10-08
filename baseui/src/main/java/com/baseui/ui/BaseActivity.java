package com.baseui.ui;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baseui.R;
import com.baseui.utils.StatusBarUtil;
import com.magicindicator.MagicIndicator;
import com.magicindicator.ViewPagerHelper;
import com.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import com.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import com.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import com.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import com.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by 王少岩 on 2016/10/8.
 */

public abstract class BaseActivity extends AppCompatActivity {
    protected Toolbar mToolbar;
    protected TextView mTitle;
    protected FrameLayout mLayout;
    protected MagicIndicator mIndicator;
    protected RadioGroup mRadioGroup;
    protected RadioButton mRbNoComplete, mRbComplete;
    protected DrawerLayout mDrawerLayout;
    private long mClickTime = 0l;
    private static int EXIT_TIMEOUT = 2500;
    protected Toolbar.OnMenuItemClickListener mOnMenuItemClickListener = new Toolbar.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            onRightClick(item);
            return false;
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.transparencyBar(this);
        StatusBarUtil.setStatusBarColor(this, R.color.black);
        setCustomerView(R.layout.activity_base);
        localOnCreate(savedInstanceState);
    }

    protected void setCustomerView(int res) {
        super.setContentView(res);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mTitle = (TextView) findViewById(R.id.tv_title);
        mLayout = (FrameLayout) findViewById(R.id.contentFrame);
        mIndicator = (MagicIndicator) findViewById(R.id.magic_indicator);
        mRadioGroup = (RadioGroup) findViewById(R.id.rg_title);
        mRbNoComplete = (RadioButton) findViewById(R.id.tv_no_complete);
        mRbComplete = (RadioButton) findViewById(R.id.tv_complete);
        mToolbar.setOnMenuItemClickListener(mOnMenuItemClickListener);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onLeftClick(v)) onBackPressed();
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
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
        mTitle.setVisibility(TextUtils.isEmpty(title) ? View.GONE : View.VISIBLE);
        mIndicator.setVisibility(View.GONE);
        mRadioGroup.setVisibility(View.GONE);
        mTitle.setText(title);
    }

    @Override
    public void setTitle(int titleId) {
        setTitle(getString(titleId));
    }

    protected void showLeft() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mToolbar.setNavigationIcon(R.mipmap.ic_back);
        mToolbar.setPadding(0, 0, 0, 0);
    }

    protected void setLogo(int id) {
        mToolbar.setNavigationIcon(id);
        mToolbar.setPadding((int) getResources().getDimension(R.dimen.dimen_10), 0, 0, 0);
    }

    protected void setTabs(final List<String> tabTitles, final ViewPager viewPager) {
        mToolbar.setVisibility(tabTitles.size() == 0 ? View.GONE : View.VISIBLE);
        mTitle.setVisibility(View.GONE);
        mRadioGroup.setVisibility(View.GONE);
        mIndicator.setVisibility(View.VISIBLE);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);  // 自适应模式
        commonNavigator.setSkimOver(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return tabTitles == null ? 0 : tabTitles.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(Color.GRAY);
                colorTransitionPagerTitleView.setSelectedColor(Color.BLACK);
                colorTransitionPagerTitleView.setText(tabTitles.get(index));
                colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        viewPager.setCurrentItem(index);
                    }
                });
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        mIndicator.setNavigator(commonNavigator);
        ViewPagerHelper.bind(mIndicator, viewPager);
    }

    protected void showTab(int id) {
        mToolbar.setVisibility(View.VISIBLE);
        mRadioGroup.setVisibility(View.VISIBLE);
        mIndicator.setVisibility(View.GONE);
        mTitle.setVisibility(View.GONE);
        mRadioGroup.check(id);
    }

    protected boolean onLeftClick(View v) {
        return false;
    }

    protected void onRightClick(MenuItem item) {
    }

    protected void setOnclickListener(View.OnClickListener listener, View... views) {
        if (listener != null) {
            int len = views.length;
            for (int i = 0; i < len; i++) {
                View view = views[i];
                if (view != null) {
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
}
