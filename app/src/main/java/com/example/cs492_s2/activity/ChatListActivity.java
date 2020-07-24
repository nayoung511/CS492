package com.example.cs492_s2.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class ChatListActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatlist);


        mDatabase = FirebaseDatabase.getInstance().getReference();


    }

    private void getUserChat(String userId, String name, String time, String content, int unread, String photoUrl){
        ChatList chatList = new ChatList(name, time, content, unread, photoUrl);

        mDatabase.child("users").child(userId).setValue(chatList);



    }



}

//create chatList object
class ChatList {
    private String name;
    private String time;
    private String content;
    private int unread;
    private String photoUrl;

    public ChatList(String name, String time, String content, int unread, String photoUrl){
        this.name = name;
        this.time = time;
        this.content = content;
        this.unread = unread;
        this.photoUrl = photoUrl;
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUnread() {
        return unread;
    }

    public void setUnread(int unread) {
        this.unread = unread;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
