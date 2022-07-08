package project.mac.recycleviewdemo1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by mac on 9/26/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
    ArrayList<Logo> ds;
    Context context;
    public MyAdapter(Context context, ArrayList<Logo> ds)
    {
        this.ds=ds;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.viewholder_layout,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Logo logo=ds.get(position);
        holder.iv.setImageResource(logo.hinh);
        holder.tv.setText(logo.name);
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }
}
