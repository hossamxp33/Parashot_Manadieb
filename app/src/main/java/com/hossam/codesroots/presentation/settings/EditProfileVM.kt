package com.hossam.codesroots.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hossam.codesroots.dataLayer.apiData.ApiClient
import com.hossam.codesroots.dataLayer.apiData.ApiInterface
import com.hossam.codesroots.entities.EditProfile
import com.hossam.codesroots.helper.PreferenceHelper
import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hossam on 1/7/2020.
 */
class EditProfileVM(application: Application) : AndroidViewModel(application) {

    val callBack : MutableLiveData<EditProfile> = MutableLiveData()
    val callBackInfo : MutableLiveData<EditProfile> = MutableLiveData()
    val callBackError : MutableLiveData<Throwable> = MutableLiveData()

    fun getResponse(
        username: String,
        mail: String,
        mobile: String,
        gender: String
    ) {
        val call = getApiService().editProfile(PreferenceHelper.getUserId(),username, mail, mobile, gender)
        call.enqueue(object : Callback<com.hossam.codesroots.entities.EditProfile> {
            override fun onResponse(
                call: Call<EditProfile>,
                response: Response<EditProfile>
            ) {
                if (response.isSuccessful()) {
                    callBack.postValue(response.body())
                } else {
                    callBack.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<EditProfile>, t: Throwable) {
            }
        })
    }

    fun getUserInfo(userId: String  ) {
        val call = getApiService().getUserInfo(userId)
        call.enqueue(object : Callback<EditProfile> {
            override fun onResponse(
                    call: Call<EditProfile>,
                    response: Response<EditProfile>
            ) {
                    callBackInfo.postValue(response.body())
            }
            override fun onFailure(call: Call<EditProfile>, t: Throwable) {
            }
        })
    }


    private fun getApiService(): ApiInterface {
        return ApiClient.getClient().create(ApiInterface::class.java)
    }
}