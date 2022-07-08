package com.example.administrator.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by Administrator on 4/8/2017.
 */
public class Left_Fragment extends Fragment {

    Button bt,bt2;
    EditText et;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_layout_left,container,false);
        bt=(Button)view.findViewById(R.id.button);
        bt2=(Button)view.findViewById(R.id.button2);
        et=(EditText)view.findViewById(R.id.editText);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //truy xuat len thang cha (main activity)
                ((MainActivity)getActivity()).capnhattextview(et.getText().toString());

                //truy xuat qua thang con ben kia (right fragment)
                Right_Fragment rf=(Right_Fragment)getFragmentManager().findFragmentById(R.id.idfragmentright);
                rf.capnhattextviewfragment(et.getText().toString());


                Toast.makeText(getContext(),"tui la cai toast",Toast.LENGTH_SHORT).show();
            }
        });


        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager=getFragmentManager();
                FragmentTransaction transaction=fragmentManager.beginTransaction();
                Right2_Fragment r2=new Right2_Fragment();
                Right_Fragment rf=(Right_Fragment)getFragmentManager().findFragmentById(R.id.idfragmentright);
                transaction.replace(R.id.idfragmentright,r2);
                transaction.commit();
            }
        });
        return view;
    }
}
