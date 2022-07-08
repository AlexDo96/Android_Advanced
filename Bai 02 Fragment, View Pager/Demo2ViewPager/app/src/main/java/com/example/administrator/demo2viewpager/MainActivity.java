package com.example.administrator.demo2viewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager vp;
    String [] title={"hinh 1","text","hinh 2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp=(ViewPager)findViewById(R.id.viewpager);

        MyAdapter adapter=new MyAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
    }


    class MyAdapter extends FragmentPagerAdapter
    {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }

        @Override
        public Fragment getItem(int position) {
            switch (position)
            {
                case 0:
                    return new Fragment_Image(R.mipmap.ic_launcher);
                case 1:
                    return new Fragment_Text();
                case 2:
                    return new Fragment_Image(R.drawable.a);

            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}
