package com.example.mac.tablayoutviewpagerdemo1;

import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ViewPager viewpager;
    TabLayout tablayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewpager=(ViewPager)findViewById(R.id.viewpager);
        tablayout=(TabLayout)findViewById(R.id.sliding_tabs);

        FragmentPagerAdapter adapter=new FragmentAdapter(
                getSupportFragmentManager(),
                MainActivity.this);
        viewpager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt("position", tablayout.getSelectedTabPosition());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        viewpager.setCurrentItem(savedInstanceState.getInt("position"));
    }
}
