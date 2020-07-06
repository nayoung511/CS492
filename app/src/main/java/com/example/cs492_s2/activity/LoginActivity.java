package com.example.cs492_s2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAthu;
    private static final String TAG = "register";
    EditText et_user_name, et_user_email, et_password, et_age;
    Button btn_save, btn_register;
    TextView btn_login;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout content view
        setContentView(R.layout.activity_login);

        mAthu = FirebaseAuth.getInstance();
        findViewById(R.id.btn_login).setOnClickListener(onClickListener);
        findViewById(R.id.gotoPasswordResetBtn).setOnClickListener(onClickListener);


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_login:
                    login();
                    break;
                case R.id.gotoPasswordResetBtn:
                    mystartActivity(PasswordResetActivity.class);
                    break;
            }
        }
    };


    private void login(){
        String email = ((EditText)findViewById(R.id.et_user_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.et_password)).getText().toString();

        if(email.length() > 0 && password.length() > 0){
            //check for the admin
            if(email.equals("cs492_admin@admin.com") && password.equals("admin1234")){
                mystartActivity(AdminActivity.class);
            }
            else{
                mAthu.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAthu.getCurrentUser();
                                    startToast("로그인 성공했어요");
                                    mystartActivity(MemberinitActivity.class);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    if (task.getException() != null) {
                                        startToast("로그인 실패했어요");
                                        startToast(task.getException().toString());
                                    }
                                }
                            }
                        });
            }

        }
        else{
            startToast("이메일 또는 비밀번호를 입력해 주세요");
        }


    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    //go to c activity
    private void mystartActivity(Class c){
        Intent intent = new Intent(this,c);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
