package com.example.administrator.dangkidangnhapdemo1;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 5/7/2017.
 */
public class MyFunctions {
    JSONParser jsonparser;
/*
    String loginurl="http://10.0.2.2:8080/android_db_1/index.php";
    String registerurl="http://10.0.2.2:8080/android_db_1/index.php";*/
    String loginurl="http://192.168.10.26/android_db_1/index.php";
    String registerurl="http://192.168.10.26/android_db_1/index.php";
    //khai bao cho all product
    String allproducturl="http://192.168.10.26/android_db_1/getallproducts.php";
    //khai bao cho add new product
    String createproducturl="http://192.168.10.26/android_db_1/createproduct.php";

    String deleteproducturl="http://192.168.10.26/android_db_1/deleteproduct.php";


    String login_tag="login";
    String register_tag="register";

    Context context;

    //ham tao khoi ta doi tuong jsonparser
    public MyFunctions(Context context)
    {
        jsonparser=new JSONParser();
        this.context=context;
    }

    //doc tu shared neu da log neu chua log tra ve false, log roi tra ve true
    public boolean checkLogin()
    {
        SharedPreferences lay=
                context.getSharedPreferences(null,context.MODE_WORLD_READABLE);
        String emaillogined=lay.getString("emaillogined","chua login");
        if(emaillogined.equals("chua login"))
            return false;
        else
            return true;
    }

    //lay email da login
    public String getEmail()
    {
        SharedPreferences lay=
                context.getSharedPreferences(null,context.MODE_WORLD_READABLE);
        String emaillogined=lay.getString("emaillogined","chua login");
        return emaillogined;
    }

    //ghi du lieu lai cho emaillogined thanh "chua login"
    public boolean logOut()
    {
        SharedPreferences ghi=
                context.getSharedPreferences(null,context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor=ghi.edit();
        editor.putString("emaillogined", "chua login");
        editor.commit();
        return true;
    }

    //khi da login thi luu lai email lenh shared de biet da log
    public boolean setemaillogin(String email)
    {
        SharedPreferences ghi=
                context.getSharedPreferences(null,context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor=ghi.edit();
        editor.putString("emaillogined", email);
        editor.commit();
        return true;
    }

    /* nhan du lieu goi ham va tra ve json
		chu y ArrayList va BasicNameValuePair
	*/
    public JSONObject loginUser(String email, String password)
    {
        ArrayList<String> cacdoiso=new ArrayList<String>();
        cacdoiso.add("login");
        cacdoiso.add(email);
        cacdoiso.add(password);
        JSONObject jobj=jsonparser.getJSONFromUrl(loginurl, cacdoiso);

        //setemaillogin(email);//gan len share de nho da login roi

        return jobj;
    }

    /*nhan du lieu dang ki
	 * chu y: tag, register_tag va doi so name
	 */
    public JSONObject registerUser(String name, String email, String password)
    {
        ArrayList<String> cacdoiso=new ArrayList<String>();
        cacdoiso.add("register");
        cacdoiso.add(email);
        cacdoiso.add(password);
        cacdoiso.add(name);
        JSONObject jobj=jsonparser.getJSONFromUrl(loginurl, cacdoiso);
        return jobj;
    }


    //tra ve tat ca du lieu tu bang product
    public JSONObject getAllProducts()
    {
        ArrayList<String> cacdoiso=new ArrayList<String>();
        cacdoiso.add("getallproduct");
        //POST khong can doi so nen ta tao doi so rong
        JSONObject jobj=jsonparser.getJSONFromUrl(allproducturl, cacdoiso);
        return jobj;
    }

    //tao san pham moi
    public JSONObject createProduct(String name,String price,String description)
    {
        // tao cac doi so
        ArrayList<String> cacdoiso=new ArrayList<String>();
        cacdoiso.add("createproduct");
        cacdoiso.add(name);
        cacdoiso.add(price);
        cacdoiso.add(description);
        JSONObject jobj=jsonparser.getJSONFromUrl(createproducturl, cacdoiso);
        return jobj;
    }

    //tao delete sanpham theo id
    public JSONObject deleteProduct(String id)
    {
        // tao cac doi so
        ArrayList<String> cacdoiso=new ArrayList<String>();
        cacdoiso.add("deleteproduct");
        cacdoiso.add(id);
        JSONObject jobj=jsonparser.getJSONFromUrl(deleteproducturl, cacdoiso);
        return jobj;
    }



}
