package com.hossam.codesroots.presentation.notifications.adapter;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;


import com.hossam.codesroots.entities.Notifications;
import com.hossam.codesroots.delivery24.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private Context context;
    List<Notifications.DataBean> data;


    public NotificationsAdapter(FragmentActivity activity, List<Notifications.DataBean> data1) {
        this.context =  context;
        this.data = data1;
    }

    @Override
    public NotificationsAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.notifications_adapter_item, parent, false);
        return new NotificationsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationsAdapter.ViewHolder holder, final int position) {


        holder.name.setText(data.get(position).getText());
        holder.time.setText(getdate(data.get(0).getCreated()));
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    private String  getdate(String date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj= sdf.parse(date);
            Log.d("newdatein",dateObj.getTime()+"");
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
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        TextView name,comment,time;
        RatingBar ratingBar;
        ImageView person_img;
        public ViewHolder(View v) {
            super(v);
            mView=v;

            name =mView.findViewById(R.id.notification_text);
            comment =mView.findViewById(R.id.comment);
            time =mView.findViewById(R.id.time);
            ratingBar =mView.findViewById(R.id.rates);
            person_img =mView.findViewById(R.id.person_img);
        }


    }

}
