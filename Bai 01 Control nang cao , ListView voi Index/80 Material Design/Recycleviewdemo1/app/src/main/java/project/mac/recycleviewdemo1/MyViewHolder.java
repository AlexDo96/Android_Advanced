package project.mac.recycleviewdemo1;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by mac on 9/26/15.
 */
public class MyViewHolder extends RecyclerView.ViewHolder{
    public ImageView iv;
    public TextView tv;
    public MyViewHolder(View view)
    {
        super(view);
        this.iv=(ImageView)view.findViewById(R.id.imageView);
        this.tv=(TextView)view.findViewById(R.id.textView);
    }
}
