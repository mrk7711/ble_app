package com.example.ble_application;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.PopupMenu;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;

public class Program extends AppCompatActivity {
    ImageButton g1;
    MenuItem a1;
    MenuItem a2;
    MenuItem a3;
    MenuItem a4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program);
        findViewByIdes();
        implementListeners();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.testmenu,menu);
         a1=menu.getItem(R.id.item1);
         a2=menu.getItem(R.id.item2);
         a3=menu.getItem(R.id.item3);
         a4=menu.getItem(R.id.item4);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item==a1)
        {
            startActivity(new Intent(Program.this, FirstMode.class));
        }
        if(item==a2)
        {
            startActivity(new Intent(Program.this, SecondMode.class));
        }
        if(item==a3)
        {
            startActivity(new Intent(Program.this, ThirdMode.class));
        }
        if(item==a4)
        {
            startActivity(new Intent(Program.this, FourthMode.class));
        }
        return super.onOptionsItemSelected(item);
    }

    private void findViewByIdes(){
        g1=(ImageButton) findViewById(R.id.g1);
    }
    private void implementListeners(){
        g1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}