package com.example.sapp17;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class slide1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide1);
    }



    public void customer(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
    }

    public void partner(View view) {
        startActivity(new Intent(getApplicationContext(),login.class));
    }

    public void new1(View view) {
        startActivity(new Intent(getApplicationContext(),CandW.class));
    }
}
