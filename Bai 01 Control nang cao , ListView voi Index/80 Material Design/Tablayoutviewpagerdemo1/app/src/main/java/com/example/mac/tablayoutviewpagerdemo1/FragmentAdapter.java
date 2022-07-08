package com.example.mac.tablayoutviewpagerdemo1;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

/**
 * Created by mac on 9/19/15.
 */
public class FragmentAdapter extends FragmentPagerAdapter{

    String tieude[]={"tab 1","tab 2","tab 3"};
    private int[] imageResId = {
            R.drawable.ic_action_one,
            R.drawable.ic_action_two,
            R.drawable.ic_action_three
    };
    Context context;
    public FragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;
    }

    @Override
    public Fragment getItem(int position) {
        PageFragment fragment=PageFragment.newInstance(position);
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = ContextCompat.getDrawable(context, imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString("   " + tieude[position]);
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
        //return tieude[position];
    }

}
