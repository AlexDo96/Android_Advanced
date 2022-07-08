package luffy.khoa.anh.layouttonghop;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class Loai4Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView days_list;
    CollapsingToolbarLayout collapsing_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai4);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        days_list = (RecyclerView) findViewById(R.id.days_list);
        collapsing_container=(CollapsingToolbarLayout)findViewById(R.id.collapsing_container);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsing_container.setTitle("loai 4");


        days_list.setLayoutManager(new LinearLayoutManager(this));
        days_list.setAdapter(new RecyclerViewAdapter(
                getResources().getStringArray(R.array.days_names)
        ));
    }
}
