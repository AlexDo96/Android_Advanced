package luffy.khoa.anh.layouttonghop;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import jp.wasabeef.recyclerview.animators.ScaleInAnimator;
import jp.wasabeef.recyclerview.animators.SlideInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;

public class Loai6Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView days_list;
    CollapsingToolbarLayout collapsing_container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai6);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        days_list = (RecyclerView) findViewById(R.id.days_list);
        collapsing_container=(CollapsingToolbarLayout)findViewById(R.id.collapsing_container);

        setSupportActionBar(toolbar);

        TextView text = new TextView(this);
        text.setText("loai 6");
        text.setTextAppearance(this, android.R.style.TextAppearance_Material_Widget_ActionBar_Title_Inverse);
         toolbar.addView(text);

       // days_list.setItemAnimator(new SlideInUpAnimator());
/*        GridLayoutManager layoutManager=new GridLayoutManager(this,
                2,
                GridLayoutManager.VERTICAL,
                false
        );
        layoutManager.setSpanSizeLookup(
                new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        return (position % 3 == 0 ? 2 : 1 );
                    }
                }
        );*/
/*        StaggeredGridLayoutManager layoutManager=new StaggeredGridLayoutManager(
                2,
                StaggeredGridLayoutManager.VERTICAL
        );
        days_list.setLayoutManager(layoutManager);

        //days_list.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        days_list.setAdapter(new RecyclerViewAdapter(
                getResources().getStringArray(R.array.days_names)
        ));*/

       // AdapterLoai6 adapter=new AdapterLoai6()
        AdapterLoai6 adapter = new AdapterLoai6(this, Data.getData());
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.HORIZONTAL);
        days_list.setLayoutManager(layoutManager);



        days_list.setAdapter(adapter);
    }


}
