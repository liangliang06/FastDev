package com.liangliang.android.component.rx.exception;

import android.os.Build;

import androidx.annotation.RequiresApi;

/**
 * 网络超时异常
 */
public class NetworkTimeOutException extends NetworkException{
    public NetworkTimeOutException(String errorMsg) {
        super(errorMsg);
    }

    public NetworkTimeOutException(String message, String errorMsg) {
        super(message, errorMsg);
    }

    public NetworkTimeOutException(String message, String errorMsg, Throwable cause) {
        super(message, errorMsg, cause);
    }

    public NetworkTimeOutException(Throwable cause, String errorMsg) {
        super(cause, errorMsg);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public NetworkTimeOutException(String message, String errorMsg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, errorMsg, cause, enableSuppression, writableStackTrace);
    }
}
