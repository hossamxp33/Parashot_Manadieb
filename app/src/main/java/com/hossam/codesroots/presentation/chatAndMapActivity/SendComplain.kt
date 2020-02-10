package com.delivery24.view.chat

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.delivery24.R
import com.delivery24.helpers.PermissionCheck
import com.delivery24.helpers.PreferenceHelper
import com.delivery24.helpers.Utility
import com.delivery24.helpers.Utils
import com.delivery24.service.webApi.GetCallBack
import com.theartofdev.edmodo.cropper.CropImage
import kotlinx.android.synthetic.main.activity_send_complain.*
import kotlinx.android.synthetic.main.toolbar.*

class SendComplain : AppCompatActivity(), View.OnClickListener {
    private var mLastClickTime: Long = 0
    lateinit var helper: PreferenceHelper
    internal var delv_id = ""
    internal var ord_id = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_complain)
        helper = PreferenceHelper(this)

        initialize()
        initToolBar()
    }

    private fun initialize() {
        send.setOnClickListener(this)
        selectedimage.setOnClickListener(this)

        val bundle = intent.extras
        if (bundle != null) {
            delv_id = bundle.get("delv_id").toString()
            ord_id = bundle.get("ord_id").toString()
        }

    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.send -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                if (!Utils.FieldValidation(
                        this,
                        et_complain,
                        1,
                        getString(R.string.pleasefillMissinginfo),
                        maincomplain
                    )
                )
                    return
                if (!Utils.FieldValidation(
                        this,
                        etnote,
                        1,
                        getString(R.string.pleasefillMissinginfo),
                        maincomplain
                    )
                )
                    return

                if (uri != null) {
                    sendComplain(
                        uri!!,
                        et_complain.text.toString().trim(),
                        etnote.text.toString().trim(),
                        helper.userID, delv_id, ord_id
                    )
                } else {
                    Toast.makeText(this, getString(R.string.uploadphoto), Toast.LENGTH_LONG).show()
                }

            }
            R.id.selectedimage -> if (!PermissionCheck.Check_CAMERA(this)) {
                PermissionCheck.Request_CAMERA(this, 25)
            } else {
                CropImage.activity().start(this@SendComplain)
            }
        }
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        btnBack.setOnClickListener { v ->
            finish()
        }
    }


    var uri: Uri? = null

    public override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                uri = result.uri
                selectedimage.setImageURI(uri)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                val error = result.error
            }
        }
    }

    fun sendComplain(
        ur: Uri,
        title: String,
        note: String,
        user_id: String,
        del_id: String,
        order_id: String
    ) {
        val dialog = Utility.showProgressDialog(this, getString(R.string.loading), false)
        val vm = ViewModelProviders.of(this).get(SendComplainVM::class.java)
        vm.getResponse(
            ur,
            user_id,
            del_id,
            order_id,
            title,
            note,
            GetCallBack { isOk, requestCode, o ->
                if (isOk) {
                    uri = null
                    selectedimage.setImageURI(null)
                    etnote.setText("")
                    et_complain.setText("")
                    Toast.makeText(this, getString(R.string.complainSent), Toast.LENGTH_LONG).show()
                    Utility.hideProgress(dialog)
                } else {
                    Utility.hideProgress(dialog)
                }
            })
    }
}
