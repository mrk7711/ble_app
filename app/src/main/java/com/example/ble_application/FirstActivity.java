package com.example.ble_application;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.media.MediaPlayer;
import android.os.Bundle;

public class FirstActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.firstactivity);
        //mediaPlayer = MediaPlayer.create(this, R.raw.ce4972e0a2287d91b61ed22049685d07);
        //mediaPlayer.start();
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //mediaPlayer.stop();
                //mediaPlayer.release();
                FirstActivity.this.startActivity(new Intent(FirstActivity.this,SecondActivity.class));
                FirstActivity.this.finish();
            }
        },3000);
    }
}