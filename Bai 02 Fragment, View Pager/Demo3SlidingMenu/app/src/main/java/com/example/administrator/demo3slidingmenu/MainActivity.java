package com.example.administrator.demo3slidingmenu;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    String [] danhsach;
    DrawerLayout drawerlayout;
    ListView listview;
    ActionBarDrawerToggle drawertoggle;
    String drawertitle;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerlayout= (DrawerLayout)findViewById(R.id.drawer_layout);
        listview=(ListView)findViewById(R.id.drawer_list);
        danhsach=getResources().getStringArray(R.array.thanhpho);
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(this,R.layout.drawer_list_item,danhsach);
        listview.setAdapter(adapter);

        title=drawertitle=(String) getTitle();

        drawertoggle=new ActionBarDrawerToggle(
                this, //ap vao activity nao
                drawerlayout, //drawerlayout
                R.mipmap.ic_launcher,//icon
                R.string.drawer_open, //chuoi mo
                R.string.drawer_close /*chuoidong*/
        )
        {
            //goi khi drawer da hoan tat close
            @Override
            public void onDrawerClosed(View drawerView) {
// TODO Auto-generated method stub
                super.onDrawerClosed(drawerView);
     //           getActionBar().setTitle(title);
            }
            //goi khi drawer da hoan tat open
            @Override
            public void onDrawerOpened(View drawerView) {
// TODO Auto-generated method stub
                super.onDrawerOpened(drawerView);
//                getActionBar().setTitle("chon thanh pho");
            }
        };
//co lenh nay moi thuc hien duoc ham onDrawerClosed va onDrawerOpened
        drawerlayout.setDrawerListener(drawertoggle);

        //hien nut home len goc trai cua actionbar
      //  getActionBar().setHomeButtonEnabled(true);
//nhan nut home tra ve 1 level chu khong ve top level
      //  getActionBar().setDisplayHomeAsUpEnabled(true);



        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
// TODO Auto-generated method stub
                String [] dstp=getResources().getStringArray(R.array.thanhpho);
                title=dstp[position];
                CityFragment fragment=new CityFragment();
                Bundle data=new Bundle();
                data.putInt("vitri", position);
                fragment.setArguments(data);
                FragmentManager manager=getSupportFragmentManager();
                FragmentTransaction tran=manager.beginTransaction();
                tran.replace(R.id.content_frame, fragment);
                tran.commit();
                drawerlayout.closeDrawer(listview);
            }
        });
    }
}
