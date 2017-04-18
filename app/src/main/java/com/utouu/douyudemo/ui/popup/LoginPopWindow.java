package com.utouu.douyudemo.ui.popup;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.logger.Logger;
import com.utouu.douyudemo.R;

import butterknife.BindView;
import butterknife.OnClick;
import razerdp.basepopup.BasePopupWindow;

/**
 * Create by 黄思程 on 2017/4/18  11:16 x
 * Function：
 * Desc：登录对话框
 */
public class LoginPopWindow extends BasePopupWindow {

    @BindView(R.id.tv_login_way)
    TextView tvLoginWay;
    @BindView(R.id.btn_close_popup)
    ImageView btnClosePopup;
    @BindView(R.id.tv_login_wx)
    TextView tvLoginWx;
    @BindView(R.id.tv_login_qq)
    TextView tvLoginQq;
    @BindView(R.id.tv_login_wb)
    TextView tvLoginWb;
    @BindView(R.id.tv_login_douyu)
    TextView tvLoDouyu;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    public LoginPopWindow(Activity context) {
        super(context);
        Logger.e(">>>>>   登录对话框");
    }

    @Override
    protected Animation initShowAnimation() {
        return null;
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
        return btnClosePopup;
    }

    @Override
    public View onCreatePopupView() {
        return createPopupById(R.layout.popup_login);
    }

    @Override
    public View initAnimaView() {
        return null;
    }


    @OnClick(R.id.btn_close_popup)
    public void closePopup() {
        this.dismiss();
    }
}
