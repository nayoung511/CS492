package com.example.cs492_s2.activity;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.example.cs492_s2.R;


//purpose: when the users clicks btn_add, inflate the question layout
public class AddQnaActivity extends LinearLayout {
    public AddQnaActivity(Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    public AddQnaActivity(Context context){
        super(context);
        init(context);
    }

    private void init(Context context){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.activity_add_qna, this, true);
    }
}
