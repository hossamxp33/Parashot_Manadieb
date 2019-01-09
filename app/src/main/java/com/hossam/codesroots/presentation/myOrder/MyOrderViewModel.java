package com.hossam.codesroots.presentation.myOrder;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;
import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.MYOrdersModel;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.hossam.codesroots.helper.MyApplication.TAG;

public class MyOrderViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    int deliveryId = 1;
    public MyOrderViewModel() {
        getObservable(deliveryId).subscribeWith(getObserver());
    }

    MutableLiveData<MYOrdersModel> myOrders = new MutableLiveData<>();
    MutableLiveData<Throwable> myOrdersError = new MutableLiveData<>();
    MutableLiveData<FilterMyOrder> allMyOrders = new MutableLiveData<FilterMyOrder>();
    ApiInterface apiService;
    int user;
    public MyOrderViewModel(ApiInterface apiService1, int userid) {
        apiService = apiService1;
        user = userid;
    }

    public MyOrderViewModel(MyOrderViewModel myOrderRepositry) {
    }


    @SuppressLint("CheckResult")
    public Observable<MYOrdersModel> getObservable( int deliverId) {

        Observable<MYOrdersModel> myOrders = ApiClient.getClient().create(ApiInterface.class).getMyOrders(deliverId);
        myOrders.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return myOrders;
    }

    public DisposableObserver<MYOrdersModel> getObserver() {
        return new DisposableObserver<MYOrdersModel>() {
            @Override
            public void onNext(@NonNull MYOrdersModel result) {
                allMyOrders.postValue(filterData(result));
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();
                myOrdersError.postValue(e.getCause());
            }
            @Override
            public void onComplete() {
            }

        };
    }

    private FilterMyOrder filterData(MYOrdersModel body) {

        List<MYOrdersModel.DataBean> commpleteOrderData=new ArrayList<>();
        List<MYOrdersModel.DataBean> notCommpleteOrderData=new ArrayList<>();

        for (int i=0;i<body.getData().size();i++)
        {
            if (body.getData().get(i).getOrder_status().matches("3"))
                commpleteOrderData.add(body.getData().get(i));
            else
                notCommpleteOrderData.add(body.getData().get(i));

        }

        return new FilterMyOrder(commpleteOrderData,notCommpleteOrderData);
    }

}
