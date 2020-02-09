
package com.hossam.codesroots.presentation.notifications;

import android.annotation.SuppressLint;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.annotation.NonNull;
import android.util.Log;
import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.Notifications;
import com.hossam.codesroots.helper.PreferenceHelper;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import static com.hossam.codesroots.helper.MyApplication.TAG;

public class NotificationsViewModel extends ViewModel {

    MutableLiveData<Notifications> NotificationsMutableLiveData = new MutableLiveData<>();
    MutableLiveData<Throwable> errorAccount = new MutableLiveData<>();
    ApiInterface apiInterface;

    public NotificationsViewModel(ApiInterface apiService) {
        apiInterface = apiService;
        getData();
    }

    public void getData ()
    {
        getObservable(PreferenceHelper.getUserId()).subscribeWith(getObserver());
    }

    @SuppressLint("CheckResult")
    public Observable<Notifications> getObservable(int userID) {
        Observable<Notifications> Notifications = apiInterface.getNotifications(1);
        Notifications.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
        return Notifications;
    }


    public DisposableObserver<Notifications> getObserver() {
        return new DisposableObserver<Notifications>() {
            @Override
            public void onNext(@NonNull Notifications result) {
                NotificationsMutableLiveData.postValue(result);
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

    // TODO: Implement the ViewModel
}
