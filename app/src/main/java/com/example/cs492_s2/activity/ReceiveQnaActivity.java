package com.example.cs492_s2.activity;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReceiveQnaActivity extends AppCompatActivity {

    DatabaseReference user = FirebaseDatabase.getInstance().getReference();
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    FirebaseUser Quser = FirebaseAuth.getInstance().getCurrentUser();
//
//    TextView question_1 = (TextView) findViewById(R.id.question_1);
//    TextView question_2 = (TextView) findViewById(R.id.question_2);
//    TextView question_3 = (TextView) findViewById(R.id.question_3);
//    TextView question_4 = (TextView) findViewById(R.id.question_4);
//
//    EditText answer_1 = (EditText) findViewById(R.id.answer_1);
//    EditText answer_2 = (EditText) findViewById(R.id.answer_2);
//    EditText answer_3 = (EditText) findViewById(R.id.answer_3);
//    EditText answer_4 = (EditText) findViewById(R.id.answer_4);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receiveqna);

        Log.e("리시브", "레이아웃 실행");

/*
        //get the document (Qna) from the user
//        DocumentReference docRef = db.collection("Qna_question").document("ntSiCZh1OdcybsGC6qPN5Q13zTz2");
//        CollectionReference qnaRef = db.collection("Qna_question");
//        Query query = qnaRef.get(qna_question).
//        //set the question based on the uploaded info
//        question_1.setTextAlignment();
//
        user.child("Qna_question").child("ntSiCZh1OdcybsGC6qPN5Q13zTz2").addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Qna qna = snapshot.getValue(Qna.class);
                        String qna_question = qna.qna_question;
                        String qna_question_2 = qna.qna_question_2;
                        String qna_question_3 = qna.qna_question_3;
                        String qna_question_4 = qna.qna_question_4;


                        question_1.setText(qna_question);
                        question_2.setText(qna_question_2);
                        question_3.setText(qna_question_3);
                        question_4.setText(qna_question_4);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );

        uploadQna();
    }

    public void uploadQna(){
        //at least 4 questions should be uploaded
        final String answer_1 = ((EditText) findViewById(R.id.answer_1)).getText().toString();
        final String answer_2 =((EditText) findViewById(R.id.answer_2)).getText().toString();
        final String answer_3 =((EditText) findViewById(R.id.answer_3)).getText().toString();
        final String answer_4 =((EditText) findViewById(R.id.answer_4)).getText().toString();

        if(answer_1.length() > 0 && answer_2.length() > 0 && answer_3.length() > 0 && answer_4.length() > 0){
            Qna_Answer_Adapter qna_answer_adapter = new Qna_Answer_Adapter(answer_1, answer_2, answer_3, answer_4);
            if(user != null){
                db.collection("Qna_question").document(Quser.getUid()).set(qna_answer_adapter)
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
        startActivityForResult(intent, 0);
    }
*/
    }
}
