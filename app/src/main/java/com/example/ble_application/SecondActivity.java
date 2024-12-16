package com.example.ble_application;
import java.util.Locale;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import java.util.ArrayList;
import java.util.List;
import android.widget.Button;
import android.widget.Spinner;
import android.content.res.Configuration;
public class SecondActivity extends AppCompat {
    private Button b1;
    private Button b2;
    private Button b3;
    private Button b4;
    private Button b5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //applyLanguage(); // اعمال زبان انتخاب شده
        setContentView(R.layout.activity_second);
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.Continue2);
        b3 = findViewById(R.id.language);
        b4 = findViewById(R.id.language2);
        b5 = findViewById(R.id.togglelanguage);

        LanguageManager lang= new LanguageManager(this);
        String currentLang = lang.getLang();
        b5.setText(currentLang.equals("fa") ? "en" : "fa");
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
                //lang.updateResource("en");
                //recreate();
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //lang.updateResource("fa");
                //recreate();
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lang.updateResource(b5.getText().toString());
                recreate();
            }
        });
    }
}