package com.hossam.codesroots.presentation.chatAndMapActivity.presentation.map;

import android.location.Location;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.nkzawa.emitter.Emitter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.hossam.codesroots.helper.LatLngInterpolator;
import com.hossam.codesroots.helper.MarkerAnimation;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;

import static com.hossam.codesroots.helper.MyService.mSocket;


public class MapingFragment extends Fragment implements OnMapReadyCallback {

    //private List<Overlay> mapOverlays;

    private Projection projection;
    private Marker currentLocationMarker;
    private Location currentLocation;
    private boolean firstTimeFlag = true;
    private MapView mapView;
    private GoogleMap mMap;
    String roomId;
    public MapingFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.livemapwithchat, container, false);

        roomId = getArguments().getString("roomId");
        mSocket.on("change_position", changePosition);
        PreferenceHelper.setRoomId(roomId);

        return view;
    }


    private Emitter.Listener changePosition = new Emitter.Listener() {
        @Override
        public void call(Object... args) {

            if (getActivity() != null) {
                getActivity().runOnUiThread(() -> {
                    Log.d("sads", args[0].toString());
                    Log.d("sads1", args[1].toString());
                    String location = args[1].toString();
                    String dd = location.substring(1, location.length() - 1);
                    String[] locations = dd.split(",");
                    String lat = locations[0];
                    String lang = locations[1];

                    if (currentLocation == null)
                        currentLocation = new Location(location);
                    currentLocation.setLatitude(Float.valueOf(lat));
                    currentLocation.setLongitude(Float.valueOf(lang));
                    if (firstTimeFlag && mMap != null) {
                        animateCamera(currentLocation);
                        firstTimeFlag = false;
                    }
                    if (currentLocation != null)
                        showMarker(currentLocation);
                });

            }
        }
    };

    private void animateCamera(@NonNull Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(getCameraPositionWithBearing(latLng)));
    }

    private void showMarker(@NonNull Location currentLocation) {
        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        if (currentLocationMarker == null)
            currentLocationMarker = mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.defaultMarker()).position(latLng).
                    title(currentLocation.getLatitude() + "," + currentLocation.getLongitude() + ""));
        else
            MarkerAnimation.animateMarkerToGB(currentLocationMarker, latLng, new LatLngInterpolator.Spherical());
    }

    @NonNull
    private CameraPosition getCameraPositionWithBearing(LatLng latLng) {
        return new CameraPosition.Builder().target(latLng).zoom(12).build();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mapView =  view.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        mapView.onResume();
        mapView.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.mMap = googleMap;
        //mMap.addPolyline()
    }


}
