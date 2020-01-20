package com.hossam.codesroots.presentation.myAccount;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.entities.MyAccount;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.availablebanks.BanksActivity;
import com.paytabs.paytabs_sdk.payment.ui.activities.PayTabActivity;
import com.paytabs.paytabs_sdk.utils.PaymentParams;
import static android.app.Activity.RESULT_OK;

public class MyAccountFragment extends Fragment {

    private MyAccountViewModel mViewModel;
    MYOrdersModel data;

    public static MyAccountFragment newInstance() {
        return new MyAccountFragment();
    }


    TextView totalBalance, acceptedOrder, acceptedOrderCost, canceledOrder, canceledOrderCost,ratingBar,
            monthlyOrder, monthlyOrderCost, weeklyOrder, weeklyOrderCost, totalMonthlyOrder, appPercentage, total,banks,payonline;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.firstmyccount, container, false);
        findViewsFromXml(view);
        banks.setOnClickListener(v -> startActivity(new Intent(getActivity(), BanksActivity.class)));
        payonline.setOnClickListener(v -> payonlineByPaytabs());
        return view;
    }

    private void findViewsFromXml(View view) {

        //totalBalance = view.findViewById(R.id.totalBalance);
        acceptedOrder = view.findViewById(R.id.acceptedOrder);
        acceptedOrderCost = view.findViewById(R.id.acceptedOrderCost);
        canceledOrder = view.findViewById(R.id.canceledOrder);
        canceledOrderCost = view.findViewById(R.id.canceledOrderCost);
        monthlyOrder = view.findViewById(R.id.monthlyorder);
        monthlyOrderCost = view.findViewById(R.id.monthlycost);
        weeklyOrder = view.findViewById(R.id.weekorder);
        weeklyOrderCost = view.findViewById(R.id.weekcost);
        totalMonthlyOrder = view.findViewById(R.id.totalmonthlyCost);
        appPercentage = view.findViewById(R.id.appPercentage);
        total = view.findViewById(R.id.total);
        banks = view.findViewById(R.id.banks);
        payonline = view.findViewById(R.id.payonline);

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this, getViewModelFactory()).get(MyAccountViewModel.class);
        mViewModel.myAccountMutableLiveData.observe(this, new Observer<MyAccount>() {
            @Override
            public void onChanged(@Nullable MyAccount myAccount) {
                setDataintoFields(myAccount);
            }
        });
        // TODO: Use the ViewModel


    }

    private void setDataintoFields(MyAccount myAccount) {

        try {
            totalBalance.setText(String.valueOf(myAccount.getSort().getTotal()));
            monthlyOrder.setText(String.valueOf(myAccount.getSort().getMonthcount()));
            weeklyOrder.setText(String.valueOf(myAccount.getSort().getWeekcount()));
            weeklyOrderCost.setText(String.valueOf(myAccount.getSort().getTotal_week()));
            monthlyOrderCost.setText(String.valueOf(myAccount.getSort().getTotal_month()));
            totalMonthlyOrder.setText(String.valueOf(myAccount.getSort().getTotal_month()));
            acceptedOrder.setText(String.valueOf(myAccount.getSort().getAcceptcount()));
            acceptedOrderCost.setText(String.valueOf(myAccount.getSort().getAcceptsum()));
            canceledOrder.setText(String.valueOf(myAccount.getSort().getCancelcount()));
            if (myAccount.getSort().getCancelsum() != null)
                canceledOrderCost.setText(String.valueOf(myAccount.getSort().getCancelcount()));
            else
                canceledOrderCost.setText("0");

            double percentage = myAccount.getSort().getTotal_month()*0.20;

            appPercentage.setText(String.valueOf(percentage));
            total.setText(String.valueOf(myAccount.getSort().getTotal_month()-percentage));

        } catch (Exception e) {
            Log.d("fsd", e.getMessage());
        }
    }

    @NonNull
    private ViewModelProvider.Factory getViewModelFactory() {
        return new ViewModelFactory(getActivity().getApplication());
    }

    private void payonlineByPaytabs()
    {
        Intent in = new Intent(getActivity().getApplicationContext(), PayTabActivity.class);
        in.putExtra(PaymentParams.MERCHANT_EMAIL, "info@codesroots.com"); //this a demo account for testing the sdk
        in.putExtra(PaymentParams.SECRET_KEY,"DbfBxdK5eJDiV9qLinW7ZmhGuveKPQAs1P4z2PEhyiAtr0fKobRox942FiNv9nYbc2LfRpxpI8T988TxkLVSVZUBiH6s44Eh6pxY");//Add your Secret Key Here
        in.putExtra(PaymentParams.LANGUAGE,PaymentParams.ENGLISH);
        in.putExtra(PaymentParams.TRANSACTION_TITLE, "Test Paytabs android library");
        in.putExtra(PaymentParams.AMOUNT, 50.00);

        in.putExtra(PaymentParams.CURRENCY_CODE, "SAU");
        in.putExtra(PaymentParams.CUSTOMER_PHONE_NUMBER, "01151355233");
        in.putExtra(PaymentParams.CUSTOMER_EMAIL, "osama.omar712016@gmail.com");
        in.putExtra(PaymentParams.ORDER_ID, "123456");
        in.putExtra(PaymentParams.PRODUCT_NAME, "Product 1, Product 2");

        //Billing Address
        in.putExtra(PaymentParams.ADDRESS_BILLING, "Flat 1,Building 123, Road 2345");
        in.putExtra(PaymentParams.CITY_BILLING, "MAADI");
        in.putExtra(PaymentParams.STATE_BILLING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_BILLING, "SAU");
        in.putExtra(PaymentParams.POSTAL_CODE_BILLING, "11564"); //Put Country Phone code if Postal code not available '00973'

        //Shipping Address
        in.putExtra(PaymentParams.ADDRESS_SHIPPING, "Flat 1,Building 123, Road 2345");
        in.putExtra(PaymentParams.CITY_SHIPPING, "Manama");
        in.putExtra(PaymentParams.STATE_SHIPPING, "Manama");
        in.putExtra(PaymentParams.COUNTRY_SHIPPING, "SAU");
        in.putExtra(PaymentParams.POSTAL_CODE_SHIPPING, "11564"); //Put Country Phone code if Postal code not available '00973'

        //Payment Page Style
        in.putExtra(PaymentParams.PAY_BUTTON_COLOR, "#2474bc");
        in.putExtra(PaymentParams.THEME, PaymentParams.THEME_LIGHT);

        //Tokenization
        in.putExtra(PaymentParams.IS_TOKENIZATION, true);
        startActivityForResult(in, PaymentParams.PAYMENT_REQUEST_CODE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PaymentParams.PAYMENT_REQUEST_CODE) {
            Log.e("Tag", data.getStringExtra(PaymentParams.RESPONSE_CODE));
            Log.e("Tag", data.getStringExtra(PaymentParams.TRANSACTION_ID));
            Toast.makeText(getActivity(), data.getStringExtra(PaymentParams.RESPONSE_CODE), Toast.LENGTH_LONG).show();
            Toast.makeText(getActivity(), data.getStringExtra(PaymentParams.TRANSACTION_ID), Toast.LENGTH_LONG).show();
            if (data.hasExtra(PaymentParams.TOKEN) && !data.getStringExtra(PaymentParams.TOKEN).isEmpty()) {
                Log.e("Tag", data.getStringExtra(PaymentParams.TOKEN));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_EMAIL));
                Log.e("Tag", data.getStringExtra(PaymentParams.CUSTOMER_PASSWORD));
            }
        }
    }

}
