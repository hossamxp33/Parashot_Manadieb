package com.hossam.codesroots.presentation.newOrderFragment.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
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
    List<Orderdetail> productData;


    public StoreAdapter(FragmentActivity activity) {
        mcontext=activity;

    }

    @Override
    public StoreAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.store_item, parent, false);
        return new StoreAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final StoreAdapter.ViewHolder holder, final int position) {

//        if  (productData.get(position) !=null ) {
//            if (productData.get(position).getStoreIcon() !=null)
//                Glide.with(mcontext).load(productData.get(position).getStoreIcon()).into(holder.item_img);
//        }
//        else
//            Glide.with(mcontext).load(productData.get(position).getStoreIcon()).into(holder.item_img);
//
//        if (productData.size() > 0) {
//            holder.name.setText(productData.get(0).getSmallstore().getName());
//            holder.storePlace.setText(productData.get(0).getSmallstore().getAddress());
//
//            //      holder.price.setText(productData.get(0).getPrice() + " ريال ");
//            // holder.descrip.setText(productData.get(0).getSmallstore().getDescription());
//            //holder.productrate.setText(productData.get(0).getSmallstore().getAddress());
//
//        } else {
//            //   holder.price.setText(mcontext.getText(R.string.procenotdetected));
//            //  holder.name.setText(productData.get(0).getNotes());
//        }

//        if (productData.get(position).getProduct().getTotal_rating().size()>0) {
//            holder.ratecount.setText("(" + productData.get(position).getProduct().getTotal_rating().get(0).getCount() + ")");
//            holder.ratingBar.setRating(productData.get(position).getProduct().getTotal_rating().get(0).getStars());
//        }
//
//        else
//        {
//            holder.ratecount.setText("(0)");
//            holder.ratingBar.setRating(0);
//        }

    }

    @Override
    public int getItemCount() {
//        if (productData!= null)
//            return  productData.size();
//        else

            return 1;
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
