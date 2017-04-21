package com.utouu.douyudemo.view.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.utouu.douyudemo.view.TestFragment;

/**
 * Create by 李俊鹏 on 2017/4/14 17:06
 * Function：
 * Desc：
 */
public class VerticalLiveNewsAdapter extends FragmentStatePagerAdapter {

    private String[] mTitle;

    public VerticalLiveNewsAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.mTitle = title;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle[position];
    }

    @Override
    public int getCount() {
        return mTitle.length;
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance();
    }
}
