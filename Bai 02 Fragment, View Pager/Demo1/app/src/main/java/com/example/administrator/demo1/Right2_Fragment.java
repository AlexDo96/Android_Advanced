package com.example.administrator.demo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Administrator on 4/8/2017.
 */
public class Right2_Fragment extends Fragment {

    TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_layout_right2,container,false);
        tv=(TextView)view.findViewById(R.id.textView);
        return view;
    }

    public void capnhattextviewfragment(String chuoi)
    {
        tv.setText(chuoi);
    }
}
