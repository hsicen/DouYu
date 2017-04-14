package com.utouu.douyudemo.view.live.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.utouu.douyudemo.model.logic.live.bean.LiveOtherColumn;
import com.utouu.douyudemo.view.live.fragment.LiveAllColumnFragment;
import com.utouu.douyudemo.view.live.fragment.LiveCommonColumnFragment;
import com.utouu.douyudemo.view.live.fragment.LiveOtherColumnFragment;
import com.utouu.douyudemo.view.live.fragment.LiveSportsColumnFragment;

import java.util.List;

/**
 * Create by 李俊鹏 on 2017/4/14 17:06
 * Function：
 * Desc：
 */
public class LiveAllColumnAdapter extends FragmentStatePagerAdapter {

    private List<LiveOtherColumn> mLiveOtherColumns;
    private String[] mTitle;

    public LiveAllColumnAdapter(FragmentManager fm, List<LiveOtherColumn> mLiveOtherColumns, String[] title) {
        super(fm);
        this.mLiveOtherColumns = mLiveOtherColumns;
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
        if (position == 0) {
            return LiveCommonColumnFragment.getInstance();
        } else if (position == 1) {
            return LiveAllColumnFragment.getInstance();
        } else if (position == mTitle.length - 1) {
            return LiveSportsColumnFragment.getInstance();
        } else {
//            其他一级栏目分类
            return LiveOtherColumnFragment.getInstance(mLiveOtherColumns.get(position - 2), position - 2);
        }
    }
}
