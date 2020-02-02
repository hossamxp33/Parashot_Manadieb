package com.hossam.codesroots.dataLayer.repositries;

import androidx.annotation.NonNull;
import androidx.core.util.Consumer;


import com.hossam.codesroots.dataLayer.apiData.ApiInterface;
import com.hossam.codesroots.entities.LoginResponseModel;
import com.hossam.codesroots.entities.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginRepository {


    private ApiInterface apiService;
    private Consumer<LoginResponseModel> onSuccess;
    private Consumer<Throwable> onError;
    private Consumer<Integer> codeResbonse;

    public LoginRepository(ApiInterface apiService) {
        this.apiService = apiService;
    }

    public void login(User user) {

         apiService.login(
                 createPartFromString(user.getUsername()),
                 createPartFromString(user.getPassword())
                ).
                 enqueue(new Callback<LoginResponseModel>() {
            @Override
            public void onResponse(Call<LoginResponseModel> call, final Response<LoginResponseModel> response) {
                if (response.body() != null) {

                        if (onSuccess != null) {
                            onSuccess.accept(response.body());
                        }
                }
            else if (response.code()==401)
                {
                    if (codeResbonse != null)
                        codeResbonse.accept(401);
                }

                else {  // TODO: return error
                    if (onError != null) {
                        onError.accept(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponseModel> call, Throwable t) {
                if (onError != null) {
                    onError.accept(t);
                }
            }
        });
    }

    @NonNull
    private RequestBody createPartFromString(String descriptionString) {
        return RequestBody.create(
                MultipartBody.FORM, descriptionString);
    }

    public void setOnSuccess(Consumer<LoginResponseModel> onSuccess) {
        this.onSuccess = onSuccess;
    }

    public void setOnError(Consumer<Throwable> onError) {
        this.onError = onError;
    }

    public void setcodeSuccess(Consumer<Integer> code) {
        this.codeResbonse = code;
    }

}
