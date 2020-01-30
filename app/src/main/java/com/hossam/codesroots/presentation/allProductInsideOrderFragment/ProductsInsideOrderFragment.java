package com.hossam.codesroots.presentation.allProductInsideOrderFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hossam.codesroots.entities.Orderdetail;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.allProductInsideOrderFragment.adapter.ProductsInsideOrderAdapter;
import com.hossam.codesroots.presentation.newOrderFragment.adapter.StoreAdapter;

import java.util.List;


public class ProductsInsideOrderFragment extends Fragment {

    private RecyclerView recyclerView;
    private StoreAdapter storeAdapter;
    List<Orderdetail> orderdetailsBeans;
    public ProductsInsideOrderFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_product_inside_orders, container, false);
        recyclerView = view.findViewById(R.id.recylerviewInside);

        assert getArguments() != null;
        orderdetailsBeans =  getArguments().getParcelableArrayList("allProduct");
        storeAdapter = new StoreAdapter(getActivity(),orderdetailsBeans);
       // storeAdapter.setData(orderdetailsBeans.get(0).s);
        recyclerView.setAdapter(storeAdapter);
        return view;
    }

}
