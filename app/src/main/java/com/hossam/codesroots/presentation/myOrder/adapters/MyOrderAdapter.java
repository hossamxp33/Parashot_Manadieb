package com.hossam.codesroots.presentation.myOrder.adapters;

import android.content.Context;
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
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.parashot_manadieb.R;
import com.hossam.codesroots.presentation.allProductInsideOrderFragment.ProductsInsideOrderFragment;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.CustomView> {


    private Context context;
    List<MYOrdersModel.DataBean> orderData;
    public MyOrderAdapter(Context context, List<MYOrdersModel.DataBean> data) {
        this.context = context;
        orderData = data;
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

        if (orderData.get(position).getOrderdetails().size()>0) {
            if (orderData.get(position).getOrderdetails().get(0).getProduct().getProductphotos().size() > 0)
                Glide.with(context).load(orderData.get(position).getOrderdetails().get(0).getProduct().getProductphotos().get(0).getPhoto()).into(holder.item_img);
        }
        else
            Glide.with(context).load(orderData.get(position).getStore_icon()).into(holder.item_img);

        if (orderData.get(position).getSmallStore()!=null)
            holder.storeName.setText(orderData.get(position).getSmallStore().getName());
        else
            holder.storeName.setText(orderData.get(position).getStorename());


        holder.clientName.setText(orderData.get(position).getUser().getName());
        if (orderData.get(position).getCreated()!=null)
            holder.orderDate.setText(getdate(orderData.get(position).getCreated()));

            if (orderData.get(position).getOrderdetails().size() > 0) {
                holder.productName.setText(orderData.get(position).getOrderdetails().get(0).getProduct().getName());
                holder.productPrice.setText(orderData.get(position).getOrderdetails().get(0).getProduct().getPrice() + " ريال ");
            }
            else
            {
                holder.productPrice.setText(context.getText(R.string.procenotdetected));
                holder.productName.setText(orderData.get(position).getNotes());

            }

        holder.itemView.setOnClickListener(v -> {
            if (orderData.get(position).getStore_id() != 0) {
                Fragment fragment = new ProductsInsideOrderFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList("allProduct" , (ArrayList<? extends Parcelable>) orderData.get(position).getOrderdetails());
                fragment.setArguments(bundle);
                ((FragmentActivity) context).getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).commit();
            }
            else
                Toast.makeText(context,context.getText(R.string.orderfromgoogle),Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return orderData.size();
    }

    class CustomView extends RecyclerView.ViewHolder {
        TextView storeName,clientName,orderStatues,orderDate,productName,productPrice;
        ImageView item_img;
        public final View mView;
        public CustomView(@NonNull View itemView) {

            super(itemView);
            mView = itemView;
            storeName = mView.findViewById(R.id.storenamevalue);
            clientName = mView.findViewById(R.id.clientnamevalue);
            orderStatues = mView.findViewById(R.id.orderstatuesvalue);
            orderDate = mView.findViewById(R.id.orderdatevalue);
            item_img = mView.findViewById(R.id.item_img);
            productName = mView.findViewById(R.id.item_description);
            productPrice = mView.findViewById(R.id.item_price);
        }
    }


    private String getdate(String date) {

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
