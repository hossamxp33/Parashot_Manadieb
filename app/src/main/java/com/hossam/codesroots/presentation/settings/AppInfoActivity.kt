package com.hossam.codesroots.presentation.settings
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hossam.codesroots.delivery24.R

import kotlinx.android.synthetic.main.activity_app_info.*
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.toolbar.*


class AppInfoActivity : AppCompatActivity() {
    private var title: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_info)

        val bundle: Bundle? = intent.extras
        title = bundle!!.getString("title")
        initToolBar()

        val vm = ViewModelProviders.of(this).get(AppInfoVM::class.java)
        vm.callBack.observe(this, Observer {

            if (title.equals("about")) {
                staticText.text = it.data.get(0).about_us
            } else if (title.equals("term")) {
                staticText.text = it.data.get(0).terms
            } else if (title.equals("policy")) {
                staticText.text = it.data.get(0).uses
            }
        })

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

