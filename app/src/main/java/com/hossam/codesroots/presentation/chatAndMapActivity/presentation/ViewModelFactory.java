package com.hossam.codesroots.presentation.chatAndMapActivity.presentation;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.presentation.chatAndMapActivity.datalayer.repositries.ChatingRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {


    private Application application;

    public ViewModelFactory(Application application1) {
        application = application1;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

         if (modelClass == ChatViewModel.class)
        {
            return (T) new ChatViewModel(getChatingRepositry());
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private ChatingRepository getChatingRepositry() {
        return new ChatingRepository(getApiService());
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
