package com.app_saved_instance_state;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Button button;
    private TextView textview;
    private  Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);
        textview = findViewById(R.id.text_view);

        button.setOnClickListener((view -> {
            int lotto = r.nextInt(100);
            textview.setText(lotto +"");
        }));
        /*
        if(savedInstanceState != null && savedInstanceState.getString("lotto")!= null){
            textview.setText(savedInstanceState.getString("lotto"));
        }
        */
    }

    //若 savedInstanceState 有資料 則將資料取出
    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        if(savedInstanceState != null && savedInstanceState.getString("lotto")!= null){
            textview.setText(savedInstanceState.getString("lotto"));
        }
        super.onRestoreInstanceState(savedInstanceState);
    }

    // 將 lotto 資料放入 Bundle 物件中
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString("lotto", textview.getText().toString());
        super.onSaveInstanceState(outState);
    }
}