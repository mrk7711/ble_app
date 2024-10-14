package com.example.ble_application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.slider.Slider;

public class SecondMode extends AppCompatActivity {
    Slider slider1;
    Slider slider2;
    Slider slider3;
    Slider slider4;
    Slider slider5;
    TextView text1;
    TextView text2;
    TextView text4;
    TextView text5;
    TextView text6;

    private Button n1;
    private Button e1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_mode);
        findViewByIdes();
        implementListeners();
    }
    private void implementListeners(){

        slider1.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                text1.setText(Integer.toString((int) value));
            }
        });

        slider2.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                text2.setText(Integer.toString((int) value));
            }
        });
        n1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slider3.setVisibility(View.VISIBLE);
                slider4.setVisibility(View.VISIBLE);
                slider5.setVisibility(View.VISIBLE);
                text4.setVisibility(View.VISIBLE);
                text5.setVisibility(View.VISIBLE);
                text6.setVisibility(View.VISIBLE);
            }
        });
        e1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                slider3.setVisibility(View.VISIBLE);
                slider4.setVisibility(View.VISIBLE);
                slider5.setVisibility(View.VISIBLE);
                text4.setVisibility(View.VISIBLE);
                text5.setVisibility(View.VISIBLE);
                text6.setVisibility(View.VISIBLE);
            }
        });
    }
    private void findViewByIdes(){
        slider1=findViewById(R.id.slider1);
        slider2=findViewById(R.id.slider2);
        slider3=findViewById(R.id.slider3);
        slider4=findViewById(R.id.slider4);
        slider5=findViewById(R.id.slider5);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        text4=findViewById(R.id.text4);
        text5=findViewById(R.id.text5);
        text6=findViewById(R.id.text6);
        e1=findViewById(R.id.E1);
        n1=findViewById(R.id.N1);
    }
}