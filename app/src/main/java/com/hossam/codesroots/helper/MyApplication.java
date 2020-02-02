package com.hossam.codesroots.helper;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import androidx.multidex.MultiDex;
import android.util.Log;
import com.crashlytics.android.ndk.CrashlyticsNdk;
import com.crashlytics.android.Crashlytics;
import com.github.nkzawa.emitter.Emitter;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.MainActivity;

import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MyApplication extends Application {

    public static final String TAG = MyApplication.class.getSimpleName();
    private static MyApplication mInstance;
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics(), new CrashlyticsNdk());
        mInstance = this;
        context = getApplicationContext();
        PreferenceHelper preferenceHelper = new PreferenceHelper(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/JF-Flat-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
       MultiDex.install(this);
    }

    private Emitter.Listener onNewOrder = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d("asdf", (String) args[0]);
            Intent intent = new Intent(context,MainActivity.class);
            intent.putExtra("neworeder",1);
            intent.putExtra("order",args[0].toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
    };
    public static synchronized MyApplication getInstance() {
        return mInstance;
    }



//    public RequestQueue getRequestQueue() {
//        if (mRequestQueue == null) {
//            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
//        }
//
//        return mRequestQueue;
//    }
//
    public void setConnectivityListener(NetworkChangeReceiver.ConnectivityReceiverListener listener) {
        NetworkChangeReceiver.connectivityReceiverListener = listener;
    }
}

