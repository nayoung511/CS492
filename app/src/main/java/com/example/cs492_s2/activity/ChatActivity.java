package com.example.cs492_s2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cs492_s2.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList;
    private String nick = "배나영";
    private DatabaseReference myRef;

    Button btn_send_chat;
    EditText chatbox;
    TextView user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_2);

        //전송 버튼
        btn_send_chat = findViewById(R.id.Button_send);
        //메세지 내용_상대
//        other_chat_box = findViewById(R.id.other_chat_box);
//        //메세지 내용_나
//        my_chat_box = findViewById(R.id.my_chat_box);
        //메세지 박스 전송
        chatbox = findViewById(R.id.EditText_chat);
        user_name = findViewById(R.id.user_name);


        //상단 채팅방 대체
        //상단 검색 결과로 텍스트 대치
        Intent intent = getIntent();
        String user_name_chat = intent.getExtras().getString("user_name");
        user_name.setText(user_name_chat);


        //1. recyclerView - 반복
        btn_send_chat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String msg = chatbox.getText().toString(); //보낼 메세지 전송

                if(msg != null){
                    ChatData chat = new ChatData();
                    chat.setNickname(nick);
                    chat.setMsg(msg);
                    myRef.push().setValue(chat);
                    //전송 후 editText를 비워줍니다.
                    chatbox.getText().clear();
                }
            }
        });

        mRecyclerView = findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatAdapter(chatList, ChatActivity.this, nick);

        mRecyclerView.setAdapter(mAdapter);

        //db declaration
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = FirebaseDatabase.getInstance().getReference(); //database.getReference();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                //Log.e("CHATCHAT", snapshot.getValue().toString());
                ChatData chat = snapshot.getValue(ChatData.class);
                ((ChatAdapter)mAdapter).addChat(chat);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}
