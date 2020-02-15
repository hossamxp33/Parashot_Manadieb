package com.hossam.codesroots.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hossam.codesroots.dataLayer.apiData.ApiClient
import com.hossam.codesroots.dataLayer.apiData.ApiInterface
import com.hossam.codesroots.entities.ContactUsModel

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ContactUsVM() : ViewModel() {


    val callBack :MutableLiveData<ContactUsModel> = MutableLiveData()
    val callBackError :MutableLiveData<Throwable> = MutableLiveData()

    fun getResponse(
        username: String,
        mobile: String,
        mail: String,
        message: String
    ) {
        val call = getApiService().contaactUs(username, mobile, mail, message)
        call.enqueue(object : Callback<ContactUsModel> {
            override fun onResponse(
                call: Call<ContactUsModel>,
                response: Response<ContactUsModel>
            ) {
                if (response.isSuccessful()) {
                    callBack.postValue(response.body())
                } else {
                    callBack.postValue( response.body())
                }
            }

            override fun onFailure(call: Call<ContactUsModel>, t: Throwable) {
                callBackError.postValue(t)
                callBack.postValue(ContactUsModel(false))

            }
        })
    }


    private fun getApiService(): ApiInterface {
        return ApiClient.getClient().create(ApiInterface::class.java)
    }
}