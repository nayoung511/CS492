package com.example.cs492_s2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAthu;
    private static final String TAG = "register";
    EditText et_user_name, et_user_email, et_password, et_age, et_password_check;
    Button btn_save, btn_register, btn_login;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout content view
        setContentView(R.layout.activity_register);

        mAthu = FirebaseAuth.getInstance();

        findViewById(R.id.btn_register).setOnClickListener(onClickListener);
        findViewById(R.id.gotoLoginButton).setOnClickListener(onClickListener);

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }


    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_register:
                    signUp();
                    break;
                case R.id.gotoLoginButton:
                    mystartActivity(LoginActivity.class);
                    break;
            }
        }
    };


    private void signUp(){
        String email = ((EditText)findViewById(R.id.et_user_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.et_password)).getText().toString();
        String passwordCheck = ((EditText)findViewById(R.id.et_password_check)).getText().toString();

        if(email.length() > 0 && password.length() > 0 && passwordCheck.length() > 0){
            if(password.equals(passwordCheck)) {

                mAthu.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    FirebaseUser user = mAthu.getCurrentUser();
                                    startToast("회원가입 성공했어요");
                                    mystartActivity(MainActivity.class);
                                    //success: updateUI(user);
                                } else {
                                    if (task.getException() != null) {
                                        startToast(task.getException().toString());
                                    }
                                    // If sign in fails, display a message to the user.
                                    //fail UIupdateUI(null);
                                }

                            }
                        });
            }else{
                startToast("비밀번호가 일치하지 않습니다");//입력한 비밀번호와 비밀번호 확인이 일치하지 않음
                Toast.makeText(RegisterActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();

            }
        }else{
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
