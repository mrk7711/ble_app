package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

public class Myapppage extends AppCompat {
    private TextView findhearingaid, tapcontrol, learnabout, guidingtips, ratemysound, requestassistance, newsetting, update;
    private Button home,status,more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myapppage);
        findhearingaid = findViewById(R.id.exit_demo_mode);
        tapcontrol = findViewById(R.id.guiding_tips_label);
        learnabout = findViewById(R.id.general_label);
        guidingtips = findViewById(R.id.about_label);
        ratemysound = findViewById(R.id.support_label);
        requestassistance = findViewById(R.id.request_label);
        newsetting = findViewById(R.id.setting_label);
        update = findViewById(R.id.update_label);
        home=findViewById(R.id.homeButton);
        status=findViewById(R.id.statusButton);
        more=findViewById(R.id.moreButton);
        findhearingaid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Exit Demo mode"
                //Intent intent = new Intent(Myapppage.this, ConnectionPage.class);
                //startActivity(intent);
            }
        });

        tapcontrol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Guiding tips"
                //Intent intent = new Intent(Morepage.this, GuidingTipsActivity.class);
                // startActivity(intent);
            }
        });

        learnabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Auto-activate favorite locations"
                //Intent intent = new Intent(Morepage.this, AutoActivateActivity.class);
                //startActivity(intent);
            }
        });

        guidingtips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "About"
                //Intent intent = new Intent(Morepage.this, AboutActivity.class);
                //startActivity(intent);
                //loadFragment(new AboutFragment());
            }
        });

        ratemysound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Legal information"
                //Intent intent = new Intent(Morepage.this, LegalInfoActivity.class);
                //startActivity(intent);
            }
        });

        requestassistance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Support"
                //Intent intent = new Intent(Morepage.this, SupportActivity.class);
                //startActivity(intent);
            }
        });
        newsetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Legal information"
                //Intent intent = new Intent(Morepage.this, LegalInfoActivity.class);
                //startActivity(intent);
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Legal information"
                //Intent intent = new Intent(Morepage.this, LegalInfoActivity.class);
                //startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "home"
                Intent intent = new Intent(Myapppage.this, DemoSlideActivity.class);
                startActivity(intent);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "status"
                Intent intent = new Intent(Myapppage.this, Statuspage.class);
                startActivity(intent);
            }
        });
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "myapp"
                Intent intent = new Intent(Myapppage.this, Morepage.class);
                startActivity(intent);
            }
        });
    }
}

