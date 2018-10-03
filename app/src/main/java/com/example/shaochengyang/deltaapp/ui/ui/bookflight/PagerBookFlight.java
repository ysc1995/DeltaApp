package com.example.shaochengyang.deltaapp.ui.ui.bookflight;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shaochengyang.deltaapp.ui.ui.bookflight.multicity.MultiCityFragment;
import com.example.shaochengyang.deltaapp.ui.ui.bookflight.oneway.OneWayFragment;
import com.example.shaochengyang.deltaapp.ui.ui.bookflight.roundtrip.RoundTripFragment;

public class PagerBookFlight extends FragmentStatePagerAdapter {

    int tabCount;

    public PagerBookFlight(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                RoundTripFragment tab1 = new RoundTripFragment();
                return tab1;

            case 1:
                OneWayFragment tab2 = new OneWayFragment();
                return tab2;
            case 2:
                MultiCityFragment tab3 = new MultiCityFragment();
                //FindTripFragment tab3 = new FindTripFragment();
                return tab3;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return this.tabCount;
    }
}
