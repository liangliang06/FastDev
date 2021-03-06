package com.liangliang.android.component.photopicker.picker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import androidx.core.content.FileProvider;

import com.liangliang.android.component.R;
import com.liangliang.android.component.base.activity.AbsActivity;
import com.liangliang.android.core.album.AlbumUtils;
import com.liangliang.android.core.utils.DateUtils;
import com.liangliang.android.core.utils.DeviceUtils;
import com.liangliang.android.core.utils.FileUtils;
import com.liangliang.android.core.utils.ToastUtils;
import com.liangliang.android.core.utils.UiHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * 拍照页面
 */
public class TakePhotoActivity extends AbsActivity {
    /**
     * 照相请求码
     */
    private static final int REQUEST_CAMERA = 777;

    /**
     * 启动页面
     *
     * @param context 上下文
     */
    public static void start(Context context) {
        Intent starter = new Intent(context, TakePhotoActivity.class);
        context.startActivity(starter);
    }

    /**
     * 照片图片
     */
    private ImageView mPhotoImg;
    /**
     * 取消按钮
     */
    private ImageView mCancelBtn;
    /**
     * 确定按钮
     */
    private ImageView mConfirmBtn;

    /**
     * 选择数据
     */
    private PickerBean mPickerBean;
    /**
     * 临时文件路径
     */
    private String mTempFilePath = "";

    @Override
    protected void startCreate() {
        super.startCreate();
        mPickerBean = PickerManager.sPickerBean;
    }

    @Override
    protected int getAbsLayoutId() {
        return R.layout.component_activity_take_photo_layout;
    }

    @Override
    protected void findViews(Bundle savedInstanceState) {
        mPhotoImg = findViewById(R.id.photo_img);
        mCancelBtn = findViewById(R.id.cancel_btn);
        mConfirmBtn = findViewById(R.id.confirm_btn);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            DeviceUtils.setStatusBarColor(getContext(), getWindow(), android.R.color.black);
            DeviceUtils.setNavigationBarColorRes(getContext(), getWindow(), android.R.color.black);
        }
    }

    @Override
    protected void setListeners() {
        super.setListeners();

        // 取消按钮
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleCameraCancel();
            }
        });

        // 确定按钮
        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleConfirm();
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        takePhoto();
    }

    /**
     * 拍照
     */
    private void takePhoto() {
        if (!FileUtils.isFileExists(mPickerBean.cameraSavePath) && !FileUtils.createFolder(mPickerBean.cameraSavePath)) {
            ToastUtils.showShort(getContext(), R.string.component_photo_folder_fail);
            return;
        }

        mTempFilePath = mPickerBean.cameraSavePath + "P_" + DateUtils.getCurrentFormatString(DateUtils.TYPE_4) + ".jpg";
        if (!FileUtils.createNewFile(mTempFilePath)) {
            ToastUtils.showShort(getContext(), R.string.component_photo_temp_file_fail);
            return;
        }

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (cameraIntent.resolveActivity(getPackageManager()) == null) {
            ToastUtils.showShort(getContext(), R.string.component_no_camera);
            return;
        }
        // 设置系统相机拍照后的输出路径
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getContext(), mPickerBean.authority, FileUtils.createFile(mTempFilePath)));
            } catch (Exception e) {
                e.printStackTrace();
                ToastUtils.showShort(getContext(), R.string.component_photo_temp_file_fail);
                return;
            }
        } else {
            cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(FileUtils.createFile(mTempFilePath)));
        }
        startActivityForResult(cameraIntent, REQUEST_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA) {
            if (resultCode == Activity.RESULT_OK) {
                AlbumUtils.notifyScanImage(getContext(), mTempFilePath);// 更新相册
                if (mPickerBean.isImmediately) {
                    handleConfirm();
                } else {
                    UiHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            handleCameraSuccess();
                        }
                    }, 300);
                }
                return;
            }
            handleCameraCancel();
        }
    }

    /**
     * 处理确认照片
     */
    private void handleConfirm() {
        List<String> list = new ArrayList<>();
        list.add(mTempFilePath);
        if (mPickerBean.photoPickerListener != null) {
            mPickerBean.photoPickerListener.onPickerSelected(list);
        }
        finish();
    }

    /**
     * 处理拍照成功
     */
    private void handleCameraSuccess() {
        if (mPickerBean.previewLoader != null) {
            mPickerBean.previewLoader.displayImg(getContext(), mTempFilePath, mPhotoImg);
        }
    }

    /**
     * 处理拍照取消
     */
    private void handleCameraCancel() {
        if (!TextUtils.isEmpty(mTempFilePath)) {
            FileUtils.delFile(mTempFilePath);// 删除临时文件
        }
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        mTempFilePath = "";
        PickerManager.sPickerBean.clear();
        mPickerBean.clear();
    }
}
