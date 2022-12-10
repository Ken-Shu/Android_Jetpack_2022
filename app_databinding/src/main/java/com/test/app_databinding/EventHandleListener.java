package com.test.app_databinding;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class EventHandleListener {
    private Context context;

    public EventHandleListener(Context context){
        this.context = context;
    }
    public void onButtonClicked(View view){
        Toast.makeText(context, "Click me! ", Toast.LENGTH_SHORT).show();
    }
}