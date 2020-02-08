package com.delivery24.view.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.delivery24.R;
import com.delivery24.databinding.ListNotificationBinding;
import com.delivery24.service.models.NotifModel;

import org.jetbrains.annotations.NotNull;
import org.sufficientlysecure.htmltextview.HtmlHttpImageGetter;

import java.text.ParseException;
import java.util.ArrayList;

import static com.delivery24.helpers.Utils.TimeFormat_TimeDifferent;

/**
 * Created by Hossam on 12/24/2019.
 */
public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<NotifModel.DataBean> items;

    public NotificationAdapter(Context context, ArrayList<NotifModel.DataBean> models) {
        this.context = context;
        this.items = models;
    }

    @NotNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        ListNotificationBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.list_notification, parent, false);

        return new NotificationAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NotNull final NotificationAdapter.ViewHolder holder, final int position) {

        if (items.get(position).getText() != null && !items.get(position).getText().equals("")) {
            holder.binding.text.setHtml(items.get(position).getText(),
                    new HtmlHttpImageGetter(holder.binding.text));
        }
        try {
            if (items.get(position).getCreated() != null) {
                holder.binding.time.setText(TimeFormat_TimeDifferent(context, items.get(position).getCreated()));
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (position == 0) {
            holder.binding.circle.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_red));
        } else {
            holder.binding.circle.setBackground(ContextCompat.getDrawable(context, R.drawable.circle_gray));
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final ListNotificationBinding binding;

        ViewHolder(ListNotificationBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
