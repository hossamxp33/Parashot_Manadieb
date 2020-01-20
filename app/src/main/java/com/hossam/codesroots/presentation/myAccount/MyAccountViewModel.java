package com.hossam.codesroots.presentation.myAccount;

import android.annotation.SuppressLint;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.util.Log;

import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.MyAccount;
import com.hossam.codesroots.helper.PreferenceHelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

import static com.hossam.codesroots.helper.MyApplication.TAG;

public class MyAccountViewModel extends ViewModel {
    // TODO: Implement the ViewModel


    public MyAccountViewModel() {
    }


    MutableLiveData<MyAccount> myAccountMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorAccount = new MutableLiveData<>();
    ApiInterface apiInterface;

    public MyAccountViewModel(ApiInterface apiService) {
        apiInterface = apiService;
        getData();
    }


    public void getData ()
    {
        getObservable(PreferenceHelper.getDeliveryId()).subscribeWith(getObserver());
    }


    @SuppressLint("CheckResult")
    public Observable<MyAccount> getObservable(int deliverId) {
        Observable<MyAccount> myAccount = apiInterface.getMyAccount(deliverId);
        myAccount.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return myAccount;
    }

    public DisposableObserver<MyAccount> getObserver() {
        return new DisposableObserver<MyAccount>() {
            @Override
            public void onNext(@NonNull MyAccount result) {
                myAccountMutableLiveData.postValue(result);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();
                errorAccount.postValue(e.getCause());
            }
            @Override
            public void onComplete() {
            }
        };
    }

}
