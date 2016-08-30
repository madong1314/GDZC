package com.app.gdzc.net;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.app.gdzc.base.BaseApplication;
import com.app.gdzc.utils.NetStateUtils;
import com.app.gdzc.utils.Utils;

import org.json.JSONException;

import java.lang.ref.WeakReference;
import java.util.Map;

public class HttpResponseUtils {
    private static HttpResponseUtils httpUtils;
    private static WeakReference<Activity> mActivity;
    private RequestQueue mQueue;

    private HttpResponseUtils() {
        mQueue = BaseApplication.getAppContext().getRequestQueue();

    }

    public static HttpResponseUtils getInstance(Activity activity) {
        mActivity = new WeakReference(activity);
        if (httpUtils == null) {
            httpUtils = new HttpResponseUtils();
        }
        return httpUtils;
    }

    // volley使用post上传数据
    public synchronized <T> void sendPost(final String tag, final String url,
                                          final Map<String, String> params, final Class<T> clz,
                                          final ResponseListener responseListener, final boolean showLoading) {
        printUrlLog(url,"post",params);
        if (!checkNetWork(tag, responseListener)) return;
        if(showLoading)  Utils.showLoading(mActivity.get());
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Utils.hideLoading();
                        if (!TextUtils.isEmpty(response)) {
                            Log.i("postUrl",url+"  ----------response-->>" + response);
                            try {
                                responseListener.requestCompleted(tag, GsonUtils.json2Bean(response, clz));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("VolleyError", "-----VolleyError--->>" + error.toString());
                if (showLoading) Utils.hideLoading();
                responseListener.requestError(tag, "网络异常！");
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return params;
            }

        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(postRequest);

    }

    // volley使用get上传数据
    public synchronized <T> void sendGet(final String tag, final String url, final Class<T> clz,
                                         final ResponseListener responseListener,  final boolean showLoading) {
        printUrlLog(url,"Get",null);
        if (!checkNetWork(tag, responseListener)) return;
        if (showLoading) Utils.showLoading(mActivity.get());
        StringRequest postRequest = new StringRequest(Request.Method.GET, url,
                new Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Utils.hideLoading();
                        if (!TextUtils.isEmpty(response)) {
                            try {
                                responseListener.requestCompleted(tag, GsonUtils.json2Bean(response, clz));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (showLoading) Utils.hideLoading();
                responseListener.requestError(tag, "网络异常！");
            }
        });
        postRequest.setRetryPolicy(new DefaultRetryPolicy(
                10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        mQueue.add(postRequest);

    }

    public synchronized <T> void sendGet(final String tag, final String url, final Class<T> clz,
                                         final ResponseListener responseListener){
        sendGet(tag, url, clz, responseListener, false);
    }

    public synchronized <T> void sendPost(final String tag, final String url,
                                          final Map<String, String> params, final Class<T> clz,
                                          final ResponseListener responseListener){
        sendPost(tag, url, params, clz, responseListener, false);
    }

    private boolean checkNetWork(String tag, ResponseListener responseListener) {
        if (!NetStateUtils.isNetworkConnected(mActivity.get())) {
            responseListener.requestError(tag, "请检查网络是否连接");
            return false;
        }
        return true;
    }
    //打印请求数据
    private void printUrlLog(String url,String method,Map<String, String> params){
        String param = "?";
        if (params!=null){
            for (Map.Entry<String, String> entry : params.entrySet()) {
                param = param +entry.getKey()+"="+ Utils.UTF8(entry.getValue())+"&";
            }
        }
        param = param.substring(0,param.length()-1);//去除最后一个& 或者 ？
        String reStr = "\n" +
                "==============================================\n" +
                "url    -> " + url+param + "\n" +
                "==============================================\n";
        Log.i("send url", "send url " + method+ " -> " + reStr );
    }

}
