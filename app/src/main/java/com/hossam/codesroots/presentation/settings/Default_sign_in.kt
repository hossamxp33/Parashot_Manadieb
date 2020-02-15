package com.hossam.codesroots.presentation.settings

import android.Manifest.permission_group.SMS
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.hossam.codesroots.delivery24.R

import com.facebook.*
import com.facebook.appevents.AppEventsLogger
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.hossam.codesroots.dataLayer.repositries.GetCallBack
import com.hossam.codesroots.entities.SocialMediaModel
import com.hossam.codesroots.helper.PreferenceHelper
import com.hossam.codesroots.helper.Utility
import com.hossam.codesroots.helper.Utils
import com.hossam.codesroots.presentation.MainActivity
import com.hossam.codesroots.presentation.settings.SocialMedialVM
import kotlinx.android.synthetic.main.activity_contact_us.*
import kotlinx.android.synthetic.main.activity_default_sign_in.*


class Default_sign_in : AppCompatActivity(), View.OnClickListener {

    lateinit var helper: PreferenceHelper
    private var mLastClickTime: Long = 0
    private var vm: SocialMedialVM? = null
    var gender: String = ""

    // facebook
    private var callbackManager: CallbackManager? = null

    //gmail
    val RC_SIGN_IN: Int = 1
    lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var mGoogleSignInOptions: GoogleSignInOptions
    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_default_sign_in)
        helper = PreferenceHelper(this)
        facebook.setOnClickListener(this)
        gmail.setOnClickListener(this)
        phone.setOnClickListener(this)
        if (PreferenceHelper.getCurrentLanguage(this) == "ar") {
                    Utility.setLocale(this,"ar");
            helper.setlanguage("ar")

                }
        else {
            Utility.setLocale(this, "en");
        }
        initFacebook()
        FacebookSdk.sdkInitialize(getApplicationContext());
        configureGoogleSignIn()

    }


    private fun configureGoogleSignIn() {
        mGoogleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .requestProfile()
                .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, mGoogleSignInOptions)
        FirebaseApp.initializeApp(this);
        firebaseAuth = FirebaseAuth.getInstance()
    }

    private fun setupUI() {
        google_button.setOnClickListener {
            signIn()
        }
    }

    private fun signIn() {
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun initFacebook() {
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object :
                FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                val request =
                        GraphRequest.newMeRequest(loginResult.accessToken) { `object`, response ->
                            try {
                                if (`object`.has("id")) {
                                    if (`object`.has("email")) {
                                        helper.email = response.getJSONObject().getString("email")
                                    }
                                    if (`object`.has("first_name")) {
                                        helper.firstName =
                                                response.getJSONObject().getString("first_name")
                                    }
                                    if (`object`.has("last_name")) {
                                        helper.lastName =
                                                response.getJSONObject().getString("last_name")
                                    }
                                    if (`object`.has("gender")) {
                                        helper.gender = response.getJSONObject().getString("gender")
                                        gender = response.getJSONObject().getString("gender")
                                    }
                                    SendApi(
                                            loginResult.accessToken.token.toString(),
                                            response.getJSONObject().getString("first_name") + " " + response.getJSONObject().getString(
                                                    "last_name"
                                            ),
                                            "", "", "", gender
                                    )
                                    LoginManager.getInstance().logOut()
                                } else {
                                    Log.e("FBLOGIN_FAILD", `object`.toString())
                                }
                            } catch (e: Exception) {
                                e.printStackTrace()
//                        dismissDialogLogin()
                            }
                        }
                val parameters = Bundle()
                parameters.putString("fields", "id,email,first_name,last_name,gender")
                request.parameters = parameters
                request.executeAsync()
            }

            override fun onCancel() {
                Log.e("FBLOGIN_FAILD", "Cancel")
            }

            override fun onError(error: FacebookException) {
                Log.e("FBLOGIN_FAILD", "ERROR", error)
            }
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager?.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                account?.let { firebaseAuthWithGoogle(it) }
            } catch (e: ApiException) {

                Log.e("FBLOGIN_FAILD", e.toString())

                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun firebaseAuthWithGoogle(acct: GoogleSignInAccount) {
        val credential = GoogleAuthProvider.getCredential(acct.idToken, null)
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener {
            if (it.isSuccessful) {
//                startActivity(Default_sign_in.getLaunchIntent(this))
                helper.email = acct.email!!.toString()
                helper.firstName = acct.displayName!!.toString()
                SendApi(
                        acct.idToken!!.toString(),
                        acct.displayName!!.toString(),
                        acct.email!!.toString(), "", "", ""
                )
            } else {

                Toast.makeText(this, "Google sign in failed:(", Toast.LENGTH_LONG).show()
            }
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
                val signInIntent: Intent = mGoogleSignInClient.signInIntent
                startActivityForResult(signInIntent, RC_SIGN_IN)
            }
            R.id.phone -> {
                if (SystemClock.elapsedRealtime() - mLastClickTime < 500) {
                    return
                }
                mLastClickTime = SystemClock.elapsedRealtime()
//                val intent = Intent(this, SMS::class.java)
//                startActivity(intent)
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
     //   val dialog = Utility.showProgressDialog(this, getString(R.string.loading), false)
        vm = ViewModelProviders.of(this).get(SocialMedialVM::class.java)
        vm!!.callBacks.observe(this, Observer {
            if (it.isSuccess) {
                val response = it as SocialMediaModel
//                Utils.hideKeyboard(this)
                    if (response.data!!.getLast() != "1" || response.data.delivery == null) {
                        helper.userID = response.data.id.toString()
                        helper.accessToken = response.data.token
                        val intent = Intent(this, Register::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        helper.userID = response.data!!.id.toString()
                        helper.accessToken = response.data!!.token
                        helper.setdeliveryId(response.data.delivery.toString())

                        val i = Intent(this, MainActivity::class.java)
                        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                        startActivity(i)
                        finish()
                    }


            }
        })
        vm!!.getResponse(
                token, username, mail, pass, mobile, gender,
                GetCallBack { isOk, requestCode, o ->

                })
    }

}
