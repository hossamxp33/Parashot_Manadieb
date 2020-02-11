package com.hossam.codesroots.presentation.chatAndMapActivity.presentation.chat;

import android.Manifest;

import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.PopupMenu;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.delivery24.view.chat.SendComplain;
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
import com.hossam.codesroots.presentation.myOrder.MyOrderViewModel;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.map.MapingFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import static android.app.Activity.RESULT_OK;
import static com.hossam.codesroots.helper.MyService.mSocket;


public class ChatingFragment extends Fragment implements View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    RecyclerView recyclerView;
    ChatListAdapter chatListAdapter;
    private List<chatmessages.MyChatBean> allMessage;
    private chatmessages allData;
    EditText etMessage;
    private static final int LOAD_IMG_REQUEST_CODE = 123;
    ImageView getimage, userImage;
    ChatViewModel chatViewModel;
    FrameLayout progress;
    TextView typing, send;
    String roomId;
    TextView userName, storeName, ordernumber, cost, store_location, user_location;
    ImageView storeCall, deliveryCall, storeLocation, userLocation, opendialog;
    int orderId;
    String ordercost, notes;
    ProgressBar progressBarload;
    private MyOrderViewModel orderViewModel;
    View view1;

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
        view1 = view;
        mSocket.on("room_message", onNewMessage);
        roomId = getArguments().getString("roomId");
        mSocket.emit("create_room", roomId);
        orderId = getArguments().getInt("orderid", 0);
        PreferenceHelper.setOrderId(orderId);
        notes = getArguments().getString("notes");
        ordercost = getArguments().getString("ordercost");
        send.setOnClickListener(v -> sendmessage());
        getimage.setOnClickListener(v -> addimage(view));
        //mSocket.on("typing", onNewTyping);
        chatViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(ChatViewModel.class);
        orderViewModel = ViewModelProviders.of(this).get(MyOrderViewModel.class);


        orderViewModel.editResult.observe(this, aBoolean -> {
            if (aBoolean) {
                Toast.makeText(getContext(), "تم تعديل حالة الطلب بنجاح ", Toast.LENGTH_LONG).show();
            }
        });

        chatViewModel.getChatData(1, orderId);

        chatViewModel.chatMessages.observe(this, chatmessages ->
                {
                    progressBarload.setVisibility(View.GONE);
                    allData = chatmessages;
                    allMessage = chatmessages.getMyChat();
                    chatListAdapter = new ChatListAdapter(getActivity(), chatmessages.getMyChat());
                    recyclerView.setAdapter(chatListAdapter);
                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
                    //mLayoutManager.setReverseLayout(true);
                    if (chatmessages.getOrder() != null) {
                        if (chatmessages.getOrder().get(0).getSmallstore() != null) {
                            storeName.setText(chatmessages.getOrder().get(0).getSmallstore().getName());
                            store_location.setText(chatmessages.getOrder().get(0).getSmallstore().getAddress());
                        }
                        try {
                            userName.setText(chatmessages.getOrder().get(0).getUser().getUsername());
                            Glide.with(getContext()).load(chatmessages.getOrder().get(0).getUser().getPhoto()).into(userImage);

                        } catch (Exception e) {
                            Log.d("exception ", e.getMessage());
                        }
                        //                      ordernumber.setText("رقم الطلب : " + chatmessages.getOrder().get(0).getId());
                        //  cost.setText(chatmessages.getOrder().get(0).getDelivery_price() + "");
                        //    user_location.setText(chatmessages.getOrder().get(0).getUser_address());
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
        userName = view.findViewById(R.id.Delname);
        userImage = view.findViewById(R.id.userImage);
        storeName = view.findViewById(R.id.storname);
        ordernumber = view.findViewById(R.id.order_num);
        cost = view.findViewById(R.id.delivery_cost);
        deliveryCall = view.findViewById(R.id.call);
        storeCall = view.findViewById(R.id.call2);
        opendialog = view.findViewById(R.id.opendialog);
        deliveryCall.setOnClickListener(this);
        opendialog.setOnClickListener(this);
//        storeCall.setOnClickListener(this);
        progress = view.findViewById(R.id.progress);
        getimage = view.findViewById(R.id.cam);
        send = view.findViewById(R.id.send);
//        typing = view.findViewById(R.id.typing);
        recyclerView = view.findViewById(R.id.rvList);
        etMessage = view.findViewById(R.id.chatMSG);
        storeLocation = view.findViewById(R.id.stor_location); //action
        userLocation = view.findViewById(R.id.location);  //action
        userLocation.setOnClickListener(this);

        user_location = view.findViewById(R.id.user_location_txt); // text
        store_location = view.findViewById(R.id.store_location);//text
        progressBarload = view.findViewById(R.id.progressBarload);//text
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

            case R.id.location:
                Fragment fragment1 = new MapingFragment();
                Bundle bundle = new Bundle();
                bundle.putString("roomId", roomId);
                fragment1.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment1).addToBackStack(null).commit();
                break;


            case R.id.stor_location:

                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://maps.google.com/maps?saddr=" +
                        PreferenceHelper.getCURRENTLAT() + "," + PreferenceHelper.getCURRENTLONG() +
                        "&daddr=" + allData.getOrder().get(0).getSmallstore().getLatitude() + "," + allData.getOrder().get(0).getSmallstore().getLongitude()));
                startActivity(i);
                break;

            case R.id.opendialog:

                PopupMenu popup = new PopupMenu(getContext(), v);
                popup.setOnMenuItemClickListener(this);
                MenuInflater inflater = popup.getMenuInflater();
                inflater.inflate(R.menu.order_options, popup.getMenu());
                popup.show();

                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delivered:
                orderViewModel.editResult(orderId, 3, "");

                return true;
            case R.id.cancel_order:

                Dialog builder = new Dialog(Objects.requireNonNull(getContext()));
                builder.setContentView(R.layout.order_cancel_resones);
                EditText resons = builder.findViewById(R.id.resones);
                TextView send = builder.findViewById(R.id.send);
                send.setOnClickListener(v -> {
                    builder.dismiss();
                    orderViewModel.editResult(orderId, 4, resons.getText().toString());/// cancel order
                });
                builder.show();
                return true;

            case R.id.add_Complaint:
                Intent intent = new Intent(getContext(), SendComplain.class);
                intent.putExtra("delv_id", 1);
                intent.putExtra("ord_id", orderId);
                startActivity(intent);
                return true;
            default:
                return false;
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
