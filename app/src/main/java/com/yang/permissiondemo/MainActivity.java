package com.yang.permissiondemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_runtime_permissions, R.id.btn_permissions_dispatcher})
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_runtime_permissions:
                intent = new Intent(this, RuntimePermissionsActivity.class);
                break;

            case R.id.btn_permissions_dispatcher:
                intent = new Intent(this, PermissionsDispatcherActivity.class);
                break;
        }
        if (intent != null) {
            startActivity(intent);
        }
    }
}
