package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class ThirdActivity extends AppCompatActivity {
    private BluetoothAdapter BA;
    Button b1;
    Button b2;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.demo);
        BA = BluetoothAdapter.getDefaultAdapter();
        if(BA.isEnabled())
        {
            new Handler().postDelayed(new Runnable(){
                @Override
                public void run() {
                    ThirdActivity.this.startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
                    ThirdActivity.this.finish();
                }
            },2000);
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!BA.isEnabled()) {
                    Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(turnOn, 0);
                    flag=1;
                    new Handler().postDelayed(new Runnable(){
                        @Override
                        public void run() {
                            if(flag==1)
                            {
                                ThirdActivity.this.startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
                                ThirdActivity.this.finish();
                            }
                        }
                    },4000);
                }
                else
                {
                    startActivity(new Intent(ThirdActivity.this, FourthActivity.class));
                }

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this, MainActivity.class));
            }
        });
    }
}