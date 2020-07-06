package com.example.cs492_s2;

public class Qna_Answer_Adapter {
    private String qna_answer;
    private String qna_answer_1;
    private String qna_answer_2;
    private String qna_answer_3;

    public Qna_Answer_Adapter(String qna_answer, String qna_answer_1, String qna_answer_2, String qna_answer_3){
        this.qna_answer = qna_answer;
        this.qna_answer_1 = qna_answer_1;
        this.qna_answer_2 = qna_answer_2;
        this.qna_answer_3 = qna_answer_3;
    }

    public String getQna_answer(){
        return this.qna_answer;
    }
    public void setQna_answer(String qna_answer){
        this.qna_answer = qna_answer;
    }

    public String getQna_answer_1(){
        return this.qna_answer;
    }
    public void setQna_answer_1(String qna_answer_1){
        this.qna_answer_1 = qna_answer_1;
    }

    public String getQna_answer_2(){
        return this.qna_answer;
    }
    public void setQna_answer_2(String qna_answer_2){
        this.qna_answer_2 = qna_answer_2;
    }

    public String getQna_answer_3(){
        return this.qna_answer;
    }
    public void setQna_answer_3(String qna_answer_3){
        this.qna_answer_3 = qna_answer_3;
    }
}


