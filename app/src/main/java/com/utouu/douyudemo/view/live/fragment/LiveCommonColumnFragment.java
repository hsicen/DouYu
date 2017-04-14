package com.utouu.douyudemo.view.live.fragment;

import android.os.Bundle;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
/**
 * Create by 李俊鹏 on 2017/4/14 15:46
 * Function：
 * Desc：直播列表中常用页面
 */
public class LiveCommonColumnFragment extends BaseFragment {

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
    protected void lazyFetchData() {

    }
}
