package com.app.gdzc.data.source;

/**
 * Created by 王少岩 on 2016/8/25.
 */
public interface DataSource {
    interface Callback<T> {

        void onComplete(int tag, T object);

        void onError(int tag, String error);
    }
}

