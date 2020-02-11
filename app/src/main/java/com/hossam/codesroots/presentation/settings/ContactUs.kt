package com.hossam.codesroots.presentation.settings

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hossam.codesroots.delivery24.R

import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.toolbar.*

class ContactUs : AppCompatActivity(), View.OnClickListener {
    private var mLastClickTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        initToolBar()
        sendBT.setOnClickListener(this)

    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        btnBack.setOnClickListener { v ->
            finish()
        }
        name.text = getString(R.string.contactus)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.sendBT -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                if (!Utils.FieldValidation(
//                        this,
//                        Edname,
//                        3,
//                        getString(R.string.enterfullname),
//                        mainContactUs
//                    )
//                )
//                    return
//                if (!Utils.EmailValidation(
//                        this,
//                        Edemail,
//                        getString(R.string.enteremail),
//                        mainContactUs
//                    )
//                )
//                    return
//                if (!Utils.FieldValidation(
//                        this,
//                        Edphone,
//                        6,
//                        getString(R.string.enterphone),
//                        mainContactUs
//                    )
//                )
//                    return
//                if (!Utils.FieldValidation(
//                        this,
//                        Edmsg,
//                        3,
//                        getString(R.string.pleasefillMissinginfo),
//                        mainContactUs
//                    )
//                )
//                    return

                sendMessage(
                        Edname.text.toString().trim(),
                        Edphone.text.toString().trim(),
                        Edemail.text.toString().trim(),
                        Edmsg.text.toString().trim()
                )
            }
        }
    }

    fun sendMessage(
            username: String,
            mobile: String,
            email: String,
            msg: String
    ) {
        val vm = ViewModelProviders.of(this).get(ContactUsVM::class.java)
        vm.getResponse(username, mobile, email, message = msg)
        vm.callBack.observe(this, Observer {
            if (it.success) {
                Toast.makeText(this, "تم ارسال رسالتك بنجاح", Toast.LENGTH_SHORT).show()
                Edname.setText("")
                Edphone.setText("")
                Edemail.setText("")
                Edmsg.setText("")
            } else
                Toast.makeText(this, "حدث خطأ حاول مرة أخري", Toast.LENGTH_SHORT).show()

        })
    }

}
