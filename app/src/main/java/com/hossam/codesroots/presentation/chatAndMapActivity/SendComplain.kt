package com.delivery24.view.chat

import android.Manifest
import android.app.Activity
import android.app.PendingIntent.getActivity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.hossam.codesroots.delivery24.R
import com.hossam.codesroots.helper.PreferenceHelper
import com.hossam.codesroots.helper.Utils
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.activity_send_complain.*
import kotlinx.android.synthetic.main.toolbar.*

class SendComplain : AppCompatActivity(), View.OnClickListener {
    private var mLastClickTime: Long = 0
    lateinit var helper: PreferenceHelper
    internal var delv_id = ""
    internal var ord_id = ""
    lateinit var vm :SendComplainVM
    private val LOAD_IMG_REQUEST_CODE = 5555

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_send_complain)
        helper = PreferenceHelper(this)
         vm = ViewModelProviders.of(this).get(SendComplainVM::class.java)

        vm.callBack.observe(this, Observer {
            if (it.success) {
                Toast.makeText(this, "تم ارسال الشكوي بنجاح", Toast.LENGTH_SHORT).show()
                et_complain.setText("")
                etnote.setText("")

            } else
                Toast.makeText(this, "حدث خطأ حاول مرة أخري", Toast.LENGTH_SHORT).show()

        })
        initialize()
        initToolBar()
    }

    private fun initialize() {
        send.setOnClickListener(this)
        selectedimage.setOnClickListener(this)

        val bundle = intent.extras
        if (bundle != null) {
            delv_id = intent.getIntExtra("delv_id",0).toString()
            ord_id = intent.getIntExtra("ord_id",0).toString()
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
                        "1", delv_id, ord_id
                    )
                } else {
                    Toast.makeText(this, getString(R.string.uploadphoto), Toast.LENGTH_LONG).show()
                }

            }
            R.id.selectedimage ->
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    val photoPickerIntent = Intent(Intent.ACTION_PICK)
                    photoPickerIntent.type = "image/*"
                    startActivityForResult(photoPickerIntent, LOAD_IMG_REQUEST_CODE)
                } else {
                    ActivityCompat.requestPermissions(this,
                            arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1234)
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
            if (resultCode == Activity.RESULT_OK) {
                uri = data?.data
                selectedimage.setImageURI(uri)
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
        vm.getResponse(
            ur,
            user_id,
            del_id,
            order_id,
            title,
            note)
    }
}
