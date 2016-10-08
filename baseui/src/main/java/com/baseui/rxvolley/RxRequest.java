package com.baseui.rxvolley;

import android.app.Activity;
import android.net.Uri;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.toolbox.RequestFuture;

import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by cmlBeliever on 2016/2/1.
 * <p>
 * 将Volley请求封装成RxJava结构返回，注意：所有的请求都在IO线程中处理
 */
public class RxRequest {

    /**
     * 发送post请求
     *
     * @param url
     * @param target
     * @param <T>
     * @return
     */
    public static <T> Observable<T> post(Activity activity, String url, Class<?> target, Map<String, String> params) {
        return request(activity, url, target, Request.Method.POST, params, new DefaultRetryPolicy(
                10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * 发送post请求
     *
     * @param url
     * @param target
     * @param <T>
     * @return
     */
    public static <T> Observable<T> post(Activity activity, String url, Class<?> target, Map<String, String> params, RetryPolicy retryPolicy) {
        return request(activity, url, target, Request.Method.POST, params, retryPolicy);
    }

    /**
     * 发送Get请求
     *
     * @param url
     * @param target
     * @param <T>
     * @return
     */
    public static <T> Observable<T> get(Activity activity, String url, Class<?> target) {
        return request(activity, url, target, Request.Method.GET, null, new DefaultRetryPolicy(
                10 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    /**
     * 发送Get请求
     *
     * @param url
     * @param target
     * @param <T>
     * @return
     */
    public static <T> Observable<T> get(Activity activity, String url, Class<?> target, RetryPolicy retryPolicy) {
        return request(activity, url, target, Request.Method.GET, null, retryPolicy);
    }

    public static <T> Observable<T> request(final Activity activity, String url, Class<?> target, int method, Map<String, String> params, RetryPolicy retryPolicy) {

        final RequestFuture<T> requestFuture = RequestFuture.newFuture();

        final GsonRequest<T> request = new GsonRequest<T>(target, method, url, params == null?null:appendParameter(url, params), requestFuture, requestFuture);
        request.setRetryPolicy(retryPolicy);
        request.setTag(url);

        requestFuture.setRequest(request);

        return Observable.create(new Observable.OnSubscribe<T>() {

            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    //只在被订阅后才进行网络请求处理
                    RequestManager.getRequestQueue().add(request);
                    if (!subscriber.isUnsubscribed() && !requestFuture.isCancelled()) {
                        subscriber.onNext(requestFuture.get());
                        subscriber.onCompleted();
                    }
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }

        }).subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 取消请求
     *
     * @param url
     */
    public static void cancel(final String url) {
        RequestManager.getRequestQueue().cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                return request.getTag().equals(url);
            }
        });
    }

    /**
     * 请求参数
     *
     * @param url
     * @param params
     * @return
     */
    private static String appendParameter(String url, Map<String, String> params) {
        if (params != null) {
            Uri uri = Uri.parse(url);
            Uri.Builder builder = uri.buildUpon();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            return builder.build().getQuery();
        } else {
            return null;
        }
    }

}
