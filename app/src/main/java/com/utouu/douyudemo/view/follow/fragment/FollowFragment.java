package com.utouu.douyudemo.view.follow.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.flyco.tablayout.SlidingTabLayout;
import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.view.LoadDataView;
import com.utouu.douyudemo.view.TestFragment;

import butterknife.BindView;

/**
 * Create by 黄思程 on 2017/4/18  10:19
 * Function：
 * Desc：关注页面
 */
public class FollowFragment extends BaseFragment {

    @BindView(R.id.st_follow)
    SlidingTabLayout mTabFollow;
    @BindView(R.id.vp_follow)
    ViewPager mVpFollow;

    SVProgressHUD svProgressHUD;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_follow;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(getActivity());

        mVpFollow.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return TestFragment.newInstance();
            }

            @Override
            public int getCount() {
                return 2;
            }
        });

        mTabFollow.setViewPager(mVpFollow,new String[]{"直播","动态"});
    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void getLoadView(LoadDataView mLoadView) {

    }

    @Override
    protected void lazyFetchData() {

    }


}
