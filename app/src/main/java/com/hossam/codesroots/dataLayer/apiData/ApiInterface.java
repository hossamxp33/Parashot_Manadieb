package com.hossam.codesroots.dataLayer.apiData;


import com.hossam.codesroots.entities.AddOrder;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.AddMessage;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.ChatList;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.chatmessages;


import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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


    @FormUrlEncoded
    @POST("http://manadeeb.codesroots.com/api/chats/chatBTWusers.json")
    Call<chatmessages> getChatData(
            @Field("too") int user_too,
            @Field("fromm") int user_from
    );


    @GET("http://manadeeb.codesroots.com/api/messages/mychat/{userid}.json")
    @Headers("Accept: Application/json")
    Call<ChatList> getChatList(
            @Path(value = "userid") int userid
    );

    @Multipart
    @POST("http://manadeeb.codesroots.com/api/chats/add.json")
    Call<AddMessage> addmessages(
            @Part("too") RequestBody user_too,
            @Part("fromm") RequestBody user_from,
            @Part("post") RequestBody post,
            @Part  MultipartBody.Part photo
    );
}

