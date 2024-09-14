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
        sliderItems.add(new SliderItem(R.drawable.photo_5992214955950195160_y, "All_Around", "Noise filter", "Speech clarity"));
        sliderItems.add(new SliderItem(R.drawable.photo_5992214955950195161_y, "Hear in noise", "Noise filter", "Speech clarity"));
        sliderItems.add(new SliderItem(R.drawable.photo_5992214955950195162_y, "Outdoor", "Option 1", "Option 2"));
        sliderItems.add(new SliderItem(R.drawable.photo_5992214955950195163_y, "Lecture", "Noise filter", "Speech clarity"));

        // تنظیم آداپتر برای ViewPager
        sliderAdapter = new SliderAdapter(sliderItems);
        viewPager.setAdapter(sliderAdapter);
    }
}
