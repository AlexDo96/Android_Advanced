package luffy.khoa.anh.layouttonghop;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by kerry on 20/06/15.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    String data[];

    public RecyclerViewAdapter(String data[]){
        this.data = data;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_one_item, parent, false);

        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, final int position) {
        holder.text_view.setText(data[position]);

        holder.text_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("abc",position+"");
            }

        });

        holder.text_view.setTag(position);
        holder.text_view.setOnClickListener(click);

    }

    View.OnClickListener click=new View.OnClickListener(){

        @Override
        public void onClick(View v) {

            Log.d("abc",(Integer)v.getTag()+"");
        }
    };

    @Override
    public int getItemCount() {
        return data.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView text_view;
        public ViewHolder(View v) {
            super(v);
            text_view = (TextView) v.findViewById(R.id.planet_name);
        }

    }

}
