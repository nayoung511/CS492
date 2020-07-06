package com.example.cs492_s2.activity;

public class ProfileInfo {
    //private String photoUrl;
    private String job;
    private String personality;
    private String unique;
    private String self_intro;


    public ProfileInfo(String job, String personality, String unique, String self_intro){
        //this.photoUrl = photoUrl;
        this.job = job;
        this.personality = personality;
        this.unique = unique;
        this.self_intro = self_intro;
    }

//    public String getPhotoUrl(){
//        return this.photoUrl;
//    }
//    public void setPhotoUrl(String photoUrl){this.photoUrl = photoUrl;}

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }
}
