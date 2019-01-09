package com.hossam.codesroots.presentation.mainFragment;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;
import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;
import com.hossam.codesroots.entities.OrderDetails;
import com.hossam.codesroots.entities.UserInfo;
import com.hossam.codesroots.helper.PreferenceHelper;
import org.json.JSONException;
import org.json.JSONObject;


public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    MutableLiveData<UserInfo> newUser = new MutableLiveData<>();
    public MutableLiveData<String> ddd = new MutableLiveData<>();
    public MutableLiveData<OrderDetails> orderDetailsMutableLiveData = new MutableLiveData<>();
    boolean isFirst = false;     ///////// isFirst Connection

    public MainViewModel() {

//        if (!isFirst) {
//            makeUserConnection();
//            isFirst = true;
//        }

       // mSocket.on("create_user", onNewConnection);
        //mSocket.on("invite_room", onNewOrder);
    }


    private Emitter.Listener onNewOrder = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            Log.d("asdf", (String) args[0]);
          //  orderDetailsMutableLiveData.postValue(new OrderDetails());
        }
    };


    private Emitter.Listener onNewConnection = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject data = (JSONObject) args[0];
            Log.d("sd", args[0].toString());
            UserInfo userInfo = new UserInfo();
            try {
                userInfo.setName(data.getString("name"));
                userInfo.setUerlat(data.getString("uerlat"));
                userInfo.setUsdeerlang(data.getString("usdeerlang"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
          //  newUser.postValue(userInfo);

        }
    };

}
