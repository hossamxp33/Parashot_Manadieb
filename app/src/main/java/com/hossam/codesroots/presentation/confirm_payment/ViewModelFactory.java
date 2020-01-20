package com.hossam.codesroots.presentation.confirm_payment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public ViewModelFactory(Application application1) {
        application = application1;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

         if (modelClass == ConfirmPaymentViewModel.class)
        {
            return (T) new ConfirmPaymentViewModel(getApiService());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
