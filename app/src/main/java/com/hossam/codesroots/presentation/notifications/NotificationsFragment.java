package com.hossam.codesroots.presentation.notifications;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.notifications.adapter.NotificationsAdapter;

public class NotificationsFragment extends Fragment {

    private NotificationsViewModel mViewModel;
    private RecyclerView recyclerView;
    private FrameLayout progress;

    public static NotificationsFragment newInstance() {
        return new NotificationsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,@Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.notifications_fragment, container, false);
        progress = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.notification_recycle);
        mViewModel = ViewModelProviders.of(this,getViewModelFactory()).get(NotificationsViewModel.class);

        mViewModel.NotificationsMutableLiveData.observe(this,notifications ->
                {
                    if (notifications.getData().size()>0)
                    recyclerView.setAdapter(new NotificationsAdapter(getActivity(),notifications.getData()));

                    else
                        Snackbar.make(view,R.string.no_notification,Snackbar.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                });

        mViewModel.errorAccount.observe(this,throwable ->
                {
                    Toast.makeText(getActivity(),getText(R.string.error_add),Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                });
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }


    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        if (getActivity()!=null)
        return new ViewModelFactory(getActivity().getApplication());
        else
            return null;
    }

}
