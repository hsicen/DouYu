package com.utouu.douyudemo.ui.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.utils.ToastUtils;

import razerdp.basepopup.BasePopupWindow;

/**
 * Create by 黄思程 on 2017/4/18  11:16
 * Function：
 * Desc：登录对话框
 */
public class LoginPopWindow extends BasePopupWindow implements View.OnClickListener{

    public LoginPopWindow(Activity context) {
        super(context);
        ImageView btnClose = ((ImageView) findViewById(R.id.btn_close_popup));
        TextView loginWeiXin = ((TextView) findViewById(R.id.tv_login_wx));
        TextView loginQQ = ((TextView) findViewById(R.id.tv_login_qq));
        TextView loginDouYu = ((TextView) findViewById(R.id.tv_login_douyu));
        TextView loginWeiBo = ((TextView) findViewById(R.id.tv_login_wb));
        TextView regist = ((TextView) findViewById(R.id.tv_register));

        setViewClickListener(this, btnClose, loginWeiXin, loginQQ, loginDouYu, loginWeiBo, regist);
    }

    @Override
    protected Animation initShowAnimation() {
        AnimationSet set=new AnimationSet(false);
        Animation shakeAnima=new RotateAnimation(0,15,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        shakeAnima.setInterpolator(new CycleInterpolator(5));
        shakeAnima.setDuration(400);
        set.addAnimation(getDefaultAlphaAnimation());
        set.addAnimation(shakeAnima);
        return set;
    }

    @Override
    public Animator initExitAnimator() {
        AnimatorSet set = new AnimatorSet();
        set.playTogether(ObjectAnimator.ofFloat(mAnimaView, "rotationX", 0f, 90f).setDuration(400),
                ObjectAnimator.ofFloat(mAnimaView, "translationY", 0f, 250f).setDuration(400),
                ObjectAnimator.ofFloat(mAnimaView, "alpha", 1f, 0f).setDuration(400 * 3 / 2));
        return set;
    }

    @Override
    public View getClickToDismissView() {
        return getPopupWindowView();
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_login);
    }

    @Override
    public View initAnimaView() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_close_popup:
                dismiss();
                break;
            case R.id.tv_login_wx:
            case R.id.tv_login_qq:
            case R.id.tv_login_douyu:
            case R.id.tv_login_wb:
            case R.id.tv_register:
                ToastUtils.showShort(getContext(),"正在开发");
                dismiss();
                break;
        }
    }
}