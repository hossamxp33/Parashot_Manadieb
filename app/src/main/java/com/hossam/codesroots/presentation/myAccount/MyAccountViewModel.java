package com.hossam.codesroots.presentation.myAccount;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
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


  public   MutableLiveData<MyAccount> myAccountMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorAccount = new MutableLiveData<>();
    ApiInterface apiInterface;

    public MyAccountViewModel(ApiInterface apiService) {
        apiInterface = apiService;
        getData();
    }


    public void getData ()
    {
        getObservable(PreferenceHelper.getdeliveryId()).subscribeWith(getObserver());
    }


    @SuppressLint("CheckResult")
    public Observable<MyAccount> getObservable(String deliverId) {
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
