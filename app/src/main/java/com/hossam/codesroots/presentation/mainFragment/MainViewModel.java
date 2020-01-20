package com.hossam.codesroots.presentation.mainFragment;


import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.entities.UserInfo;

import org.json.JSONException;
import org.json.JSONObject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MutableLiveData<UserInfo> newUser = new MutableLiveData<>();
    public MutableLiveData<String> ddd = new MutableLiveData<>();
    public MutableLiveData<MYOrdersModel> DeliveryOrder = new MutableLiveData<>();
    boolean isFirst = false;     ///////// isFirst Connection
    ApiInterface apiService;

    public MainViewModel(ApiInterface apiService1) {
        apiService = apiService1;
//        if (!isFirst) {
//            makeUserConnection();
//            isFirst = true;
//        }

       // mSocket.on("create_user", onNewConnection);
        //mSocket.on("invite_room", onNewOrder);
    }

    public MainViewModel(MainViewModel myOrderRepositry) {
    }

    public void CheckForOrders (int deliverId)
    {
        getObservable(deliverId).subscribeWith(getObserver());
    }
    @SuppressLint("CheckResult")
    public Observable<MYOrdersModel> getObservable(int deliverId) {

        Observable<MYOrdersModel> myOrders = ApiClient.getClient().create(ApiInterface.class).getActiveDelivery(deliverId);
        myOrders.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return myOrders;
    }

    private Emitter.Listener onNewOrder = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d("asdf", (String) args[0]);
          //  orderDetailsMutableLiveData.postValue(new OrderDetails());
        }
    };
    public DisposableObserver<MYOrdersModel> getObserver() {
        return new DisposableObserver<MYOrdersModel>() {
            @Override
            public void onNext(@NonNull MYOrdersModel result) {
                DeliveryOrder.postValue(result);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                e.printStackTrace();
           //     myOrdersError.postValue(e.getCause());
            }
            @Override
            public void onComplete() {
            }
        };
    }

    private Emitter.Listener onNewConnection = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            Log.d("sd", args[0].toString());
            UserInfo userInfo = new UserInfo();
            try {
                userInfo.setName(data.getString("name"));
                userInfo.setUerlat(data.getString("uerlat"));
                userInfo.setUsdeerlang(data.getString("usdeerlang"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
          //  newUser.postValue(userInfo);

        }
    };

}
