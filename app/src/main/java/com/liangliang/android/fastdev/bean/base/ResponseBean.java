package com.liangliang.android.fastdev.bean.base;

import com.liangliang.android.component.rx.status.ResponseStatus;

/**
 * 数据包裹层
 */
public class ResponseBean<T> implements ResponseStatus {
    /**
     * 失败
     */
    public static final int Fail = 500;
    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 本次接口请求返回的状态
     */
    public int code = Fail;
    /**
     * 对code的简单描述
     */
    public String msg = "";
    /**
     * 数据对象
     */
    public T data;
    /**
     * 分页数据对象
     */
    public PageBean page;

    @Override
    public boolean isSuccess() {
        return code == SUCCESS;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
