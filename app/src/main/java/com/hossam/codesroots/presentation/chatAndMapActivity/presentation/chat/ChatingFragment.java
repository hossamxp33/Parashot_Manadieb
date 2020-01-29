package com.hossam.codesroots.presentation.chatAndMapActivity.presentation.chat;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.nkzawa.emitter.Emitter;
import com.google.gson.Gson;
import com.hossam.codesroots.helper.FileUtils;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.chatAndMapActivity.Helper.message;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.chatmessages;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.ChatViewModel;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.ViewModelFactory;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.adapter.ChatListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.hossam.codesroots.helper.MyService.mSocket;


public class ChatingFragment extends Fragment implements View.OnClickListener {
    RecyclerView recyclerView;
    ChatListAdapter chatListAdapter;
    private List<chatmessages.MyChatBean> allMessage;
    private chatmessages allData;
    EditText etMessage;
    private static final int LOAD_IMG_REQUEST_CODE = 123;
    ImageView send, getimage;
    ChatViewModel chatViewModel;
    FrameLayout progress;
    TextView typing;
    String roomId;
    TextView userName, storeName, ordernumber, cost, store_location, user_location;
    ImageView storeCall, deliveryCall, storeLocation, userLocation;
    int orderId;
    String ordercost,notes;

    public ChatingFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.activity_chat, container, false);
        findFromXml(view);
        mSocket.on("room_message", onNewMessage);
        roomId = getArguments().getString("roomId");
        mSocket.emit("create_room", roomId);
        orderId = getArguments().getInt("orderid", 0);
        PreferenceHelper.setOrderId(orderId);
        notes = getArguments().getString("notes");
        ordercost = getArguments().getString("ordercost");
//        send.setOnClickListener(v -> sendmessage());
//        getimage.setOnClickListener(v -> addimage(view));
        //mSocket.on("typing", onNewTyping);
        chatViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ChatViewModel.class);

        chatViewModel.getChatData(1, orderId);

        chatViewModel.chatMessages.observe(this, chatmessages ->
                {
                    allData = chatmessages;
                    allMessage = chatmessages.getMyChat();
                   // if ()
                    if (chatmessages.getOrder().get(0).getNotes()!=null)
                        chatmessages.getMyChat().add(new chatmessages.MyChatBean(chatmessages.getOrder().get(0).getNotes(),
                                0, "", "", 2));

                    chatListAdapter = new ChatListAdapter(getActivity(), chatmessages.getMyChat());
                    recyclerView.setAdapter(chatListAdapter);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    //mLayoutManager.setReverseLayout(true);
                    if (chatmessages.getOrder() != null) {
                        if (chatmessages.getOrder().get(0).getSmallstore()!=null) {
                            storeName.setText(chatmessages.getOrder().get(0).getSmallstore().getName());
                            store_location.setText(chatmessages.getOrder().get(0).getSmallstore().getAddress());
                        }
                        userName.setText(chatmessages.getOrder().get(0).getUser().getUsername());
                        ordernumber.setText("رقم الطلب : " + chatmessages.getOrder().get(0).getId());
                        cost.setText(chatmessages.getOrder().get(0).getDelivery_price() + "");
                            user_location.setText(chatmessages.getOrder().get(0).getUser_address());
                    }
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setAdapter(chatListAdapter);
                    recyclerView.scrollToPosition(chatListAdapter.getItemCount() - 1);
                }
        );

        chatViewModel.addMessageLiveDataValue.observe(this, addMessage ->
                {
                    message message = new message();
                    message.setMessage(addMessage.getChat().getPost());
                    message.setTime(addMessage.getChat().getCreated());
                    message.setFrom(addMessage.getChat().getUser_id());
                    if (addMessage.getChat().getPhoto() != null)
                        message.setImageuri(addMessage.getChat().getPhoto());
                    else
                        message.setImageuri("");
                    Gson gson = new Gson();
                    try {
                        JSONObject obj = new JSONObject(gson.toJson(message));
                        mSocket.emit("room_message", roomId, obj);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
        );

//        etMessage.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void afterTextChanged(Editable arg0) {
//                enableSubmitIfReady();
//            }
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                // mSocket.emit("typing", roomId);
//            }
//        });

        return view;
    }

    private void findFromXml(View view) {
        userName = view.findViewById(R.id.user_name);
        storeName = view.findViewById(R.id.storname);
        ordernumber = view.findViewById(R.id.order_num);
        cost = view.findViewById(R.id.delivery_cost);
        deliveryCall = view.findViewById(R.id.call);
        storeCall = view.findViewById(R.id.call2);
        deliveryCall.setOnClickListener(this);
//        storeCall.setOnClickListener(this);
        progress = view.findViewById(R.id.progress);
//        getimage = view.findViewById(R.id.ivPhoto);
//        send = view.findViewById(R.id.ivSend);
//        typing = view.findViewById(R.id.typing);
    recyclerView = view.findViewById(R.id.rvList);
//        etMessage = view.findViewById(R.id.etMessage);
        storeLocation = view.findViewById(R.id.stor_location); //action
        userLocation = view.findViewById(R.id.user_location);  //action
        user_location = view.findViewById(R.id.user_location_txt); // text
        store_location = view.findViewById(R.id.store_location);//text
//        send.setEnabled(false);
    }


    Date c = Calendar.getInstance().getTime();
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
    String formattedDate = df.format(c);

    public void enableSubmitIfReady() {

        boolean isReady = etMessage.getText().toString().length() > 0;
        send.setEnabled(isReady);
    }

    public void sendmessage() {
        chatViewModel.addMessaege(PreferenceHelper.getUserId(), orderId, roomId, etMessage.getText().toString(), null);
        etMessage.setText("");
    }

    private void addimage(View view) {
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, LOAD_IMG_REQUEST_CODE);
        } else {
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1234);
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOAD_IMG_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                final Uri imageUri = data.getData();
                MultipartBody.Part photo_part = prepareFilePart("photo", imageUri);
                chatViewModel.addMessaege(PreferenceHelper.getUserId(), orderId, roomId, etMessage.getText().toString(), photo_part);
            } else {
                Toast.makeText(getActivity(), "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        }
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new ViewModelFactory(getActivity().getApplication());
    }

    @NonNull
    private MultipartBody.Part prepareFilePart(String name, Uri fileUri) {
        File file = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            file = FileUtils.getFile(getActivity(), fileUri);
        }

        RequestBody requestFile =
                RequestBody.create(
                        MediaType.parse("image"),
                        file
                );
        return MultipartBody.Part.createFormData(name, file.getName(), requestFile);
    }

    private Emitter.Listener onNewMessage = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    JSONObject data = (JSONObject) args[0];
                    String message, time, photo;
                    int fromm;
                    try {
                        message = data.getString("message");
                        time = data.getString("time");
                        fromm = data.getInt("from");
                        photo = data.getString("imageuri");
                        allMessage.add(new chatmessages.MyChatBean(message, fromm, photo, time, 2));
                        if (chatListAdapter != null)
                            chatListAdapter.notifyDataSetChanged();
                        else
                            chatListAdapter = new ChatListAdapter(getActivity(), allMessage);

                        recyclerView.scrollToPosition(allMessage.size() - 1);
                    } catch (JSONException e) {
                        Log.d("crach", e.getMessage());
                        // return;
                    }
                });
            }
        }
    };

    private Emitter.Listener onNewTyping = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    typing.setVisibility(View.VISIBLE);
                }
            });
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        // mSocket.disconnect();
        //  mSocket.off("new message", onNewMessage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.call:
                makePhonecall(allData.getOrder().get(0).getDelivry().getPhone());
                break;

            case R.id.call2:
                makePhonecall(allData.getOrder().get(0).getSmallstore().getPhone());
                break;

            case R.id.user_location:
                Intent i2 = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+
                 PreferenceHelper.getCURRENTLAT()+","+PreferenceHelper.getCURRENTLONG()+
                        "&daddr="+allData.getOrder().get(0).getUser_lat()+","+allData.getOrder().get(0).getUser_long()));
                startActivity(i2);
                break;

            case R.id.stor_location:

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr="+
                        PreferenceHelper.getCURRENTLAT()+","+PreferenceHelper.getCURRENTLONG()+
                        "&daddr="+allData.getOrder().get(0).getSmallstore().getLatitude()+","+allData.getOrder().get(0).getSmallstore().getLongitude()));
                startActivity(i);
                break;
        }
    }

    private void makePhonecall(String phone) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phone));
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 15);
            return;
        }
        getActivity().startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        PreferenceHelper.setOrderId(0);
        super.onDestroyView();
    }
}
