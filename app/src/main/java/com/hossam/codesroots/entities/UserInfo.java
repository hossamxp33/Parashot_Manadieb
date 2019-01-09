package com.hossam.codesroots.entities;


import com.google.gson.annotations.SerializedName;

public class UserInfo {

    int userid;
    @SerializedName("group")
    int group;

    @SerializedName("id")
    String soketKey;

    @SerializedName("lat")
    String uerlat;

    @SerializedName("long")
    String usdeerlang;

    String name;

    public UserInfo() {

    }

    public String getSoketKey() {
        return soketKey;
    }

    public void setSoketKey(String soketKey) {
        this.soketKey = soketKey;
    }

    public UserInfo(int userid, String name,String soketKey, String uerlat, String usdeerlang) {
        this.userid = userid;
        this.name = name;
        this.uerlat = uerlat;
        this.usdeerlang = usdeerlang;
        this.soketKey = soketKey;
        this.group=2;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUerlat() {
        return uerlat;
    }

    public void setUerlat(String uerlat) {
        this.uerlat = uerlat;
    }

    public String getUsdeerlang() {
        return usdeerlang;
    }

    public void setUsdeerlang(String usdeerlang) {
        this.usdeerlang = usdeerlang;
    }
}
