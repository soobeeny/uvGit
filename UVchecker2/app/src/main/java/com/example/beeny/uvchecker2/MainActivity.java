package com.example.beeny.uvchecker2;

import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;
import java.security.cert.TrustAnchor;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{
    private TabLayout tabLayout;
    private TabLayout tabLayout2;
    ViewPager viewPager;
    ImageButton photobtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //linearLayout = (LinearLayout)findViewById(R.id.tabContainer);

        viewPager=(ViewPager) findViewById(R.id.pager_content);
        viewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        viewPager.setOffscreenPageLimit(1);//미리 로딩할 fragment 수
        viewPager.setCurrentItem(0);

        tabLayout = (TabLayout) findViewById(R.id.mainTablayout);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        //tabLayout.addOnTabSelectedListener(this);
        //ImageView imageView = new ImageView(this);
        //imageView.setBackgroundColor(Color.TRANSPARENT);

        //tabLayout.getTabAt(0).setCustomView(R.drawable.ic_home);
       /* tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_compare);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_photo);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_data);
        tabLayout.getTabAt(4).setIcon(R.drawable.ic_map);
*/

        int[] homeIcons = new int[]{R.drawable.tab_home,R.drawable.tab_compare,R.drawable.tab_photo,R.drawable.tab_data,R.drawable.tab_map};
        for(int i = 0 ; i<homeIcons.length; i++){
            ImageView imageView = new ImageView(this); //객체를 계속 생성해줘야하네,,?
            imageView.setBackgroundColor(Color.TRANSPARENT);
            imageView.setImageResource(homeIcons[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);//?
            imageView.setPadding(10,10,10,10);
            tabLayout.getTabAt(i).setCustomView(imageView);
        }
    }
    @Override
    public void onClick(View view) {

    }
    class MyPagerAdapter extends FragmentPagerAdapter {
    //유저가 페이지로 다시 돌아올 수 있는 한, Fragment Manager 에서 관리하는 Fragment 를 나타내주는PagerAdapter의 implementation 입니다.
        List<Fragment> fragments =new ArrayList<Fragment>();

        //create constructor matching super
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
            fragments.add(new Home()) ;
            fragments.add(new SkinCompare());
            fragments.add(new photoRegist());
            fragments.add(new UVDataLog());
            fragments.add(new UVMap());

        }
        @Override
        public Fragment getItem(int position) {
            //n번째 포지션의 Fragment Item 오브젝트 정보를 리턴한다. 채울 화면의 순서를 변경하고 싶다면 여기서 return 하는 Fragment 객체를 position 에 따라서 변경하면 된다.

            //Log.d("Log_T","position : "+position);
            return this.fragments.get(position);
        }

        @Override
        public int getCount() {
            //ViewPager에 넣을 화면의 개수를 리턴하는 함수
            return this.fragments.size();
        }
    }

}
