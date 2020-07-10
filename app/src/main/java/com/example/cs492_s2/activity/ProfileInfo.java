package com.example.cs492_s2.activity;

import java.util.Collections;
import java.util.List;

public class ProfileInfo {
    private String photoUrl;
    private String job;
    private List<String> personality;
    private List<String> unique;
    private String self_intro;


    public ProfileInfo(String photoUrl, String job, String personality, String unique, String self_intro){
        this.photoUrl = photoUrl;
        this.job = job;
        this.personality = Collections.singletonList(personality);
        this.unique = Collections.singletonList(unique);
        this.self_intro = self_intro;
    }

    public ProfileInfo(String photo, String job, List<String> box_personality, List<String> box_hobby, String self_intro) {
    }

    public String getPhotoUrl(){ return this.photoUrl; }

    public void setPhotoUrl(String photoUrl){this.photoUrl = photoUrl;}

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public List<String> getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = Collections.singletonList(personality);
    }

    public List<String> getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = Collections.singletonList(unique);
    }

    public String getSelf_intro() {
        return self_intro;
    }

    public void setSelf_intro(String self_intro) {
        this.self_intro = self_intro;
    }
}
