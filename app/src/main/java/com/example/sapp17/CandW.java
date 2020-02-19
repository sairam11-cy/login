package com.example.sapp17;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CandW extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cand_w);
    }

    public void customer1(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }

    public void partner1(View view) {
        startActivity(new Intent(getApplicationContext(),Register.class));
    }
}
