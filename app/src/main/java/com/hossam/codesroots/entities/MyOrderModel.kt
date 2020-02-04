package com.hossam.codesroots.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue


@Parcelize
data class MYOrdersModel (
        val data: List<MyOrderData>? = null,
        val id: Int? = null, val dataa: MyOrderData? = null)   : Parcelable

@Parcelize
data class MyOrderData (
        val id: Int? = null,
        @SerializedName("user_id")

        val userID: Int? = null,
        @SerializedName("store_id")

        val storeID: Long? = null,
        @SerializedName("order_status")
        val orderStatus: String? = null,

        @SerializedName("delivry_id")
        val delivryID: Long? = null,

        @SerializedName("created")
        val created: String? = null,

        @SerializedName("modified")
        val modified: String? = null,

        @SerializedName("delivery_price")
        val deliveryPrice: Long? = null,

        @SerializedName("user_lat")
        val userLat: Double? = null,

        @SerializedName("user_long")
        val userLong: Double? = null,

        @SerializedName("delivery_time")
        val deliveryTime: String? = null,

        @SerializedName("payment_id")
        val paymentID: Long? = null,

        @SerializedName("room_id")
        val roomID: String? = null,

        @SerializedName("total")
        val total: @RawValue Any? = null,

        @SerializedName("user_address")
        val userAddress: String? = null,

        @SerializedName("notes")
        val notes: String? = null,

        val user: User? = null,

        val orderdetails: List<Orderdetail>? = null,

        val delivry: Delivry? = null
) : Parcelable {

}

@Parcelize
data class Delivry (
        val id: Long? = null,
        val name: String? = null,
        val photo: String? = null
) : Parcelable {

}

//@Parcelize
//data class User (
//        @SerializedName("username")
//        val id: Long? = null,
//        val username: String? = null,
//        val photo: String? = null,
//        val password: String? = null
//) : Parcelable {
//
//        }



@Parcelize
data class Orderdetail (
        @SerializedName("id")
        val id: Long? = null,

        @SerializedName("smallstore_id")
        val smallstoreID: Long? = null,

        @SerializedName("price")
        val price: String? = null,

        @SerializedName("type")
        val type: String? = null,

        @SerializedName("created")
        val created: String? = null,

        @SerializedName("modified")
        val modified: String? = null,

        @SerializedName("order_id")
        val orderID: Long? = null,

        @SerializedName("count")
        val count: @RawValue Any? = null,

        @SerializedName("notes")
        val notes: String? = null,

        @SerializedName("photo")
        val photo: @RawValue Any? = null,

        @SerializedName("store_lat")
        val storeLat: Double? = null,

        @SerializedName("store_lng")
        val storeLng: Double? = null,

        @SerializedName("store_name")
        val storeName: String? = null,

        @SerializedName("store_icon")
        val storeIcon: @RawValue Any? = null,
        @SerializedName("smallstore")

        val smallstore: Smallstore? = null
) : Parcelable {

}

@Parcelize
data class Smallstore (
        val id: Long? = null,
        @SerializedName("name")

        val name: String? = null,
        val bankAccounts: String? = null,
        val phone: String? = null,
        val alternativePhone: String? = null,
        val cover: String? = null,
        val commercialRegisterNumber: Long? = null,
        val link: String? = null,
        val description: String? = null,
        val branches: Long? = null,
        val birthdate: @RawValue Any? = null,
        val cityID: Long? = null,
        val subcatID: Long? = null,
        val logodesignID: Long? = null,
        val visible: String? = null,
        val userID: Long? = null,
        val templateID: Long? = null,
        val categoryID: Long? = null,
        val logo: String? = null,
        val created: String? = null,
        val modified: String? = null,
        val longitude: Long? = null,
        val latitude: Long? = null,
        val address: String? = null,
        val storebackground: @RawValue Any? = null,
        val storetext: @RawValue Any? = null,
        val firstIcon: @RawValue Any? = null,
        val secondIcon: @RawValue Any? = null,
        val thirdIcon: @RawValue Any? = null,
        val forthIcon: @RawValue Any? = null,
        val tax: @RawValue Any? = null,
        val deliveryprice: Long? = null,
        val minimumOrder: @RawValue Any? = null,
        val activeDiscount: @RawValue Any? = null,
        val shipping: String? = null
) : Parcelable {

}

class RegisterResponse {

        /**
         * success : true
         * data : {"id":270,"code":200,"token":"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI3MCwiZXhwIjoxNTc4MjI5Mjg0fQ.IwRc2_V5b6sWtkavzanihIEtt6aj8i7wsAZ5itjYSEc"}
         */

        var isSuccess: Boolean = false
        var data: DataBean? = null

        class DataBean {
                /**
                 * id : 270
                 * code : 200
                 * token : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjI3MCwiZXhwIjoxNTc4MjI5Mjg0fQ.IwRc2_V5b6sWtkavzanihIEtt6aj8i7wsAZ5itjYSEc
                 */

                var id: Int = 0
                var code: Int = 0
                var token: String? = null
        }
}


