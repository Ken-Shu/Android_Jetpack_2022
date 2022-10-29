package com.app.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

// 主程式
public class MainActivity extends AppCompatActivity {

    // 程式進入點
    // Bundle 存放資料的集合
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 配置UI畫面
        setContentView(R.layout.activity_main);
    }
}