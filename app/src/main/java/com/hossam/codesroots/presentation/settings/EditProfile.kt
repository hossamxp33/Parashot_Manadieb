package com.hossam.codesroots.presentation.settings

import android.content.Context
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
import com.hossam.codesroots.delivery24.R
import com.hossam.codesroots.helper.PreferenceHelper
import com.hossam.codesroots.helper.Utils
import com.hossam.codesroots.presentation.loginfragment.RegisterVM
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.spinner_item.view.*

import kotlinx.android.synthetic.main.toolbar.*
import java.util.*

class EditProfile : AppCompatActivity(), View.OnClickListener {
    private var mLastClickTime: Long = 0
    lateinit var helper: PreferenceHelper
    private var vm: RegisterVM? = null
    var gender: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        signupBT.setOnClickListener(this)
        helper = PreferenceHelper(this)
        initToolBar()

        if (helper.firstName != null && helper.lastName != null) {
            Edname.setText(helper.firstName + " " + helper.lastName)
        }
        if (helper.email != null) {
            Edemail.setText(helper.email)
        }
        if (helper.password != null) {
            Edpassword.setText(helper.password)
        }
        if (helper.gender != null) {
            gender = helper.gender
        }
        setAdapter()
    }

//    fun updateProfile(
//        username: String,
//        mail: String,
//        pass: String,
//        mobile: String,
//        gender: String
//    ) {
//        val dialog = Utility.showProgressDialog(this, getString(R.string.loading), false)
//        val vm = ViewModelProviders.of(this).get(EditProfileVM::class.java)
//        vm.getResponse(username, mail, pass, mobile, gender, GetCallBack { isOk, requestCode, o ->
//            if (isOk) {
//                helper.firstName = username
//                helper.email = mail
//                helper.password = pass
//                helper.gender = gender
//                Toast.makeText(this, getString(R.string.profileUpdated), Toast.LENGTH_LONG)
//                    .show()
//                Utility.hideProgress(dialog)
//            } else {
//                Utility.hideProgress(dialog)
//            }
//        })
//    }

    private fun initToolBar() {
        setSupportActionBar(toolbar)
        btnBack.setOnClickListener { v ->
            finish()
        }
        name.text = getString(R.string.editprofile)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.signupBT -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()

//                if (gender.equals("")) {
//                    Snackbar.make(
//                        mainregister,
//                        getString(R.string.selectgender),
//                        Snackbar.LENGTH_LONG
//                    ).setAction(getString(R.string.close)) {}.setActionTextColor(
//                        getResources().getColor(android.R.color.white)
//                    ).show()
//                    return
//                }
//                if (!Utils.FieldValidation(
//                        this,
//                        Edpassword,
//                        5,
//                        getString(R.string.enterpassword),
//                        mainregister
//                    )
//                )
//                    return

//                updateProfile(
//                    Edname.text.toString().trim(),
//                    Edemail.text.toString().trim(),
//                    Edpassword.text.toString().trim(),
//                    "",
//                    gender
//                )
            }
        }
    }



    fun setAdapter() {
        val localesArray = ArrayList<String>()
        localesArray.add(getString(R.string.selectgender))
        localesArray.add(getString(R.string.male))
        localesArray.add(getString(R.string.female))

        val spinnerArrayAdapter =
            Adapter_spinner(this@EditProfile, R.layout.spinner_item, localesArray)
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
            val view = LayoutInflater.from(this@EditProfile)
                .inflate(R.layout.spinner_item, parent, false)
            view.spinner_textView.setText(arrayList[position])
            if (this.isEnabled(position)) {
                view.spinner_textView.setTextColor(resources.getColor(R.color.gray))
            }
            return view
        }
    }

}
