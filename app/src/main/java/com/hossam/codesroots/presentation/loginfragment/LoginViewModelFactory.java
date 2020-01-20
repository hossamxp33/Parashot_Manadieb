package com.hossam.codesroots.presentation.loginfragment;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.dataLayer.repositries.LoginRepository;

public class LoginViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    public LoginViewModelFactory(@NonNull Application application1) {
        application = application1;
    }

    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass == LoginViewModel.class)
        {
            return (T) new LoginViewModel(getLoginRepositry());
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private LoginRepository getLoginRepositry() {
        return new LoginRepository(getApiService());
    }

    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
