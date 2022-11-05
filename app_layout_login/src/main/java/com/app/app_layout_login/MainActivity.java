package com.app.app_layout_login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    private CircleImageView braImageView;
    private BottomNavigationView bottomNavMenu;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        setTitle("Bra");
        braImageView = findViewById(R.id.bra_id);
        bottomNavMenu = findViewById(R.id.bottom_nav_menu);
        // 監聽 bottom_nav_menu
        bottomNavMenu.setOnItemSelectedListener((item) -> {
            switch (item.getItemId()){
                case R.id.bottom_QR: // 按下QR code menu
                    Intent intent = new Intent(context,QRCodeActivity.class);
                    startActivity(intent);
                    break;
            }
            Toast.makeText(this, item.getItemId() + "", Toast.LENGTH_SHORT).show();
            return true; // 事件到此結束 不會往下傳遞
        });
        registerForContextMenu(braImageView); // 註冊 context menu
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) { // 建立 optional menu
        // 將 options_menu.xml 轉物件 膨脹為物件
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) { // 按下 optionals menu item
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId() == R.id.bra_id){
            getMenuInflater().inflate(R.menu.context_menu , menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        Toast.makeText(this, item.getTitle(), Toast.LENGTH_SHORT).show();
        return super.onContextItemSelected(item);
    }


}