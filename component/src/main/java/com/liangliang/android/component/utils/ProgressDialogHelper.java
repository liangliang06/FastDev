package com.liangliang.android.component.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.liangliang.android.component.R;

/**
 * 加载框帮助类
 */
public class ProgressDialogHelper {
    /**
     * 获取一个加载框
     *
     * @param context                上下文
     * @param cancelable             是否返回键关闭
     * @param canceledOnTouchOutside 是否点击空白处关闭
     */
    public static AlertDialog getProgressDialog(@NonNull Context context, boolean cancelable, boolean canceledOnTouchOutside) {
        return getProgressDialog(context, "", cancelable, canceledOnTouchOutside, null);
    }

    /**
     * 获取一个加载框
     *
     * @param context                上下文
     * @param msg                    提示语
     * @param cancelable             是否返回键关闭
     * @param canceledOnTouchOutside 是否点击空白处关闭
     */
    public static AlertDialog getProgressDialog(@NonNull Context context, String msg, boolean cancelable, boolean canceledOnTouchOutside) {
        return getProgressDialog(context, msg, cancelable, canceledOnTouchOutside, null);
    }

    /**
     * 获取一个加载框
     *
     * @param context                上下文
     * @param cancelable             是否返回键关闭
     * @param canceledOnTouchOutside 是否点击空白处关闭
     * @param listener               关闭监听器
     */
    public static AlertDialog getProgressDialog(@NonNull Context context, boolean cancelable,
                                                boolean canceledOnTouchOutside, DialogInterface.OnCancelListener listener) {
        return getProgressDialog(context, "", cancelable, canceledOnTouchOutside, listener);
    }

    /**
     * 获取一个加载框
     *
     * @param context                上下文
     * @param msg                    提示语
     * @param cancelable             是否返回键关闭
     * @param canceledOnTouchOutside 是否点击空白处关闭
     * @param listener               关闭监听器
     */
    public static AlertDialog getProgressDialog(@NonNull Context context, String msg, boolean cancelable,
                                                boolean canceledOnTouchOutside, DialogInterface.OnCancelListener listener) {
        View view = LayoutInflater.from(context).inflate(R.layout.component_view_progress_layout, null);
        AlertDialog progressDialog = new AlertDialog.Builder(context, R.style.ProgressStyle)
                .setView(view)
                .create();
        if (!TextUtils.isEmpty(msg)) {
            TextView msgTextView = view.findViewById(R.id.msg);
            msgTextView.setVisibility(View.VISIBLE);
            msgTextView.setText(msg);
        }
        progressDialog.setCanceledOnTouchOutside(canceledOnTouchOutside);
        progressDialog.setCancelable(cancelable);
        if (listener != null) {
            progressDialog.setOnCancelListener(listener);
        }
        Window window = progressDialog.getWindow();
        if (window != null) {
            window.setGravity(Gravity.CENTER);
        }
        return progressDialog;
    }
}
