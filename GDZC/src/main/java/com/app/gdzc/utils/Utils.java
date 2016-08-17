package com.app.gdzc.utils;

import android.app.Activity;
import android.app.Dialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.app.gdzc.R;
import com.app.gdzc.widget.LoadingDialog;
import com.app.gdzc.widget.MessageDialog;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Utils {
    private static final String TAG = "Utils";
    private static LoadingDialog mLoadingDialog;

    // 土司
    public static void showToast(Context context, String text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    //显示loading
    public static void showLoading(final Activity activity) {
        hideLoading();
        mLoadingDialog = new LoadingDialog(activity);
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                activity.onKeyDown(keyCode, event);
                return false;
            }
        });
        mLoadingDialog.show();
    }

    public static void showMsgDialog(Activity activity, String msg, MessageDialog.OnDialogFinishListener listener) {
        MessageDialog msgDialog = new MessageDialog(activity, msg, false, listener);
        msgDialog.show();
    }

    //隐藏loading
    public static void hideLoading() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    // UTF8编码
    public static String UTF8(String str) {
        String value = ""; //默认
        if (null == str) {
            return value;
        }
        try {
            value = URLEncoder.encode(str, "UTF-8");
        } catch (UnsupportedEncodingException e) {
        }

        return value;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return
     */
    public static int getWindowWidth(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return display.getWidth();
    }

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getWindowHeight(Activity activity) {
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        return display.getHeight();
    }

    /**
     * 在屏幕中间显示dialog
     * @param context
     * @param view
     * @return
     */
    public static Dialog showCenterDialog(Context context, View view){
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Service.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mDisplayMetrics);
        Dialog dialog = new Dialog(context, R.style.DialogStyleBottom);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        // 设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = mDisplayMetrics.widthPixels * 4 / 5;
        wl.gravity = Gravity.CENTER;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);// 设置点击外围解散
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();
        return dialog;
    }

    /**
     * 在屏幕底部显示dialog
     * @param context
     * @param view
     * @return
     */
    public static Dialog showBottomDialog(Context context, View view){
        DisplayMetrics mDisplayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Service.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(mDisplayMetrics);
        Dialog dialog = new Dialog(context, R.style.DialogStyleBottom);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        // 设置显示动画
        WindowManager.LayoutParams wl = window.getAttributes();
        wl.width = mDisplayMetrics.widthPixels;
        wl.gravity = Gravity.BOTTOM;

        // 设置显示位置
        dialog.onWindowAttributesChanged(wl);// 设置点击外围解散
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
        return dialog;
    }

    /**
     * bitmap转String
     * @param bitmap
     * @return
     */
    public static String getImgStr(Bitmap bitmap) {
        String imgStr = "";
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] b = stream.toByteArray();
        imgStr = new String(Base64.encode(b, Base64.DEFAULT));
        return imgStr;
    }
}
