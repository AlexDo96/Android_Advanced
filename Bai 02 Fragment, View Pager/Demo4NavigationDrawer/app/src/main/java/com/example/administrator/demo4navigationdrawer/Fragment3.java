package com.example.administrator.demo4navigationdrawer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Administrator on 4/8/2017.
 */
public class Fragment3 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment3_layout,container,false);
        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }
}
