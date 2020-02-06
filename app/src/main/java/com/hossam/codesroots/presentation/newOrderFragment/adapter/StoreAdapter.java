package com.hossam.codesroots.presentation.newOrderFragment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.entities.Orderdetail;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.helper.Utils;

import java.math.BigDecimal;
import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    private Context mcontext;
    private List<Orderdetail> storesData;

    public StoreAdapter(FragmentActivity activity,List<Orderdetail> storedata) {
          mcontext=activity;
          storesData = storedata;
    }

    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item, parent, false);
        return new StoreAdapter.ViewHolder(view);
    }

    public void setData(List<Orderdetail> data)
    {
        storesData = data;
        notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(final StoreAdapter.ViewHolder holder, final int position)
    {
        holder.name.setText(storesData.get(position).getStoreName());
        if (storesData.get(position).getNotes()!=null) {
            holder.descrip.setText(storesData.get(position).getNotes());
        }
        Glide.with(mcontext).load(storesData.get(position).getSmallstore().getLogo()).into(holder.item_img);
        holder.distance1.setText(Utils.customFormat(BigDecimal.valueOf(Utils.calculateDistance(storesData.get(position).getSmallstore().getLatitude(),
                storesData.get(position).getSmallstore().getLongitude(),
                PreferenceHelper.getCURRENTLAT(), PreferenceHelper.getCURRENTLONG()))));
        holder.distance2.setText(Utils.customFormat(BigDecimal.valueOf(Utils.calculateDistance(storesData.get(position).getStoreLat(), storesData.get(position).getStoreLng(),
                PreferenceHelper.getCURRENTLAT(), PreferenceHelper.getCURRENTLONG()))));



    }


    @Override
    public int getItemCount() {
        if (storesData!= null)
            return  storesData.size();
        else
            return storesData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        RecyclerView recyclerViewImages;
        TextView name,price, storePlace,productrate,descrip,distance1,distance2;
        RatingBar ratingBar;
        ImageView item_img;
        public ViewHolder(View v) {
            super(v);
            mView=v;
            name =mView.findViewById(R.id.stor_name);
            storePlace =mView.findViewById(R.id.store_place);
            descrip =mView.findViewById(R.id.stor_des);
            item_img = mView.findViewById(R.id.stor_img);
            distance1 =mView.findViewById(R.id.distance1);
            distance2 =mView.findViewById(R.id.distance2);
        }
    }
}
