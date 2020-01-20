package com.hossam.codesroots.entities;

import okhttp3.MultipartBody;

public class ConfirmpaymentData {

    int bankid;
    String owner_bankacount,user_bankname,phone;
    MultipartBody.Part image;

    public ConfirmpaymentData(int bankid, String owner_bankacount, String user_bankname, String phone, MultipartBody.Part image) {
        this.bankid = bankid;
        this.owner_bankacount = owner_bankacount;
        this.user_bankname = user_bankname;
        this.phone = phone;
        this.image = image;
    }

    public String getBankid() {
        return String.valueOf(bankid);
    }

    public void setBankid(int bankid) {
        this.bankid = bankid;
    }

    public String getOwner_bankacount() {
        return owner_bankacount;
    }

    public void setOwner_bankacount(String owner_bankacount) {
        this.owner_bankacount = owner_bankacount;
    }

    public String getUser_bankname() {
        return user_bankname;
    }

    public void setUser_bankname(String user_bankname) {
        this.user_bankname = user_bankname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MultipartBody.Part getImage() {
        return image;
    }

    public void setImage(MultipartBody.Part image) {
        this.image = image;
    }
}
