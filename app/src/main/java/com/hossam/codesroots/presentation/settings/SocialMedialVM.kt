package com.hossam.codesroots.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

import com.hossam.codesroots.dataLayer.apiData.ApiClient
import com.hossam.codesroots.dataLayer.apiData.ApiInterface
import com.hossam.codesroots.dataLayer.repositries.GetCallBack
import com.hossam.codesroots.entities.ContactUsModel
import com.hossam.codesroots.entities.SocialMediaModel
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hossam on 1/6/2020.
 */
class SocialMedialVM(application: Application) : AndroidViewModel(application) {
    val callBacks : MutableLiveData<SocialMediaModel> = MutableLiveData()
    val callBackError : MutableLiveData<Throwable> = MutableLiveData()
    fun getResponse(
        socialToken: String,
        username: String,
        mail: String,
        pass: String,
        mobile: String,
        gender: String,
        callBack: GetCallBack
    ) {


        val call = getApiService().socialLogin(socialToken, username, mail, pass, mobile, gender)
        call.enqueue(object : Callback<SocialMediaModel> {
            override fun onResponse(
                    call: Call<SocialMediaModel>,
                    response: Response<SocialMediaModel>
            ) {
                if (response.isSuccessful()) {
                    callBacks.postValue(response.body())
                } else {
                    callBacks.postValue( response.body())
                }
            }

            override fun onFailure(call: Call<SocialMediaModel>, t: Throwable) {
                callBackError.postValue( t)

            }
        })
    }

    private fun getApiService(): ApiInterface {
        return ApiClient.getClient().create(ApiInterface::class.java)
    }
}