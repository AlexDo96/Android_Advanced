package com.example.downloadfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ArrayList<FileDownload> ds_file;
	ProgressDialog pg_dialog;
	ListView lv_file;

	ProgressDialog dl_pdialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		lv_file=(ListView)findViewById(R.id.listView1);
		ds_file=new ArrayList<FileDownload>();
		
		//tao dialog cho phan download file
		dl_pdialog= new ProgressDialog(MainActivity.this);
		dl_pdialog.setMessage("dang download");
		dl_pdialog.setIndeterminate(false);
		dl_pdialog.setMax(100);
		dl_pdialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
		
		//neu co internet thi ket noi va tao lai list va dua vao sqlite
		//neu khong co internet thi lay truc tiep tu sqlite
		if(checkNetworkStatus()==true)//co internet
		{
			Toast.makeText(getApplicationContext(),"co net", Toast.LENGTH_LONG).show();
			new xulygetallfile().execute();
		}
		else if(checkNetworkStatus()==false)//khong co internet
		{
			Toast.makeText(getApplicationContext(),"khong net", Toast.LENGTH_LONG).show();
			doctuSQLite(MainActivity.this);
			lv_file.setAdapter(new myadapter(MainActivity.this));
		}
		
		//xu kien khi click chuot vao mot item tren listview
		lv_file.setOnItemClickListener(new OnItemClickListener() {
		
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				//lay textview id(textview nay an)
				//day chinh la id cua MySQL
				TextView tv_idchon=(TextView)arg1.findViewById(R.id.fileid);
				String id=tv_idchon.getText().toString();
				Toast.makeText(getApplicationContext(),"id=" +id , Toast.LENGTH_SHORT).show();				
			
				String dd_duocchon="";
				for(int i=0;i<ds_file.size();i++)
				{
					if(ds_file.get(i).id==id)
					{
						dd_duocchon=ds_file.get(i).duongdan;
						break;
					}
				}
				//cach 1. down binh thuong khong co progressdialog va asyntask
				//ghiFile(dd_duocchon);
				
				ghiFilePD ghifile=new ghiFilePD();
				ghifile.execute(new String[]{dd_duocchon});
				
				
				Toast.makeText(getApplicationContext(),dd_duocchon, Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	
	
	
	//ham download va ghi file theo cach 1 
	public void ghiFile(String dd)
	{
		//cat ra chi lay ten file pdf khong lay http://...
		String tenfile=dd.substring(dd.lastIndexOf("/")+1,dd.length());
		tenfile=tenfile.replace(" ","%20");
		//lay duong dan external Storage
		String extStorageDirectory = 
				Environment.getExternalStorageDirectory().toString();
		File folder = new File(extStorageDirectory, "thumucchuapdf");
		folder.mkdir();//tao thu muc chua
		
		File file = new File(folder,tenfile);
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        Downloader.DownloadFile(dd, file);
        Log.d("duongdan",folder.toString());
        showPdf(tenfile);
        
	}
	
	class ghiFilePD extends AsyncTask<String,Integer,String>{
	
		@Override
		protected String doInBackground(String... arg0) {
			// TODO Auto-generated method stub
			String dd=arg0[0];//doi so dau la duong dan
			//cat ra chi lay ten file pdf khong lay http://...
			String tenfile=dd.substring(dd.lastIndexOf("/")+1,dd.length());
			tenfile=tenfile.replace(" ","%20");
			
			//lay duong dan external Storage
			String extStorageDirectory = 
					Environment.getExternalStorageDirectory().toString();
			File folder = new File(extStorageDirectory, "thumucchuapdf");
			folder.mkdir();//tao thu muc chua
			
			File file = new File(folder,tenfile);
	        try {
	            file.createNewFile();
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	        
	        //////////////ket noi va ghi vao file da tao//////////
	        try {
	        	//neu duong dan co khoang trang thi thay bang %20
	            dd=dd.replace(" ", "%20");
	            FileOutputStream f = new FileOutputStream(file);
	            URL u = new URL(dd);
	            HttpURLConnection c = (HttpURLConnection) u.openConnection();
	            c.setRequestMethod("GET");
	            c.setDoOutput(true);
	            c.connect();
	            int dungluong=c.getContentLength();//lay kich thuoc file
	            InputStream in = c.getInputStream();
	            byte[] buffer = new byte[1024];
	            int da_down=0;//size da down, dua ra progressdialog
	            int len1 = 0;
	            while ((len1 = in.read(buffer)) > 0) {
	            	da_down+=len1;
	                f.write(buffer, 0, len1);
	                publishProgress((int)(da_down*100/dungluong));
	            }
	            f.close();
	        } catch (Exception e) {
	            e.printStackTrace();
	            Log.d("dulieu","luu khong duoc"+ e.toString());
	        }
	        //tra tenfile de goi doc file PDF
	        return tenfile;
		}
	
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			dl_pdialog.show();
		}
	
		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			dl_pdialog.setProgress(values[0]);
		}
	
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			dl_pdialog.dismiss();
			showPdf(result);
		}
		
	}

	//dua ten file se goi activity de xem pdf
	public void showPdf(String tenfile)
	{
	    File file = new File(Environment.getExternalStorageDirectory()+"/thumucchuapdf/"+tenfile);
	    Log.d("dulieu","..." + file.toString());
	
	    Intent intent = new Intent();
	    intent.setAction(Intent.ACTION_VIEW);
	    Uri uri = Uri.fromFile(file);
	    intent.setDataAndType(uri, "application/pdf");
	    startActivity(intent);     
	}
	
	//doc du lieu tu SQLite va ghi vao ds_file
	public void doctuSQLite(Context context)
	{
		QuanLyFileDownload ql=new QuanLyFileDownload(context);
		Cursor c =ql.getall();
		c.moveToFirst();	
		do
		{
			//id cua SQLite khong lay duoc, ma cung khong can
			String id=c.getString(0);//id cua MySQL
			Log.d("dulieu","id"+id);
			String ten=c.getString(1);
			String duongdan=c.getString(2); 
			String mota=c.getString(3);
			FileDownload f=new FileDownload(id, ten, duongdan, mota);
			ds_file.add(f);	
		}while(c.moveToNext());
	}
	
	//ghi du lieu tu ds_file vao SQLite
	public void ghivaoSQLite(Context context)
	{
		QuanLyFileDownload ql=new QuanLyFileDownload(context);
		ql.taolai();
		for(int i=0;i<ds_file.size();i++)
		{
			String id=ds_file.get(i).id ;
			String ten=ds_file.get(i).ten;
			String duongdan=ds_file.get(i).duongdan;
			String mota=ds_file.get(i).mota;
			ql.taoDuLieu(id, ten, duongdan, mota);
		}	
	}
	
	///ham kiem tra co ket noi internet chua
	public boolean  checkNetworkStatus(){
	
	    final ConnectivityManager connMgr = (ConnectivityManager)
	     this.getSystemService(Context.CONNECTIVITY_SERVICE);
	
	     final android.net.NetworkInfo wifi =
	     connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	
	     final android.net.NetworkInfo mobile =
	     connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
	
	     if( wifi.isAvailable() )//neu co wifi
	     {
	    	 Toast.makeText(getApplicationContext(), "wifi", Toast.LENGTH_SHORT).show();
	    	return true; 
	     }
	     else if( mobile.isAvailable() ) //neu co 3G
	     {
	    	 Toast.makeText(getApplicationContext(), "3G", Toast.LENGTH_SHORT).show();
	    	 return true;
	     }
		 else //khong co internet
		 {
			 Toast.makeText(getApplicationContext(), "0 net", Toast.LENGTH_SHORT).show();
	    	 return false;
		 }
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	
	class xulygetallfile extends AsyncTask<Void,Void,Void>
	{
		MyFunctions myfunctions;
	
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			String thanhcong=null;
			try {
				myfunctions=new MyFunctions(getApplicationContext());
				JSONObject jsonobject=myfunctions.getAllFileDownload();
				
				thanhcong=jsonobject.getString("thanhcong");
				
				//doc tat ca du lieu tu json bo vao ArrayList
				if(Integer.parseInt(thanhcong)==1)//thanh cong
				{
					//truy mang ten filedownload trong json
					JSONArray jsonarray=jsonobject.getJSONArray("filedownload");
					//duyet mang
					Log.d("size",jsonarray.length()+"");
					for(int i=0;i<jsonarray.length();i++)
					{
						JSONObject item=jsonarray.getJSONObject(i);
						String id=item.getString("id");
						String ten=item.getString("ten");
						String duongdan=item.getString("duongdan");
						String mota=item.getString("mota");
						FileDownload f=new FileDownload(id,ten,duongdan,mota);
						ds_file.add(f);					
					}
				}
				else //that bai
				{
					Toast.makeText(getApplicationContext(),"that bai", Toast.LENGTH_SHORT).show();					
				}				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();
			pg_dialog=new ProgressDialog(MainActivity.this);
			pg_dialog.setMessage("dang nap du lieu");
			pg_dialog.setIndeterminate(false);
			pg_dialog.setCancelable(false);//co the cancel bang phim back
			pg_dialog.show();
		}
		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			pg_dialog.dismiss();		
			for(int i=0;i<ds_file.size();i++)
			{
				String x=ds_file.get(i).id + ds_file.get(i).ten + ds_file.get(i).duongdan + ds_file.get(i).mota;
				Log.d("file",x);
			}
			lv_file.setAdapter(new myadapter(MainActivity.this));
			ghivaoSQLite(getApplicationContext());
		}
	}

	public static class View_Mot_O
	{
		TextView tvid;
		TextView tvname;
	}
	class myadapter extends BaseAdapter{
		Context context;	
		myadapter(Context c)
		{
			context=c;
		}
		public int getCount() {
			return ds_file.size();
		}
		public Object getItem(int arg0) {
			return ds_file.get(arg0);
		}
		public long getItemId(int arg0) {
			return 0;
		}
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View_Mot_O mot_o;
			LayoutInflater inf= ((Activity)context).getLayoutInflater();	
			if(arg1==null)  
			{  
				mot_o = new View_Mot_O();  
				arg1 = inf.inflate(R.layout.list_item, null);  
				mot_o.tvid = (TextView) arg1.findViewById(R.id.fileid);  
				mot_o.tvname = (TextView)arg1.findViewById(R.id.filename);  
				arg1.setTag(mot_o);
			}
			else
				mot_o=(View_Mot_O)arg1.getTag();				
			mot_o.tvid.setText(ds_file.get(arg0).id);
			mot_o.tvname.setText(ds_file.get(arg0).ten);
			return arg1;
		}	
	}
}
