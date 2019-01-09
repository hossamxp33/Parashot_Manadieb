package com.hossam.codesroots.helper;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.google.gson.Gson;
import com.hossam.codesroots.entities.UserInfo;
import com.hossam.codesroots.presentation.MainActivity;
import com.hossam.codesroots.presentation.mainFragment.MainViewModel;
import org.json.JSONException;
import org.json.JSONObject;
import java.net.URISyntaxException;


public class MyService extends Service {
    BroadcastReceiver mReceiver;
    boolean isRegistered = false;
    String userKey = null;
    MainViewModel mainViewModel = new MainViewModel();
    // use this as an inner class like here or as a top-level class
    public static com.github.nkzawa.socketio.client.Socket mSocket;
    {
        try {
            mSocket = IO.socket("http://parashotescoket.codesroots.com:2800");
            // mSocket = IO.socket("http://192.168.1.25:2400");
        } catch (URISyntaxException e) {
        }
    }

    @Override
    public void onCreate() {
        // get an instance of the receiver in your service
        IntentFilter filter = new IntentFilter();
        filter.addAction("action");
        filter.addAction("anotherAction");
        mReceiver = new MyReceiver();
        mSocket.connect();
        registerReceiver(mReceiver, filter);

        mSocket.on("user_connection", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("dfsd", args[0].toString());
                if (userKey == null) {
                    userKey = args[0].toString();
                    makeUserConnection();
                    //  BroadcastHelper.sendInform(getApplicationContext(),"connected");
                }
            }
        });


        mSocket.on("create_user", new Emitter.Listener() {
            @Override
            public void call(Object... args) {

                JSONObject data = (JSONObject) args[0];
                Log.d("sd", args[0].toString());
                UserInfo userInfo = new UserInfo();
                Intent intent = null;
                try {
                    intent = new Intent("new_user");
                    intent.putExtra("name", data.getString("name"));
                    intent.putExtra("lat", data.getString("lat"));
                    intent.putExtra("long", data.getString("long"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                BroadcastHelper.sendInform(getApplicationContext(), "new_user", intent);
            }
        });


        mSocket.on("invite_room", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("name", "ss");
                try {
                    JSONObject jsonObject = (JSONObject) args[1];
                    intent1.putExtra("new_order",1);
                    intent1.putExtra("user_name",jsonObject.getString("user_long"));
                    intent1.putExtra("user_lat",jsonObject.getString("user_lat"));
                    intent1.putExtra("user_long",jsonObject.getString("user_long"));
                    intent1.putExtra("user_address",jsonObject.getString("clientaddress"));
                    intent1.putExtra("stor_lat",jsonObject.getString("storelat"));
                    intent1.putExtra("stor_long",jsonObject.getString("storelang"));
                    intent1.putExtra("store_name",jsonObject.getString("storename"));
                    intent1.putExtra("price",jsonObject.getString("productPrice"));
                    intent1.putExtra("productname",jsonObject.getString("productname"));
                    intent1.putExtra("payment",jsonObject.getString("user_long"));
                    intent1.putExtra("storeaddress",jsonObject.getString("storeaddress"));
                    intent1.putExtra("orderId",jsonObject.getInt("id"));
                    intent1.putExtra("userid",jsonObject.getInt("userId"));

                }
                catch (Exception e) {
                }
                startActivity(intent1);

                Log.d("sdfa", (String) args[0]);
            }
        });
    }


    public void makeUserConnection() {
        UserInfo userInfo = new UserInfo(PreferenceHelper.getUserId(), "osama_mandoib", userKey
                ,  PreferenceHelper.getCURRENTLAT(),PreferenceHelper.getCURRENTLONG());
        Gson gson = new Gson();
        try {
            JSONObject obj = new JSONObject(gson.toJson(userInfo));
            mSocket.emit("create_user", obj);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            // do something
            Log.v("r", "receive " + intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME));
            String methodName = intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            if (methodName.matches("newMyPrice")) {
            }
        }

        // constructor
        public MyReceiver() {
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mReceiver != null)
            if (isRegistered) {
                unregisterReceiver(mReceiver);
            }
        mSocket.disconnect();
    }

}