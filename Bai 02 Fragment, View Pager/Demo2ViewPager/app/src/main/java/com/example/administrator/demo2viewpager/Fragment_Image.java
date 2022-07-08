package com.example.administrator.demo2viewpager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 4/8/2017.
 */
@SuppressLint("ValidFragment")
public class Fragment_Image extends Fragment {

    ImageView iv;

    int image_id;

    public Fragment_Image(int image_id)
    {
        this.image_id=image_id;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_layout_image,container,false);
        iv=(ImageView)v.findViewById(R.id.imageView);
        iv.setImageResource(image_id);
        return v;
    }
}
