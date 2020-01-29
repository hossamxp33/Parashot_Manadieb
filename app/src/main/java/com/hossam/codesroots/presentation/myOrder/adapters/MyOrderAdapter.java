package com.hossam.codesroots.presentation.myOrder.adapters;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.hossam.codesroots.entities.MyOrderData;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.allProductInsideOrderFragment.ProductsInsideOrderFragment;
import com.hossam.codesroots.presentation.chatAndMapActivity.ChatAndMapActivity;
import com.hossam.codesroots.presentation.myOrder.MyOrderViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.CustomView> {

    private Context context;
    List<MyOrderData> orderData;
    MyOrderViewModel mViewModel;

    int orderId;

    public MyOrderAdapter(Context context, List<MyOrderData> data, MyOrderViewModel mViewModel1) {
        this.context = context;
        orderData = data;
        mViewModel = mViewModel1;
    }


    @NonNull
    @Override
    public CustomView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.myorder_adapter_item, parent, false);

        return new CustomView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomView holder, final int position) {

        switch (orderData.get(position).getOrderStatus()) {
            case "0":
                holder.orderStatues.setText(context.getString(R.string.gotoclient));
                holder.orderStatues.setTextColor(context.getResources().getColor(R.color.gray));
                break;
            case "1":
                holder.orderStatues.setText(context.getString(R.string.waitstart));
                holder.orderStatues.setTextColor(context.getResources().getColor(R.color.red));
                break;
            case "2":
                holder.orderStatues.setText(context.getString(R.string.gotoclientnow));
                holder.orderStatues.setTextColor(context.getResources().getColor(R.color.green));
                break;

            case "3":
                holder.orderStatues.setText(context.getString(R.string.waitdeliver));
                holder.orderStatues.setTextColor(context.getResources().getColor(R.color.blue));
                break;
        }

        if  (orderData.get(position).getUser() != null) {
         if (orderData.get(position).getUser().getPhoto() !=null)
                Glide.with(context).load(orderData.get(position).getUser().getPhoto()).into(holder.item_img);
        }
        else
            Glide.with(context).load(orderData.get(position).getOrderdetails()).into(holder.item_img);

        holder.clientName.setText(orderData.get(position).getUser().getUsername());
        holder.orderId.setText(orderData.get(position).getId().toString());
        if (orderData.get(position).getCreated() != null)
            holder.orderDate.setText(getDate(orderData.get(position).getCreated()));

        if (orderData.get(position).getOrderdetails().size() > 0) {
         holder.orderCost.setText(orderData.get(position).getOrderdetails().get(0).getPrice() + " ريال ");
        } else {
            holder.orderCost.setText(context.getText(R.string.procenotdetected));
            holder.orderCost.setText(orderData.get(position).getNotes());
        }

        holder.itemView.setOnClickListener(v -> {
            if (orderData.get(position).getOrderdetails().size() > 0) {
                Fragment fragment = new ProductsInsideOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("allProduct", (ArrayList<? extends Parcelable>) orderData.get(position).getOrderdetails());
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
            } else
                Toast.makeText(context, context.getText(R.string.orderfromgoogle), Toast.LENGTH_SHORT).show();
        });

        holder.action.setOnClickListener(v -> {
            int newStatues = 0;
            if (orderData.get(position).getOrderStatus().matches("1"))
                newStatues = 1;
            else if (orderData.get(position).getOrderStatus().matches("2"))
                newStatues = 2;
            else if (orderData.get(position).getOrderStatus().matches("3"))
                newStatues = 3;
            String roomId = orderData.get(position).getRoomID();
            mViewModel.editResult(orderData.get(position).getId(), newStatues);
            int finalNewStatues = newStatues;

            mViewModel.editResult.observe((LifecycleOwner) context, aBoolean -> {
                if (aBoolean) {
                    if (finalNewStatues == 3)
                        PreferenceHelper.setRoomId(roomId);
                    orderData.get(position).getOrderStatus();
                    notifyDataSetChanged();
                }
            });
        });

        holder.map.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatAndMapActivity.class);
            intent.putExtra("chatormap", "map");
            intent.putExtra("roomId", orderData.get(position).getRoomID());
            intent.putExtra("notes", orderData.get(position).getNotes());
            context.startActivity(intent);
        });

        holder.chat.setOnClickListener(v -> {
            Intent intent = new Intent(context, ChatAndMapActivity.class);
            intent.putExtra("chatormap", "chat");
            intent.putExtra("roomId", orderData.get(position).getId());
            intent.putExtra("orderid", orderData.get(position).getId());
            intent.putExtra("roomId", orderData.get(position).getRoomID());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return orderData.size();
    }

    class CustomView extends RecyclerView.ViewHolder {
        TextView  clientName, orderStatues, action,orderId,orderCost,orderDate;
        ImageView item_img, chat, map;
        public final View mView;

        public CustomView(@NonNull View itemView) {

            super(itemView);
            mView = itemView;
            orderId = mView.findViewById(R.id.orderIdvalue);
            clientName = mView.findViewById(R.id.clientnamevalue);
            orderStatues = mView.findViewById(R.id.orderstatuesvalue);
            orderCost = mView.findViewById(R.id.order_costvalue);
            orderDate = mView.findViewById(R.id.order_datevalue);
            item_img = mView.findViewById(R.id.item_img);
            action = mView.findViewById(R.id.action);
            map = mView.findViewById(R.id.delivery_view);
            chat = mView.findViewById(R.id.chating);
        }
    }


    private String getDate(String date) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZZZZZ");
        try {
            Date dateObj = sdf.parse(date);
            Log.d("newdatein", dateObj.getTime() + "");
            String timestamp = String.valueOf(dateObj.getTime());//  //Example -> in ms
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            String dateString = formatter.format(new Date(Long.parseLong(timestamp)));
            return dateString;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
