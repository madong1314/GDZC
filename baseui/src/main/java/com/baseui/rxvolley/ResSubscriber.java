package com.baseui.rxvolley;

import android.app.Activity;

import com.baseui.data.BaseProtocolBean;
import com.baseui.utils.NetStateUtils;
import com.baseui.utils.Utils;

import rx.Subscriber;
import rx.functions.Action1;

/**
 * Created by 王少岩 on 2016/9/28.
 */

public class ResSubscriber<T> extends Subscriber<T> {

    final Activity mActivity;
    final Action1<? super T> onNext;
    final Action1<Throwable> onError;

    public ResSubscriber(Activity activity, Action1<? super T> onNext) {
        this.mActivity = activity;
        this.onNext = onNext;
        this.onError = null;
    }

    public ResSubscriber(Activity activity, Action1<? super T> onNext, Action1<Throwable> onError) {
        mActivity = activity;
        this.onNext = onNext;
        this.onError = onError;
    }

    @Override
    public void onStart() {
        Utils.showLoading(mActivity);
        if(!NetStateUtils.isNetworkConnected(mActivity)){
            onMyError("请检查网络是否连接");
        }
    }

    @Override
    public void onCompleted() {
        Utils.hideLoading();
    }

    @Override
    public void onError(Throwable e) {
        Utils.hideLoading();
        if (onError!=null) onError.call(e);
    }

    @Override
    public void onNext(T t) {
        BaseProtocolBean bean = (BaseProtocolBean) t;
        if (bean.status.isSuccess())
            onNext.call(t);
        else
            onMyError(bean.status.msg);
    }

    private void onMyError(String msg){
        Utils.showToast(mActivity, msg);
        onError(null);
    }
}
