package com.liangliang.android.fastdev.ui.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.liangliang.android.component.widget.dialog.BaseCenterDialog;
import com.liangliang.android.fastdev.R;

/**
 * 测试中间弹框（缩放动画）
 */
public class TestCenterScaleDialog extends BaseCenterDialog {
    public TestCenterScaleDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.dialog_test_center_layout;
    }

    @Override
    protected void findViews() {

    }

    @Override
    protected void onStartInit(Context context) {
        super.onStartInit(context);
    }
}
