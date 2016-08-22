package com.app.gdzc.base;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by 王少岩 on 2016/8/22.
 */
public class BaseFragment extends Fragment {

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
}
