package com.example.cs492_s2;

public class Qna_Question_Adapter {
    private String qna_question;
    private String qna_question_2;
    private String qna_question_3;
    private String qna_question_4;


    public Qna_Question_Adapter(String qna_answer, String qna_question_2, String qna_question_3, String qna_question_4){
        this.qna_question = qna_answer;
        this.qna_question_2 = qna_question_2;
        this.qna_question_3 = qna_question_3;
        this.qna_question_4 = qna_question_4;
    }

    public String getQna_question(){
        return this.qna_question;
    }
    public void setQna_question(String qna_question){
        this.qna_question = qna_question;
    }

    public String getQna_question_2(){
        return this.qna_question_2;
    }
    public void setQna_question_2(String qna_question_2){
        this.qna_question_2 = qna_question_2;
    }

    public String getQna_question_3() {
        return qna_question_3;
    }

    public void setQna_question_3(String qna_question_3) {
        this.qna_question_3 = qna_question_3;
    }

    public String getQna_question_4(){
        return this.qna_question_4;
    }

    public void setQna_question_4(String qna_question_4) {
        this.qna_question_4 = qna_question_4;
    }
}
