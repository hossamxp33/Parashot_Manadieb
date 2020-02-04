package com.hossam.codesroots.presentation.settings
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.hossam.codesroots.delivery24.R

import kotlinx.android.synthetic.main.activity_app_info.*
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by Hossam on 1/6/2020.
 */


class AppInfoActivity : AppCompatActivity() {
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        val bundle: Bundle? = intent.extras
        title = bundle!!.getString("title")
        initToolBar()

//        val dialog = Utility.showProgressDialog(this, getString(R.string.loading), false)
//        val vm = ViewModelProviders.of(this).get(GetAppVM::class.java)
//        vm.getResponse(GetCallBack { isOk, requestCode, o ->
//            if (isOk) {
//                val response = o as getAppinfo
//                if (response.data != null && !response.data.isEmpty()) {
//                    if (title.equals("about")) {
//                        if (response.data[0].about_us != null) {
//                            staticText.text = response.data[0].about_us
//                        }
//                    } else if (title.equals("term")) {
//                        if (response.data[0].terms != null) {
//                            staticText.text = response.data[0].terms
//                        }
//                    } else if (title.equals("policy")) {
//                        if (response.data[0].uses != null) {
//                            staticText.text = response.data[0].uses
//                        }
//                    }
//                } else {
//
//                }
//                Utility.hideProgress(dialog)
//            } else {
//                Utility.hideProgress(dialog)
//            }
//        })
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        btnBack.setOnClickListener { v ->
            finish()
        }
        if (title.equals("about")) {
            name.text = getString(R.string.aboutapp)
        } else if (title.equals("term")) {
            name.text = getString(R.string.terms)
        } else if (title.equals("policy")) {
            name.text = getString(R.string.usage)
        }
    }

}

