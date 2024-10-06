package com.example.ble_application;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

public class SlideActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private SliderAdapter sliderAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide);
        viewPager = findViewById(R.id.viewPager);
        // لیست صفحات
        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.p1, "P1", "Noise Reduction", "Sound Enhancer"));
        sliderItems.add(new SliderItem(R.drawable.p2, "P2", "Noise Reduction", "Sound Enhancer"));
        sliderItems.add(new SliderItem(R.drawable.p3, "P3", "Noise Reduction", "Sound Enhancer"));
        sliderItems.add(new SliderItem(R.drawable.p4, "P4", "Noise Reduction", "Sound Enhancer"));

        // تنظیم آداپتر برای ViewPager
        sliderAdapter = new SliderAdapter(sliderItems);
        viewPager.setAdapter(sliderAdapter);
    }
}
