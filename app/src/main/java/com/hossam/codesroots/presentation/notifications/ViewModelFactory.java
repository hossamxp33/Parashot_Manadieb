package com.hossam.codesroots.presentation.notifications;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.presentation.myAccount.MyAccountViewModel;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Application application;

    public ViewModelFactory(Application application1) {
        application = application1;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

         if (modelClass == NotificationsViewModel.class)
        {
            return (T) new NotificationsViewModel(getApiService());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }




    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
