package com.example.cs492_s2.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;

public class IdealDetailActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ideal_detail);

        Log.e("아이디얼 디테일", "After loading");

    }
}
