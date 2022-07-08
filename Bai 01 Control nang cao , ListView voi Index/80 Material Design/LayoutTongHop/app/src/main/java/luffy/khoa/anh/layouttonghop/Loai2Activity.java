package luffy.khoa.anh.layouttonghop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

public class Loai2Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView days_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        days_list = (RecyclerView) findViewById(R.id.days_list);
        setSupportActionBar(toolbar);
        toolbar.setTitle("loai 2");



        days_list.setLayoutManager(new LinearLayoutManager(this));
        days_list.setAdapter(new RecyclerViewAdapter(
                getResources().getStringArray(R.array.days_names)
        ));
    }
}
