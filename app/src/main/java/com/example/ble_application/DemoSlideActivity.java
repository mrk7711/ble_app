package com.example.ble_application;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DemoSlideActivity extends AppCompat {

    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;
    private Button b1,b2,b3,b4,b5;
    private ImageButton i1,i2,i3,i4;
    private SeekBar s1,s2;
    private TextView t1;
    private int x,y;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_slide);
        viewPager = findViewById(R.id.viewPager);
        b1=findViewById(R.id.button1);
        b2=findViewById(R.id.button2);
        b3=findViewById(R.id.statusButton);
        b4=findViewById(R.id.myappButton);
        b5=findViewById(R.id.moreButton);
        i1=findViewById(R.id.mutepic1);
        i2=findViewById(R.id.mutepic2);
        i3=findViewById(R.id.mutepic3);
        i4=findViewById(R.id.mutepic4);
        s1=findViewById(R.id.slider1);
        s2=findViewById(R.id.slider2);
        t1=findViewById(R.id.show);
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
                startActivity(new Intent(DemoSlideActivity.this,DemoNoiseReduction.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoSlideActivity.this, DemoSoundEnhancer.class));

            }
        });
        i1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(0);
                t1.setVisibility(View.INVISIBLE);
            }
        });
        i2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s2.setVisibility(View.VISIBLE);
                i3.setVisibility(View.VISIBLE);
                i4.setVisibility(View.VISIBLE);
                i2.setVisibility(View.INVISIBLE);
                //i1.setImageResource(R.drawable.r);
                i3.setImageResource(R.drawable.baseline_volume_up_24);
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
                i1.setImageResource(R.drawable.baseline_volume_up_24);
            }
        });
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //currentValueTextView.setText(String.valueOf(progress));
                viewPager.setAlpha(0.1f); // کاهش شفافیت برای مات کردن
                t1.setVisibility(View.VISIBLE);
                x = progress;
                if(x==0)
                {
                    i1.setImageResource(R.drawable.baseline_volume_off_24);
                }
                else
                {
                    i1.setImageResource(R.drawable.baseline_volume_up_24);
                }
                if(x==0)
                    t1.setText("0");

                if(x==1)
                    t1.setText("1");
                if (x==2)
                    t1.setText("2");
                if (x==3)
                    t1.setText("3");
                if (x==4)
                    t1.setText("4");
                if (x==5)
                    t1.setText("5");
                if (x==6)
                    t1.setText("6");
                if (x==7)
                    t1.setText("7");
                if (x==8)
                    t1.setText("8");
                if (x==9)
                    t1.setText("9");
                if (x==10) {
                    t1.setText("10");

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
                viewPager.setAlpha(1.0f); // بازگشت به حالت عادی
                t1.setVisibility(View.GONE); // مخفی کردن متن
            }
        });
        s2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //currentValueTextView.setText(String.valueOf(progress));
                viewPager.setAlpha(0.1f); // کاهش شفافیت برای مات کردن
                t1.setVisibility(View.VISIBLE);
                y = progress;
                if(y==0)
                {
                    i2.setImageResource(R.drawable.baseline_volume_off_24);
                }
                else
                {
                    i2.setImageResource(R.drawable.baseline_volume_up_24);
                }
                if(y==0)
                    t1.setText("0");

                if(y==1)
                    t1.setText("1");
                if (y==2)
                    t1.setText("2");
                if (y==3)
                    t1.setText("3");
                if (y==4)
                    t1.setText("4");
                if (y==5)
                    t1.setText("5");
                if (y==6)
                    t1.setText("6");
                if (y==7)
                    t1.setText("7");
                if (y==8)
                    t1.setText("8");
                if (y==9)
                    t1.setText("9");
                if (y==10) {
                    t1.setText("10");

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
                viewPager.setAlpha(1.0f); // بازگشت به حالت عادی
                t1.setVisibility(View.GONE); // مخفی کردن متن
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoSlideActivity.this, Statuspage.class));

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoSlideActivity.this, Myapppage.class));

            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DemoSlideActivity.this, Morepage.class));
            }
        });
    }
}