package com.utouu.douyudemo.view.live.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.view.LoadDataView;

import butterknife.ButterKnife;

/**
 * Create by 李俊鹏 on 2017/4/14 15:46
 * Function：
 * Desc：直播列表中常用页面
 */
public class LiveCommonColumnFragment extends BaseFragment {

    LoadDataView mLoadView;
    public static LiveCommonColumnFragment getInstance() {
        return new LiveCommonColumnFragment();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_commoncolumn;
    }

    @Override
    protected void onInitView(Bundle bundle) {

    }

    @Override
    protected void onEvent() {

    }

    @Override
    protected BaseView getViewImp() {
        return null;
    }

    @Override
    protected void getLoadView( LoadDataView mLoadView) {
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

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }
}
