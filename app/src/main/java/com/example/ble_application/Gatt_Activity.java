package com.example.ble_application;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class Gatt_Activity extends AppCompatActivity {
    TextView status2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gatt);
        findViewByIdes();
    }

    private void findViewByIdes() {

        status2 = findViewById(R.id.status2);
        status2.setText("Connecting");
    }
}