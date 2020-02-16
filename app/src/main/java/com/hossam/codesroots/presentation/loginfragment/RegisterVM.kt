package com.hossam.codesroots.presentation.loginfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hossam.codesroots.dataLayer.apiData.ApiClient
import com.hossam.codesroots.dataLayer.apiData.ApiInterface
import com.hossam.codesroots.dataLayer.repositries.GetCallBack
import com.hossam.codesroots.entities.ContactUsModel
import com.hossam.codesroots.entities.RegisterResponse
import com.hossam.codesroots.entities.SocialMediaModel
import com.hossam.codesroots.helper.PreferenceHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Created by Hossam on 12/30/2019.
 */
class RegisterVM(application: Application) : AndroidViewModel(application) {

    val callBacks : MutableLiveData<RegisterResponse> = MutableLiveData()
    val callBackError : MutableLiveData<Throwable> = MutableLiveData()

    fun getResponse(
            name: String,  mobile: String,
            gender: String, callBack: GetCallBack
    ) {

println(PreferenceHelper(getApplication()).userID)
        val call = getApiService().addUser(name,  mobile, gender, PreferenceHelper(getApplication()).userID.toString())
        call.enqueue(object : Callback<RegisterResponse> {
            override fun onResponse(
                    call: Call<RegisterResponse>,
                    response: Response<RegisterResponse>
            ) {
                if (response.isSuccessful()) {
                    callBacks.postValue(response.body())
                } else {
                    callBacks.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                callBack.getCallBack(false, 1, t)
            }
        })
    }

    private fun getApiService(): ApiInterface {
        return ApiClient.getClient().create(ApiInterface::class.java)
    }
}