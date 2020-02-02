package com.hossam.codesroots.presentation.newOrderFragment;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
import android.util.Log;
import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.AddOrder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import static com.hossam.codesroots.helper.MyApplication.TAG;


public class NewOrderViewModel extends ViewModel {
    // TODO: Implement the ViewModel
     MutableLiveData<Boolean> newoffer = new MutableLiveData<>();


    public  void addOffer(int orderId,int userid,int deliverId,String offer)
    {
        getObservable(orderId,userid,deliverId,offer).subscribeWith(getObserver());
    }

    @SuppressLint("CheckResult")
    public Observable<AddOrder> getObservable(int orderId,int userid,int deliverId,String offer) {
        Observable<AddOrder> secondHomeDataObservable =  ApiClient.getClient().
                create(ApiInterface.class).setoffertoOrder(orderId,userid,deliverId,offer);
        secondHomeDataObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return secondHomeDataObservable;
    }

    public DisposableObserver<AddOrder> getObserver() {
        return new DisposableObserver<AddOrder>() {
            @Override
            public void onNext(@NonNull AddOrder result) {

                if (result.isSuccess())
                newoffer.postValue(true);
                else
                    newoffer.postValue(false);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();
                newoffer.postValue(true);
            }
            @Override
            public void onComplete() {
                newoffer.postValue(true);
            }

        };
    }


}
