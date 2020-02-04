package com.hossam.codesroots.presentation.settings

import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders


import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.hossam.codesroots.delivery24.R

import com.hossam.codesroots.helper.PreferenceHelper
import kotlinx.android.synthetic.main.activity_default_sign_in.*


class Default_sign_in : AppCompatActivity(), View.OnClickListener {

    lateinit var helper: PreferenceHelper
    private var mLastClickTime: Long = 0
    var gender: String = ""

    // facebook

    //gmail
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInOptions: GoogleSignInOptions


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_sign_in)
        helper = PreferenceHelper(this)
        facebook.setOnClickListener(this)
        gmail.setOnClickListener(this)
        phone.setOnClickListener(this)


    }



    private fun setupUI() {
        google_button.setOnClickListener {
        }
    }






    override fun onClick(view: View) {
        when (view.id) {
            R.id.facebook -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                fb_login_bn.performClick()
            }
            R.id.gmail -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                google_button.performClick()

            }
            R.id.phone -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                startActivity(intent)
            }
        }
    }

    fun SendApi(
        token: String, username: String,
        mail: String,
        pass: String,
        mobile: String,
        gender: String
    ) {

    }

}
