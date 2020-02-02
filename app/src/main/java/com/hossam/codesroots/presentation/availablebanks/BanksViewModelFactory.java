package com.hossam.codesroots.presentation.availablebanks;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;

public class BanksViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public BanksViewModelFactory(Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

         if (modelClass == BanksViewModel.class)
        {
            return (T) new BanksViewModel(getApiService());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }

}
