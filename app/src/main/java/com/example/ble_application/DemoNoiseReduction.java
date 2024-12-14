package com.example.ble_application;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;



public class DemoNoiseReduction extends AppCompat {
    private Button b1;
    private SeekBar s1;
    private TextView t1;
    private int x=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_noise_reduction);
        b1=findViewById(R.id.reset);
        s1=findViewById(R.id.seekBar);
        t1=findViewById(R.id.textView1);
        s1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                x = progress;
                if (x==0)
                    t1.setText(getString(R.string.OFF));
                if (x==1)
                    t1.setText(getString(R.string.Mild));
                if (x==2)
                    t1.setText(getString(R.string.Moderate));
                if (x==3)
                    t1.setText(getString(R.string.Considerable));
                if (x==4)
                    t1.setText(getString(R.string.Strong));
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

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                s1.setProgress(1);
            }
        });
    }
}