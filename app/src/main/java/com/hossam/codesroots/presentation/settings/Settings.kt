package com.hossam.codesroots.presentation.settings

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.core.content.ContextCompat.startActivity
import androidx.appcompat.app.AppCompatActivity
import android.view.View

import com.hossam.codesroots.delivery24.R
import com.hossam.codesroots.helper.PreferenceHelper
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity(), View.OnClickListener {
    private var mLastClickTime: Long = 0
    lateinit var helper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        helper = PreferenceHelper(this)
        editProfile.setOnClickListener(this)
        lang.setOnClickListener(this)
        termsofuse.setOnClickListener(this)
        usagepolicy.setOnClickListener(this)
        rateapp.setOnClickListener(this)
        contactus.setOnClickListener(this)
        logout.setOnClickListener(this)
        aboutapp.setOnClickListener(this)
        howitwork.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.editProfile -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                val i = Intent(this, EditProfile::class.java)
//                startActivity(i)
            }
            R.id.lang -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                val i = Intent(this, SetLang::class.java)
//                startActivity(i)

            }
            R.id.termsofuse -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                val i = Intent(this, AppInfoActivity::class.java)
//                i.putExtra("title", "term")
//                startActivity(i)
            }
            R.id.usagepolicy -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                val i = Intent(this, AppInfoActivity::class.java)
//                i.putExtra("title", "policy")
//                startActivity(i)
            }
            R.id.rateapp -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                val uri = Uri.parse("market://details?id=" + getPackageName())
                val goToMarket = Intent(Intent.ACTION_VIEW, uri)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    goToMarket.addFlags(
                        Intent.FLAG_ACTIVITY_NO_HISTORY or
                                Intent.FLAG_ACTIVITY_NEW_DOCUMENT or
                                Intent.FLAG_ACTIVITY_MULTIPLE_TASK
                    )
                }
                try {
                    startActivity(goToMarket)
                } catch (e: ActivityNotFoundException) {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())
                        )
                    )
                }
            }
            R.id.contactus -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                val i = Intent(this, ContactUs::class.java)
//                startActivity(i)
            }
            R.id.logout -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                Utility.LogoutDialogue(this, R.style.DialogAnimation) { chose ->
//                    if (chose === DialogClicks.okChose) {
//                        helper.Logout()
//                        val i = Intent(this, Default_sign_in::class.java)
//                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                        startActivity(i)
//                    }
//                }
            }
            R.id.aboutapp -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                val i = Intent(this, AppInfoActivity::class.java)
//                i.putExtra("title", "about")
//                startActivity(i)

            }
            R.id.howitwork -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
            }
        }
    }

}
