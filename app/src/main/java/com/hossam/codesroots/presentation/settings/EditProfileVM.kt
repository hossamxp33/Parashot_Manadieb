package com.hossam.codesroots.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import okhttp3.MediaType
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hossam on 1/7/2020.
 */
class EditProfileVM(application: Application) : AndroidViewModel(application) {

    fun getResponse(
        username: String,
        mail: String,
        pass: String,
        mobile: String,
        gender: String
    ) {
//        val service = ApiClient.createServiceDelUrl(ApiInterface::class.java)
//
//        val name = RequestBody.create(MediaType.parse("text/plain"), username)
//        val email = RequestBody.create(MediaType.parse("text/plain"), mail)
//        val password = RequestBody.create(MediaType.parse("text/plain"), pass)
//        val phone = RequestBody.create(MediaType.parse("text/plain"), mobile)
//        val gend = RequestBody.create(MediaType.parse("text/plain"), gender)
//        val call = service.editProfile(name, email, password, phone, gend)
//        call.enqueue(object : Callback<EditProfile> {
//            override fun onResponse(
//                call: Call<EditProfile>,
//                response: Response<EditProfile>
//            ) {
//                if (response.isSuccessful()) {
//                    callBack.getCallBack(true, response.code(), response.body())
//                } else {
//                    callBack.getCallBack(false, response.code(), response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<EditProfile>, t: Throwable) {
//                callBack.getCallBack(false, 1, t)
//            }
//        })
    }

}