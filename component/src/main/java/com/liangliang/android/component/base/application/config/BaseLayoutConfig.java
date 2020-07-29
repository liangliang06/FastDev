package com.liangliang.android.component.base.application.config;

import android.widget.LinearLayout;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 基类配置
 */
public class BaseLayoutConfig {
    @IntDef({LinearLayout.HORIZONTAL, LinearLayout.VERTICAL})
    @Retention(RetentionPolicy.SOURCE)
    public @interface OrientationType {
    }

    /**
     * 加载页面配置
     */
    private LoadingLayoutConfig mLoadingLayoutConfig;
    /**
     * 异常页面配置
     */
    private ErrorLayoutConfig mErrorLayoutConfig;
    /**
     * 无数据页面配置
     */
    private NoDataLayoutConfig mNoDataLayoutConfig;
    /**
     * 标题栏配置
     */
    private TitleBarLayoutConfig mTitleBarLayoutConfig;


    public BaseLayoutConfig() {
        this.mErrorLayoutConfig = new ErrorLayoutConfig();
        this.mLoadingLayoutConfig = new LoadingLayoutConfig();
        this.mNoDataLayoutConfig = new NoDataLayoutConfig();
        this.mTitleBarLayoutConfig = new TitleBarLayoutConfig();
    }

    /**
     * 获取加载页面配置
     */
    public LoadingLayoutConfig getLoadingLayoutConfig() {
        return mLoadingLayoutConfig;
    }

    /**
     * 获取异常页面配置
     */
    public ErrorLayoutConfig getErrorLayoutConfig() {
        return mErrorLayoutConfig;
    }

    /**
     * 获取无数据页面配置
     */
    public NoDataLayoutConfig getNoDataLayoutConfig() {
        return mNoDataLayoutConfig;
    }

    /**
     * 获取标题栏配置
     */
    public TitleBarLayoutConfig getTitleBarLayoutConfig() {
        return mTitleBarLayoutConfig;
    }
}
