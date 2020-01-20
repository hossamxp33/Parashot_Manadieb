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
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.chatAndMapActivity.entities.ChatList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ChatAllListAdapter extends RecyclerView.Adapter<ChatAllListAdapter.CustomView> {


    private Context context;
    private List<ChatList.MyChatBean> allchats;
    PreferenceHelper preferenceHelper;

    public ChatAllListAdapter(FragmentActivity activity, List<ChatList.MyChatBean> list) {
        this.context =  activity;
        allchats = list;
        preferenceHelper =new PreferenceHelper(context);
    }


    @Override
    public ChatAllListAdapter.CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myorder_adapter_item, parent, false);
        return new ChatAllListAdapter.CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        holder.message.setText(allchats.get(position).get_matchingData().getChats().getPost());
        if (allchats.get(position).getUsers2()!=null)
        {
            holder.tvNameC.setText(allchats.get(position).getUsers2().getUsername());
            if (allchats.get(position).getUsers2().getPhoto()!=null)
            Glide.with(context).load(allchats.get(position).getUsers2().getPhoto()).into(holder.itemImage);
        }

        else if (allchats.get(position).getUsers1()!=null)
        {
            holder.tvNameC.setText(allchats.get(position).getUsers1().getUsername());
            if (allchats.get(position).getUsers1().getPhoto()!=null)
                Glide.with(context).load(allchats.get(position).getUsers1().getPhoto()).into(holder.itemImage);
        }

        holder.time.setText(getdate(allchats.get(position).getCreated()));
        holder.mView.setOnClickListener(v -> {
        });


//        if (allchats.get(position).getImageuri()!=null)
//        {
//            holder.cardwithimage.setVisibility(View.VISIBLE);
//            holder.cardwithmessage.setVisibility(View.GONE);
//            if(allchats.get(position).getLocalOrOnlinePath()==0)
//            {
//                Uri myUri = Uri.parse(allchats.get(position).getImageuri());
//               holder.itemImage.setImageURI(myUri);
//            }
//
//        }

    }

    @Override
    public int getItemCount() {
        return allchats.size();
    }

     class CustomView extends RecyclerView.ViewHolder {

        private final View mView;
        private ImageView itemImage;
        private TextView message,time,tvNameC;
        LinearLayout cardwithimage;
        CardView cardwithmessage;
        private CustomView(View view) {
            super(view);
            mView = view;

            //message=mView.findViewById(R.id.tvMessageC);
           // time=mView.findViewById(R.id.tvDateC);
          //  tvNameC=mView.findViewById(R.id.tvNameC);
            itemImage=mView.findViewById(R.id.image);
            cardwithmessage=mView.findViewById(R.id.cardmessage);
            cardwithimage=mView.findViewById(R.id.cardforimage);

        }
    }


    private String  getdate(String date)
    {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj= sdf.parse(date);
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
