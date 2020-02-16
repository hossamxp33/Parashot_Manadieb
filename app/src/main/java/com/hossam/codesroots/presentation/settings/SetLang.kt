package com.hossam.codesroots.presentation.settings

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.hossam.codesroots.delivery24.R
import com.hossam.codesroots.helper.PreferenceHelper
import com.hossam.codesroots.helper.Utility
import com.hossam.codesroots.presentation.MainActivity
import com.hossam.codesroots.presentation.settings.Default_sign_in
import kotlinx.android.synthetic.main.activity_set_lang.*
import kotlinx.android.synthetic.main.toolbar.*
import java.util.*


class SetLang : AppCompatActivity() {
    lateinit var helper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_lang)
        helper = PreferenceHelper(this)
        initToolBar()

        val lang = PreferenceHelper.getCurrentLanguage(this)
println(PreferenceHelper.getCurrentLanguage(this))
        if (PreferenceHelper.getCurrentLanguage(this).equals("ar")) {
            radioArabic.setChecked(true)
            radioEngish.setChecked(false)
        } else {
            radioArabic.setChecked(false)
            radioEngish.setChecked(true)
        }


        radio.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.radioArabic -> {
                    helper.setlanguage("ar")
                    Utility.setLocale(this,"ar");
                    val resources: Resources = this.resources
                    val displayMetrics = resources.displayMetrics
                    val configuration = resources.configuration
                    configuration.setLocale(Locale("ar", "US"))
                    resources.updateConfiguration(configuration, displayMetrics)
                    val i = Intent(this, MainActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                }
                R.id.radioEngish -> {
                    helper.setlanguage("en")
                    Utility.setLocale(this,"en");
                    val resources: Resources = this.resources
                    val displayMetrics = resources.displayMetrics
                    val configuration = resources.configuration
                    configuration.setLocale(Locale("en", "US"))
                    resources.updateConfiguration(configuration, displayMetrics)

                    val i = Intent(this, MainActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                }

            }
        }
    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        btnBack.setOnClickListener { v ->
            finish()
        }
        name.text = getString(R.string.language)
    }
}
