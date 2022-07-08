package Adapter;

import java.util.ArrayList;
import com.example.chat_it.R;
import Data.DataMessage;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class ChatAdapter extends ArrayAdapter<DataMessage> {
		ArrayList<DataMessage> array;
		int resource;
		Context context;
		LayoutInflater vi;

	public ChatAdapter(Context context, int textViewResourceId,ArrayList<DataMessage> objects) {
		super(context, textViewResourceId, objects);
		this.context=context;
		this.resource=textViewResourceId;
		this.array=objects;
		vi=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v =convertView;
		v=vi.inflate(resource,null);
		final DataMessage message=array.get(position);		
		if(message!=null)
		{			
			TextView txtMessage=(TextView) v.findViewById(R.id.txtMessage)	;	
			//Kiem tra tin nhan gui
			if(message.receive==false){
				//Set background cho Textview txtMessage
				txtMessage.setBackgroundResource(R.drawable.chat_1);		
				//Thiet lap hien thi tu ben trai
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
				//Thiet lap noi dung cho TextView txtMessage
				txtMessage.setText(message.message);
				txtMessage.setLayoutParams(params);
				
			}
			// kiem tra tin nhan nhan
			else{
				//Set background cho Textview txtMessage
				txtMessage.setBackgroundResource(R.drawable.chat_2);
				//Thiet lap hien thi tu ben phai
				RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
				params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
				//Thiet lap noi dung cho TextView txtMessage
				txtMessage.setText(message.message);
				txtMessage.setLayoutParams(params);
			}
		}
		return v;
	}

}
