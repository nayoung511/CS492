package com.example.cs492_s2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference = db.collection("qna").document(user.getUid());

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btn_save_profile = findViewById(R.id.btn_save_profile);

        btn_save_profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                updateProfile();
                mystartActivity(SendQnaActivity.class);
            }
        });

                /*
                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot doc = task.getResult();
                            if(doc.exists()){
                                //if user already updated qna;
                                mystartActivity(LocationActivity.class);
                            }else{
                                mystartActivity(SendQnaActivity.class);
                            }
                        }
                    }
                });
                mystartActivity(LocationActivity.class);
            }
            */




    }

    public void updateProfile(){
        final String job = ((EditText)findViewById(R.id.input_profile_job)).getText().toString();
        final String personality = ((EditText)findViewById(R.id.input_profile_personality)).getText().toString();
        final String unique = ((EditText)findViewById(R.id.input_profile_char)).getText().toString();
        final String self_intro = ((EditText)findViewById(R.id.input_self_intro)).getText().toString();

        if(job.length() > 0 && personality.length()> 0 && unique.length() > 0 && self_intro.length()>0){
            if(self_intro.length() >100) {
                startToast("100자 이하로 작성해 주세요. ");
            }else{

                ProfileInfo profileInfo = new ProfileInfo(job, personality, unique, self_intro);
                if(user != null){
                    db.collection("profile").document(user.getUid()).set(profileInfo)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    startToast("프로필 등록에 성공했어요.");
                                    finish();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    startToast("프로필 등록에 실패했어요. ");
                                }
                            });
                }
            }
        }else{
            startToast("프로필 정보를 입력해주세요. ");
        }
    }

    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    //go to c activity
    private void mystartActivity(Class c){
        Intent intent = new Intent(this,c);
        startActivityForResult(intent, 0);
    }
}
