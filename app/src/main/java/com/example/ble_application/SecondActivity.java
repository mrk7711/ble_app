package com.example.ble_application;
import java.util.Locale;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.res.Configuration;
public class SecondActivity extends AppCompatActivity {
    private Button b1;
    private Button b2;
    private Button b3;
    private boolean isEnglish = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //applyLanguage(); // اعمال زبان انتخاب شده
        setContentView(R.layout.activity_second);
        isEnglish = LocaleHelper.getLanguage(this).equals("en");
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.Continue2);
        b3 = findViewById(R.id.language);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, ThirdActivity.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, DemoSlideActivity.class));
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEnglish)
                {
                    LocaleHelper.setLocale(SecondActivity.this, "fa");
                }
                else
                {
                    LocaleHelper.setLocale(SecondActivity.this, "en");
                }
                isEnglish = !isEnglish;
                recreate();
                    //LocaleHelper.setLocale(SecondActivity.this, "fa");
                    //recreate();
            }
        });
    }
    private void applyLanguage() {
        String currentLanguage = LocaleHelper.getLanguage(this);
        LocaleHelper.setLocale(this, currentLanguage);
    }
}