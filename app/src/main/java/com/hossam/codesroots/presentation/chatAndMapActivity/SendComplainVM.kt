package com.delivery24.view.chat

import android.app.Application
import android.net.Uri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.hossam.codesroots.dataLayer.apiData.ApiClient
import com.hossam.codesroots.dataLayer.apiData.ApiInterface
import com.hossam.codesroots.entities.ContactUsModel
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.AddMessage
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File


class SendComplainVM(application: Application) : AndroidViewModel(application) {

    val callBack : MutableLiveData<AddMessage> = MutableLiveData()
    val callBackError : MutableLiveData<Throwable> = MutableLiveData()

    fun getResponse(
        image: Uri,
        user: String, deliv: String, order: String, desc: String, comment: String
    ) {

        val filePart: MultipartBody.Part
        val file = File(image.getPath())
        filePart = MultipartBody.Part.createFormData(
            "photo",
            file.name,
            RequestBody.create("image/*".toMediaTypeOrNull(), file)
        )

        val user_id = RequestBody.create("text/plain".toMediaTypeOrNull(), user)
        val deliv_id = RequestBody.create("text/plain".toMediaTypeOrNull(), deliv)
        val order_id = RequestBody.create("text/plain".toMediaTypeOrNull(), order)
        val descr = RequestBody.create("text/plain".toMediaTypeOrNull(), desc)
        val comm = RequestBody.create("text/plain".toMediaTypeOrNull(), comment)
        val call = getApiService().addcomplaint(user_id, deliv_id, order_id, descr, comm)
        call.enqueue(object : Callback<AddMessage> {
            override fun onResponse(
                    call: Call<AddMessage>,
                    response: Response<AddMessage>
            ) {
                if (response.isSuccessful()) {
                    callBack.postValue(response.body())
                } else {
                    callBack.postValue(response.body())
                }
            }

            override fun onFailure(call: Call<AddMessage>, t: Throwable) {
                callBackError.postValue(t)
            }
        })
    }

    private fun getApiService(): ApiInterface {
        return ApiClient.getClient().create(ApiInterface::class.java)
    }

}