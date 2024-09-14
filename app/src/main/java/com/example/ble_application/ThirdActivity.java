package com.example.ble_application;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import android.widget.Button;
import android.widget.Toast;
import android.provider.Settings;
public class ThirdActivity extends AppCompatActivity {
    private BluetoothAdapter BA;
    private ActivityResultLauncher<Intent> enableBluetoothLauncher;
    Button b1;
    Button b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        b1 = findViewById(R.id.Continue);
        b2 = findViewById(R.id.demo);
        // دریافت BluetoothAdapter برای دسترسی به تنظیمات بلوتوث دستگاه
        BA = BluetoothAdapter.getDefaultAdapter();

        // بررسی اینکه آیا دستگاه بلوتوث دارد یا نه

        if(BA == null)
        {
            showToast("There is not any bluetooth");
        }
        if(!BA.isEnabled()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                // Use the modern method for Android API level 30 and above
                enableBluetoothLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                    if (result.getResultCode() == RESULT_OK) {
                        // Bluetooth is enabled
                        showToast("Bluetooth is on");
                        ThirdActivity.this.startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
                    } else {
                        // Bluetooth is not enabled
                        showToast("Bluetooth is off");
                    }
                });

                Intent enableBtIntent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                enableBluetoothLauncher.launch(enableBtIntent);
            } else {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
            }
        }
        // بررسی وضعیت بلوتوث

        else
        {
            ThirdActivity.this.startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
            ThirdActivity.this.finish();
        }
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!BA.isEnabled()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                        // Use the modern method for Android API level 30 and above
                        enableBluetoothLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                            if (result.getResultCode() == RESULT_OK) {
                                // Bluetooth is enabled
                                showToast("Bluetooth is on");
                                ThirdActivity.this.startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
                            } else {
                                // Bluetooth is not enabled
                                showToast("Bluetooth is off");
                            }
                        });

                        Intent enableBtIntent = new Intent(Settings.ACTION_BLUETOOTH_SETTINGS);
                        enableBluetoothLauncher.launch(enableBtIntent);
                    } else {
                        Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(enableBtIntent, 1);
                    }

                }
                else
                {
                    ThirdActivity.this.startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
                    ThirdActivity.this.finish();
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ThirdActivity.this, MainActivity.class));
            }
        });
    }

    // دریافت نتیجه درخواست روشن کردن بلوتوث
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // اگر کاربر بلوتوث را روشن کرد
                showToast("Bluetooth is on");
                ThirdActivity.this.startActivity(new Intent(ThirdActivity.this,FourthActivity.class));
            }
            else
            {
                // اگر کاربر بلوتوث را روشن نکرد
                showToast("Bluetooth is off");
            }
        }
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}