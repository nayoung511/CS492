package com.example.cs492_s2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MemberinitActivity extends AppCompatActivity {
    private static final String TAG = "MemberinitActivity";
    private FirebaseAuth mAthu;
    EditText et_birthdate, et_phone, et_name, et_location ;
    Button btn_check;

    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //layout content view
        setContentView(R.layout.activity_member_init);

        mAthu = FirebaseAuth.getInstance();
        findViewById(R.id.btn_check).setOnClickListener(onClickListener);
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        finish();
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_check:
                    profileUpdate();
                    break;
            }
        }
    };

    private void profileUpdate(){
        String name = ((EditText)findViewById(R.id.et_name)).getText().toString();
        String phone = ((EditText)findViewById(R.id.et_phoneNum)).getText().toString();
        String birthdate = ((EditText)findViewById(R.id.et_birthdate)).getText().toString();
        String location = ((EditText)findViewById(R.id.et_location)).getText().toString();

        if(name.length() > 0 && phone.length()>9 && birthdate.length() > 5 && location.length()>0){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            // Access a Cloud Firestore instance from your Activity
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            //유저 정보
            MemberInfo memberInfo = new MemberInfo(name, phone, birthdate, location);

            if(user != null){
                db.collection("users").document(user.getUid()).set(memberInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void avoid) {
                                startToast("회원정보 등록을 성공했어요");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("회원정보 등록 실패했어요");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }



        }else{
            startToast("회원 정보를 입력해주세요");
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
