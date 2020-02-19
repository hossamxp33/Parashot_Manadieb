package com.hossam.codesroots.presentation.chatAndMapActivity.datalayer.repositries;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;
import android.util.Log;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.AddMessage;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.ChatList;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.Chatmessages;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class ChatingRepository {

    private ApiInterface apiService;
    private Consumer<Chatmessages> onSuccess;
    private Consumer<ChatList> onSuccessList;
    private Consumer<AddMessage> onSuccessAdd;
    private Consumer<Boolean> onSuccessAddstatues;
    private Consumer<Throwable> onError;
    private Consumer<Throwable> onErrorChatList;
    private Consumer<Boolean> loading;

    public ChatingRepository(ApiInterface apiService1) {
        apiService = apiService1;

    }


    public void chatMessages(int page,int orderid) {
        try {
            apiService.getChatData(page,orderid).enqueue(new Callback<Chatmessages>() {
                @Override
                public void onResponse(Call<Chatmessages> call, final Response<Chatmessages> response) {
                    if (response.body() != null) {
                            if (onSuccess != null) {
                                onSuccess.accept(response.body());
                            }
                        else {
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<Chatmessages> call, Throwable t) {
                    Log.d("chating fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });

        } catch (Exception e) {
            Log.d("chating Repositry", e.getMessage());
            onError.accept(e);
        }
    }

    public void chatList(int userid) {
        try {
            apiService.getChatList(userid).enqueue(new Callback<ChatList>() {
                @Override
                public void onResponse(Call<ChatList> call, final Response<ChatList> response) {
                    if (response.body() != null) {
                        if (onSuccessList != null) {
                            onSuccessList.accept(response.body());
                        }
                        else {
                            if (onError != null) {
                                onError.accept(new Exception());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<ChatList> call, Throwable t) {
                    Log.d("chating fail -> ", call.toString());
                    // TODO: return error
                    if (onError != null) {
                        onError.accept(t);
                    }
                }
            });

        } catch (Exception e) {
            Log.d("chating Repositry", e.getMessage());
            onError.accept(e);
        }
    }

    public void addMessage(int userid,int orderid ,String roomid,String mesg, MultipartBody.Part part) {
        try {
            apiService.addmessages(createPartFromString(String.valueOf(userid)),createPartFromString(String.valueOf(orderid))
                    ,createPartFromString(String.valueOf(roomid)),
                    createPartFromString(mesg),createPartFromString("1"),part).enqueue(new Callback<AddMessage>() {
                @Override
                public void onResponse(Call<AddMessage> call, final Response<AddMessage> response) {
                    if (response.body() != null) {
                        if (onSuccessAdd != null) {
                            onSuccessAdd.accept(response.body());
                        }
                    }
                    else {
                        if (onError != null)
                            onError.accept(new Exception());
                    }
                }

                @Override
                public void onFailure(Call<AddMessage> call, Throwable t) {
                    Log.d("chating fail -> ", call.toString());
                    onSuccessAddstatues.accept(false);
                }
            });

        } catch (Exception e) {
            onSuccessAddstatues.accept(false);
        }
    }


    public void setOnSuccess(Consumer<Chatmessages> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnSuccessChatList(Consumer<ChatList> onSuccess) {
        this.onSuccessList = onSuccess;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

    public void setOnSuccessAdd(Consumer<Boolean> add) {
        this.onSuccessAddstatues = add;
    }

    public void setOnSuccessAddValue(Consumer<AddMessage> add) {
        this.onSuccessAdd = add;
    }


    public void setOnErrorChatList(Consumer<Throwable> onError) {
        this.onErrorChatList = onError;
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

}
