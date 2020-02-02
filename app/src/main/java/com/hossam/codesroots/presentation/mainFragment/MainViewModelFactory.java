package com.hossam.codesroots.presentation.mainFragment;

import android.app.Application;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.annotation.NonNull;

import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.presentation.myOrder.MyOrderViewModel;



public class MainViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userid;//// for getorder to this user



    public MainViewModelFactory(Application application1, int user_id) {
        application = application1;
        userid =user_id;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (modelClass == MyOrderViewModel.class)
        {
            return (T) new MainViewModel(getMyOrderRepositry(userid));
        }
        throw new IllegalArgumentException("Invalid view model class type");
    }


    @NonNull
    private MainViewModel getMyOrderRepositry(int userid) {
        return new MainViewModel(getApiService());
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
