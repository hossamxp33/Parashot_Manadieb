package com.hossam.codesroots.presentation;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.aurelhubert.ahbottomnavigation.notification.AHNotification;
import com.crashlytics.android.Crashlytics;
import com.hossam.codesroots.helper.GpsTracker;
import com.hossam.codesroots.helper.MyService;
import com.hossam.codesroots.helper.PreferenceHelper;
import com.hossam.codesroots.parashot_manadieb.R;
import com.hossam.codesroots.presentation.chatAndMapActivity.ChatAndMapActivity;
import com.hossam.codesroots.presentation.mainFragment.MainFragment;
import com.hossam.codesroots.presentation.myOrder.MyOrderFragment;
import com.hossam.codesroots.presentation.newOrderFragment.NewOrderFragment;
import com.onesignal.OSNotificationOpenResult;
import com.onesignal.OneSignal;
import io.fabric.sdk.android.Fabric;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    GpsTracker gpsTracker;
    AHBottomNavigation bottomNavigation;

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
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,new MainFragment()).commit();
        PreferenceHelper preferenceHelper = new PreferenceHelper(MainActivity.this);
        notification();
        setupNavigation();
        addPadgetoNavigation("2",3);
        startActivity(new Intent(MainActivity.this,ChatAndMapActivity.class));
        bottomNavigation.setOnTabSelectedListener((position, wasSelected) -> {
            // Do something cool here...
            if (position==3)
            {
               getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MyOrderFragment()).addToBackStack(null).commit();
                bottomNavigation.setNotification("",3);
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


        //////// when new order come
        int new_order = getIntent().getIntExtra("new_order",0);
        if (new_order==1)
        {
            gotoNewOrderFragment(getIntent());
        }
    }

    private void addPadgetoNavigation(String text, int position) {

        AHNotification notification = new AHNotification.Builder()
                .setText(text)
                .setBackgroundColor(ContextCompat.getColor(MainActivity.this, R.color.colorPrimary))
                .setTextColor(ContextCompat.getColor(MainActivity.this, R.color.white))
                .build();
        bottomNavigation.setNotification(notification, position);
    }

    private void setupNavigation() {
         bottomNavigation =  findViewById(R.id.bottom_navigation);
       // Create items
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.gray);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.gray);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.gray);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.gray);
        AHBottomNavigationItem item5 = new AHBottomNavigationItem(R.string.tab_1, R.drawable.home, R.color.gray);

       // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);
        bottomNavigation.addItem(item5);
        bottomNavigation.setCurrentItem(2);


    }

    private void gotoNewOrderFragment(Intent intent) {
        intent.getStringExtra("user_lat");
        Fragment fragment = new NewOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putString("user_name",  intent.getStringExtra("user_name"));
        bundle.putString("user_lat",  intent.getStringExtra("user_lat"));
        bundle.putString("user_long",  intent.getStringExtra("user_long"));
        bundle.putString("user_address",  intent.getStringExtra("user_address"));
        bundle.putString("stor_lat",  intent.getStringExtra("stor_lat"));
        bundle.putString("stor_long",  intent.getStringExtra("stor_long"));
        bundle.putString("store_name",  intent.getStringExtra("store_name"));
        bundle.putString("price",  intent.getStringExtra("price"));
        bundle.putInt("id",  intent.getIntExtra("orderId",0));
        bundle.putInt("userid",  intent.getIntExtra("userid",0));
        fragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).
                setCustomAnimations(R.anim.animation_new_order,R.anim.animation_new_order2).commit();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        //////// when new order come
        int new_order = intent.getIntExtra("new_order",0);
        if (new_order==1)
        {
           // bottomNavigation.setNotification(notification, bottomNavigation.getItemsCount() - 1);
            gotoNewOrderFragment(intent);
        }
    }

    private void callPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},11);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(this,MyService.class));
    }


    //////////////////Notification
    private void notification() {
        OneSignal.startInit(this)
                .setNotificationOpenedHandler(new ExampleNotificationOpenedHandler())
                .init();
        OneSignal.enableSound(true);
        OneSignal.sendTag("id","1");
      //  OneSignal.sendTag("user_group_id", String.valueOf(PreferenceHelper.getUSER_GROUP_ID()));

    }

    private class ExampleNotificationOpenedHandler implements OneSignal.NotificationOpenedHandler {
        @Override
        public void notificationOpened(OSNotificationOpenResult result) {
            Log.d("result",result.toString());
            JSONObject additionalData =  result.notification.payload.additionalData;
        }
    }

}
