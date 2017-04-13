package com.utouu.douyudemo.view.live.fragment;

import android.os.Bundle;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/2/21 下午1:54
 **/
public class LiveCommonColumnFragment extends BaseFragment {

    public static LiveCommonColumnFragment getInstance() {
        LiveCommonColumnFragment rf = new LiveCommonColumnFragment();
        return rf;
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
