package com.app.gdzc.net;

import org.json.JSONException;


public interface ResponseListener<T> {
    void requestCompleted(String tag, T response) throws JSONException;

    void requestError(String tag, String error);
}
