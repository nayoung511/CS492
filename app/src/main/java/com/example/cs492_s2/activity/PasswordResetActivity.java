package com.example.cs492_s2.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class PasswordResetActivity extends AppCompatActivity {
    private FirebaseAuth mAthu;
    EditText et_user_name, et_user_email, et_password, et_age, et_password_check;
    Button btn_save, btn_register, btn_login;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout content view
        setContentView(R.layout.activity_password_reset);

        mAthu = FirebaseAuth.getInstance();

        findViewById(R.id.sendButton).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.sendButton:
                    send();
                    break;
            }
        }
    };


    private void send(){
        String email = ((EditText)findViewById(R.id.et_user_email)).getText().toString();

        if(email.length() > 0){
            mAthu.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startToast("이메일을 보냈습니다.");
                            }
                        }
                    });
        }else{
            startToast("이메일 또는 비밀번호를 입력해 주세요");
        }

    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

}
