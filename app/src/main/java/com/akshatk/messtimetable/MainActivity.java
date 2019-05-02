package com.akshatk.messtimetable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    Calendar calendar= Calendar.getInstance();
    FragmentPagerAdapter mainPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setMealArray();

        ViewPager vpPagerMain = (ViewPager) findViewById(R.id.vpPagerMain);
        mainPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        vpPagerMain.setAdapter(mainPagerAdapter);

    }

    public static class MainPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MainPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages.
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for a particular page.
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Homepagefragment.newInstance();
                case 1:
                    return Selectmealfragment.newInstance();
                case 2:
                    return Aboutfragment.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Home";
                case 1:
                    return "Select";
                case 2:
                    return "About";
                default:
                    return null;
            }
        }

    }


}



















