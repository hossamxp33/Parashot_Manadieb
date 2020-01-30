package com.hossam.codesroots.presentation.newOrderFragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.hossam.codesroots.entities.MyOrderData;
import com.hossam.codesroots.entities.Orderdetail;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.helper.directionhelpers.FetchURL;
import com.hossam.codesroots.helper.directionhelpers.TaskLoadedCallback;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.myOrder.MyOrderFragment;
import com.hossam.codesroots.presentation.newOrderFragment.adapter.StoreAdapter;
import com.hossam.codesroots.presentation.newOrderFragment.adapter.newOrderFragmentAdapter;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Objects;
import static android.content.ContentValues.TAG;


@SuppressLint("ValidFragment")
public class NewOrderFragment extends Fragment implements
        OnMapReadyCallback,TaskLoadedCallback,LocationListener,View.OnClickListener {

    MapView mapView;
    GoogleMap map;
    private MarkerOptions placemandoib, placeuser,placestor;
    String uname,sname,ulat,ulong,uaddress,saddress,productname,price,slat,slong;
    int deliveryId = 1,orderId,userid;
    TextView txtuname,txtsname,txtuaddress,txtsaddress,txtproductname,txtprice,slideTitle,accept,refuse;
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
                    Toast.makeText(getActivity(),getText(R.string.addOfferSucess),Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(),getText(R.string.erroroccur),Toast.LENGTH_SHORT).show();

            }
        });

        findFromXml(view);
        if (getArguments()!=null)
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
                if (mBottomSheetBehaviour.getState()==BottomSheetBehavior.STATE_EXPANDED) {
                    mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    slideTitle.setText(R.string.moredetails);
                    nestedScrollView.fullScroll(ScrollView.FOCUS_UP);
                }
                else {
                    mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
                    slideTitle.setText(R.string.lessdetails);
                }
            }
        });
        return view;
    }

    private void setDataInFields(Bundle arguments) {
        DecimalFormat decimalFormat = new DecimalFormat("#.#####");
        uname= getArguments().getString("user_name");
        //uname= "osama";
        data = arguments.getParcelable("data");
        storeAdapter = new StoreAdapter(getActivity(),data.getData().get(0).getOrderdetails());

        rv_stores.setAdapter(storeAdapter);
//        ulat= arguments.getString("user_lat");
//        ulong= arguments.getString("user_long");
//        uaddress= arguments.getString("user_address");
//        slat= arguments.getString("stor_lat");
//        slong= arguments.getString("stor_long");
//        sname= arguments.getString("store_name");
//        price= arguments.getString("price");
//        productname= arguments.getString("productname");
//        saddress= arguments.getString("storeaddress");
       orderId= data.getData().get(0).getId();
       userid = data.getData().get(0).getUserID();


//        ulat="31.013056";
//        ulong="32.013056";

//        if (ulat.length()>10)
//        ulat = ulat.substring(0,10);
//        if (ulong.length()>10)
//        ulong = ulong.substring(0,10);


//        txtuname.setText(uname);
//        txtsname.setText(sname);
//        txtuaddress.setText(uaddress);
//        txtsaddress.setText(saddress);
//        txtproductname.setText(productname);
//        txtprice.setText(price);

//        placemandoib = new MarkerOptions().
//                position(new LatLng(Double.valueOf(PreferenceHelper.getCURRENTLAT()),
//                        Double.valueOf(PreferenceHelper.getCURRENTLONG()))).
//                title(" موقعك ");
//
       placeuser = new MarkerOptions().position(new LatLng(Double.valueOf(data.getData().get(0).getUserLat()),Double.valueOf(data.getData().get(0).getUserLong()))).title(uname);
        for (int i=0;i<data.getData().get(0).getOrderdetails().size();i++) {
            placestor = new MarkerOptions().position(new LatLng(Double.valueOf(data.getData().get(0).getOrderdetails().get(0).getSmallstore().getLatitude()), Double.valueOf(data.getData().get(0).getOrderdetails().get(0).getSmallstore().getLongitude()))).title(data.getData().get(0).getOrderdetails().get(0).getSmallstore().getName());
        }
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

    private  void findFromXml(View view)
    {
//        txtuname = view.findViewById(R.id.username);
//        txtsname = view.findViewById(R.id.store_name);
//       // txtuaddress = view.findViewById(R.id.userplace);
//        txtsaddress = view.findViewById(R.id.store_place);
//        txtproductname = view.findViewById(R.id.product_name);
//        txtprice = view.findViewById(R.id.productPrice);
        slideTitle = view.findViewById(R.id.title);
//        accept = view.findViewById(R.id.acceptorder);
//        refuse = view.findViewById(R.id.refuseorder);
//        accept.setOnClickListener(this);
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
                if (new_status==BottomSheetBehavior.STATE_EXPANDED) {
                    slideTitle.setText(R.string.lessdetails);
                    nestedScrollView.fullScroll(ScrollView.FOCUS_UP);
                }
                else {
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
        placestor = new MarkerOptions().position(new LatLng(29.9603988, 31.270689)).title("موقع الاستلام ");
        placeuser = new MarkerOptions().position(new LatLng(30.0326172, 31.3032935)).title("موقع التسليم  ");

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

        }
        catch (Exception e)
        {
          //  Log.d(TAG,e.getMessage());
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
        switch (v.getId())
        {
            case R.id.acceptorder:
                openDialogForAddStorage();
                break;
        }
    }

    private void openDialogForAddStorage(){

        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.alert_add_price_offer, null);
        dialogBuilder.setView(dialogView);
        final EditText storagetxt =  dialogView.findViewById(R.id.dialogEditText);
        TextView save;
        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        alertDialog.show();
        save =dialogView.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (storagetxt.getText().toString().matches(""))
                    storagetxt.setError(getResources().getString(R.string.complet));
                else {
                    newOrderViewModel.addOffer(orderId,userid,deliveryId,storagetxt.getText().toString());
                    progress.setVisibility(View.VISIBLE);
                    alertDialog.dismiss();
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new MyOrderFragment()).addToBackStack(null).
                            setCustomAnimations(R.anim.animation_new_order,R.anim.animation_new_order2).commit();
                }
            }
        });
    }

}
