package com.utouu.douyudemo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Toast相关方法
 * Created by tsy on 16/8/15.
 */
public class ToastUtils {

    public static final int TIME_SHORT = Toast.LENGTH_SHORT;
    public static final int TIME_LONG = Toast.LENGTH_LONG;

    private static Toast mToast;

    /**
     * 显示short message
     * @param context 全局context
     * @param resId string string资源id
     */
    public static void showShort(Context context, int resId) {
        showToast(context,resId,TIME_SHORT);
    }

    /**
     * 显示short message
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showShort(Context context, String message) {
        showToast(context,message,TIME_SHORT);
    }

    /**
     * 显示long message
     * @param context 全局context
     * @param resId string string资源id
     */
    public static void showLong(Context context, int resId) {
        showToast(context,resId,TIME_LONG);
    }

    /**
     * 显示long message
     * @param context 全局context
     * @param message 显示msg
     */
    public static void showLong(Context context, String message) {
        showToast(context,message,TIME_LONG);
    }

    private static void showToast(Context mContext,String text,int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, text,duration);
        } else {
            mToast.setText(text);
        }
        mToast.show();
    }

    private static void showToast(Context mContext,int resId,int duration) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, resId,duration);
        } else {
            mToast.setText(resId);
        }
        mToast.show();
    }
}
