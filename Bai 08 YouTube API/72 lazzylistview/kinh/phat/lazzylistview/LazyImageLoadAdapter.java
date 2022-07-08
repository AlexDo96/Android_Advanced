package kinh.phat.lazzylistview;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
//Adapter class extends with BaseAdapter and implements with OnClickListener 
public class LazyImageLoadAdapter extends BaseAdapter implements OnClickListener{
     
    //private Activity activity;
	Context context;
    private String[] data;
    private static LayoutInflater inflater=null;
    public ImageLoader imageLoader; 
    ArrayList<HashMap<String, String>> menuitems;
    
    public LazyImageLoadAdapter(Context context, ArrayList<HashMap<String, String>> menuitems) {
        //activity = a;
    	this.context=context;
        this.menuitems=menuitems;
        inflater = (LayoutInflater)this.context.
                            getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         
        // Create ImageLoader object to download and show image in list
        // Call ImageLoader constructor to initialize FileCache
        imageLoader = new ImageLoader(this.context.getApplicationContext());
    }
 
    public int getCount() {
        return menuitems.size();
    }
 
    public Object getItem(int position) {
        return position;
    }
 
    public long getItemId(int position) {
        return position;
    }
     
    /********* Create a holder Class to contain inflated xml file elements *********/
    public static class ViewHolder{
          
        public TextView text;
        public TextView text1;
        public TextView textWide;
        public ImageView image;
  
    }
     
    public View getView(int position, View convertView, ViewGroup parent) {
         
        View vi=convertView;
        ViewHolder holder;
          
        if(convertView==null){
              
            /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
            vi = inflater.inflate(R.layout.list_row, null);
              
            /****** View Holder Object to contain tabitem.xml file elements ******/
 
            holder = new ViewHolder();
            holder.text = (TextView) vi.findViewById(R.id.title);
            holder.text1=(TextView)vi.findViewById(R.id.tv_tieude1);
            holder.image=(ImageView)vi.findViewById(R.id.list_image);
              
           /************  Set holder with LayoutInflater ************/
            vi.setTag( holder );
        }
        else
            holder=(ViewHolder)vi.getTag();
         
         
        holder.text.setText((position+1) + ". "+menuitems.get(position).get("title").toString());
        ImageView image = holder.image;
        imageLoader.DisplayImage(menuitems.get(position).get("thumbnailUrl").toString(), image);     
        int tg=Integer.parseInt(menuitems.get(position).get("length").toString());		
		holder.text1.setText(getDurationString(tg));
        
        /******** Set Item Click Listner for LayoutInflater for each row ***********/

//////////khong bat cho nay ma mat su kien o videoparser roi, neu khong 
/////////thi bat o day cung duoc
		//vi.setOnClickListener(new OnItemClickListener(position));
        return vi;
    }
 
    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
         
    }
     
     
    /********* Called when Item click in ListView ************/
    private class OnItemClickListener  implements OnClickListener{           
        private int mPosition;
         
       OnItemClickListener(int position){
             mPosition = position;
        }
         
        @Override
        public void onClick(View arg0) {
 //           MainActivity sct = (MainActivity)activity;
 //           sct.onItemClick(mPosition);
        }               
    } 
    
	private String getDurationString(int seconds) {

	    int hours = seconds / 3600;
	    int minutes = (seconds % 3600) / 60;
	    seconds = seconds % 60;

	    return twoDigitString(hours) + ":" + twoDigitString(minutes) + ":" + twoDigitString(seconds);
	}

	private String twoDigitString(int number) {

	    if (number == 0) {
	        return "00";
	    }

	    if (number / 10 == 0) {
	        return "0" + number;
	    }

	    return String.valueOf(number);
	}
}
