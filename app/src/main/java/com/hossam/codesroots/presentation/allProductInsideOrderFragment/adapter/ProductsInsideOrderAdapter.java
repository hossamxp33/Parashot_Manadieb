package com.hossam.codesroots.presentation.allProductInsideOrderFragment.adapter;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.parashot_manadieb.R;
import java.util.List;


public class ProductsInsideOrderAdapter extends RecyclerView.Adapter<ProductsInsideOrderAdapter.ViewHolder> {

    Context mcontext;
    RecycleImagesInsideOrderAdapter recycle_images_adapter;
    List<MYOrdersModel.DataBean.OrderdetailsBean> productData;


    public ProductsInsideOrderAdapter(FragmentActivity activity, List<MYOrdersModel.DataBean.OrderdetailsBean> orderdetailsBeans) {

        mcontext=activity;
        productData = orderdetailsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_insideorders_adapter_item, parent, false);
        return new ProductsInsideOrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {

        if (productData.get(position).getProduct().getProductphotos()!=null)
        {
            recycle_images_adapter = new RecycleImagesInsideOrderAdapter(mcontext,productData.get(position).getProduct().getProductphotos());
            holder.recyclerViewImages.setLayoutManager(new GridLayoutManager(mcontext,3));
            holder.recyclerViewImages.setAdapter(recycle_images_adapter);
        }

        holder.name.setText(productData.get(position).getProduct().getName());
        holder.price.setText(productData.get(position).getProduct().getPrice()+" ريال ");
        if (productData.get(position).getProduct().getTotal_rating().size()>0) {
            holder.ratecount.setText("(" + productData.get(position).getProduct().getTotal_rating().get(0).getCount() + ")");
            holder.ratingBar.setRating(productData.get(position).getProduct().getTotal_rating().get(0).getStars());
        }

        else
        {
            holder.ratecount.setText("(0)");
            holder.ratingBar.setRating(0);
        }

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
        TextView name,price, ratecount,productrate;
        RatingBar ratingBar;
        public ViewHolder(View v) {
          super(v);
            mView=v;

            recyclerViewImages = mView.findViewById(R.id.viewpager);
            name =mView.findViewById(R.id.item_name);
            price =mView.findViewById(R.id.item_price);
            ratecount =mView.findViewById(R.id.ratecount);
            ratingBar =mView.findViewById(R.id.rates);
            productrate =mView.findViewById(R.id.productrate);
        }


    }
}
