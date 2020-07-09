package com.example.cs492_s2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cs492_s2.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {
    //personality_func
    int count_personality = 0;
    List<String> box_personality=new ArrayList<String>();

    //String[] box_personality = new String[3];
    //personality flag
    int flag_p1 = 0; int flag_p2 = 0; int flag_p3 = 0; int flag_p4 = 0; int flag_p5 = 0;
    int flag_p6 = 0; int flag_p7 = 0; int flag_p8 = 0; int flag_p9 = 0; int flag_p10 = 0;
    int flag_p11 = 0; int flag_p12 = 0; int flag_p13 = 0; int flag_p14 = 0; int flag_p15 = 0;

    //hobby_func
    int count_hobby = 0;
    List<String> box_hobby=new ArrayList<String>();
    //hobby flag
    int flag_h1 = 0; int flag_h2 = 0; int flag_h3 = 0; int flag_h4 = 0; int flag_h5 = 0;
    int flag_h6 = 0; int flag_h7 = 0; int flag_h8 = 0; int flag_h9 = 0; int flag_h10 = 0;
    int flag_h11 = 0; int flag_h12 = 0; int flag_h13 = 0; int flag_h14 = 0; int flag_h15 = 0;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    DocumentReference documentReference = db.collection("qna").document(user.getUid());

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btn_save_profile = findViewById(R.id.btn_save_profile);

        //personality button
        final Button btn_p1 = findViewById(R.id.profile_p1);
        final Button btn_p2 = findViewById(R.id.profile_p2);
        final Button btn_p3 = findViewById(R.id.profile_p3);
        final Button btn_p4 = findViewById(R.id.profile_p4);
        final Button btn_p5 = findViewById(R.id.profile_p5);
        final Button btn_p6 = findViewById(R.id.profile_p6);
        final Button btn_p7 = findViewById(R.id.profile_p7);
        final Button btn_p8 = findViewById(R.id.profile_p8);
        final Button btn_p9 = findViewById(R.id.profile_p9);
        final Button btn_p10 = findViewById(R.id.profile_p10);
        final Button btn_p11 = findViewById(R.id.profile_p11);
        final Button btn_p12 = findViewById(R.id.profile_p12);
        final Button btn_p13 = findViewById(R.id.profile_p13);
        final Button btn_p14 = findViewById(R.id.profile_p14);
        final Button btn_p15 = findViewById(R.id.profile_p15);


        //hobby button
        final Button btn_h1 = findViewById(R.id.profile_h1);
        final Button btn_h2 = findViewById(R.id.profile_h2);
        final Button btn_h3 = findViewById(R.id.profile_h3);
        final Button btn_h4 = findViewById(R.id.profile_h4);
        final Button btn_h5 = findViewById(R.id.profile_h5);
        final Button btn_h6 = findViewById(R.id.profile_h6);
        final Button btn_h7 = findViewById(R.id.profile_h7);
        final Button btn_h8 = findViewById(R.id.profile_h8);
        final Button btn_h9 = findViewById(R.id.profile_h9);
        final Button btn_h10 = findViewById(R.id.profile_h10);
        final Button btn_h11 = findViewById(R.id.profile_h11);
        final Button btn_h12 = findViewById(R.id.profile_h12);
        final Button btn_h13 = findViewById(R.id.profile_h13);
        final Button btn_h14 = findViewById(R.id.profile_h14);
        final Button btn_h15 = findViewById(R.id.profile_h15);


        //if personality button is clicked, then count them til 3
        //if not clicked --> change background to yellow
        //if want to cancel --> change to normal state
        btn_p1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //if not clicked,
                if(flag_p1 == 0){
                    //check if user already 3 personalities
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        //if not, change the background and increment the counter
                        count_personality++;
                        clickPersonality(btn_p1);
                        flag_p1 = 1;
                        box_personality.add(btn_p1.getText().toString());
                        //box_personality[count_personality-1] = btn_p1.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p1);
                    flag_p1 = 0;
                    box_personality.remove(btn_p1.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p2 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p2);
                        flag_p2 = 1;
                        box_personality.add(btn_p2.getText().toString());
                        //box_personality[count_personality-1] = btn_p2.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p2);
                    flag_p2 = 0;
                    box_personality.remove(btn_p2.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p3 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p3);
                        flag_p3 = 1;
                        box_personality.add(btn_p3.getText().toString());
                        //box_personality[count_personality-1] = btn_p3.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p3);
                    flag_p3 = 0;
                    box_personality.remove(btn_p3.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p4 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p4);
                        flag_p4 = 1;
                        box_personality.add(btn_p4.getText().toString());
                        //box_personality[count_personality-1] = btn_p4.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p4);
                    flag_p4 = 0;
                    box_personality.remove(btn_p4.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p5 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p5);
                        flag_p5 = 1;
                        box_personality.add(btn_p5.getText().toString());
                        //box_personality[count_personality-1] = btn_p5.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p5);
                    flag_p5 = 0;
                    box_personality.remove(btn_p5.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p6 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p6);
                        flag_p6 = 1;
                        box_personality.add(btn_p6.getText().toString());
                        //box_personality[count_personality-1] = btn_p6.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p6);
                    flag_p6 = 0;
                    box_personality.remove(btn_p6.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p7 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p7);
                        flag_p7 = 1;
                        box_personality.add(btn_p7.getText().toString());
                        //box_personality[count_personality-1] = btn_p7.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p7);
                    flag_p7 = 0;
                    box_personality.remove(btn_p7.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p8 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p8);
                        flag_p8 = 1;
                        box_personality.add(btn_p8.getText().toString());
                        //box_personality[count_personality-1] = btn_p8.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p8);
                    flag_p8 = 0;
                    box_personality.remove(btn_p8.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p9 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p9);
                        flag_p9 = 1;
                        box_personality.add(btn_p9.getText().toString());
                        //box_personality[count_personality-1] = btn_p9.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p9);
                    flag_p9 = 0;
                    box_personality.remove(btn_p9.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p10 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p10);
                        flag_p10 = 1;
                        box_personality.add(btn_p10.getText().toString());
                        //box_personality[count_personality-1] = btn_p10.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p10);
                    flag_p10 = 0;
                    box_personality.remove(btn_p10.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p11 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p11);
                        flag_p11 = 1;
                        box_personality.add(btn_p11.getText().toString());
                        //box_personality[count_personality-1] = btn_p11.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p11);
                    flag_p11 = 0;
                    box_personality.remove(btn_p11.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p12 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p12);
                        flag_p12 = 1;
                        box_personality.add(btn_p12.getText().toString());
                        //box_personality[count_personality-1] = btn_p12.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p12);
                    flag_p12 = 0;
                    box_personality.remove(btn_p12.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p13 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p13);
                        flag_p13 = 1;
                        box_personality.add(btn_p13.getText().toString());
                        //box_personality[count_personality-1] = btn_p13.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p13);
                    flag_p13 = 0;
                    box_personality.remove(btn_p13.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p14 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p14);
                        flag_p14 = 1;
                        box_personality.add(btn_p14.getText().toString());
                        //box_personality[count_personality-1] = btn_p14.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p14);
                    flag_p14 = 0;
                    box_personality.remove(btn_p14.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });

        btn_p15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_p15 == 0){
                    if(count_personality == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_personality++;
                        clickPersonality(btn_p15);
                        flag_p15 = 1;
                        box_personality.add(btn_p15.getText().toString());
                        //box_personality[count_personality-1] = btn_p15.getText().toString();
                    }
                }else{
                    count_personality--;
                    unclickPersonality(btn_p15);
                    flag_p15 = 0;
                    box_personality.remove(btn_p15.getText().toString());
                    //box_personality[count_personality] = null;
                }
            }
        });


        //hobby button
        //if personality button is clicked, then count them til 3
        //if not clicked --> change background to yellow
        //if want to cancel --> change to normal state
        btn_h1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //if not clicked,
                if(flag_h1 == 0){
                    //check if user already 3 personalities
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        //if not, change the background and increment the counter
                        count_hobby++;
                        clickPersonality(btn_h1);
                        flag_h1 = 1;
                        box_hobby.add(btn_h1.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h1);
                    flag_h1 = 0;
                    box_hobby.remove(btn_h1.getText().toString());
                }
            }
        });

        btn_h2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h2 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h2);
                        flag_h2 = 1;
                        box_hobby.add(btn_h2.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h2);
                    flag_h2 = 0;
                    box_hobby.remove(btn_h2.getText().toString());
                }
            }
        });

        btn_h3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h3 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h3);
                        flag_h3 = 1;
                        box_hobby.add(btn_h3.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h3);
                    flag_h3 = 0;
                    box_hobby.remove(btn_h3.getText().toString());
                }
            }
        });

        btn_h4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h4 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h4);
                        flag_h4 = 1;
                        box_hobby.add(btn_h4.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h4);
                    flag_h4 = 0;
                    box_hobby.remove(btn_h4.getText().toString());
                }
            }
        });

        btn_h5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h5 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h5);
                        flag_h5 = 1;
                        box_hobby.add(btn_h5.getText().toString());}
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h5);
                    flag_h5 = 0;
                    box_hobby.remove(btn_h5.getText().toString());
                }
            }
        });

        btn_h6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h6 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h6);
                        flag_h6 = 1;
                        box_hobby.add(btn_h6.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h6);
                    flag_h6 = 0;
                    box_hobby.remove(btn_h6.getText().toString());
                }
            }
        });

        btn_h7.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h7 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h7);
                        flag_h7 = 1;
                        box_hobby.add(btn_h7.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h7);
                    flag_h7 = 0;
                    box_hobby.remove(btn_h7.getText().toString());
                }
            }
        });

        btn_h8.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h8 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h8);
                        flag_h8 = 1;
                        box_hobby.add(btn_h8.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h8);
                    flag_h8 = 0;
                    box_hobby.remove(btn_h8.getText().toString());
                }
            }
        });

        btn_h9.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h9 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h9);
                        flag_h9 = 1;
                        box_hobby.add(btn_h9.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h9);
                    flag_h9 = 0;
                    box_hobby.remove(btn_h9.getText().toString());
                }
            }
        });

        btn_h10.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h10 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h10);
                        flag_h10 = 1;
                        box_hobby.add(btn_h10.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h10);
                    flag_h10 = 0;
                    box_hobby.remove(btn_h10.getText().toString());
                }
            }
        });

        btn_h11.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h11 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h11);
                        flag_h11 = 1;
                        box_hobby.add(btn_h11.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h11);
                    flag_h11 = 0;
                    box_hobby.remove(btn_h11.getText().toString());
                }
            }
        });

        btn_h12.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h12 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h12);
                        flag_h12 = 1;
                        box_hobby.add(btn_h12.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h12);
                    flag_h12 = 0;
                    box_hobby.remove(btn_h12.getText().toString());
                }
            }
        });

        btn_h13.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h13 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h13);
                        flag_h13 = 1;
                        box_hobby.add(btn_h13.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h13);
                    flag_h13 = 0;
                    box_hobby.remove(btn_h13.getText().toString());
                }
            }
        });

        btn_h14.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h14 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h14);
                        flag_h14 = 1;
                        box_hobby.add(btn_h14.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h14);
                    flag_h14 = 0;
                    box_hobby.remove(btn_h14.getText().toString());
                }
            }
        });

        btn_h15.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(flag_h15 == 0){
                    if(count_hobby == 3) { startToast("3개만 선택할 수 있어요!");}
                    else{
                        count_hobby++;
                        clickPersonality(btn_h15);
                        flag_h15 = 1;
                        box_hobby.add(btn_h15.getText().toString());
                    }
                }else{
                    count_hobby--;
                    unclickPersonality(btn_h15);
                    flag_h15 = 0;
                    box_hobby.remove(btn_h15.getText().toString());
                }
            }
        });



        //upload to firebase
        btn_save_profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //updateProfile();
                Log.e("profile activity_personality", box_personality.get(0) + " "+ box_personality.get(1) + " " + box_personality.get(2));
                Log.e("profile activity_hobby", box_hobby.get(0) + " "+ box_hobby.get(1) + " " + box_hobby.get(2));
                //mystartActivity(SendQnaActivity.class);
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

//    public void updateProfile(){
//        final String job = ((EditText)findViewById(R.id.input_profile_job)).getText().toString();
//        final String personality = ((EditText)findViewById(R.id.input_profile_personality)).getText().toString();
//        final String unique = ((EditText)findViewById(R.id.input_profile_char)).getText().toString();
//        final String self_intro = ((EditText)findViewById(R.id.input_self_intro)).getText().toString();
//
//        if(job.length() > 0 && personality.length()> 0 && unique.length() > 0 && self_intro.length()>0){
//            if(self_intro.length() >100) {
//                startToast("100자 이하로 작성해 주세요. ");
//            }else{
//
//                ProfileInfo profileInfo = new ProfileInfo(job, personality, unique, self_intro);
//                if(user != null){
//                    db.collection("profile").document(user.getUid()).set(profileInfo)
//                            .addOnSuccessListener(new OnSuccessListener<Void>() {
//                                @Override
//                                public void onSuccess(Void aVoid) {
//                                    startToast("프로필 등록에 성공했어요.");
//                                    finish();
//                                }
//                            })
//                            .addOnFailureListener(new OnFailureListener() {
//                                @Override
//                                public void onFailure(@NonNull Exception e) {
//                                    startToast("프로필 등록에 실패했어요. ");
//                                }
//                            });
//                }
//            }
//        }else{
//            startToast("프로필 정보를 입력해주세요. ");
//        }
//    }

    //클릭시 버튼을 다른 배경으로 바꿔준다.
    private void clickPersonality(Button btn){
        //if button is clicked, then change the background
        btn.setBackground(getResources().getDrawable(R.drawable.box_hashtag_clicked_final));
    }


    //클릭 해제시 원래 배경으로 돌아간다.
    private void unclickPersonality(Button btn){
        btn.setBackground(getResources().getDrawable(R.drawable.box_hashtag_profile));
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
