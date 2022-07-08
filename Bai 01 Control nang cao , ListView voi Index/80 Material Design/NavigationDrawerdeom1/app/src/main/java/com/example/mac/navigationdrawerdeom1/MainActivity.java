package com.example.mac.navigationdrawerdeom1;

import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawer;
    Toolbar toolbar;
    NavigationView navigation;
    ActionBarDrawerToggle toggle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawer=(DrawerLayout)findViewById(R.id.drawer_layout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);

        //set toolbar thay the cho actionbar
        setSupportActionBar(toolbar);

        ActionBar ab=getSupportActionBar();
      //  ab.setHomeAsUpIndicator(R.drawable.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        navigation=(NavigationView)findViewById(R.id.nvView);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                xulychonmenu(menuItem);

                return false;
            }
        });
        toggle=new ActionBarDrawerToggle(MainActivity.this,drawer,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawer.setDrawerListener(toggle);
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        toggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
