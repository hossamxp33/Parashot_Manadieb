package com.hossam.codesroots.presentation.chatAndMapActivity.presentation.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hossam.codesroots.parashot_manadieb.R;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.chatmessages;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.CustomView> {

    private Context context;
    private List<chatmessages.DataBean> allMessage;
    private static final int VIEW_TYPE_MESSAGE_SENT = 1;
    private static final int VIEW_TYPE_MESSAGE_RECEIVED = 2;
    public ChatListAdapter(FragmentActivity activity, List<chatmessages.DataBean> messages) {

        this.context =  activity;
        allMessage = messages;
     //   preferenceHelper =new PreferenceHelper(context);
    }


    @Override
    public ChatListAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view;

            if (viewType == 1)
                view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_chat_send, parent, false);

            else
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_item_chat_recievied, parent, false);


        return new ChatListAdapter.CustomView(view);

    }


    @Override
    public int getItemViewType(int position) {

        if (allMessage.get(position).getFromm()==1)
            return VIEW_TYPE_MESSAGE_SENT;
        else
            return  VIEW_TYPE_MESSAGE_RECEIVED;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        if (!allMessage.get(position).getPost().matches("")) {
            holder.message.setText(allMessage.get(position).getPost());
            holder.cardwithimage.setVisibility(View.GONE);
            holder.cardwithmessage.setVisibility(View.VISIBLE);
            holder.time.setText(getTime(allMessage.get(position).getCreated()));
        }
        else if (!allMessage.get(position).getPhoto().matches(""))
        {
            holder.cardwithimage.setVisibility(View.VISIBLE);
            holder.cardwithmessage.setVisibility(View.GONE);
            Glide.with(context).load(allMessage.get(position).getPhoto()).into(holder.itemImage);
            holder.time.setText(getTime(allMessage.get(position).getCreated()));
        }
    }

    @Override
    public int getItemCount() {
        return allMessage.size();
    }

     class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView message,time;
        LinearLayout cardwithimage;
        CardView cardwithmessage;


        private CustomView(View view) {
            super(view);
            mView = view;

            message=mView.findViewById(R.id.tvMessage);
            time=mView.findViewById(R.id.tvTime);
            itemImage=mView.findViewById(R.id.image);
            cardwithmessage=mView.findViewById(R.id.cardmessage);
            cardwithimage=mView.findViewById(R.id.cardforimage);

        }
    }

    private String  getTime(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj= sdf.parse(date);
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm aa");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}