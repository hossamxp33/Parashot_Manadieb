package com.hossam.codesroots.presentation.mainFragment;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hossam.codesroots.helper.BroadcastHelper;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;

import java.util.Objects;

import static android.content.ContentValues.TAG;


public class MainFragment extends Fragment implements OnMapReadyCallback,LocationListener {

    MapView mapView;
    GoogleMap map;
    MyReceiver mReceiver;
    boolean isRegistered = false;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        try {
            MapsInitializer.initialize(Objects.requireNonNull(getActivity()));
            mapView = view.findViewById(R.id.mapView);
            mapView.onCreate(savedInstanceState);
            mapView.getMapAsync(this);

        } catch (InflateException e) {
            Log.e(TAG, "Inflate Map exception");
        }

        return view;
    }

    protected void createMarker(double latitude, double longitude, String title, String snippet, int iconResID) {

        map.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                .snippet(snippet)
                .icon(BitmapDescriptorFactory.fromResource(iconResID)));
    }


    @Override
    public void onResume() {
        mapView.onResume();
        super.onResume();
        if (mReceiver == null) {
            mReceiver = new MyReceiver();
            IntentFilter filter = new IntentFilter(BroadcastHelper.ACTION_NAME);
            Objects.requireNonNull(getActivity()).registerReceiver(mReceiver, filter);
            isRegistered = true;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
        if (mReceiver != null)
            if (isRegistered) {
                Objects.requireNonNull(getActivity()).unregisterReceiver(mReceiver);
            }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;
        //////////////my location
        if (PreferenceHelper.getCURRENTLAT()!=null) {
            LatLng sydney = new LatLng(Double.valueOf(PreferenceHelper.getCURRENTLAT()), Double.valueOf(PreferenceHelper.getCURRENTLONG()));
            map.addMarker(new MarkerOptions().position(sydney).title(PreferenceHelper.getUserName()).snippet(getString(R.string.yourLocation)));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(sydney).zoom(15).build();
            map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
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

    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            // do something
            Log.v("r", "receive " + intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME));
            String methodName = intent.getStringExtra(BroadcastHelper.BROADCAST_EXTRA_METHOD_NAME);
            if (methodName.matches("new_user"))
            {
                createMarker(Double.valueOf(intent.getStringExtra("lat")),Double.valueOf(intent.getStringExtra("long")),
                        intent.getStringExtra("name"),  getString(R.string.client),R.drawable.mark);
            }
        }
    }
}
