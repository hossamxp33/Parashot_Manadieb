package com.hossam.codesroots.presentation.chatAndMapActivity;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.hossam.codesroots.delivery24.R;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.chat.ChatingFragment;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.map.MapingFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ChatAndMapActivity extends AppCompatActivity {
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;
    TextView title;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatwithmap);
        title = findViewById(R.id.chat_title);
        title.setText("الدردشة");
        Bundle bundle = new Bundle();
        bundle.putInt("orderid", getIntent().getIntExtra("orderid", 0));
        bundle.putString("roomId", getIntent().getStringExtra("roomId"));
        bundle.putString("notes", getIntent().getStringExtra("notes"));
        Fragment fragment = new ChatingFragment();
        Fragment fragment1 = new MapingFragment();
        fragment.setArguments(bundle);
        fragment1.setArguments(bundle);

        if (getIntent().getStringExtra("chatormap").matches("chat")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment).addToBackStack(null).commit();
            title.setText(getResources().getString(R.string.chating));
        }
        else  if (getIntent().getStringExtra("chatormap").matches("map")) {
            getSupportFragmentManager().beginTransaction().replace(R.id.main_frame,fragment1).addToBackStack(null).commit();
            title.setText(getResources().getString(R.string.orderdelivery));
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new ChatingFragment();
                case 1:
                    return new MapingFragment();
            }
            return null;
        }


        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.chating);
                case 1:
                    return getString(R.string.maping);
            }
            return null;
        }
    }
}
