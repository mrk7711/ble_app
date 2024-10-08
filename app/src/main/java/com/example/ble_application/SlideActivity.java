package com.example.ble_application;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class SlideActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;
    Button b1;
    Button b2;
    ImageButton i1;
    ImageButton i2;
    ImageButton i3;
    ImageButton i4;
    SeekBar s1;
    SeekBar s2;
    int x;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        viewPager = findViewById(R.id.viewPager);
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        i1=findViewById(R.id.mutepic1);
        i2=findViewById(R.id.mutepic2);
        i3=findViewById(R.id.mutepic3);
        i4=findViewById(R.id.mutepic4);
        s1=findViewById(R.id.slider1);
        s2=findViewById(R.id.slider2);
        // لیست صفحات
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.p1, "P1"));
        sliderItems.add(new SliderItem(R.drawable.p2, "P2"));
        sliderItems.add(new SliderItem(R.drawable.p3, "P3"));
        sliderItems.add(new SliderItem(R.drawable.p4, "P4"));

        // تنظیم آداپتر برای ViewPager
        sliderAdapter = new SliderAdapter(sliderItems);
        viewPager.setAdapter(sliderAdapter);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlideActivity.this,NoiseReduction.class));

            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SlideActivity.this, SoundEnhancer.class));

            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s2.setVisibility(View.VISIBLE);
                i3.setVisibility(View.VISIBLE);
                i4.setVisibility(View.VISIBLE);
                i2.setVisibility(View.INVISIBLE);
                i1.setImageResource(R.drawable.r);
                i3.setImageResource(R.drawable.l);
                x=s1.getProgress();
                s2.setProgress(x);
            }
        });
        i4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s2.setVisibility(View.INVISIBLE);
                i4.setVisibility(View.INVISIBLE);
                i2.setVisibility(View.VISIBLE);
                i3.setVisibility(View.INVISIBLE);
                i1.setImageResource(R.drawable.baseline_volume_mute_24);
            }
        });
    }
}
