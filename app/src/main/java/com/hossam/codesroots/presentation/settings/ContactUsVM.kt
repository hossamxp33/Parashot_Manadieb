package com.hossam.codesroots.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hossam on 1/15/2020.
 */
class ContactUsVM(application: Application) : AndroidViewModel(application) {

    fun getResponse(
        username: String,
        mobile: String,
        mail: String,
        message: String
    ) {
//        val service = ApiClient.createServiceDelUrl(ApiInterface::class.java)
//
//        val name = RequestBody.create(MediaType.parse("text/plain"), username)
//        val phone = RequestBody.create(MediaType.parse("text/plain"), mobile)
//        val email = RequestBody.create(MediaType.parse("text/plain"), mail)
//        val msg = RequestBody.create(MediaType.parse("text/plain"), message)
//        val call = service.contaactUs(name, phone, email, msg)
//        call.enqueue(object : Callback<ContactUsModel> {
//            override fun onResponse(
//                call: Call<ContactUsModel>,
//                response: Response<ContactUsModel>
//            ) {
//                if (response.isSuccessful()) {
//                    callBack.getCallBack(true, response.code(), response.body())
//                } else {
//                    callBack.getCallBack(false, response.code(), response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<ContactUsModel>, t: Throwable) {
//                callBack.getCallBack(false, 1, t)
//            }
//        })
    }

}