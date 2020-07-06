package com.example.cs492_s2.activity;

import android.widget.ImageView;

import java.io.Serializable;

public class ChatData implements Serializable {

    private String msg;
    private String nickname;
    private ImageView profile_img;

    public ImageView getProfile_img() {return profile_img;}

    public void setProfile_img(ImageView profile_img) {
        this.profile_img = profile_img;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getNickname(){
        return nickname;
    }

    public void setNickname(String nickname){
        this.nickname = nickname;
    }
}
