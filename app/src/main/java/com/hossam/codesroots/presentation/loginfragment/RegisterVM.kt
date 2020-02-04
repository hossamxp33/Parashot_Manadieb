package com.hossam.codesroots.presentation.loginfragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel


/**
 * Created by Hossam on 12/30/2019.
 */
class RegisterVM(application: Application) : AndroidViewModel(application) {

    fun getResponse(
        name: String, mail: String, pass: String, mobile: String,
        gender: String, callBack: String
    ) {
//        val service = ApiClient.createServiceDelUrl(ApiInterface::class.java)
//
//        val username = RequestBody.create(MediaType.parse("text/plain"), name)
//        val email = RequestBody.create(MediaType.parse("text/plain"), mail)
//        val password = RequestBody.create(MediaType.parse("text/plain"), pass)
//        val phone = RequestBody.create(MediaType.parse("text/plain"), mobile)
//        val gend = RequestBody.create(MediaType.parse("text/plain"), gender)
//        val call = service.addUser(username, email, password, phone, gend)
//        call.enqueue(object : Callback<RegisterResponse> {
//            override fun onResponse(
//                call: Call<RegisterResponse>,
//                response: Response<RegisterResponse>
//            ) {
//                if (response.isSuccessful()) {
//                    callBack.getCallBack(true, response.code(), response.body())
//                } else {
//                    callBack.getCallBack(false, response.code(), response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
//                callBack.getCallBack(false, 1, t)
//            }
//        })
    }

}