package com.example.cs492_s2;

//-----------기본 회원 정보

public class MemberInfo {
    private String name;
    private String phone;
    private String birthdate;
    private String location;
    private String photoUrl;

    public MemberInfo(String name, String phone, String birthdate, String location, String photoUrl){
        this.name = name;
        this.phone = phone;
        this.birthdate = birthdate;
        this.location = location;
        this.photoUrl = photoUrl;
    }

    public MemberInfo(String name, String phone, String birthdate, String location){
        this.name = name;
        this.phone = phone;
        this.birthdate = birthdate;
        this.location = location;
    }


    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getBirthdate(){
        return this.birthdate;
    }
    public void setBirthdate(String birthdate){
        this.birthdate = birthdate;
    }

    public String getLocation(){
        return this.location;
    }
    public void setLocation(String location){
        this.location = location;
    }

    public String getPhotoUrl(){
        return this.photoUrl;
    }
    public void setPhotoUrl(String photoUrl){
        this.photoUrl = photoUrl;
    }

}
