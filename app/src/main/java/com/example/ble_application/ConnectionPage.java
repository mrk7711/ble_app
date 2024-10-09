package com.example.ble_application;
import android.animation.ObjectAnimator;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
public class ConnectionPage extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_page);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        ObjectAnimator rotate = ObjectAnimator.ofFloat(progressBar, "rotation", 0f, 360f);
        rotate.setDuration(6000);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setRepeatCount(ObjectAnimator.INFINITE);
        rotate.start(); //
    }
}