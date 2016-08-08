package com.app.gdzc.baseui.bean;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 王少岩 on 2016/5/20.
 */
public class BaseNavView {
    private List<Integer> txtWidths = new ArrayList<Integer>();
    private List<TextView> tabTextViews = new ArrayList<TextView>();
    private int currentTxt = 0;
    private int baseX = 0;
    private Context mContext;
    private View bottonLine;

    public List<Integer> getTxtWidths() {
        return txtWidths;
    }

    public void setTxtWidths(List<Integer> txtWidths) {
        this.txtWidths = txtWidths;
    }

    public List<TextView> getTabTextViews() {
        return tabTextViews;
    }

    public void setTabTextViews(List<TextView> tabTextViews) {
        this.tabTextViews = tabTextViews;
    }

    public int getCurrentTxt() {
        return currentTxt;
    }

    public void setCurrentTxt(int currentTxt) {
        this.currentTxt = currentTxt;
    }

    public int getBaseX() {
        return baseX;
    }

    public void setBaseX(int baseX) {
        this.baseX = baseX;
    }

    public Context getContext() {
        return mContext;
    }

    public void setContext(Context context) {
        mContext = context;
    }

    public View getBottonLine() {
        return bottonLine;
    }

    public void setBottonLine(View bottonLine) {
        this.bottonLine = bottonLine;
    }
}
