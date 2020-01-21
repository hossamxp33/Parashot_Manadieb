package com.hossam.codesroots.helper;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.IO;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.entities.MyOrderData;
import com.hossam.codesroots.entities.UserInfo;
import com.hossam.codesroots.presentation.MainActivity;
import com.hossam.codesroots.presentation.mainFragment.MainViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.net.URISyntaxException;


public class MyService extends Service implements  NetworkChangeReceiver.ConnectivityReceiverListener  {
    BroadcastReceiver mReceiver;
    boolean isRegistered = false;
    String userKey = null;
    public static com.github.nkzawa.socketio.client.Socket mSocket;
    {
        try {
          //  mSocket = IO.socket("http://parashotescoket.codesroots.com:2800");
          mSocket = IO.socket("http://192.168.1.2:2800");
        }
        catch (URISyntaxException e) {
        }
    }

    @Override
    public void onCreate() {
        // get an instance of the receiver in your service
        IntentFilter filter = new IntentFilter();
        filter.addAction("action");
        filter.addAction("anotherAction");
        mReceiver = new MyReceiver();
        MyApplication.getInstance().setConnectivityListener(this);
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


        mSocket.on("disconnect", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent1.putExtra("action", "disconnect");
                startActivity(intent1);
                userKey = null;
            }
        });

        mSocket.on("client_online", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Log.d("active_users",(String) args[0]);
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

        mSocket.on("invite_room", args -> {
            Gson g = new Gson();
            MYOrdersModel p = g.fromJson(String.valueOf((JSONObject) args[1]), (Type) MYOrdersModel.class);


            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("name", "ss");
            try {
                JSONObject jsonObject = (JSONObject) args[1];
                intent1.putExtra("new_order", 1);
               intent1.putExtra("data",p);
            }
            catch (Exception e) {
                Log.d("asdf",e.getMessage());
            }
            startActivity(intent1);

            Log.d("sdfa", (String) args[0]);
        });
        mSocket.on("accept_offer", args -> {
            Gson g = new Gson();
            MYOrdersModel p = g.fromJson(String.valueOf((JSONObject) args[0]), (Type) MYOrdersModel.class);


            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("name", "ss");
            try {
                JSONObject jsonObject = (JSONObject) args[1];
                intent1.putExtra("new_order", 1);
                intent1.putExtra("data",p);
            }
            catch (Exception e) {
                Log.d("asdf",e.getMessage());
            }
            startActivity(intent1);

            Log.d("sdfa", (String) args[0]);
        });

    }


    public void makeUserConnection() {
        UserInfo userInfo = new UserInfo(PreferenceHelper.getUserId(), "osama_mandoib", userKey
                , PreferenceHelper.getCURRENTLAT(), PreferenceHelper.getCURRENTLONG());
//        UserInfo userInfo = new UserInfo(PreferenceHelper.getUserId(), "osama_mandoib", userKey
//                , "29.973101258870273", "31.23717986047268");
        Gson gson = new Gson();
        try {
            JSONObject obj = new JSONObject(gson.toJson(userInfo));
            mSocket.emit("create_user", obj);
            Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
            intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent1.putExtra("action", "connect");
            startActivity(intent1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (isConnected)
                makeUserConnection();
    }

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // do something
            Log.v("r", "receive " + intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME));
            String methodName = intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            if (methodName.matches("startTrack")) {
                mSocket.emit("", 12);
            }
        }

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