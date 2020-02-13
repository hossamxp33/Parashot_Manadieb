package com.hossam.codesroots.presentation.myOrder;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.snackbar.Snackbar;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import com.hossam.codesroots.entities.MyOrderData;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.presentation.myOrder.adapters.MyOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFragment extends Fragment {

    private MyOrderViewModel mViewModel;
    private FrameLayout progress;
    List<MyOrderData> completOrders = new ArrayList<>(), notcompleteOrders = new ArrayList<>();
    private RadioGroup orderstatues;
    TextView txtnotfound;
    MyOrderAdapter myOrderAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout_user;
    public static MyOrderFragment newInstance() {
        return new MyOrderFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_main_myorders, container, false);

        orderstatues = view.findViewById(R.id.orderstatues);
        recyclerView = view.findViewById(R.id.recylerview);
        progress = view.findViewById(R.id.progress);
        txtnotfound = view.findViewById(R.id.txtnotfound);
        refreshLayout_user = view.findViewById(R.id.refreshLayout_user);
        mViewModel = ViewModelProviders.of(this).get(MyOrderViewModel.class);
        mViewModel.getData(new PreferenceHelper(getContext()).getdeliveryId());

        refreshLayout_user.setOnRefreshListener(() -> {
            mViewModel.getData(new PreferenceHelper(getContext()).getdeliveryId());
            refreshLayout_user.setRefreshing(false);
        });

        mViewModel.editResult.observe(this, aBoolean -> {
            if (aBoolean) {
                mViewModel.getData(new PreferenceHelper(getContext()).getdeliveryId());
            }
        });


        mViewModel.allMyOrders.observe(this, myOrdersModel -> {
            progress.setVisibility(View.GONE);
            assert myOrdersModel != null;
            notcompleteOrders =myOrdersModel.notCommpleteOrderData;
            completOrders =myOrdersModel.commpleteOrderData;
            orderstatues.check(R.id.ordernotcommplet);

            if (notcompleteOrders.size()>0) {
                myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders,mViewModel);
                recyclerView.setAdapter(myOrderAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                txtnotfound.setVisibility(View.GONE);
            }
            else {
                txtnotfound.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });


        mViewModel.myOrdersError.observe(this,throwable ->
        { Snackbar.make(view,getText(R.string.erroroccur),Snackbar.LENGTH_LONG).show();
                progress.setVisibility(View.GONE);
        }
        );

        orderstatues.setOnCheckedChangeListener((group, checkedId) -> {

            switch (checkedId) {
                case R.id.ordercommplet:
                    if (completOrders.size()>0) {
                        myOrderAdapter = new MyOrderAdapter(getActivity(), completOrders,mViewModel);
                        recyclerView.setAdapter(myOrderAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                        txtnotfound.setVisibility(View.GONE);
                    }
                    else {
                        txtnotfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                    break;
                case R.id.ordernotcommplet:
                    if (notcompleteOrders.size()>0) {
                        myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders,mViewModel);
                        recyclerView.setAdapter(myOrderAdapter);
                        recyclerView.setVisibility(View.VISIBLE);
                        txtnotfound.setVisibility(View.GONE);
                    }
                    else {
                        txtnotfound.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }
                    break;
            }
        });

        return  view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // TODO: Use the ViewModel
    }

//    @NonNull
//    private ViewModelProvider.Factory getViewModelFactory() {
//        return new MyOrderViewModelFactory(getActivity().getApplication(),userid);
//    }
}
