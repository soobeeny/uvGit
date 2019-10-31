package com.example.beeny.uvchecker2;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class TestViewPager extends AppCompatActivity {
    ViewPager viewPager ;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_view_pager);

        viewPager = (ViewPager)findViewById(R.id.main2ViewPager);
        tabLayout = (TabLayout)findViewById(R.id.main2TabLayout);

        int[] homeIcons = new int[]{R.drawable.ic_home,R.drawable.ic_compare,R.drawable.ic_photo,R.drawable.ic_data,R.drawable.ic_map};
        for(int layout = 0; layout<homeIcons.length; layout++){
            //tabLayout.addTab(tabLayout.newTab());
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundColor(Color.TRANSPARENT);
            imageView.setImageResource(homeIcons[layout]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);//?
            imageView.setPadding(10,10,10,10);
            //tabLayout.getTabAt(layout).setCustomView(imageView);
            tabLayout.addTab(tabLayout.newTab().setCustomView(imageView));
        }

        viewPager.setAdapter(new myAdapter(getSupportFragmentManager(),tabLayout.getTabCount()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    class myAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fragments = new ArrayList<Fragment>();
        private int tabCount;

        public myAdapter(FragmentManager fm,int tabCount) {
            super(fm);
            this.tabCount = tabCount;
            /*
            fragments.add(new Home());
            fragments.add(new SkinCompare());
            fragments.add(new photoRegist());
            fragments.add(new UVDataLog());
            fragments.add(new UVMap());
        */
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    Home home = new Home();
                    return home;
                case 1 :
                    SkinCompare skinCompare = new SkinCompare();
                    return skinCompare;
                case 2:
                    photoRegist photoRegist1 = new photoRegist();
                    return photoRegist1;
                case 3:
                    UVDataLog uvDataLog = new UVDataLog();
                    return uvDataLog;
                case 4:
                    UVMap uvMap = new UVMap();
                    return uvMap;

                default:
                    return null;
            }

            //return  fragments.get(position);
        }

        @Override
        public int getCount() {
            //return 0;
            return tabCount;
        }
    }
}
