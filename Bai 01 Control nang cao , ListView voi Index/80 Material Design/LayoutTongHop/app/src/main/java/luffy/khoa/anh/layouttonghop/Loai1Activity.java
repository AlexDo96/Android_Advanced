package luffy.khoa.anh.layouttonghop;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class Loai1Activity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView days_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loai1);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        days_list = (RecyclerView) findViewById(R.id.days_list);
        setSupportActionBar(toolbar);
        toolbar.setTitle("loai 1");



        days_list.setLayoutManager(new LinearLayoutManager(this));
        days_list.setAdapter(new RecyclerViewAdapter(
                getResources().getStringArray(R.array.days_names)
        ));


        days_list.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), days_list, new ClickListener() {
            @Override
            public void onClick(View view, int position) {

                Toast.makeText(getApplicationContext(),position + " is selected!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }


    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Loai1Activity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Loai1Activity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
}
