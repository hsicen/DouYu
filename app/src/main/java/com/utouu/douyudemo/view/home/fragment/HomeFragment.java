package com.utouu.douyudemo.view.home.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.OvershootInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.flyco.tablayout.SlidingTabLayout;
import com.github.clans.fab.FloatingActionMenu;
import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.home.HomeCateListModelLogic;
import com.utouu.douyudemo.model.logic.home.bean.HomeCateList;
import com.utouu.douyudemo.presenter.home.impl.HomeCateListPresenterImp;
import com.utouu.douyudemo.presenter.home.interfaces.HomeCateListContract;
import com.utouu.douyudemo.utils.ToastUtils;
import com.utouu.douyudemo.view.home.adapter.HomeAllListAdapter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Create by 黄思程 on 2017/4/13  17:08
 * Function：
 * Desc：首页
 */
public class HomeFragment extends BaseFragment<HomeCateListModelLogic, HomeCateListPresenterImp>
        implements HomeCateListContract.View {

    @BindView(R.id.et_hint) TextView etHint;
    @BindView(R.id.iv_search) ImageView ivSearch;
    @BindView(R.id.iv_scan) ImageView ivScan;
    @BindView(R.id.iv_game) ImageView ivGame;
    @BindView(R.id.iv_history) ImageView ivHistory;
    @BindView(R.id.sliding_tab) SlidingTabLayout slidingTab;
    @BindView(R.id.viewpager) ViewPager viewpager;
    @BindView(R.id.mainFloatButton) FloatingActionMenu mFloatButton;

    SVProgressHUD svProgressHUD;
    private List<String> hintText;
    private Subscription subscribe;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }
    @Override
    protected void onInitView(Bundle bundle) {
        hintText = Arrays.asList("skt", "asmr", "h1z1", "lck", "糯米", "狼人杀",
                "初代", "绝地求生", "贝贝", "青春联练习生");
        svProgressHUD = new SVProgressHUD(getActivity());

        mFloatButton.setClosedOnTouchOutside(true);
        createCustomAnimation();
        changeSearchHint();
    }
    @Override
    protected void onEvent() {

    }
    @Override
    protected BaseView getViewImp() {
        return this;
    }

    @Override
    protected void lazyFetchData() {
        mPresenter.getHomeCateList();
    }

    @Override
    public void getHomeAllList(List<HomeCateList> cateLists) {
        String[] mTitles = new String[cateLists.size() + 1];
        mTitles[0]="推荐";
        for (int i=0;i<cateLists.size();i++)
            mTitles[i+1]=cateLists.get(i).getTitle();

        viewpager.setOffscreenPageLimit(mTitles.length);
        HomeAllListAdapter mAdapter = new HomeAllListAdapter(getChildFragmentManager(), cateLists, mTitles);
        viewpager.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        slidingTab.setViewPager(viewpager, mTitles);
    }
    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
    }

    @OnClick({R.id.et_hint, R.id.iv_search, R.id.iv_scan, R.id.iv_game, R.id.iv_history})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.et_hint:
                break;
            case R.id.iv_search:
                ToastUtils.showShort(mContext,"跳转搜索界面");
                break;
            case R.id.iv_scan:
                ToastUtils.showShort(mContext,"跳转二维码扫描界面");
                break;
            case R.id.iv_game:
                ToastUtils.showShort(mContext,"跳转游戏中心界面");
                break;
            case R.id.iv_history:
                ToastUtils.showShort(mContext,"跳转观看历史界面");
                break;
        }
    }

    /**
     * 改变搜索的提示
     */
    private void changeSearchHint() {
        subscribe = Observable.interval(0, 5, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> etHint.setText(hintText.get((int) (aLong % hintText.size()))));
    }

    /**
     * 自定义悬浮按钮动画
     */
    public void createCustomAnimation() {
        AnimatorSet set = new AnimatorSet();

        ObjectAnimator scaleOutX = ObjectAnimator.ofFloat(mFloatButton.getMenuIconView(), "scaleX", 1.0f, 0.2f);
        ObjectAnimator scaleOutY = ObjectAnimator.ofFloat(mFloatButton.getMenuIconView(), "scaleY", 1.0f, 0.2f);
        ObjectAnimator alphaOut = ObjectAnimator.ofFloat(mFloatButton.getMenuIconView(),"alpha",1.0f,0f);

        ObjectAnimator scaleInX = ObjectAnimator.ofFloat(mFloatButton.getMenuIconView(), "scaleX", 0.2f, 1.0f);
        ObjectAnimator scaleInY = ObjectAnimator.ofFloat(mFloatButton.getMenuIconView(), "scaleY", 0.2f, 1.0f);
        ObjectAnimator alphaIn = ObjectAnimator.ofFloat(mFloatButton.getMenuIconView(),"alpha",0f,1.0f);

        scaleOutX.setDuration(200);
        scaleOutY.setDuration(200);

        scaleInX.setDuration(300);
        scaleInY.setDuration(300);

        scaleInX.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                mFloatButton.getMenuIconView().setImageResource(mFloatButton.isOpened()
                        ? R.drawable.icon_home_menu : R.drawable.icon_menu_camera_live);
            }
        });

        set.play(scaleOutX).with(scaleOutY).with(alphaOut);
        set.play(scaleInX).with(scaleInY).with(alphaIn).after(scaleOutX);
        set.setInterpolator(new OvershootInterpolator(2));

        mFloatButton.setIconToggleAnimatorSet(set);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        subscribe.unsubscribe();
    }
}
