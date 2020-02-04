package com.hossam.codesroots.presentation.settings

import android.app.Application
import androidx.lifecycle.AndroidViewModel

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hossam on 1/6/2020.
 */
class GetAppVM(application: Application) : AndroidViewModel(application) {

   // fun getResponse(callBack: GetCallBack) {
//        val service = ApiClient.createService(ApiInterface::class.java)
//        val call = service.info()
//        call.enqueue(object : Callback<getAppinfo> {
//            override fun onResponse(
//                call: Call<getAppinfo>,
//                response: Response<getAppinfo>
//            ) {
//                if (response.isSuccessful()) {
//                    callBack.getCallBack(true, response.code(), response.body())
//                } else {
//                    callBack.getCallBack(false, response.code(), response.body())
//                }
//            }
//
//            override fun onFailure(call: Call<getAppinfo>, t: Throwable) {
//                callBack.getCallBack(false, 1, t)
//            }
//        })
//
  //  }
}