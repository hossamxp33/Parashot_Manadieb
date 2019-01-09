package com.hossam.codesroots.presentation.myOrder;

import android.app.Application;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
import com.hossam.codesroots.dataLayer.apiData.ApiClient;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;

public class MyOrderViewModelFactory implements ViewModelProvider.Factory {


    private Application application;
    int userid;//// for getorder to this user



    public MyOrderViewModelFactory(Application application1, int user_id) {
        application = application1;
        userid =user_id;
    }


    @SuppressWarnings("SingleStatementInBlock")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {


         if (modelClass == MyOrderViewModel.class)
        {
            return (T) new MyOrderViewModel(getMyOrderRepositry(userid));
        }

        throw new IllegalArgumentException("Invalid view model class type");
    }




    @NonNull
    private MyOrderViewModel getMyOrderRepositry(int userid) {
        return new MyOrderViewModel(getApiService(),userid);
    }


    private ApiInterface getApiService() {
        return ApiClient.getClient().create(ApiInterface.class);
    }


}
