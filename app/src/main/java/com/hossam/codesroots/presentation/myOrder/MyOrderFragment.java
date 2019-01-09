package com.hossam.codesroots.presentation.myOrder;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.parashot_manadieb.R;
import com.hossam.codesroots.presentation.myOrder.adapters.MyOrderAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyOrderFragment extends Fragment {

    private MyOrderViewModel mViewModel;
    private FrameLayout progress;
    List<MYOrdersModel.DataBean> completOrders = new ArrayList<>(), notcompleteOrders = new ArrayList<>();
    private RadioGroup orderstatues;
    TextView txtnotfound;
    MyOrderAdapter myOrderAdapter;
    private RecyclerView recyclerView;

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
        mViewModel = ViewModelProviders.of(this).get(MyOrderViewModel.class);
        mViewModel.allMyOrders.observe(this, new Observer<FilterMyOrder>() {
            @Override
            public void onChanged(@Nullable FilterMyOrder myOrdersModel) {

                progress.setVisibility(View.GONE);
                assert myOrdersModel != null;
                notcompleteOrders =myOrdersModel.notCommpleteOrderData;
                completOrders =myOrdersModel.commpleteOrderData;
                myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
                recyclerView.setAdapter(myOrderAdapter);
                if (notcompleteOrders.size()>0) {
                    myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
                    recyclerView.setAdapter(myOrderAdapter);
                    recyclerView.setVisibility(View.VISIBLE);
                    txtnotfound.setVisibility(View.GONE);
                }
                else {
                    txtnotfound.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }
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
                        myOrderAdapter = new MyOrderAdapter(getActivity(), completOrders);
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
                        myOrderAdapter = new MyOrderAdapter(getActivity(), notcompleteOrders);
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
