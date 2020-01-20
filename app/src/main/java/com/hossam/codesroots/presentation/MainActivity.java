package com.hossam.codesroots.presentation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.hossam.codesroots.entities.MYOrdersModel;
import com.hossam.codesroots.entities.MyOrderData;
import com.hossam.codesroots.helper.GpsTracker;
import com.hossam.codesroots.helper.MyService;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.loginfragment.LoginFragment;
import com.hossam.codesroots.presentation.mainFragment.MainFragment;
import com.hossam.codesroots.presentation.myAccount.MyAccountFragment;
import com.hossam.codesroots.presentation.myOrder.MyOrderFragment;
import com.hossam.codesroots.presentation.newOrderFragment.NewOrderFragment;
import com.hossam.codesroots.presentation.notifications.NotificationsFragment;
import com.onesignal.OSNotification;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import org.json.JSONObject;
import java.util.ArrayList;
import static com.hossam.codesroots.helper.MyService.mSocket;
import static com.onesignal.OneSignal.*;


public class MainActivity extends AppCompatActivity {
    private static final long UPDATE_INTERVAL = 20000;
    private static final long FASTEST_INTERVAL = 30000;
    private static final int PLAY_SERVICE_RES_REQUEST = 7001;
    private static final float DISPLACEMENT = (float) 0.1;
    private static final int MY_PERMISSION_REQUEST_CODE = 7000;
    GpsTracker gpsTracker;
    AHBottomNavigation bottomNavigation;
    LocationRequest mLocationRequest;
    LocationCallback locationCallback;
    private GoogleApiClient mGoogIeApiCIient;
    private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 5445;
    private FusedLocationProviderClient fusedLocationProviderClient;
    TextView online_statues;


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        setContentView(R.layout.activity_main);
        callPermission();
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).commit();
        PreferenceHelper preferenceHelper = new PreferenceHelper(MainActivity.this);
        notification();
        setupNavigation();
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            // Do something cool here...
            if (position == 3) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MyOrderFragment()).addToBackStack(null).commit();
                bottomNavigation.setNotification("", 3);
            } else if (position == 4) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MyAccountFragment()).addToBackStack(null).commit();
            } else if (position == 2) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MainFragment()).addToBackStack(null).commit();
            }
            else if (position == 1) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new NotificationsFragment()).addToBackStack(null).commit();
            }
            else if (position == 0) {
                getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new LoginFragment()).addToBackStack(null).commit();
            }
            return true;
        });


        gpsTracker = new GpsTracker(MainActivity.this);
        if (gpsTracker.canGetLocation()) {
            double latitude = gpsTracker.getLatitude();
            double longitude = gpsTracker.getLongitude();
            PreferenceHelper.setCURRENTLAT(String.valueOf(latitude));
            PreferenceHelper.setCURRENTLONG(String.valueOf(longitude));
        }

        locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                super.onLocationResult(locationResult);
                Log.d("locations", locationResult.getLocations().toString());
                if (PreferenceHelper.getRoomId() != null) {
                    ArrayList<String> position = new ArrayList<>();
                    position.add(String.valueOf(locationResult.getLocations().get(0).getLatitude()));
                    position.add(String.valueOf(locationResult.getLocations().get(0).getLongitude()));
                    String room = PreferenceHelper.getRoomId();
                    mSocket.emit("change_position", PreferenceHelper.getRoomId(), position, PreferenceHelper.getUserId());
                    mSocket.on("change_position", args -> Log.d("sads", args[0].toString()));
                }
            }
        };

        //////// when new order come
        int new_order = getIntent().getIntExtra("new_order", 0);
        if (new_order == 1) {
            gotoNewOrderFragment(getIntent());
        }
    }

    private void addPadgetoNavigation(String text, int position) {
        AHNotification notification = new AHNotification.Builder()
                .setText(text)
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.green))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        bottomNavigation.setNotification(notification, position);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //////// when new order come
        int new_order = intent.getIntExtra("new_order", 0);
        if (new_order == 1) {
            gotoNewOrderFragment(intent);
        }

        String action = intent.getStringExtra("action");
        if (action!=null) {
            if (action.matches("connect")) {
                online_statues.setBackgroundResource(R.drawable.circle_back_for_online);
                Log.d("action", "connect");
            } else if (action.matches("disconnect")) {
                Log.d("action", "disconnect");
                online_statues.setBackgroundResource(R.drawable.circle_back_for_offline);
            }
        }
    }

    private void callPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 11);
    }


    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this, MyService.class));
        if (isGooglePlayServicesAvailable()) {
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
            startCurrentLocationUpdates();
        }
    }

    private void startCurrentLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(FASTEST_INTERVAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
                return;
            }
        }

        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.myLooper());
    }

    private boolean isGooglePlayServicesAvailable() {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status)
            return true;
        else {
            if (googleApiAvailability.isUserResolvableError(status))
                Toast.makeText(this, "Please Install google play services to use this application", Toast.LENGTH_LONG).show();
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                Toast.makeText(this, "Permission denied by uses", Toast.LENGTH_SHORT).show();
            else if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                startCurrentLocationUpdates();
        }
    }

    //////////////////Notification
    private void notification() {
        startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .setNotificationReceivedHandler(new ExampleNotificationrecieved ())
                .init();
        enableSound(true);
        sendTag("id","237");
       // sendTag("id",String.valueOf(PreferenceHelper.getUserId()));
        OneSignal.setInFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification);
        //  OneSignal.sendTag("user_group_id", String.valueOf(PreferenceHelper.getUSER_GROUP_ID()));
    }


    private class ExampleNotificationOpenedHandler implements NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            Log.d("result", result.toString());
            JSONObject additionalData = result.notification.payload.additionalData;
        }
    }


    private class ExampleNotificationrecieved implements NotificationReceivedHandler {
        @Override
        public void notificationReceived(OSNotification notification) {

          notification.shown=false;
         JSONObject jsonObject =  notification.toJSONObject();
            Log.d("asdf",notification.toString());
            notification.isAppInFocus=false;
            notification.shown = false;
            return;
        }
    }


    private void setupNavigation() {
        bottomNavigation = findViewById(R.id.bottom_navigation);
        online_statues = findViewById(R.id.online_statues);
        // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.register, R.drawable.more, R.color.gray);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.notificatin, R.drawable.notificatio, R.color.gray);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.myOrder, R.drawable.noun_order, R.color.gray);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.gray);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.myAccount, R.drawable.noun_userr, R.color.gray);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);
        bottomNavigation.setCurrentItem(2);

        bottomNavigation.setAccentColor(Color.parseColor("#ef1919"));
        bottomNavigation.setInactiveColor(Color.parseColor("#ff808080"));
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);
        bottomNavigation.setForceTint(true);

    }

    private void gotoNewOrderFragment(Intent intent) {
        MYOrdersModel data = intent.getParcelableExtra("data");

        Fragment fragment = new NewOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("data",data);

        fragment.setArguments(bundle);

        
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, fragment).addToBackStack(null).
                setCustomAnimations(R.anim.animation_new_order, R.anim.animation_new_order2).commit();
    }

}
