package com.yang.permissiondemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class PermissionsDispatcherActivity extends AppCompatActivity {

    @Bind(R.id.tv_permission_status)
    TextView tvPermissionStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_request_permission)
    public void onClick() {
        requestPermission();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void requestPermission() {
        //申请权限
        PermissionsDispatcherActivityPermissionsDispatcher.openCameraWithCheck(this);
    }

    @NeedsPermission(Manifest.permission.CAMERA)
    void openCamera() {
        tvPermissionStatus.setTextColor(Color.GREEN);
        tvPermissionStatus.setText("相机权限已申请");
    }

    @OnShowRationale(Manifest.permission.CAMERA)
    void showRationale(final PermissionRequest request) {
        new AlertDialog.Builder(this)
                .setMessage("申请相机权限")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //再次执行请求
                        request.proceed();
                    }
                })
                .show();
    }

    @OnPermissionDenied(Manifest.permission.CAMERA)
    void permissionDenied() {
        Toast.makeText(this, "权限被拒绝", Toast.LENGTH_SHORT).show();
    }

    @OnNeverAskAgain(Manifest.permission.CAMERA)
    void neverAskAgain() {
        Toast.makeText(this, "不再询问", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsDispatcherActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
