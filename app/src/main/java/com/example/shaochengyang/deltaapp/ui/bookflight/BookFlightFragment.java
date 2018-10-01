package com.example.shaochengyang.deltaapp.ui.bookflight;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shaochengyang.deltaapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BookFlightFragment extends Fragment {


    @BindView(R.id.pager_bookflight)
    ViewPager pagerBookflight;
    Unbinder unbinder;
    @BindView(R.id.menu_bf_top)
    TabLayout menuBfTop;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_book_flight, container, false);
        unbinder = ButterKnife.bind(this, view);

        PagerBookFlight pagerBookFlight = new PagerBookFlight(getFragmentManager(), menuBfTop.getTabCount());

        pagerBookflight.setAdapter(pagerBookFlight);

        menuBfTop.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerBookflight.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pagerBookflight.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                menuBfTop.setScrollPosition(position,0,true);
                menuBfTop.setSelected(true);
                //subtab_layout.setVisibility(View.GONE);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
