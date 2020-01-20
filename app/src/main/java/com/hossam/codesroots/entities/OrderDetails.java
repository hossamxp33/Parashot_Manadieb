package com.hossam.codesroots.entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class OrderDetails implements Parcelable {

    String storename,storeaddress,clientname,clientaddress,
            productname,productPrice,paymentWay;

    @SerializedName("user_lat")
    String userlat;

    @SerializedName("user_long")
    String userlang;

    @SerializedName("store_lat")
    String storelat;
    @SerializedName("store_long")
    String storelang;

    public OrderDetails() {
    }


    protected OrderDetails(Parcel in) {
        storename = in.readString();
        storeaddress = in.readString();
        clientname = in.readString();
        clientaddress = in.readString();
        productname = in.readString();
        productPrice = in.readString();
        paymentWay = in.readString();
        userlat = in.readString();
        userlang = in.readString();
        storelat = in.readString();
        storelang = in.readString();
    }

    public static final Creator<OrderDetails> CREATOR = new Creator<OrderDetails>() {
        @Override
        public OrderDetails createFromParcel(Parcel in) {
            return new OrderDetails(in);
        }

        @Override
        public OrderDetails[] newArray(int size) {
            return new OrderDetails[size];
        }
    };


    public String get() {
        return storename;
    }
    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getStoreaddress() {
        return storeaddress;
    }

    public void setStoreaddress(String storeaddress) {
        this.storeaddress = storeaddress;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getClientaddress() {
        return clientaddress;
    }

    public void setClientaddress(String clientaddress) {
        this.clientaddress = clientaddress;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public String getPaymentWay() {
        return paymentWay;
    }

    public void setPaymentWay(String paymentWay) {
        this.paymentWay = paymentWay;
    }

    public String getUserlat() {
        return userlat;
    }

    public void setUserlat(String userlat) {
        this.userlat = userlat;
    }

    public String getUserlang() {
        return userlang;
    }

    public void setUserlang(String userlang) {
        this.userlang = userlang;
    }

    public String getStorelat() {
        return storelat;
    }

    public void setStorelat(String storelat) {
        this.storelat = storelat;
    }

    public String getStorelang() {
        return storelang;
    }

    public void setStorelang(String storelang) {
        this.storelang = storelang;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(storename);
        dest.writeString(storeaddress);
        dest.writeString(clientname);
        dest.writeString(clientaddress);
        dest.writeString(productname);
        dest.writeString(productPrice);
        dest.writeString(paymentWay);
        dest.writeString(userlat);
        dest.writeString(userlang);
        dest.writeString(storelat);
        dest.writeString(storelang);
    }
}
