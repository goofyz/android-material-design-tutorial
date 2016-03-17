package com.thirtysparks.tutorial.designlib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;


public class TabPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 5;
    private Context context;

    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return TabFragment.newInstance("Fragment tab", "No. "+ position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable icon = context.getResources().getDrawable(R.drawable.ic_add_circle_outline_white);
        // should use : icon.setBounds(0, 0, icon.getIntrinsicWidth(), icon.getIntrinsicHeight());
        // however, my icon is too large, so I used a fix size, please use an icon with proper size :)
        icon.setBounds(0, 0, 50, 50);
        ImageSpan iconSpan = new ImageSpan(icon, ImageSpan.ALIGN_BOTTOM);

        SpannableString sb = new SpannableString("  " + "Tab  " + position);
        sb.setSpan(iconSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sb;
    }
}
