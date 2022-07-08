package com.example.downloadfile;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import android.content.Context;

public class MyFunctions {
	
	JSONParser jsonparser;
	String fileurl="http://10.0.2.2:8080/android_download/xulyandroid.php";
	String getall_tag="getallfiledownload";	
	Context context;
	
	//ham tao khoi ta doi tuong jsonparser
	public MyFunctions(Context context)
	{
		jsonparser=new JSONParser();
		this.context=context;
	}

	//tra ve tat ca du lieu tu bang product
	public JSONObject getAllFileDownload()
	{
		//POST khong can doi so nen ta tao doi so rong
		List<NameValuePair> cacdoiso=new ArrayList<NameValuePair>();
		cacdoiso.add(new BasicNameValuePair("tag",getall_tag) );

		JSONObject jobj=jsonparser.getJSONFromUrl(fileurl, cacdoiso);
		return jobj;
	}
}
