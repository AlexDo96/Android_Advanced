package com.example.mac.navigationdrawerdeom1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by mac on 9/18/15.
 */
public class Fragment2 extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment2_layout,container,false);

        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }
}
