package com.example.administrator.demo4navigationdrawer;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

//set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);

        ActionBar ab=getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_gachgach);
        ab.setDisplayHomeAsUpEnabled(true);

        navigation=(NavigationView)findViewById(R.id.nvView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                xulychonmenu(menuItem);

                return false;
            }
        });


    }

    void xulychonmenu(MenuItem menuItem)
    {
        int id=menuItem.getItemId();
        Fragment fragment=null;
        Class classfragment=null;

        if(id==R.id.nav_first_fragment)
            classfragment=Fragment1.class;
        if(id==R.id.nav_second_fragment)
            classfragment=Fragment2.class;
        if(id==R.id.nav_third_fragment)
            classfragment=Fragment3.class;
//....tao cac fragment cho tung muc

        try {
            fragment=(Fragment)classfragment.newInstance();

            FragmentManager fmanager= getSupportFragmentManager();
            fmanager.beginTransaction()
                    .replace(R.id.flContent,fragment)
                    .commit();

            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());
            drawer.closeDrawer(GravityCompat.START);
        }catch(Exception e) {

        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
//no inspection Simplifiable If Statement
        /*if (id == R.id.action_settings) {
            return true;
        }*/
        if(id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

}
