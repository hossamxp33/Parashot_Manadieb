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
import com.hossam.codesroots.entities.Orderdetail;
import com.hossam.codesroots.delivery24.R;

import java.util.List;


public class newOrderFragmentAdapter extends RecyclerView.Adapter<com.hossam.codesroots.presentation.newOrderFragment.adapter.newOrderFragmentAdapter.ViewHolder> {

    Context mcontext;
    List<Orderdetail> productData;


    public newOrderFragmentAdapter(FragmentActivity activity, List<Orderdetail> orderdetailsBeans) {
        mcontext=activity;
        productData = orderdetailsBeans;

    }

    @Override
    public com.hossam.codesroots.presentation.newOrderFragment.adapter.newOrderFragmentAdapter.ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.first_card_in_new_order, parent, false);
        return new com.hossam.codesroots.presentation.newOrderFragment.adapter.newOrderFragmentAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final com.hossam.codesroots.presentation.newOrderFragment.adapter.newOrderFragmentAdapter.ViewHolder holder, final int position) {

        if  (productData.get(position) !=null ) {
            if (productData.get(position).getStoreIcon() !=null)
                Glide.with(mcontext).load(productData.get(position).getStoreIcon()).into(holder.item_img);
        }
        else
            Glide.with(mcontext).load(productData.get(position).getStoreIcon()).into(holder.item_img);

        if (productData.size() > 0) {
            holder.name.setText(productData.get(0).getSmallstore().getName());
            holder.storePlace.setText(productData.get(0).getSmallstore().getAddress());

            //      holder.price.setText(productData.get(0).getPrice() + " ريال ");
            // holder.descrip.setText(productData.get(0).getSmallstore().getDescription());
            //holder.productrate.setText(productData.get(0).getSmallstore().getAddress());

        } else {
            //   holder.price.setText(mcontext.getText(R.string.procenotdetected));
            //  holder.name.setText(productData.get(0).getNotes());
        }

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
        if (productData!= null)
            return  productData.size();
        else

            return 0;
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
            item_img = mView.findViewById(R.id.item_img);

        }


    }
}
