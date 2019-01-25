# PermissionDemo
Android 6.0运行时权限详解

## **1.介绍** ##

[Runtime Permissions官方说明](https://developer.android.com/about/versions/marshmallow/android-6.0-changes.html#behavior-runtime-permissions)

Android 6.0之前，权限在应用安装过程中只询问一次，以列表的形式展现给用户，然而大多数用户并不会注意到这些，直接就下一步了，应用安装成功后就会被赋予清单文件中的所有权限，应用就可以在用户不知情的情况下进行非法操作（比如偷偷的上传用户数据）。

Android 6.0版本中运行时权限的出现解决了这一问题，一些高危权限会在应用的运行过程中动态申请，这样用户就可以选择是否允许，比如一个单机游戏要获取通讯录权限，那肯定要禁止了。

并不是所有的权限都需要动态申请，需要申请的权限如下表所示：
**注意：同一组内的任何一个权限被授权了，其他权限也自动被授权。例如，一旦READ_CALENDAR被授权了，应用也有WRITE_CALENDAR权限了。**
<table>
  <tbody><tr>
    <th scope="col">Permission Group</th>
    <th scope="col">Permissions</th>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#CALENDAR">CALENDAR</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#READ_CALENDAR">READ_CALENDAR</a></code>
        </li>
      </ul>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#WRITE_CALENDAR">WRITE_CALENDAR</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#CAMERA">CAMERA</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#CAMERA">CAMERA</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#CONTACTS">CONTACTS</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#READ_CONTACTS">READ_CONTACTS</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#WRITE_CONTACTS">WRITE_CONTACTS</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#GET_ACCOUNTS">GET_ACCOUNTS</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#LOCATION">LOCATION</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_FINE_LOCATION">ACCESS_FINE_LOCATION</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#ACCESS_COARSE_LOCATION">ACCESS_COARSE_LOCATION</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#MICROPHONE">MICROPHONE</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#RECORD_AUDIO">RECORD_AUDIO</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#PHONE">PHONE</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#READ_PHONE_STATE">READ_PHONE_STATE</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#CALL_PHONE">CALL_PHONE</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#READ_CALL_LOG">READ_CALL_LOG</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#WRITE_CALL_LOG">WRITE_CALL_LOG</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#ADD_VOICEMAIL">ADD_VOICEMAIL</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#USE_SIP">USE_SIP</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#PROCESS_OUTGOING_CALLS">PROCESS_OUTGOING_CALLS</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#SENSORS">SENSORS</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#BODY_SENSORS">BODY_SENSORS</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td><code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#SMS">SMS</a></code></td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#SEND_SMS">SEND_SMS</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#RECEIVE_SMS">RECEIVE_SMS</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#READ_SMS">READ_SMS</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#RECEIVE_WAP_PUSH">RECEIVE_WAP_PUSH</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#RECEIVE_MMS">RECEIVE_MMS</a></code>
        </li>
      </ul>
    </td>
  </tr>

  <tr>
    <td>
      <code><a href="https://developer.android.com/reference/android/Manifest.permission_group.html#STORAGE">STORAGE</a></code>
    </td>
    <td>
      <ul>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#READ_EXTERNAL_STORAGE">READ_EXTERNAL_STORAGE</a></code>
        </li>
        <li>
          <code><a href="https://developer.android.com/reference/android/Manifest.permission.html#WRITE_EXTERNAL_STORAGE">WRITE_EXTERNAL_STORAGE</a></code>
        </li>
      </ul>
    </td>
  </tr>

</tbody></table>

## **2.运行时权限处理** ##

通过一个Demo来了解运行时权限的处理，先上图

第一次申请权限，拒绝后再次申请，申请成功

![成功申请](http://upload-images.jianshu.io/upload_images/3270074-6713d9fcebc101b2?imageMogr2/auto-orient/strip)

多次拒绝后，点击不再提示

![拒绝申请](http://upload-images.jianshu.io/upload_images/3270074-f2f1c49ac8ac5139?imageMogr2/auto-orient/strip)

二话不说，上代码

```
package com.yang.permissiondemo;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RuntimePermissionsActivity extends AppCompatActivity {

    @Bind(R.id.tv_permission_status)
    TextView tvPermissionStatus;

    private final int CAMERA_REQUEST_CODE = 1;

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
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // 第一次请求权限时，用户如果拒绝，下一次请求shouldShowRequestPermissionRationale()返回true
            // 向用户解释为什么需要这个权限
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                new AlertDialog.Builder(this)
                        .setMessage("申请相机权限")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //申请相机权限
                                ActivityCompat.requestPermissions(RuntimePermissionsActivity.this,
                                        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                            }
                        })
                        .show();
            } else {
                //申请相机权限
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
            }
        } else {
            tvPermissionStatus.setTextColor(Color.GREEN);
            tvPermissionStatus.setText("相机权限已申请");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                tvPermissionStatus.setTextColor(Color.GREEN);
                tvPermissionStatus.setText("相机权限已申请");
            } else {
                //用户勾选了不再询问
                //提示用户手动打开权限
                if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast.makeText(this, "相机权限已被禁止", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}

```

<font color=#008080 size=4>**首先来看requestPermission方法**</font>

1>首先判断当前应用有没有CAMERA权限，如果没有则进行申请。
```
if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) 
```

2>如果没有CAMERA权限，进行如下判断，当第一次申请权限时		shouldShowRequestPermissionRationale返回false，第一次用户拒绝，再次申请的时候返回true，在此判断中提示用户为什么要申请这个权限。
```
if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA))
```

3>如果用户点击了允许，则调用requestPermissions方法申请权限，注意里面接收的参数是一个String数组，也就是说可以同时申请多个权限，不过不建议这么做。
```
//申请相机权限
ActivityCompat.requestPermissions(this,
        new String[]{Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
```

<font color=#008080 size=4>**onRequestPermissionsResult回调方法**</font>

此方法是权限申请的回调方法，在此方法中处理权限申请成功或失败后的操作。

1>因为可以同时申请多个权限，所以回调的结果是以数组方式返回的，如果用户点击允许的话，此判断为true，可以在里面处理打开摄像头的操作。
```
if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
```

2>当多次（两次或两次以上）请求操作时，会有不再提示的选择框，如果用户选择了不再提示，shouldShowRequestPermissionRationale为false，在此判断中提示用户权限已被禁止，需要在应用管理中自行打开。
```
if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                    Toast.makeText(this, "相机权限已被禁止", Toast.LENGTH_SHORT).show();
                }
```

<font color=#008080 size=4>**shouldShowRequestPermissionRationale()说明**</font>

> 1. shouldShowRequestPermissionRationale() 默认返回 false。
 
> 2. 第一次请求权限时，如果用户拒绝了，再次请求时 shouldShowRequestPermissionRationale() 返回 true。

> 3. 多次请求权限（超过一次），用户如果选择了不再提醒并拒绝，shouldShowRequestPermissionRationale() 返回 false。

> 4. 设备的策略禁止当前应用获取这个权限的授权，shouldShowRequestPermissionRationale() 返回 false。

## **3.第三方库PermissionsDispatcher** ##

PermissionsDispatcher通过注解的方式，动态生成类处理运行时权限。配合插件使用，可自动生成代码。

github地址：https://github.com/hotchemi/PermissionsDispatcher

**使用方法如下：**

1>将下面这段代码添加到project的build.gradle文件中。

```
buildscript {
  dependencies {
    classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
  }
}
```

2>将下面的代码添加到app module的build.gradle文件中，${latest.version}填写最新的版本号，目前为2.1.3。

```
apply plugin: 'android-apt'

dependencies {
  compile 'com.github.hotchemi:permissionsdispatcher:${latest.version}'
  apt 'com.github.hotchemi:permissionsdispatcher-processor:${latest.version}'
}
```

3>在Android Studio中选择File——Setting——Plugins，搜索PermissionsDispatcher，点击install安装，如下图所示：

![安装插件](http://upload-images.jianshu.io/upload_images/3270074-90de293109158985?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

4>准备工作已经完成了，下面来进行代码的生成，在Android Studio中选择Code——Generate——Generate Runtime Permissions，如下图所示：

![生成代码](http://upload-images.jianshu.io/upload_images/3270074-9974a02a46222850?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> @NeedsPermission
> 当申请的权限被用户允许后，调用此方法。

> @OnShowRationale
> 当第一次申请权限时，用户选择拒绝，再次申请时调用此方法，在此方法中提示用户为什么需要这个权限。

> @OnPermissionDenied
> 当申请的权限被用户拒绝后，调用此方法

> @OnNeverAskAgain
> 当用户点击不再询问后，调用此方法。

代码处理如下：

```
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

```

## **4.兼容性** ##

为了保持兼容性建议使用v4包的兼容方法：
> ContextCompat.checkSelfPermission()
> ActivityCompat.requestPermissions()
> ActivityCompat.OnRequestPermissionsResultCallback
> ActivityCompat.shouldShowRequestPermissionRationale()

当targetSdkVersion小于23，但是设备是6.0系统时：

- 设备权限模型使用老的版本 
- 清单文件中列出的权限只会在安装时询问 
- 用户可以在设置列表中编辑相关权限，这对应用能否正常运行有很大影响

当targetSdkVersion大于等于23，但是设备系统小于6.0时：

- 设备权限模型使用老的版本 
- 清单文件中列出的权限只会在安装时询问 


[Demo下载](http://download.csdn.net/detail/kong_gu_you_lan/9628379)

**我把使用运行时权限遇到的一些问题汇总到了一篇博客中，欢迎点击查看[《Android 6.0运行时权限问题汇总》](http://blog.csdn.net/kong_gu_you_lan/article/details/53023287)。**

## License

```
Copyright (C) 2017 YangLe

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
