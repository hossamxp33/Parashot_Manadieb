package com.hossam.codesroots.presentation.confirm_payment;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
import android.util.Log;

import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.AddPaymentRes;
import com.hossam.codesroots.entities.ConfirmpaymentData;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static io.fabric.sdk.android.Fabric.TAG;

public class ConfirmPaymentViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    public ConfirmPaymentViewModel() {
    }

    MutableLiveData<Boolean> AddPaymentmutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorAddpayment = new MutableLiveData<>();
    ApiInterface apiInterface;

    public ConfirmPaymentViewModel(ApiInterface apiService) {
        apiInterface = apiService;
    }

    public void  AddPayment (ConfirmpaymentData confirmpaymentData)
    {
        addProductObservable(confirmpaymentData).subscribeWith(getEditObserver());
    }

    @SuppressLint("CheckResult")
    private Observable<AddPaymentRes> addProductObservable(ConfirmpaymentData confirmpaymentData) {
        Observable<AddPaymentRes> mySetting =  apiInterface.ADDPayment(
                createPartFromString(confirmpaymentData.getBankid()),
                createPartFromString(confirmpaymentData.getOwner_bankacount()),
                createPartFromString(confirmpaymentData.getUser_bankname()),
                createPartFromString(confirmpaymentData.getPhone()),
                confirmpaymentData.getImage());

        mySetting.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        return mySetting;
    }


    private DisposableObserver<AddPaymentRes> getEditObserver() {
        return new DisposableObserver<AddPaymentRes>() {
            @Override
            public void onNext(@NonNull AddPaymentRes addPaymentRes) {
                if (addPaymentRes.isSuccess())
                     AddPaymentmutableLiveData.postValue(true);
                else
                    AddPaymentmutableLiveData.postValue(false);
            }
            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG, "Error" + e);
                e.printStackTrace();
                errorAddpayment.postValue(e.getCause());
            }
            @Override
            public void onComplete() {
            }
        };
    }


    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

}
