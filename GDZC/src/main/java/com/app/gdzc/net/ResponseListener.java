package com.app.gdzc.net;

import org.json.JSONException;


public interface ResponseListener {
//	void requestStarted();

	void requestCompleted(int tag, Object response) throws JSONException;

	void requestError(int tag, String error);
}
