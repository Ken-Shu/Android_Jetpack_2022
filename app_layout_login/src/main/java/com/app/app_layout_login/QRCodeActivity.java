package com.app.app_layout_login;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.Result;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler{

    private ZXingScannerView scannerView;
    private TextView textResult;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode);

        context = this;

        scannerView = findViewById(R.id.zxscan);
        textResult = findViewById(R.id.text_result);

        //請求權限
        Dexter.withActivity(this)
                .withPermission(Manifest.permission.CAMERA)
                .withListener(new PermissionListener() { // 監聽使用者是否允許 Camera 權限
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        // 掃描處理結果
                        scannerView.setResultHandler(QRCodeActivity.this);
                        // 啟用相機
                        scannerView.startCamera();
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse response) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {

                    }
                })
                .check();
    }

    @Override
    public void handleResult(Result result) {
        textResult.setText(result.getText());
        //連續掃描
        scannerView.startCamera();
        scannerView.resumeCameraPreview(QRCodeActivity.this);
    }
}