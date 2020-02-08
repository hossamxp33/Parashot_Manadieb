package com.delivery24.view.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.delivery24.R
import com.delivery24.service.models.NotifModel
import com.delivery24.service.webApi.GetCallBack
import kotlinx.android.synthetic.main.fr_notification.*

/**
 * Created by Hossam on 12/24/2019.
 */
class FrNotification : Fragment() {

    private var active = false
    private var frNotification: FrNotification? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    lateinit var rootView: View;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.fr_notification, container, false)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        frNotification = this
        val notification = ViewModelProviders.of(this).get(NotificationVM::class.java)

        notification.getResponse("1", GetCallBack { isOk, requestCode, o ->
            if (isOk) {
                if (frNotification!!.isVisible) {
                    val response = o as NotifModel
                    if (response.data != null && !response.data.isEmpty()) {
                        val adaptercat = NotificationAdapter(
                            context,
                            response.data
                        )
                        rvNotif.setAdapter(adaptercat)
                        progressBarloadNotf.setVisibility(View.GONE)
                    } else {
                        noNotif.setVisibility(View.VISIBLE)
                        progressBarloadNotf.setVisibility(View.GONE)
                    }
                }
            } else {

            }
        })


    }


    override fun onStart() {
        super.onStart()
        active = true
    }

    override fun onStop() {
        super.onStop()
        active = false
    }

//    companion object {
//        lateinit var homeData: ArrayList<HomeListModel.DataBean>
//    }
//
//    override fun onResume() {
//        super.onResume()
//        if (receiver == null) {
//            receiver = Receiver()
//            val filter = IntentFilter(BroadcastHelper.ACTION_NAME)
//            activity?.registerReceiver(receiver, filter)
//            isReciverRegistered = true
//        }
//    }
//
//    override fun onDestroy() {
//        if (isReciverRegistered) {
//            if (receiver != null)
//                activity?.unregisterReceiver(receiver)
//        }
//        super.onDestroy()
//    }
//
//    private inner class Receiver : BroadcastReceiver() {
//        override fun onReceive(arg0: Context, arg1: Intent) {
//            val methodName = arg1.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME)
//            if (methodName != null && methodName.length > 0) {
//                when (methodName) {
//                    "category_update" -> if (active) {
//                        val pos = arg1.getStringExtra("pos")
//                        type = arg1.getStringExtra("type")
//                        getStores(pos)
//                        getGoogleAPi(type.toString())
//                    }
//                    else -> {
//                    }
//                }
//            }
//        }
//    }
}