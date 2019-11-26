package com.swangq.com.suanfa;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import com.sample.breakpad.BreakpadInit;

import java.io.File;

/**
 * @author swangq
 * @date 2019/11/24
 * @describe TODO
 */
public class BreakpadActivity extends Activity {
    private Button mButton;
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 100;

    static {
        System.loadLibrary("local-native-lib");
    }

    private File externalReportPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_EXTERNAL_STORAGE_REQUEST_CODE);
        } else {
            initExternalReportPath();
        }
        mButton = findViewById(R.id.button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initBreakPad();
                crash();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        initExternalReportPath();
    }

    /**
     * 一般来说，crash捕获初始化都会放到Application中，这里主要是为了大家有机会可以把崩溃文件输出到sdcard中
     * 做进一步的分析
     */
    private void initBreakPad() {
        if (externalReportPath == null) {
            externalReportPath = new File(getFilesDir(), "mydump");
            if (!externalReportPath.exists()) {
                externalReportPath.mkdirs();
            }
        }
        BreakpadInit.initBreakpad(externalReportPath.getAbsolutePath());
    }


    private void initExternalReportPath() {
        externalReportPath = new File(Environment.getExternalStorageDirectory(), "mydump");
        if (!externalReportPath.exists()) {
            externalReportPath.mkdirs();
        }
    }

    public native void crash();
}
