package com.example.shaochengyang.deltaapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.shaochengyang.deltaapp.R;
import com.example.shaochengyang.deltaapp.ui.login.LoginActivity;
import com.facebook.login.Login;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.tv_content_title)
    TextView tvContentTitle;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.pager_main)
    ViewPager pagerMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        PagerMain myPager = new PagerMain(getSupportFragmentManager(), tabLayout.getTabCount());

        pagerMain.setAdapter(myPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pagerMain.setCurrentItem(tab.getPosition());
                switch (tab.getPosition()){
                    case 0:
                        tvContentTitle.setText("Find My Trip");
                        break;
                    case 1:
                        tvContentTitle.setText("Fly Delta");
                        break;
                    case 2:
                        tvContentTitle.setText("Book A Flight");
                        break;
                    case 3:
                        tvContentTitle.setText("Fly Delta");
                        break;
                    case 4:
                        tvContentTitle.setText("Fly Delta");
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pagerMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                tabLayout.setScrollPosition(position,0,true);
                tabLayout.setSelected(true);
                //subtab_layout.setVisibility(View.GONE);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });

    }
}
