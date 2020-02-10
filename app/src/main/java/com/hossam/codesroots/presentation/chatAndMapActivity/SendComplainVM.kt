package com.delivery24.view.chat

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import com.delivery24.service.models.SendChatModel
import com.delivery24.service.webApi.ApiClient
import com.delivery24.service.webApi.ApiInterface
import com.delivery24.service.webApi.GetCallBack
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

/**
 * Created by Hossam on 1/15/2020.
 */
class SendComplainVM(application: Application) : AndroidViewModel(application) {

    fun getResponse(
        image: Uri,
        user: String, deliv: String, order: String, desc: String, comment: String,
        callBack: GetCallBack
    ) {
        val service = ApiClient.createServiceDelUrl(ApiInterface::class.java)

        val filePart: MultipartBody.Part

        val file = File(image.getPath())
        filePart = MultipartBody.Part.createFormData(
            "photo",
            file.name,
            RequestBody.create(MediaType.parse("image/*"), file)
        )

        val user_id = RequestBody.create(MediaType.parse("text/plain"), user)
        val deliv_id = RequestBody.create(MediaType.parse("text/plain"), deliv)
        val order_id = RequestBody.create(MediaType.parse("text/plain"), order)
        val descr = RequestBody.create(MediaType.parse("text/plain"), desc)
        val comm = RequestBody.create(MediaType.parse("text/plain"), comment)
        val call = service.addcomplaint(filePart, user_id, deliv_id, order_id, descr, comm)
        call.enqueue(object : Callback<SendChatModel> {
            override fun onResponse(
                call: Call<SendChatModel>,
                response: Response<SendChatModel>
            ) {
                if (response.isSuccessful()) {
                    callBack.getCallBack(true, response.code(), response.body())
                } else {
                    callBack.getCallBack(false, response.code(), response.body())
                }
            }

            override fun onFailure(call: Call<SendChatModel>, t: Throwable) {
                callBack.getCallBack(false, 1, t)
            }
        })
    }
}