package com.example.administrator.dangkidangnhapdemo1;

import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Administrator on 5/7/2017.
 */
public class JSONParser {
     InputStream is=null;
     JSONObject jobj=null;
     String json="";
    public JSONParser()
    {

    }

    //ham nhan vao duong dan va doi so, tra ve doi tuong json
    public JSONObject getJSONFromUrl(String diachi, List<String> cacdoiso)
    {
        try{

            URL url = new URL(diachi);
            String param="";
            if(cacdoiso.get(0).equalsIgnoreCase("login")) {
                param = "tag=" + URLEncoder.encode(cacdoiso.get(0), "UTF-8") +
                        "&email=" + URLEncoder.encode(cacdoiso.get(1), "UTF-8") +
                        "&password=" + URLEncoder.encode(cacdoiso.get(2), "UTF-8");
                Log.d("loi","paramne: " + param );

            }
            else if(cacdoiso.get(0).equalsIgnoreCase("register"))
            {
                param = "tag=" + URLEncoder.encode(cacdoiso.get(0), "UTF-8") +
                        "&email=" + URLEncoder.encode(cacdoiso.get(1), "UTF-8") +
                        "&password=" + URLEncoder.encode(cacdoiso.get(2), "UTF-8")+
                        "&name=" + URLEncoder.encode(cacdoiso.get(3), "UTF-8");
            }
            else if(cacdoiso.get(0).equalsIgnoreCase("getallproduct")) {
               // param="";
            }
            else if(cacdoiso.get(0).equalsIgnoreCase("createproduct")) {

                param = "name=" + URLEncoder.encode(cacdoiso.get(1), "UTF-8") +
                        "&price=" + URLEncoder.encode(cacdoiso.get(2), "UTF-8") +
                        "&description=" + URLEncoder.encode(cacdoiso.get(3), "UTF-8");

            }
            else if(cacdoiso.get(0).equalsIgnoreCase("deleteproduct")) {

                param = "id=" + URLEncoder.encode(cacdoiso.get(1), "UTF-8");

            }



            HttpURLConnection httpurl = (HttpURLConnection)url.openConnection();

            httpurl.setDoOutput(true);

            httpurl.setRequestMethod("POST");
            httpurl.setFixedLengthStreamingMode(param.getBytes().length);
           // httpurl.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            httpurl.setRequestProperty("Connection","close");
            PrintWriter out = new PrintWriter(httpurl.getOutputStream());
            out.print(param);
            out.flush();
            out.close();


            String response= "";

            /*
            Scanner inStream = new Scanner(httpurl.getInputStream());
            while(inStream.hasNextLine())
                response+=(inStream.nextLine());
            */
            BufferedReader in=new BufferedReader(new InputStreamReader(httpurl.getInputStream()));
            String line="";
            StringBuffer sb=new StringBuffer("");
            while((line=in.readLine())!=null)
            {
                sb.append(line);
            }
            in.close();
            httpurl.disconnect();

            json=sb.toString();
            Log.d("loi",json);
            jobj =new JSONObject(json);

        }
        catch(Exception e)
        {
            Log.d("loi", e.toString() + " .....");
        }
        return jobj;
    }

}
