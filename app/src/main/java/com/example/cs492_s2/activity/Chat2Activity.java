package com.example.cs492_s2.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;

public class Chat2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_2);
        Log.e("채팅", "채팅 액티비티 실행  ");
    }
}
