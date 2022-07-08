package luffy.khoa.anh.layouttonghop;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class Loai3Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView planets_list;
    RecyclerViewAdapter adapter;
    TabLayout tab_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai3);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        planets_list = (RecyclerView) findViewById(R.id.days_list);
        tab_layout = (TabLayout) findViewById(R.id.tabs);

        //gan toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Loai 3");

        //layout cho recyclerView
        RecyclerView.LayoutManager  layout_manager = new LinearLayoutManager(this);
        planets_list.setLayoutManager(layout_manager);

        //tao adapter
        adapter = new RecyclerViewAdapter(getResources().getStringArray(R.array.days_names));
        planets_list.setAdapter(adapter);

        //tao tab
        tab_layout.setTabMode(TabLayout.MODE_FIXED);

        //tab_layout.setTabMode(TabLayout.MODE_SCROLLABLE);

        tab_layout.addTab(tab_layout.newTab().setText("Tab 1"));
        tab_layout.addTab(tab_layout.newTab().setText("Tab 2"));
        tab_layout.addTab(tab_layout.newTab().setText("Tab 3"));
    }
}
