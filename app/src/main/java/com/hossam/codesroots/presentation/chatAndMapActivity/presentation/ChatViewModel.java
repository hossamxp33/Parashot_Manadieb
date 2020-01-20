package com.hossam.codesroots.presentation.chatAndMapActivity.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.hossam.codesroots.presentation.chatAndMapActivity.datalayer.repositries.ChatingRepository;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.AddMessage;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.ChatList;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.chatmessages;

import okhttp3.MultipartBody;


public class ChatViewModel extends ViewModel {

    private ChatingRepository chatingRepository;
    public  MutableLiveData<chatmessages> chatMessages = new MutableLiveData<chatmessages>();
    public  MutableLiveData<ChatList> chatList = new MutableLiveData<ChatList>();
    MutableLiveData<Throwable> errorLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorchatListLiveData = new MutableLiveData<>();
    MutableLiveData<Boolean> loading = new MutableLiveData<>();
    public MutableLiveData<Boolean> addMessageLiveData = new MutableLiveData<>();
    public MutableLiveData<AddMessage> addMessageLiveDataValue = new MutableLiveData<>();

    public ChatViewModel() {
    }

    public ChatViewModel(final ChatingRepository repository) {

        repository.setOnSuccess(chatmessages -> {
            chatMessages.postValue(chatmessages);
            loading.postValue(false);
        });


        repository.setOnSuccessChatList(chatList1  -> {
            chatList.postValue(chatList1);
            loading.postValue(false);
        });

        repository.setOnError(throwable -> {
            errorLiveData.postValue(throwable);
            loading.postValue(false);
        });


        repository.setOnErrorChatList(throwable -> {
            errorchatListLiveData.postValue(throwable);
            loading.postValue(false);
        });

        repository.setOnErrorChatList(throwable -> {
            errorchatListLiveData.postValue(throwable);
            loading.postValue(false);
        });

        repository.setOnSuccessAdd(aBoolean  -> {
            addMessageLiveData.postValue(aBoolean);
        });

        repository.setOnSuccessAddValue(addMessage   -> {
            addMessageLiveDataValue.postValue(addMessage);
        });

        this.chatingRepository = repository;
    }

    public void addMessaege(int userid,int orderid,String roomid ,String mesg, MultipartBody.Part part)
    {
        chatingRepository.addMessage(userid,orderid,roomid,mesg,part);
    }

    public void getChatData(int page,int orderid)
    {
        chatingRepository.chatMessages(page,orderid);
    }

    public void getChaListtData(int userid)
    {
        chatingRepository.chatList(userid);
    }

}
