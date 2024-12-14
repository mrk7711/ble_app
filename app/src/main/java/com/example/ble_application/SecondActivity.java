package com.example.ble_application;
import java.util.Locale;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.res.Configuration;
public class SecondActivity extends AppCompat {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //applyLanguage(); // اعمال زبان انتخاب شده
        setContentView(R.layout.activity_second);
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.Continue2);
        b3 = findViewById(R.id.language);
        b4 = findViewById(R.id.language2);
        LanguageManager lang= new LanguageManager(this);
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
                lang.updateResource("en");
                recreate();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang.updateResource("fa");
                recreate();
            }
        });
    }
}