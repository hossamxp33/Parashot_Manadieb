package com.hossam.codesroots.dataLayer.apiData;


import com.hossam.codesroots.entities.AddOrder;
import com.hossam.codesroots.entities.AddPaymentRes;
import com.hossam.codesroots.entities.AvailableBanks;
import com.hossam.codesroots.entities.LoginResponseModel;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.entities.MyAccount;
import com.hossam.codesroots.entities.Notifications;
import com.hossam.codesroots.entities.OrderEdit;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.AddMessage;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.ChatList;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.chatmessages;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {


    @FormUrlEncoded
    @POST("Deliveryoffers/adddata/{userid}.json")
    @Headers("Accept: Application/json")
    Observable<AddOrder> setoffertoOrder(
            @Field(value = "order_id") int userId,
            @Path("userid") int userid,
            @Field(value = "delivry_id") int delivry_id,
            @Field(value = "offer") String offer
    );


    @GET("orders/getordersfordelivery/{userid}.json")
    @Headers("Accept: Application/json")
    Observable<MYOrdersModel> getMyOrders(
            @Path(value = "userid") int userid
    );


    @GET("Delivries/getdeliveriesreports/{deliveryid}.json")
    @Headers("Accept: Application/json")
    Observable<MyAccount> getMyAccount(
            @Path(value = "deliveryid") int userid
    );


    @GET("Notifications/getnotifications/{userid}.json")
    @Headers("Accept: Application/json")
    Observable<Notifications> getNotifications(
            @Path(value = "userid") int userid
    );

    @FormUrlEncoded
    @POST("chats/chatBTWusers/{pageid}.json")
    Call<chatmessages> getChatData(
            @Path("pageid") int page,
            @Field("order_id") int order_id
    );


    @GET("http://manadeeb.codesroots.com/api/messages/mychat/{userid}.json")
    @Headers("Accept: Application/json")
    Call<ChatList> getChatList(
            @Path(value = "userid") int userid
    );


    @FormUrlEncoded
    @POST("orders/edit/{orderid}.json")
    Observable<OrderEdit> editOrderStatuesData(
            @Path(value = "orderid") int orderid,
            @Field("order_status") int order_status
    );


    @Multipart
    @POST("chats/add.json")
    Call<AddMessage> addmessages(
            @Part("user_id") RequestBody user_id,
            @Part("order_id") RequestBody order_id,
            @Part("room_id") RequestBody room_id,
            @Part("post") RequestBody post,
            @Part("type") RequestBody type,
            @Part  MultipartBody.Part photo
    );


    @Multipart
    @POST("users/token.json")
    Call<LoginResponseModel> login(
            @Part("username") RequestBody username,
            @Part("password") RequestBody password
    );

    @Multipart
    @POST("banktransfers/addbanktransfer.json")
    Observable<AddPaymentRes> ADDPayment(
            @Part("bank_id") RequestBody description,
            @Part("owner_bankacount") RequestBody smallstoreid,
            @Part("user_bankname") RequestBody name,
            @Part("phone") RequestBody price,
            @Part MultipartBody.Part files
    );

    @GET("banks/getbanks.json")
    @Headers("Accept: Application/json")
    Observable<AvailableBanks>
    getAvailableBanks();

}

