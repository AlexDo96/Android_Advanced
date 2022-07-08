package com.example.administrator.volleydemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText et_ten,et_tuoi;
    Button bt_get,bt_post;
    TextView tv_kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_ten=(EditText)findViewById(R.id.editText);
        et_tuoi=(EditText)findViewById(R.id.editText2);
        bt_get=(Button)findViewById(R.id.button);
        bt_post=(Button)findViewById(R.id.button2);
        tv_kq=(TextView)findViewById(R.id.textView);

        bt_get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten=et_ten.getText().toString();
                int tuoi=Integer.parseInt(et_tuoi.getText().toString());
                String dd="http://192.168.10.26/webandroid/nhanget.php?ten="+ten+"&tuoi="+tuoi;


                StringRequest request=new StringRequest(
                        Request.Method.GET,
                        dd,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                tv_kq.setText(response);
                            }
                        },
                        new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
                );

                Volley.newRequestQueue(MainActivity.this).add(request);
            }
        });



        bt_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dd="http://192.168.10.26/webandroid/nhanpost.php";

                StringRequest request=new StringRequest(
                        Request.Method.POST,
                        dd,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                tv_kq.setText(response);

                                
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }
                )
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> doiso=new HashMap<String,String>();
                        doiso.put("ten",et_ten.getText().toString());
                        doiso.put("tuoi",et_tuoi.getText().toString());

                        return doiso;
                    }
                };

                Volley.newRequestQueue(MainActivity.this).add(request);

            }
        });
    }

}
