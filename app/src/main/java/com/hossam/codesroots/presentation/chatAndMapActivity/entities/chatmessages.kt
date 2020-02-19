package com.hossam.codesroots.presentation.chatAndMapActivity.entities

data class Chatmessages (
        val order: List<Order>,
        val myChat: List<MyChat>
)

data class MyChat (
        val id: Int? = null,
        val post: String? = null,
        val created: String? = null,
        val modified: String? = null,
        val user_id: Int? = null,
        val photo: String? = null,
        val roomID: String? = null,
        val seen: Int? = null,
        val order_id: Int? = null,
        val user: MyChatUser? = null
)

data class MyChatUser (
        val id: Long? = null,
        val userGroupID: String? = null,
        val username: String? = null,
        val email: String? = null,
        val photo: String? = null
)



data class Order (
        val id: Long? = null,
        val userID: Long? = null,
        val storeID: Long? = null,
        val orderStatus: String? = null,
        val delivryID: Long? = null,
        val created: String? = null,
        val modified: String? = null,
        val deliveryPrice: Long? = null,
        val userLat: String? = null,
        val userLong: String? = null,
        val deliveryTime: String? = null,
        val paymentID: Long? = null,
        val roomID: Any? = null,
        val total: Any? = null,
        val userAddress: String? = null,
        val notes: Any? = null,
        val user: OrderUser? = null,
        val orderdetails: List<Orderdetail>? = null,
        val delivry: Delivry? = null
)

data class Delivry (
        val id: Long? = null,
        val name: String? = null,
        val userID: String? = null,
        val phone: String? = null,
        val gender: String? = null,
        val created: String? = null,
        val modified: String? = null,
        val deliveryLong: String? = null,
        val deliveryLat: String? = null,
        val photo: Any? = null,
        val total: Long? = null
)

data class Orderdetail (
        val id: Long? = null,
        val smallstoreID: Long? = null,
        val price: String? = null,
        val type: String? = null,
        val created: String? = null,
        val modified: String? = null,
        val orderID: Long? = null,
        val count: Any? = null,
        val notes: String? = null,
        val photo: Any? = null,
        val storeLat: String? = null,
        val storeLng: String? = null,
        val storeName: String? = null,
        val storeIcon: Any? = null
)

data class OrderUser (
        val id: Long? = null,
        val username: String? = null,
        val active: Long? = null,
        val emailVerified: Long? = null,
        val userGroupID: String? = null,
        val long: Any? = null,
        val lat: Any? = null,
        val gender: String? = null,
        val phone: String? = null,
        val purchaseWay: Any? = null,
        val points: Any? = null,
        val typePhone: Any? = null,
        val created: String? = null,
        val modified: String? = null,
        val email: String? = null,
        val photo: Any? = null,
        val birthdate: Any? = null,
        val facebookID: String? = null
)
