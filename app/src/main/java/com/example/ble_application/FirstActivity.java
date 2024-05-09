package com.example.ble_application;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                FirstActivity.this.startActivity(new Intent(FirstActivity.this,SecondActivity.class));
                FirstActivity.this.finish();
            }
        },4000);
    }
}