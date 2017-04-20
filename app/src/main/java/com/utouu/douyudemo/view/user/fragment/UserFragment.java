package com.utouu.douyudemo.view.user.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.facebook.drawee.view.SimpleDraweeView;
import com.github.clans.fab.FloatingActionMenu;
import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.me.MeModelLogic;
import com.utouu.douyudemo.model.logic.me.bean.PersonInfoBean;
import com.utouu.douyudemo.presenter.me.impl.MePresenterImpl;
import com.utouu.douyudemo.presenter.me.interfaces.MeContract;
import com.utouu.douyudemo.ui.popup.LoginPopWindow;
import com.utouu.douyudemo.view.LoadDataView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Create by 黄思程 on 2017/4/17  17:07
 * Function：
 * Desc：我的页面
 */
public  class UserFragment extends BaseFragment<MeModelLogic, MePresenterImpl> implements
        MeContract.View {

    @BindView(R.id.iv_avatar) SimpleDraweeView ivAvatar;
    @BindView(R.id.btn_login) Button btnLogin;
    @BindView(R.id.btn_register) Button btnRegister;
    @BindView(R.id.rl_not_login) RelativeLayout rlNotLogin;
    @BindView(R.id.tv_nickname) TextView tvNickname;
    @BindView(R.id.iv_setting) ImageView ivSetting;
    @BindView(R.id.rl_aready_login) RelativeLayout rlAreadyLogin;
    @BindView(R.id.iv_icon) ImageView ivIcon;
    @BindView(R.id.rl_history_watch) RelativeLayout rlHistoryWatch;
    @BindView(R.id.iv_icon1) ImageView ivIcon1;
    @BindView(R.id.rl_letters) RelativeLayout rlLetters;
    @BindView(R.id.iv_icon2) ImageView ivIcon2;
    @BindView(R.id.rl_my_task) RelativeLayout rlMyTask;
    @BindView(R.id.iv_icon3) ImageView ivIcon3;
    @BindView(R.id.rl_chongzhi) RelativeLayout rlChongzhi;
    @BindView(R.id.iv_anchor) ImageView ivAnchor;
    @BindView(R.id.rl_anchor_recruit) RelativeLayout rlAnchorRecruit;
    @BindView(R.id.iv_my_video) ImageView ivMyVideo;
    @BindView(R.id.rl_my_video) RelativeLayout rlMyVideo;
    @BindView(R.id.iv_video_collection) ImageView ivVideoCollection;
    @BindView(R.id.rl_video_collection) RelativeLayout rlVideoCollection;
    @BindView(R.id.iv_my_account) ImageView ivMyAccount;
    @BindView(R.id.rl_my_account) RelativeLayout rlMyAccount;
    @BindView(R.id.iv_image_game) ImageView ivImageGame;
    @BindView(R.id.rl_game_center) RelativeLayout rlGameCenter;
    @BindView(R.id.iv_clock) ImageView ivClock;
    @BindView(R.id.rl_clock) RelativeLayout rlClock;
    @BindView(R.id.mainFloatButton) FloatingActionMenu mFloatButton;

    private  LoginPopWindow mLoginPopwindow;
    SVProgressHUD svProgressHUD;

    @Override
   public void getViewPersonInfo(PersonInfoBean personInfoBean) {

    }

    @Override
    public void showLoginPopWindow() {
        if(mLoginPopwindow!=null)
        mLoginPopwindow.showPopupWindow();
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        svProgressHUD = new SVProgressHUD(getActivity());
        mLoginPopwindow=new LoginPopWindow(getActivity());
        mLoginPopwindow.setDismissWhenTouchOuside(true);

        ivAvatar.setImageResource(R.drawable.usercenter_default_avatar);
        mFloatButton.setClosedOnTouchOutside(true);
        createCustomAnimation();
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

    }

    @Override
    protected void lazyFetchData() {

    }

    @Override
    public void onPause() {
        super.onPause();
        if (mFloatButton.isOpened()){
            mFloatButton.close(false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.btn_login)
    public void login() {
        mPresenter.Login();
    }

    @OnClick(R.id.btn_register)
    public void register(){
        mPresenter.Login();
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
}
