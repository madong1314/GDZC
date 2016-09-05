package com.app.gdzc.photo;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.app.gdzc.R;

/**
 * Created by 王少岩 on 2016/9/5.
 */
public class SelectBaseActivity extends AppCompatActivity implements View.OnClickListener {
    private View selectLayout;
    private View backgroundLayout;
    private Animation showAnim;
    private Animation hideAnim;
    private boolean hasFinish = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Translucent_Fullscreen); // 这里设置主题未生效
        backgroundLayout = getWindow().getDecorView().findViewById(android.R.id.content);
        backgroundLayout.setOnClickListener(this);
        initAnim();
    }

    /**
     * 显示选择界面
     */
    public void show(boolean hasAnimation) {
        if (hasAnimation) {
            selectLayout.startAnimation(showAnim);
        } else {
            selectLayout.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 隐藏选择界面
     */
    public void hide(boolean hasAnimation) {
        this.hasFinish = false;
        if (hasAnimation) {
            selectLayout.startAnimation(hideAnim);
        } else {
            selectLayout.setVisibility(View.INVISIBLE);
            backgroundLayout.setBackgroundResource(android.R.color.transparent);
        }
    }

    /**
     * 显示选择界面
     */
    public void show() {
        show(true);
    }

    /**
     * 隐藏选择界面
     */
    public void hide() {
        hide(true);
    }

    public void dismiss() {
        this.hasFinish = true;
        selectLayout.startAnimation(hideAnim);
    }

    private void initAnim() {
        showAnim = AnimationUtils.loadAnimation(this, R.anim.plugin_show);
        showAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
            }
        });

        hideAnim = AnimationUtils.loadAnimation(this, R.anim.plugin_hide);
        hideAnim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                selectLayout.setVisibility(View.INVISIBLE);
                if (hasFinish) {
                    backgroundLayout.setBackgroundResource(android.R.color.transparent);
                    finish();
                }
            }
        });
    }

    protected void setSelectView(View selectLayout) {
        this.selectLayout = selectLayout;
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 返回时提醒
        if (keyCode == KeyEvent.KEYCODE_BACK && onBack()) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    protected boolean onBack() {
        selectLayout.startAnimation(hideAnim);
        return true;

    }

    /**获取一个升序的int数组*/
    public static int[] getRiseArray(int begin, int end) {
        int length = end - begin + 1;
        if(length < 0 ){
            length = 0;
        }
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = begin + i;
        }
        return array;
    }
}
