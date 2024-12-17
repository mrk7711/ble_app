package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;

public class ForTestEqualizer extends AppCompatActivity {
    private EqualizerBackgroundView backgroundView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_test_equalizer);
        backgroundView = findViewById(R.id.equalizerBackground);
        SeekBar seekBarBass = findViewById(R.id.seekBar2);
        SeekBar seekBarMid = findViewById(R.id.seekBar3);
        SeekBar seekBarTreble = findViewById(R.id.seekBar4);
        seekBarBass.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarMid.setOnSeekBarChangeListener(seekBarChangeListener);
        seekBarTreble.setOnSeekBarChangeListener(seekBarChangeListener);
        updateBackground(seekBarBass.getProgress(), seekBarMid.getProgress(), seekBarTreble.getProgress());
    }

    private void updateBackground(int bass, int mid, int treble) {
        backgroundView.setValues(bass, mid, treble);
    }

    private final SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            SeekBar seekBarBass = findViewById(R.id.seekBar2);
            SeekBar seekBarMid = findViewById(R.id.seekBar3);
            SeekBar seekBarTreble = findViewById(R.id.seekBar4);

            updateBackground(seekBarBass.getProgress(), seekBarMid.getProgress(), seekBarTreble.getProgress());
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) { }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) { }
    };
}
