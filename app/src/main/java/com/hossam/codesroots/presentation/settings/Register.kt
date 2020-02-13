package com.hossam.codesroots.presentation.settings

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders

import com.google.android.material.snackbar.Snackbar
import com.hossam.codesroots.dataLayer.repositries.GetCallBack
import com.hossam.codesroots.delivery24.R
import com.hossam.codesroots.entities.RegisterResponse
import com.hossam.codesroots.entities.SocialMediaModel
import com.hossam.codesroots.helper.PreferenceHelper
import com.hossam.codesroots.helper.Utility
import com.hossam.codesroots.helper.Utils
import com.hossam.codesroots.helper.Utils.EmailValidation
import com.hossam.codesroots.helper.Utils.FieldValidation
import com.hossam.codesroots.presentation.MainActivity
import com.hossam.codesroots.presentation.loginfragment.RegisterVM
import kotlinx.android.synthetic.main.activity_edit_profile.mainregister
import kotlinx.android.synthetic.main.activity_edit_profile.signupBT
import kotlinx.android.synthetic.main.activity_edit_profile.spGender
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.spinner_item.view.*
import java.util.*

class Register : AppCompatActivity(), View.OnClickListener {
    private var mLastClickTime: Long = 0
    lateinit var helper: PreferenceHelper
    private var vm: RegisterVM? = null
    var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        signupBT.setOnClickListener(this)
        helper = PreferenceHelper(this)
        setAdapter()
    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.signupBT -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
                if (!FieldValidation(
                        this,
                        ETname,
                        3,
                        getString(R.string.enterfullname),
                        mainregister
                    )
                )
                    return

                if (gender.equals("")) {
                    Snackbar.make(
                        mainregister,
                        getString(R.string.selectgender),
                        Snackbar.LENGTH_LONG
                    ).setAction(getString(R.string.close)) {}.setActionTextColor(
                        getResources().getColor(android.R.color.white)
                    ).show()
                    return
                }


                SendApi()
            }
        }
    }


    fun SendApi() {
        val dialog = Utility.showProgressDialog(this, getString(R.string.loading), false)
        vm = ViewModelProviders.of(this).get(RegisterVM::class.java)
        vm!!.callBacks.observe(this, androidx.lifecycle.Observer {
            if (it.success) {
                val response = it as RegisterResponse

                    helper.setdeliveryId(response.data!!.id.toString())

                    val i = Intent(this, MainActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                    finish()

            } else
                Toast.makeText(this, "حدث خطأ حاول مرة أخري", Toast.LENGTH_SHORT).show()
        })
        vm!!.getResponse(
            ETname.text.toString().trim(),
            phone.text.toString().trim(),
            gender,
            GetCallBack { isOk, requestCode, o ->

            })

    }

    fun setAdapter() {
        val localesArray = ArrayList<String>()
        localesArray.add(getString(R.string.selectgender))
        localesArray.add(getString(R.string.male))
        localesArray.add(getString(R.string.female))

        val spinnerArrayAdapter =
            Adapter_spinner(this@Register, R.layout.spinner_item, localesArray)
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner_item)
        spGender.setAdapter(spinnerArrayAdapter)
        spGender.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View,
                position: Int,
                id: Long
            ) {
                val selectedItemText = parent.getItemAtPosition(position) as String
                if (position > 0) {
                    gender = selectedItemText
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {}
        })
    }

    inner class Adapter_spinner(
        context: Context,
        textViewResourceId: Int,
        internal var arrayList: ArrayList<String>
    ) :
        ArrayAdapter<String>(context, textViewResourceId, arrayList) {

        override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = super.getDropDownView(position, convertView, parent)
            val tv = view as TextView
            if (position == 0) {
                tv.setTextColor(resources.getColor(R.color.black))
            } else {
                tv.setTextColor(resources.getColor(R.color.gray))
            }
            return view
        }

        override fun isEnabled(position: Int): Boolean {
            return position != 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            return getCustomView(position, parent)
        }

        private fun getCustomView(position: Int, parent: ViewGroup): View {
            val view = LayoutInflater.from(this@Register)
                .inflate(R.layout.spinner_item, parent, false)
            view.spinner_textView.setText(arrayList[position])
            if (this.isEnabled(position)) {
                view.spinner_textView.setTextColor(resources.getColor(R.color.gray))
            }
            return view
        }
    }

}
