package com.hossam.codesroots.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hossam.codesroots.dataLayer.apiData.ApiClient
import com.hossam.codesroots.dataLayer.apiData.ApiInterface
import com.hossam.codesroots.entities.AppInfo
import com.hossam.codesroots.entities.ContactUsModel

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppInfoVM() : ViewModel() {


    val callBack :MutableLiveData<AppInfo> = MutableLiveData()
    val callBackError :MutableLiveData<Throwable> = MutableLiveData()

    init {
        getResponse()
    }
    fun getResponse(
    ) {
        val call = getApiService().getAppInfo()
        call.enqueue(object : Callback<AppInfo> {
            override fun onResponse(
                call: Call<AppInfo>,
                response: Response<AppInfo>
            ) {
                if (response.isSuccessful()) {
                    callBack.postValue(response.body())
                } else {
                    callBack.postValue( response.body())
                }
            }

            override fun onFailure(call: Call<AppInfo>, t: Throwable) {
                callBackError.postValue( t)

            }
        })
    }


    private fun getApiService(): ApiInterface {
        return ApiClient.getClient().create(ApiInterface::class.java)
    }
}