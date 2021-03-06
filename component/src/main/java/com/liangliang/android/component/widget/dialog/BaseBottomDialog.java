package com.liangliang.android.component.widget.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.liangliang.android.component.R;

/**
 * 底部弹框基类
 */
public abstract class BaseBottomDialog extends BaseDialog {
    public BaseBottomDialog(@NonNull Context context) {
        super(context);
    }

    public BaseBottomDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected int getAnimations() {
        return R.style.animation_bottom_in_bottom_out;
    }

    @Override
    public void show() {
        Window window = getWindow();
        if (window != null) {
            window.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
            if (isMatchWidth()) {
                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                window.setAttributes(layoutParams);
            }
        }
        super.show();
    }

    /**
     * 是否需要填满宽度
     */
    protected boolean isMatchWidth() {
        return true;
    }
}
