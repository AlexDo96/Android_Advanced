package com.example.administrator.webservicedemo1;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Administrator on 4/16/2017.
 */
public class MyTask extends AsyncTask<Void,Void,Void>{

    String SOAPAction ="http://www.webserviceX.NET/GetCitiesByCountry";
    String NameSpace="http://www.webserviceX.NET";
    String MethodName="GetCitiesByCountry";
    String URL="http://www.webservicex.net/globalweather.asmx?WSDL";
    String param="CountryName";
    Context c;
    TextView tv;
    String tenthanhpho;
    String dulieu;
    MyTask(Context c, String tenthanhpho, TextView tv)
    {
        this.c=c;
        this.tv=tv;
        this.tenthanhpho=tenthanhpho;
    }
    @Override
    protected Void doInBackground(Void... params) {
        SoapObject request=new SoapObject(NameSpace,MethodName);
        request.addProperty(param, tenthanhpho);
        SoapSerializationEnvelope evelope=new SoapSerializationEnvelope(SoapSerializationEnvelope.VER11);
        evelope.setOutputSoapObject(request);
        evelope.dotNet=true;

        HttpTransportSE transportSE=new HttpTransportSE(URL);

        try {
            transportSE.call(SOAPAction,evelope);


        } catch (Exception e) {
            e.printStackTrace();
        }

        SoapObject object=(SoapObject)evelope.bodyIn;

        dulieu=object.getProperty(0).toString();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        tv.setText(dulieu);
        super.onPostExecute(aVoid);
    }
}
