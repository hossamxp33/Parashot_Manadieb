package com.hossam.codesroots.presentation.availablebanks.adapters;


import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.hossam.codesroots.entities.AvailableBanks;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.availablebanks.BanksActivity;
import com.hossam.codesroots.presentation.confirm_payment.ConfirmPaymentActivity;
import java.util.List;


public class BanksAdapter extends RecyclerView.Adapter<BanksAdapter.ViewHolder>  {

    private Context context;
    List<AvailableBanks.DataBean> data;

    public BanksAdapter(BanksActivity mcontext, List<AvailableBanks.DataBean> data1) {
        context = mcontext;
        this.data = data1;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bank_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

        Glide.with(context).load(data.get(position).getBankimage()).into(holder.bank_image);
        holder.bankname.setText(data.get(position).getBankname_ar());
        holder.account_num.setText(String.valueOf(data.get(position).getBankaccount_number()));
        holder.abn_num.setText(String.valueOf(data.get(position).getIban()));

        holder.mView.setOnClickListener(v -> {

            Intent intent = new Intent(context, ConfirmPaymentActivity.class);
            intent.putExtra("bankId",data.get(position).getId());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final View mView;
        private TextView bankname,account_num,abn_num;
        private ImageView bank_image;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            bankname = mView.findViewById(R.id.name);
            account_num = mView.findViewById(R.id.account);
            bank_image = mView.findViewById(R.id.item_img);
            abn_num = mView.findViewById(R.id.abn_num);
        }
    }
}