package com.app_viewmodel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;


import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText editHeight, editWeight;
    private TextView textResult, textRecord;
    private Button button;
    private MyViewModel myviewModel;
    private BmiAdapter bmiAdapter;
    private ListView listView;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //全螢幕 定要在 setContentView 上方
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);
        context = this;

        setTitle("BMI 計算器");

        //adapter
        bmiAdapter = new BmiAdapter(context);
        listView = findViewById(R.id.list_view);
        listView.setAdapter(bmiAdapter); // 設定listView適配器

        myviewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        editHeight = findViewById(R.id.edit_height);
        editWeight = findViewById(R.id.edit_weight);
        textResult = findViewById(R.id.text_result);

        button = findViewById(R.id.button);
        dataBinding();

        button.setOnClickListener((view -> {
           double h = Double.parseDouble(editHeight.getText().toString());
           double w = Double.parseDouble(editWeight.getText().toString());
           Bmi bmi = new Bmi(h,w);
           myviewModel.addBmi(bmi);
           dataBinding();
        }));
    }

    @Override
    protected void onStart() {
        dataBinding();
        super.onStart();
    }

    //databinding
    private void dataBinding(){
        List<Bmi> bmiList = myviewModel.getBmiList();
        if(bmiList.size() <= 0){ return;}
        //將最新的資料配置在 UI 上
        Bmi lastbmi = bmiList.get(bmiList.size()-1);
        editHeight.setText(String.format("%.1f",lastbmi.getHeight()));
        editWeight.setText(String.format("%.1f",lastbmi.getWeight()));
        textResult.setText(String.format("%.2f",lastbmi.getBmi()));
        //放入歷史資料
        bmiAdapter.setBmiList(bmiList); // 在適配器中更新 bmiList
        bmiAdapter.notifyDataSetChanged(); //通知變更
    }
}