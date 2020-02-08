package com.delivery24.view.notifications

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.delivery24.service.models.NotifModel
import com.delivery24.service.webApi.ApiClient
import com.delivery24.service.webApi.ApiInterface
import com.delivery24.service.webApi.GetCallBack
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Hossam on 12/24/2019.
 */
class NotificationVM(application: Application) : AndroidViewModel(application) {

    fun getResponse(id: String, callBack: GetCallBack) {
        val service = ApiClient.createServiceDelUrl(ApiInterface::class.java)
        val call = service.getNotif(id)
        call.enqueue(object : Callback<NotifModel> {
            override fun onResponse(
                call: Call<NotifModel>,
                response: Response<NotifModel>
            ) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body())
                } else {
                    callBack.getCallBack(false, response.code(), response.body())
                }
            }

            override fun onFailure(call: Call<NotifModel>, t: Throwable) {
                callBack.getCallBack(false, 1, t)
            }
        })

    }
}