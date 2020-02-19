package com.hossam.codesroots.presentation.chatAndMapActivity.presentation.adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.MyChat;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.ImageViewActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.CustomView> {

    private Context context;
    private List<MyChat> allMessage;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    public ChatListAdapter(FragmentActivity activity, List<MyChat> messages) {
        this.context =  activity;
        allMessage = messages;
     //   preferenceHelper =new PreferenceHelper(context);
    }

    @Override
    public ChatListAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;

            if (viewType == 1)
                view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_chat_right, parent, false);

            else
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_chat, parent, false);


        return new ChatListAdapter.CustomView(view);

    }


    @Override
    public int getItemViewType(int position) {

        if (allMessage.get(position).getUser_id() == PreferenceHelper.getUserId())
            return VIEW_TYPE_MESSAGE_SENT;
        else
            return  VIEW_TYPE_MESSAGE_RECEIVED;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        if (!allMessage.get(position).getPost().matches("")) {
            holder.message.setText(allMessage.get(position).getPost());
            holder.itemImage.setVisibility(View.GONE);
            holder.message.setVisibility(View.VISIBLE);
            holder.time.setText(getTime(allMessage.get(position).getCreated()));
        }
        else if (!allMessage.get(position).getPhoto().matches(""))
        {
            holder.itemImage.setVisibility(View.VISIBLE);
            holder.message.setVisibility(View.GONE);
            Glide.with(context).load(allMessage.get(position).getPhoto()).into(holder.itemImage);
            holder.time.setText(getTime(allMessage.get(position).getCreated()));
        }

//        if (allMessage.get(position).getUser().getUser_group_id()==1)
//            holder.sender.setText(R.string.clientt);
//        else  if (allMessage.get(position).getUser().getUser_group_id()==3)
//            holder.sender.setText(R.string.stor);

        holder.itemImage.setOnClickListener(v -> {
            Intent intent = new Intent(context,ImageViewActivity.class);
            intent.putExtra("imageUrl",allMessage.get(position).getPhoto());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return allMessage.size();
    }

     class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage,logo;
        private TextView message,time,timeimg,sender;
        LinearLayout cardwithimage;
        CardView cardwithmessage;

        private CustomView(View view) {
            super(view);
            mView = view;

            message=mView.findViewById(R.id.tvMessage);
            time=mView.findViewById(R.id.tvTime);
            itemImage=mView.findViewById(R.id.mesege_image);
            logo=mView.findViewById(R.id.logo);
     //       cardwithmessage=mView.findViewById(R.id.cardmessage);
       //     cardwithimage=mView.findViewById(R.id.cardforimage);
            timeimg=mView.findViewById(R.id.tvTimeomages);
            sender=mView.findViewById(R.id.sender);
        }
    }

    private String  getTime(String date)
    {
        Date dateObj;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            if (date!=null) {
                dateObj = sdf.parse(date);
                String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
                SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
                String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
                return dateString;
            }
            else
                return null;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}
