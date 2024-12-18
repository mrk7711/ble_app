package com.example.ble_application;

import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Slide;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
public class Morepage extends AppCompat {
    private TextView exitDemoMode, guidingTips, autoActivate, about, legalInfo, support;
    private Switch switchGuidingTips, switchAutoActivate;
    private Button home,status,myapp;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_morepage);
        exitDemoMode = findViewById(R.id.exit_demo_mode);
        guidingTips = findViewById(R.id.guiding_tips_label);
        autoActivate = findViewById(R.id.auto_activate_label);
        about = findViewById(R.id.about_label);
        legalInfo = findViewById(R.id.legal_info_label);
        support = findViewById(R.id.support_label);
        switchGuidingTips = findViewById(R.id.switch_guiding_tips);
        switchAutoActivate = findViewById(R.id.switch_auto_activate);
        home=findViewById(R.id.homeButton);
        status=findViewById(R.id.statusButton);
        myapp=findViewById(R.id.myappButton);
        sharedPreferences = getSharedPreferences("ThemePrefs", MODE_PRIVATE);
        editor = sharedPreferences.edit();
        boolean isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
        switchGuidingTips.setChecked(isDarkMode);
        exitDemoMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Exit Demo mode"
                Intent intent = new Intent(Morepage.this, SecondActivity.class);
                startActivity(intent);
            }
        });

        guidingTips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Guiding tips"
                //Intent intent = new Intent(Morepage.this, GuidingTipsActivity.class);
               // startActivity(intent);
            }
        });
        switchGuidingTips.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked){
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        editor.putBoolean("isDarkMode", true);
                        editor.apply();
                    }
                    else{
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        editor.putBoolean("isDarkMode", false);
                        editor.apply();
                    }
                recreate();
            }
        });
        autoActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Auto-activate favorite locations"
                //Intent intent = new Intent(Morepage.this, AutoActivateActivity.class);
                //startActivity(intent);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "About"
                //Intent intent = new Intent(Morepage.this, AboutActivity.class);
                //startActivity(intent);
                loadFragment(new AboutFragment());
            }
        });

        legalInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Legal information"
                //Intent intent = new Intent(Morepage.this, LegalInfoActivity.class);
                //startActivity(intent);
            }
        });

        support.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "Support"
                //Intent intent = new Intent(Morepage.this, SupportActivity.class);
                //startActivity(intent);
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "home"
                Intent intent = new Intent(Morepage.this, DemoSlideActivity.class);
                startActivity(intent);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "status"
                Intent intent = new Intent(Morepage.this, Statuspage.class);
                startActivity(intent);
            }
        });
        myapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // شروع اکتیویتی جدید برای "myapp"
                Intent intent = new Intent(Morepage.this, Myapppage.class);
                startActivity(intent);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);  // به پشته اضافه کردن برای بازگشت به Fragment قبلی
        transaction.commit();
    }
}
