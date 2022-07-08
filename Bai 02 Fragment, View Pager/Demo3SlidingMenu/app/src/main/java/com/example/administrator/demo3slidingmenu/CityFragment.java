package com.example.administrator.demo3slidingmenu;

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
public class CityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        int vitri =getArguments().getInt("vitri");
        String [] dsthanhpho= getResources().getStringArray(R.array.thanhpho);
        View view=inflater.inflate(R.layout.fragment_layout,container, false);
        TextView tv=(TextView)view.findViewById(R.id.tv_content);
        tv.setText(dsthanhpho[vitri]);
        //getActivity().getActionBar().setTitle(dsthanhpho[vitri]);
        return view;

    }
}
