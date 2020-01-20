package com.hossam.codesroots.entities;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    private   long id;
    private  String username,password,gender,birthdate,mobile,photo;

    public User(long id,String username, String password, String gender, String birthdate, String mobile, String photo) {

        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.birthdate = birthdate;
        this.mobile = mobile;
        this.photo = photo;

    }

    public User(String username, String pass) {
        this.username = username;
        this.password = pass;
    }

    protected User(Parcel in) {
        id = in.readLong();
        username = in.readString();
        password = in.readString();
        gender = in.readString();
        birthdate = in.readString();
        mobile = in.readString();
        photo = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
    public long getID() { return id; }

    public void setID(long value) {
        this.id = value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhoto() { return photo; }

    public void setPhoto(String value) { this.photo = value; }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(id);
        parcel.writeString(username);
        parcel.writeString(password);
        parcel.writeString(gender);
        parcel.writeString(birthdate);
        parcel.writeString(mobile);
        parcel.writeString(photo);
    }
}
