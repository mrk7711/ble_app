package com.example.ble_application;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class VolumePage extends AppCompat {

    private SeekBar volumeSeekBar;
    private TextView currentValueTextView;
    int progressChangedValue = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_page);

        volumeSeekBar = findViewById(R.id.seekBar);
        currentValueTextView= findViewById(R.id.value);

        // تنظیم مقدار فعلی اسلایدر
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //currentValueTextView.setText(String.valueOf(progress));
                progressChangedValue = progress;
                currentValueTextView.setText(String.valueOf(progressChangedValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // کاری انجام نمی‌شود
                //showToast(String.valueOf(progressChangedValue));
            }
        });
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
