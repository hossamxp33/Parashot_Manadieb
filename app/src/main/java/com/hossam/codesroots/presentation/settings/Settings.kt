package com.hossam.codesroots.presentation.settings

import android.app.PendingIntent.getActivity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders


import com.hossam.codesroots.delivery24.R
import com.hossam.codesroots.entities.MYOrdersModel
import com.hossam.codesroots.entities.MyAccount
import com.hossam.codesroots.helper.PreferenceHelper
import com.hossam.codesroots.helper.Utility
import com.hossam.codesroots.presentation.myAccount.MyAccountViewModel
import com.hossam.codesroots.presentation.myAccount.ViewModelFactory
import kotlinx.android.synthetic.main.activity_settings.*
import kotlinx.android.synthetic.main.myorder_adapter_item.*
import kotlinx.android.synthetic.main.second_card_in_myaccount.*

class Settings : AppCompatActivity(), View.OnClickListener {
    private var mLastClickTime: Long = 0
    lateinit var helper: PreferenceHelper
    private var mViewModel: MyAccountViewModel? = null
    internal var data: MYOrdersModel? = null
    internal var totalBalance: TextView? = null
    var acceptedOrder:TextView? = null
    var acceptedOrdeCount:TextView? = null

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
        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MyAccountViewModel::class.java)
        mViewModel!!.myAccountMutableLiveData.observe(this, Observer<MyAccount> { myAccount -> setDataintoFields(myAccount) })
        totalBalance = findViewById(R.id.BalaARRO);
        acceptedOrdeCount  = findViewById(R.id.OrderCountarrow)
        acceptedOrder   = findViewById(R.id.OrderCostarrow)
    }
     fun onActivityCreated(savedInstanceState: Bundle?) {




    }
    private fun setDataintoFields(myAccount: MyAccount?) {

        try {
            totalBalance!!.setText(myAccount!!.sort.total.toString())

            acceptedOrder!!.setText(myAccount.sort.acceptcount.toString())
            acceptedOrdeCount!!.text = myAccount.sort.acceptsum.toString()


        } catch (e: Exception) {
            Log.d("fsd", e.message)
        }

    }
    private fun getViewModelFactory(): ViewModelProvider.Factory {
        return ViewModelFactory(this.application)
    }
    override fun onClick(view: View) {
        when (view.id) {
            R.id.editProfile -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                val i = Intent(this, EditProfile::class.java)
                startActivity(i)
            }
            R.id.lang -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                val i = Intent(this, SetLang::class.java)
                startActivity(i)

            }
            R.id.termsofuse -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                val i = Intent(this, AppInfoActivity::class.java)
                i.putExtra("title", "term")
                startActivity(i)
            }
            R.id.usagepolicy -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                val i = Intent(this, AppInfoActivity::class.java)
                i.putExtra("title", "policy")
                startActivity(i)
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
                val i = Intent(this, ContactUs::class.java)
                startActivity(i)
            }
            R.id.logout -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                helper.Logout()
                val i = Intent(this, Default_sign_in::class.java)
                i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(i)

            }
            R.id.aboutapp -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                val i = Intent(this, AppInfoActivity::class.java)
                i.putExtra("title", "about")
                startActivity(i)

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
