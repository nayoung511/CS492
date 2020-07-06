package com.example.cs492_s2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.Qna_Question_Adapter;
import com.example.cs492_s2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class SendQnaActivity extends AppCompatActivity {
    ImageView btn_add;
    ImageView btn_delete;
    Button btn_save_qna;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendqna);

        btn_add = findViewById(R.id.btn_add);
        btn_delete = findViewById(R.id.btn_delete);
        btn_save_qna = findViewById(R.id.btn_save_qna);


        //질문 추가
        btn_add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                AddQnaActivity n_layout = new AddQnaActivity(getApplicationContext());
                LinearLayout con = findViewById(R.id.question_input);
                con.addView(n_layout);
            }
        });


        btn_save_qna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //만들어둔 정보를 올리고
                //uploadQna();
                //location Activity로 이동합니다.
                Log.e("채팅", "왜 안돼 ");
                //mystartActivity(ReceiveQnaActivity.class);
                mystartActivity(LocationActivity.class);
                //mystartActivity(ChatActivity.class);
            }
        });

    }

    public void uploadQna(){
        //at least 4 questions should be uploaded
        final String qna_question = ((EditText) findViewById(R.id.question_1)).getText().toString();
        final String qna_question_2 =((EditText) findViewById(R.id.question_2)).getText().toString();
        final String qna_question_3 =((EditText) findViewById(R.id.question_3)).getText().toString();
        final String qna_question_4 =((EditText) findViewById(R.id.question_4)).getText().toString();

        if(qna_question.length() > 0 && qna_question_2.length() > 0 && qna_question_3.length() > 0 && qna_question_4.length() > 0){
            Qna_Question_Adapter qna_question_adapter = new Qna_Question_Adapter(qna_question, qna_question_2, qna_question_3, qna_question_4);
            if(user != null){
                db.collection("Qna_question").document(user.getUid()).set(qna_question_adapter)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("질문 등록에 성공했습니다. ");
                                finish();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                startToast("질문 등록에 실패했습니다. ");
                            }
                        });
            }

        }else{
            startToast("질문을 입력해주세요. ");
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
