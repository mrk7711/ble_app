package com.example.ble_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

public class SlideActivity extends AppCompatActivity {
Slider slider1;
Slider slider2;
TextView text1;
TextView text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        findViewByIdes();
        implementListeners();
    }
    private void implementListeners(){

        slider1.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                text1.setText(Float.toString(value));
            }
        });

        slider2.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                text2.setText(Float.toString(value));
            }
        });
    }
    private void findViewByIdes(){
        slider1=findViewById(R.id.slider1);
        slider2=findViewById(R.id.slider2);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
    }
}