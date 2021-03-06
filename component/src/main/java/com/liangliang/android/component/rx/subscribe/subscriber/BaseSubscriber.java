package com.liangliang.android.component.rx.subscribe.subscriber;

import android.text.TextUtils;

import com.liangliang.android.component.base.application.BaseApplication;
import com.liangliang.android.core.log.PrintLog;
import com.liangliang.android.core.utils.AppUtils;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

/**
 * 基类订阅者（带背压）
 */
public abstract class BaseSubscriber<T> implements Subscriber<T> {
    private static final String ERROR_TAG = "error_tag";

    private Subscription mSubscription;

    @Override
    public final void onSubscribe(Subscription s) {
        mSubscription = s;
        if (isAutoSubscribe()) {
            request(Long.MAX_VALUE);
        }
        onBaseSubscribe(s);
    }

    @Override
    public final void onNext(T t) {
        onBaseNext(t);
    }

    @Override
    public final void onError(Throwable t) {
        if (t != null) {
            t.printStackTrace();
        }
        printTagLog(t);
        onBaseError(t);
    }

    /**
     * 打印标签日志
     */
    private void printTagLog(Throwable t) {
        if (BaseApplication.get() == null || t == null) {
            return;
        }
        Object o = AppUtils.getMetaData(BaseApplication.get(), ERROR_TAG);
        if (o != null && o instanceof String) {
            String tag = (String) o;
            if (!TextUtils.isEmpty(tag)) {
                PrintLog.e(tag, t.toString(), t);
            }
        }
    }

    @Override
    public final void onComplete() {
        onBaseComplete();
    }

    public Subscription getSubscription() {
        return mSubscription;
    }

    public void clearSubscription() {
        mSubscription = null;
    }

    /**
     * 停止订阅
     */
    public void cancel() {
        if (mSubscription != null) {
            mSubscription.cancel();
            onCancel();
        }
    }

    /**
     * 请求订阅
     */
    public void request(long n) {
        if (mSubscription != null) {
            mSubscription.request(n);
        }
    }

    public void onBaseSubscribe(Subscription s) {
    }

    public abstract void onBaseNext(T t);

    public abstract void onBaseError(Throwable e);

    public void onBaseComplete() {
    }

    /**
     * 取消订阅时回调
     */
    public void onCancel() {
    }

    /**
     * 是否自动订阅，默认是，否的时候需要自己调用request()方法订阅
     */
    public boolean isAutoSubscribe() {
        return true;
    }

    /**
     * 创建空调用
     */
    public static <T> Subscriber<T> empty() {
        return new Subscriber<T>() {
            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(T t) {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
