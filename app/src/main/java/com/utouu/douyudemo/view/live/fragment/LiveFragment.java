package com.utouu.douyudemo.view.live.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.flyco.tablayout.SlidingTabLayout;
import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.live.LiveOtherColumnModelLogic;
import com.utouu.douyudemo.model.logic.live.bean.LiveOtherColumn;
import com.utouu.douyudemo.presenter.live.impl.LiveOtherColumnPresenterImp;
import com.utouu.douyudemo.presenter.live.interfaces.LiveOtherColumnContract;
import com.utouu.douyudemo.utils.ViewStatus;
import com.utouu.douyudemo.view.LoadDataView;
import com.utouu.douyudemo.view.live.adapter.LiveAllColumnAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Create by 李俊鹏 on 2017/4/14 15:19
 * Function：
 * Desc：直播
 */
public class LiveFragment extends BaseFragment<LiveOtherColumnModelLogic, LiveOtherColumnPresenterImp> implements LiveOtherColumnContract.View {

    SVProgressHUD svProgressHUD;
    @BindView(R.id.live_sliding_tab)
    SlidingTabLayout liveSlidingTab;
    @BindView(R.id.live_viewpager)
    ViewPager liveViewpager;
    private LoadDataView mLoadView;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(getActivity());

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void getLoadView(LoadDataView mLoadView) {
        this.mLoadView = mLoadView;
        mLoadView.setErrorListner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        lazyFetchData();
                    }
                }, 100);
            }
        });
    }

    /**
     * 懒加载
     */
    @Override
    protected void lazyFetchData() {
        mLoadView.changeStatusView(ViewStatus.START);
        mPresenter.getPresenterLiveOtherColumn();
    }

    @Override
    public void showErrorWithStatus(String msg) {
        mLoadView.changeStatusView(ViewStatus.NOTNETWORK);
    }

    /**
     * 获取全部栏目分类
     *
     * @param mLiveOtherColumns
     */
    @Override
    public void getViewLiveOtherColumn(List<LiveOtherColumn> mLiveOtherColumns) {

        if (mLiveOtherColumns != null) {
            mLoadView.changeStatusView(ViewStatus.SUCCESS);
        } else {
            mLoadView.changeStatusView(ViewStatus.EMPTY);
        }
        String[] mTitle = new String[mLiveOtherColumns.size() + 3];
        mTitle[0] = "常用";
        mTitle[1] = "全部";
        for (int i = 0; i < mLiveOtherColumns.size(); i++) {
            mTitle[i + 2] = mLiveOtherColumns.get(i).getCate_name();
        }
        mTitle[mTitle.length - 1] = "体育直播";
        liveViewpager.setOffscreenPageLimit(mTitle.length);
        LiveAllColumnAdapter mLiveAllColumnAdapter = new LiveAllColumnAdapter(getChildFragmentManager(), mLiveOtherColumns, mTitle);
        liveViewpager.setAdapter(mLiveAllColumnAdapter);
        mLiveAllColumnAdapter.notifyDataSetChanged();
        liveSlidingTab.setViewPager(liveViewpager, mTitle);
        liveSlidingTab.setCurrentTab(1);
    }

}
