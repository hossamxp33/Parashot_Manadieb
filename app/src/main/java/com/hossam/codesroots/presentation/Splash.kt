package com.hossam.codesroots.presentation

import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.hossam.codesroots.delivery24.R
import com.hossam.codesroots.helper.Constants
import com.hossam.codesroots.helper.PreferenceHelper
import com.hossam.codesroots.helper.Utility
import com.hossam.codesroots.presentation.intro.IntroScreens
import java.util.*

class SplashScreen : AppCompatActivity() {
    lateinit var helper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        helper = PreferenceHelper(this)

        if (helper.getFirstTime() != null) {
            if (!helper.getFirstTime().equals("")) {
                if (helper.getlanguage() != null) {
                    if (!helper.getlanguage().equals("")) {
                        setLocal(true)
                    } else {
                        setLocal(false)
                    }
                } else {
                    setLocal(false)
                }
            } else {
                setLocal(false)
            }
        } else {
            setLocal(false)
        }

        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 1500)


//        if (helper.getFirstTime() != null && helper.getFirstTime() != "") {
//            Handler().postDelayed({
//                val intent = Intent(this, MainActivity::class.java)
//                startActivity(intent)
//                finish()
//            }, 1500)
//        } else {
//            helper.firstTime = "checked"
//            Handler().postDelayed({
//                val intent = Intent(this, IntroScreens::class.java)
//                startActivity(intent)
//                finish()
//            }, 1500)
//        }
    }

    private fun setLocal(isFound: Boolean) {
        val lang = PreferenceHelper.getCurrentLanguage(baseContext)
//        if (!isFound) {
//            val default = Utility.getCurrentLocale(baseContext).toString()
//            Utility.setLocale(baseContext, default)
//        } else {
            if (lang.equals("ar", ignoreCase = true)) {
                Utility.setLocale(baseContext, Constants.ARABIC_CODE)
                val resources: Resources = this.resources
                val displayMetrics = resources.displayMetrics
                val configuration = resources.configuration
                configuration.setLocale(Locale("ar", "US"))
                resources.updateConfiguration(configuration, displayMetrics)
            }
            else {
                Utility.setLocale(baseContext, Constants.ENGLISH_CODE)
                val resources: Resources = this.resources
                val displayMetrics = resources.displayMetrics
                val configuration = resources.configuration
                configuration.setLocale(Locale("en", "US"))
                resources.updateConfiguration(configuration, displayMetrics)
            }
      //  }
    }
}
