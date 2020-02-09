package com.hossam.codesroots.presentation.myOrder;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
import android.util.Log;
import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.entities.MyOrderData;
import com.hossam.codesroots.entities.OrderEdit;
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
        getData();
    }

    MutableLiveData<MYOrdersModel> myOrders = new MutableLiveData<>();
    MutableLiveData<Throwable> myOrdersError = new MutableLiveData<>();
    MutableLiveData<FilterMyOrder> allMyOrders = new MutableLiveData<FilterMyOrder>();
    public MutableLiveData<MYOrdersModel> DeliveryOrder = new MutableLiveData<>();

    public MutableLiveData<Boolean> editResult = new MutableLiveData<Boolean>();
    ApiInterface apiService;
    int user;
    public MyOrderViewModel(ApiInterface apiService1, int userid) {
        apiService = apiService1;
        user = userid;
    }

    public MyOrderViewModel(MyOrderViewModel myOrderRepositry) {
    }


    public void getData ()
    {
        getObservable(deliveryId).subscribeWith(getObserver());
    }

    public void editResult(int order,int statues,String notes)
    {
        getObservableEditResult(order,statues,notes).subscribeWith(getObserverEditResult());
    }

    public void CheckForOrders (int deliverId)
    {
        getCheckForOrders(deliverId).subscribeWith(getObserverDelivery());
    }

    @SuppressLint("CheckResult")
    public Observable<MYOrdersModel> getCheckForOrders(int deliverId) {

        Observable<MYOrdersModel> myOrders = ApiClient.getClient().create(ApiInterface.class).getActiveDelivery(deliverId);
        myOrders.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return myOrders;
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
    public DisposableObserver<MYOrdersModel> getObserverDelivery() {
        return new DisposableObserver<MYOrdersModel>() {
            @Override
            public void onNext(@io.reactivex.annotations.NonNull MYOrdersModel result) {
                DeliveryOrder.postValue(result);
            }

            @Override
            public void onError(@io.reactivex.annotations.NonNull Throwable e) {
                e.printStackTrace();
                //     myOrdersError.postValue(e.getCause());
            }
            @Override
            public void onComplete() {
            }
        };
    }
    @SuppressLint("CheckResult")
    public Observable<OrderEdit> getObservableEditResult( int orderid,int newStatues,String notes) {

        Observable<OrderEdit> myOrders = ApiClient.getClient().create(ApiInterface.class).editOrderStatuesData(orderid,newStatues,notes);
        myOrders.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return myOrders;
    }

    public DisposableObserver<OrderEdit> getObserverEditResult() {
        return new DisposableObserver<OrderEdit>() {
            @Override
            public void onNext(@NonNull OrderEdit responseBody) {
                if (responseBody.isEditorder())
                    editResult.postValue(true);
                else
                    editResult.postValue(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                editResult.postValue(false);
            }
            @Override
            public void onComplete() {
            }
        };
    }

    private FilterMyOrder filterData(MYOrdersModel body) {
        List<MyOrderData> commpleteOrderData=new ArrayList<>();
        List<MyOrderData> notCommpleteOrderData=new ArrayList<>();
        for (int i=0;i<body.getData().size();i++)
        {

            if (body.getData().get(i).getOrderStatus().matches("4"))
                commpleteOrderData.add(body.getData().get(i));
            else
                notCommpleteOrderData.add(body.getData().get(i));
        }
        return new FilterMyOrder(commpleteOrderData,notCommpleteOrderData);
    }
}
