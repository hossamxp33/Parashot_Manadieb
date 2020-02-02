package com.hossam.codesroots.presentation.availablebanks;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
import android.util.Log;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.AvailableBanks;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class BanksViewModel extends ViewModel {

    public MutableLiveData<AvailableBanks> banksViewModel = new MutableLiveData<>();
    public MutableLiveData<Throwable> throwableMutableLiveData = new MutableLiveData<>();
    ApiInterface apiService;

    public BanksViewModel(ApiInterface apiService1) {
        apiService = apiService1;
        getData();
    }

    public void getData() {
        getObservable().subscribeWith(getObserver());
    }

    @SuppressLint("CheckResult")
    public Observable<AvailableBanks> getObservable() {
        Observable<AvailableBanks> availableBanks = apiService.getAvailableBanks();
        availableBanks.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return availableBanks;
    }

    public DisposableObserver<AvailableBanks> getObserver() {
        return new DisposableObserver<AvailableBanks>() {
            @Override
            public void onNext(@NonNull AvailableBanks result) {
                banksViewModel.postValue(result);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d("Errors", "Error" + e);
                e.printStackTrace();
                throwableMutableLiveData.postValue(e.getCause());
            }
            @Override
            public void onComplete() {
            }
        };
    }
}
