package com.example.mac.tablayoutviewpagerdemo1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by mac on 9/19/15.
 */
public class PageFragment extends Fragment{

    int page;
    public static PageFragment newInstance(int page)
    {
        Bundle b=new Bundle();
        b.putInt("page", page);
        PageFragment pf=new PageFragment();
        pf.setArguments(b);
        return pf;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page=getArguments().getInt("page");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView tv = (TextView) view.findViewById(R.id.textView);
        tv.setText("Fragment thu" + page);
        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }
}
