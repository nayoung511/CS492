package com.example.cs492_s2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;

public class AdminActivity extends AppCompatActivity {

    Button btn_send_first_greeting;
    Button btn_retrieve_token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout content view
        setContentView(R.layout.activity_admin);

        btn_send_first_greeting = findViewById(R.id.btn_send_first_greeting);
        btn_retrieve_token = findViewById(R.id.btn_retrieve_token);

        btn_send_first_greeting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mystartActivity(AdminSendActivity.class);
            }
        });

        btn_retrieve_token.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mystartActivity(AdminPushActivity.class);
            }
        });
    }


//go to c activity
        private void mystartActivity(Class c){
            Intent intent = new Intent(this,c);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
}
