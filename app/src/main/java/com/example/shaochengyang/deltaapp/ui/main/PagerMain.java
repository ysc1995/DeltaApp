package com.example.shaochengyang.deltaapp.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.shaochengyang.deltaapp.ui.bookflight.BookFlightFragment;
import com.example.shaochengyang.deltaapp.ui.findtrip.FindTripFragment;

public class PagerMain extends FragmentStatePagerAdapter {

    int tabCount;

    public PagerMain(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    //get the fragment based on position
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs

        switch (position) {
            case 0:
                FindTripFragment tab1 = new FindTripFragment();
                return tab1;

            case 1:
                FindTripFragment tab2 = new FindTripFragment();
                return tab2;
            case 2:
                BookFlightFragment tab3 = new BookFlightFragment();
                //FindTripFragment tab3 = new FindTripFragment();
                return tab3;
            case 3:
                BookFlightFragment tab4 = new BookFlightFragment();
                return tab4;
            case 4:
                BookFlightFragment tab5 = new BookFlightFragment();
                return tab5;
            default:
                return null;
        }
    }


    @Override
    public int getCount() {
        return tabCount;
    }
}
