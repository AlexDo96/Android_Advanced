package project.mac.recycleviewdemo2;

import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mac on 9/26/15.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder>{

    ArrayList<Data> ds=new ArrayList<Data>();
    public MyAdapter(ArrayList<Data> ds)
    {
        this.ds=ds;
    }
    @Override
    public MyHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inf= LayoutInflater.from(viewGroup.getContext());
        View view=inf.inflate(R.layout.one_item,viewGroup,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(MyHolder myHolder, int i) {
        myHolder.tv.setText(ds.get(i).ten);
    }

    public void addItem(int position , Data data)
    {
        ds.add(position,data);
        notifyItemInserted(position);
    }

    public void removeItem(int position)
    {
        ds.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return ds.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder{
        TextView tv;
        Button bt;
        public MyHolder(View itemView) {
            super(itemView);
            tv=(TextView)itemView.findViewById(R.id.textView);
            bt=(Button)itemView.findViewById(R.id.button2);

            bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    removeItem(getAdapterPosition());
                }
            });
        }
    }
}
