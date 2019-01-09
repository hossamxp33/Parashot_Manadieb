package com.hossam.codesroots.presentation.chatAndMapActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import com.hossam.codesroots.parashot_manadieb.R;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.chat.ChatingFragment;
import com.hossam.codesroots.presentation.chatAndMapActivity.presentation.map.MapingFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class ChatAndMapActivity extends AppCompatActivity {
    ViewPager mViewPager;
    SectionsPagerAdapter mSectionsPagerAdapter;

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatwithmap);
        mViewPager =  findViewById(R.id.container);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mSectionsPagerAdapter);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
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
