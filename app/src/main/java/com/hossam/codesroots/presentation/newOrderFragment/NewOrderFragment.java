package com.hossam.codesroots.presentation.newOrderFragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetBehavior;

import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.entities.Orderdetail;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.helper.Utils;
import com.hossam.codesroots.helper.directionhelpers.FetchURL;
import com.hossam.codesroots.helper.directionhelpers.TaskLoadedCallback;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.myOrder.MyOrderFragment;
import com.hossam.codesroots.presentation.newOrderFragment.adapter.StoreAdapter;
import com.hossam.codesroots.presentation.newOrderFragment.adapter.newOrderFragmentAdapter;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;

import static android.content.ContentValues.TAG;


@SuppressLint("ValidFragment")
public class NewOrderFragment extends Fragment implements
        OnMapReadyCallback, TaskLoadedCallback, LocationListener, View.OnClickListener {

    MapView mapView;
    GoogleMap map;
    private MarkerOptions placemandoib, placeuser, placestor;
    String delivery_distancetext;
    String delivery_loctext;
    String recieve_distanctexte;
    String unametext;
    String sname;
    double ulat;
    double ulong;
    String uaddress;
    String saddress;
    String productname;
    String price;
    double slat;
    double slong;
    int deliveryId = 1, orderId, userid;
    TextView delivery_distance, delivery_loc, recieve_distance, txtuname, txtsname, txtuaddress, txtsaddress, txtproductname, txtprice, slideTitle, accept, refuse;
    private BottomSheetBehavior mBottomSheetBehaviour;
    NestedScrollView nestedScrollView;
    NewOrderViewModel newOrderViewModel;
    MYOrdersModel data;
    private FrameLayout progress;
    private RecyclerView rv_stores;
    newOrderFragmentAdapter myOrderAdapter;
    StoreAdapter storeAdapter;
    List<Orderdetail> orderdetailsBeans;

    public NewOrderFragment() {

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.firstneworder, container, false);

        newOrderViewModel = ViewModelProviders.of(this).get(NewOrderViewModel.class);
        rv_stores = view.findViewById(R.id.rv_stores);


        newOrderViewModel.newoffer.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                progress.setVisibility(View.GONE);
                if (aBoolean)
                    Toast.makeText(getActivity(), getText(R.string.addOfferSucess), Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), getText(R.string.erroroccur), Toast.LENGTH_SHORT).show();
            }
        });

        findFromXml(view);
        if (getArguments() != null)
            setDataInFields(getArguments());
        try {
            MapsInitializer.initialize(Objects.requireNonNull(getActivity()));
            mapView = view.findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);
        } catch (InflateException e) {
            Log.e(TAG, "Inflate map exception");
        }

        slideTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mBottomSheetBehaviour.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    slideTitle.setText(R.string.moredetails);
                    nestedScrollView.fullScroll(ScrollView.FOCUS_UP);
                } else {
                    mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
                    slideTitle.setText(R.string.lessdetails);
                }
            }
        });
        return view;
    }

    @SuppressLint("SetTextI18n")
    private void setDataInFields(Bundle arguments) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");

        try {

        data = arguments.getParcelable("data");
        storeAdapter = new StoreAdapter(getActivity(), data.getData().get(0).getOrderdetails());

        rv_stores.setAdapter(storeAdapter);
        ulat = data.getData().get(0).getUserLat();

        ulong = data.getData().get(0).getUserLong();
        uaddress = data.getData().get(0).getUserAddress();
        slat = data.getData().get(0).getOrderdetails().get(0).getSmallstore().getLatitude();
        slong = data.getData().get(0).getOrderdetails().get(0).getSmallstore().getLongitude();
        txtsaddress.setText(data.getData().get(0).getOrderdetails().get(0).getSmallstore().getAddress());
        delivery_loc.setText(data.getData().get(0).getUserAddress());

        addLocations(slat,slong,ulat,ulong);
        delivery_distancetext = arguments.getString("storeaddress");
        orderId = data.getData().get(0).getId();
        userid = data.getData().get(0).getUserID();

        recieve_distance.setText(BigDecimal.valueOf(Long.parseLong(Utils.calculateDistance(slat, slong,
                PreferenceHelper.getCURRENTLAT(), PreferenceHelper.getCURRENTLONG())+"")).toString());
        delivery_distance.setText(BigDecimal.valueOf(Long.parseLong(Utils.calculateDistance(ulat, ulong,
                PreferenceHelper.getCURRENTLAT(), PreferenceHelper.getCURRENTLONG())+"")).toString());

        }
        catch (Exception e)
        {}
    }

    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    private void findFromXml(View view) {
//        txtuname = view.findViewById(R.id.username);
//        txtsname = view.findViewById(R.id.store_name);
//       // txtuaddress = view.findViewById(R.id.userplace);
        txtsaddress = view.findViewById(R.id.recieve_loc);
        recieve_distance = view.findViewById(R.id.recieve_distance);


        delivery_loc = view.findViewById(R.id.delivery_loc);
        delivery_distance = view.findViewById(R.id.delivery_distance);
//        txtproductname = view.findViewById(R.id.product_name);
        txtprice = view.findViewById(R.id.cost_value);
        slideTitle = view.findViewById(R.id.title);
       accept = view.findViewById(R.id.send);
//        refuse = view.findViewById(R.id.refuseorder);
       accept.setOnClickListener(this);
////        refuse.setOnClickListener(this);
        nestedScrollView = view.findViewById(R.id.nestedScrool);
        mBottomSheetBehaviour = BottomSheetBehavior.from(nestedScrollView);
        mBottomSheetBehaviour.setHideable(false);
        mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
//        progress = view.findViewById(R.id.progress);

        // set the peek height
        mBottomSheetBehaviour.setPeekHeight(500);

        mBottomSheetBehaviour.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View view, int new_status) {
                if (new_status == BottomSheetBehavior.STATE_EXPANDED) {
                    slideTitle.setText(R.string.lessdetails);
                    nestedScrollView.fullScroll(ScrollView.FOCUS_UP);
                } else {
                    slideTitle.setText(R.string.moredetails);
                }
            }

            @Override
            public void onSlide(@NonNull View view, float v) {
                //  slideTitle.animate().scaleX(100).scaleY(10).setDuration(0).start();
            }
        });
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
    }

    public void addLocations( double d1, double d2, double d3, double d4) {
        placestor = new MarkerOptions().position(new LatLng(d1, d2)).title("موقع الاستلام ");
        placeuser = new MarkerOptions().position(new LatLng(d3, d4)).title("موقع التسليم  ");
        try {
            map.addMarker(placestor);
            //  map.addMarker(placemandoib);
            map.addMarker(placeuser);
            LatLngBounds.Builder builder = new LatLngBounds.Builder();
            builder.include(placestor.getPosition());
            // builder.include(placemandoib.getPosition());
            builder.include(placeuser.getPosition());
            LatLngBounds bounds = builder.build();
            map.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 20));
            // new FetchURL(NewOrderFragment.this).execute(getUrl(placemandoib.getPosition(), placeuser.getPosition(), "driving"), "driving");
            new FetchURL(NewOrderFragment.this).execute(getUrl(placeuser.getPosition(), placestor.getPosition(), "driving"), "driving");

        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    private String getUrl(LatLng origin, LatLng dest, String directionMode) {
        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;
        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;
        String mode = "mode=" + directionMode;
        String parameters = str_origin + "&" + str_dest + "&" + mode;
        String output = "json";
        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
        return url;
    }

    @Override
    public void onTaskDone(Object... values) {
        Polyline currentPolyline = map.addPolyline((PolylineOptions) values[0]);
        currentPolyline.setColor(R.color.colorPrimary);
        currentPolyline.setWidth(11);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                openDialogForAddStorage();
                break;
        }
    }

    private void openDialogForAddStorage() {

                newOrderViewModel.addOffer(orderId, userid, deliveryId, txtprice.getText().toString());
//                progress.setVisibility(View.VISIBLE);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MyOrderFragment()).addToBackStack(null).
                        setCustomAnimations(R.anim.animation_new_order, R.anim.animation_new_order2).commit();


    }

}
