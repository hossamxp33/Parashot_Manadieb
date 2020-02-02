package com.hossam.codesroots.presentation.newOrderFragment.adapter;

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

import java.util.List;


public class StoreAdapter extends RecyclerView.Adapter<StoreAdapter.ViewHolder> {

    Context mcontext;
    List<Orderdetail> storesData;


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

    @Override
    public void onBindViewHolder(final StoreAdapter.ViewHolder holder, final int position)
    {
        holder.name.setText( storesData.get(position).getStoreName());
        holder.descrip.setText( storesData.get(position).getNotes());
        Glide.with(mcontext).load(storesData.get(position).getNotes()).into(holder.item_img);


    }
    @Override
    public int getItemCount() {
//        if (productData!= null)
//            return  productData.size();
//        else
            return storesData.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        RecyclerView recyclerViewImages;
        TextView name,price, storePlace,productrate,descrip;
        RatingBar ratingBar;
        ImageView item_img;
        public ViewHolder(View v) {
            super(v);
            mView=v;
            name =mView.findViewById(R.id.store_name);
            storePlace =mView.findViewById(R.id.store_place);
            ratingBar =mView.findViewById(R.id.rates);
            item_img = mView.findViewById(R.id.item_img);
            productrate =mView.findViewById(R.id.productrate);
            descrip =mView.findViewById(R.id.shop_description);
        }
    }
}
