package com.hossam.codesroots.presentation.chatAndMapActivity.Helper;

import android.os.Parcel;

public class message {


        String message,imageuri,time;
        int from,to;


    public message() {
    }


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public message(Parcel in) {
        message = in.readString();
        from = in.readInt();
    }

    public String getImageuri() {
        return imageuri;
    }

    public void setImageuri(String imageuri) {
        this.imageuri = imageuri;
    }



    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

}
