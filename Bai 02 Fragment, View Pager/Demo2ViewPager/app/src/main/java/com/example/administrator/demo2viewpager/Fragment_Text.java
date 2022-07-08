package com.example.administrator.demo2viewpager;

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
public class Fragment_Text extends Fragment {

    TextView tv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_layout_text,container,false);
        tv=(TextView)v.findViewById(R.id.textView);
        tv.setText("abc");
        return v;
    }
}
