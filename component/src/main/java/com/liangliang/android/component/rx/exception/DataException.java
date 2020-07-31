package com.liangliang.android.component.rx.exception;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.liangliang.android.component.rx.status.ResponseStatus;

/**
 * 服务端数据异常
 */
public class DataException extends RxException {
    /** 数据信息 */
    private ResponseStatus mData;

    public DataException(String errorMsg) {
        super(errorMsg);
    }

    public DataException(String message, String errorMsg) {
        super(message, errorMsg);
    }

    public DataException(String message, String errorMsg, Throwable cause) {
        super(message, errorMsg, cause);
    }

    public DataException(Throwable cause, String errorMsg) {
        super(cause, errorMsg);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public DataException(String message, String errorMsg, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, errorMsg, cause, enableSuppression, writableStackTrace);
    }

    public ResponseStatus getData() {
        return mData;
    }

    public void setData(ResponseStatus data) {
        this.mData = data;
    }
}
