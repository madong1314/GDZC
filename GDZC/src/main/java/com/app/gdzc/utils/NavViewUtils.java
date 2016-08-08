package com.app.gdzc.utils;

import android.content.Context;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.gdzc.R;
import com.app.gdzc.baseui.bean.BaseNavView;

import java.util.List;

/**
 * Created by 王少岩 on 2016/5/18.
 */
public class NavViewUtils {

    private static View view_bottom_line;
    private static int padding = 0;

    /**
     * 添加tab标签
     * @param context
     * @param rlayout_root
     * @param tabNames
     * @param listener
     * @return
     */
    public static BaseNavView addTabViews(Context context, RelativeLayout rlayout_root, List<String> tabNames, View.OnClickListener listener){
        BaseNavView baseNavView = new BaseNavView();
        baseNavView.setContext(context);
        padding = (int) (8 * context.getResources().getDisplayMetrics().density);
        LinearLayout llayout_tabnames = (LinearLayout) rlayout_root.findViewById(R.id.llayout_tabnames);
        view_bottom_line = rlayout_root.findViewById(R.id.view_bottom_line);
        float textSize = 14;
        if(tabNames.size()==1){
            view_bottom_line.setVisibility(View.GONE);
            textSize = 16;
        }else{
            view_bottom_line.setVisibility(View.VISIBLE);
        }
        baseNavView.setBottonLine(view_bottom_line);
        for (int i = 0; i < tabNames.size(); i++) {
            TextView textView = createTabTextView(context, baseNavView, textSize);
            if(i == 0 && tabNames.size()>1){
                textView.setTextColor(context.getResources().getColor(R.color.red));
            }
            textView.setId(i);
            textView.setText(tabNames.get(i));
            textView.setOnClickListener(listener);
            llayout_tabnames.addView(textView);
            baseNavView.getTabTextViews().add(textView);
        }
        return baseNavView;
    }

    /**
     * 创建Tab标签TextView
     * @param context
     * @param baseNavView
     * @return
     */
    private static TextView createTabTextView(Context context, final BaseNavView baseNavView, float textSize) {
        final TextView textView = new TextView(context);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(context.getResources().getColor(R.color.text_title));
        textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
        textView.setPadding(padding, padding/4, padding, padding/4);
        ViewTreeObserver vto = textView.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                textView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                textView.getHeight();
                baseNavView.getTxtWidths().add(textView.getWidth());
                Log.e("tvWidth", textView.getWidth() + "");
                if (baseNavView.getCurrentTxt() == 0) {
                    baseNavView.setCurrentTxt(1);
                    RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(textView.getWidth() - 2 * padding, padding/4);
                    lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
                    lp.setMargins(padding, 0, padding, 0);
                    baseNavView.getBottonLine().setLayoutParams(lp);
                }
            }
        });

        return textView;
    }

    /**
     * tab标签切换动画
     * @param currentX
     * @param baseNavView
     */
    public static void startTranslateAnimation(int currentX, BaseNavView baseNavView) {
        resetTxtColor(baseNavView);
        baseNavView.getTabTextViews().get(currentX).setTextColor(baseNavView.getContext().getResources().getColor(R.color.red));
        int toX = getX(currentX, baseNavView);
        TranslateAnimation ta = new TranslateAnimation(baseNavView.getBaseX(), toX, 0, 0);
        ta.setDuration(300);
        ta.setFillAfter(true);
        baseNavView.getBottonLine().startAnimation(ta);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(baseNavView.getTxtWidths().get(currentX) - 2 * padding, padding/4);
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        lp.setMargins(padding, 0, padding, 0);
        baseNavView.getBottonLine().setLayoutParams(lp);
        baseNavView.setBaseX(toX);

    }

    private static void resetTxtColor(BaseNavView baseNavView){
        int color = baseNavView.getContext().getResources().getColor(R.color.text_title_sub);
        for(TextView txt:baseNavView.getTabTextViews()){
            txt.setTextColor(color);
        }
    }

    private static int getX(int current, BaseNavView baseNavView) {
        int fromx = 0;
        for (int i = 0; i < current; i++) {
            fromx += baseNavView.getTxtWidths().get(i);
        }
        return fromx;
    }
}
