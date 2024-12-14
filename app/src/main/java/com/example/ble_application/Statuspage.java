package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Statuspage extends AppCompat {
    private Button home,myapp,more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statuspage);
        home=findViewById(R.id.homeButton);
        myapp=findViewById(R.id.myappButton);
        more=findViewById(R.id.moreButton);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "home"
                Intent intent = new Intent(Statuspage.this, DemoSlideActivity.class);
                startActivity(intent);
            }
        });
        myapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "status"
                Intent intent = new Intent(Statuspage.this, Myapppage.class);
                startActivity(intent);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "myapp"
                Intent intent = new Intent(Statuspage.this, Morepage.class);
                startActivity(intent);
            }
        });
    }
}