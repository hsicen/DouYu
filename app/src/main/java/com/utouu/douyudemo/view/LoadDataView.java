package com.utouu.douyudemo.view;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.utils.ViewStatus;


/**
 * Create by 李俊鹏 on 2017/4/17
 * Function：
 * Desc：
 */
public class LoadDataView extends FrameLayout {

    /** 数据加载异常 */
    private final View errorView;
    /** 没有数据 */
    public final View noDataView;
    /** 数据 */
    private final View dataView;
    /** 加载数据中 */
    private final View loadingView;
    /** 网络连接失败 */
    private final View netErrorView;
    private final LayoutInflater inflater;
    private final Animation mImageViewAnimation;
    private Button loadingEmptyBtn;
    public TextView loadingEmptyTv,netErrorTv;
    private ImageView loadingEmptyImageView,loading_imagview;
    public volatile boolean isFirstLoad = true;
    private AnimationDrawable animationDrawable;
    public LoadDataView(Context context, View view) {
        super(context);
        setLayoutParams(new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        dataView = view;
        inflater = LayoutInflater.from(context);
        netErrorView = inflater.inflate(R.layout.layout_net_error, null);
        errorView = inflater.inflate(R.layout.layout_data_error, null);
        noDataView = inflater.inflate(R.layout.layout_data_empty, null);
        loadingView = inflater.inflate(R.layout.layout_data_loading, null);
        loadingEmptyBtn = (Button) noDataView.findViewById(R.id.data_error_button);
        loadingEmptyTv = (TextView) noDataView.findViewById(R.id.data_loading_empty_textview);
        netErrorTv= (TextView) noDataView.findViewById(R.id.netErrorTv);

        mImageViewAnimation = AnimationUtils.loadAnimation(context, R.anim.rote);
        mImageViewAnimation.setInterpolator(new LinearInterpolator());
        mImageViewAnimation.setDuration(2000);
        mImageViewAnimation.setRepeatCount(Animation.INFINITE);
        mImageViewAnimation.setRepeatMode(Animation.RESTART);
        initViews();
    }

    private void initViews() {
        if (null != dataView) {
            addView(dataView);
        }
        if (null != errorView) {
            addView(errorView);
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView) {
            addView(netErrorView);
            netErrorView.setVisibility(View.GONE);
        }
        if (null != loadingView) {
            addView(loadingView);
            loadingView.setVisibility(View.GONE);
        }
        if (null != noDataView) {
            addView(noDataView);
            noDataView.setVisibility(View.GONE);
        }
    }

    private void stop() {
//		if (null != loadingImg) {
//			loadingImg.clearAnimation();
//		}

        if (animationDrawable != null && animationDrawable.isRunning()) {
            animationDrawable.stop();
        }
    }

    private void start() {
        stop();
//		if (null != loadingImg) {
//
//			loadingImg.startAnimation(mImageViewAnimation);
//		}

        if (loading_imagview != null) {
            loading_imagview.setVisibility(View.VISIBLE);
        }
        if (animationDrawable == null) {
//            loading_imagview.setImageResource(R.drawable.animstion_push);
//            animationDrawable = (AnimationDrawable) loading_imagview.getDrawable();
        }

//        animationDrawable.start();
    }


    /**
     * 开始加载
     *
     * <br>
     */
    private void loadStart() {
        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.VISIBLE) {
            start();
            loadingView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 加载成功
     *
     * <br>
     */
    private void loadSuccess() {
        stop();
        if (null != dataView && dataView.getVisibility() != View.VISIBLE) {
            dataView.setVisibility(View.VISIBLE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 加载失败
     *
     */
    private void loadError() {
        stop();
        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.VISIBLE) {
            errorView.setVisibility(View.VISIBLE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 加载成功，但无数据
     *
     * <br>
     */
    private void loadNoData() {
        stop();
        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.GONE) {
            netErrorView.setVisibility(View.GONE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.VISIBLE) {
            noDataView.setVisibility(View.VISIBLE);
            loadingEmptyTv.setVisibility(View.GONE);

        }
        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    /**
     * 网络连接问题，加载异常，检查网络，点击屏幕重新连接
     *
     */
    private void loadNotNetwork() {
        stop();

        if (null != dataView && dataView.getVisibility() != View.GONE) {
            dataView.setVisibility(View.GONE);
        }
        if (null != errorView && errorView.getVisibility() != View.GONE) {
            errorView.setVisibility(View.GONE);
        }
        if (null != netErrorView && netErrorView.getVisibility() != View.VISIBLE) {
            netErrorView.setVisibility(View.VISIBLE);
        }
        if (null != noDataView && noDataView.getVisibility() != View.GONE) {
            noDataView.setVisibility(View.GONE);
        }

        if (null != loadingView && loadingView.getVisibility() != View.GONE) {
            loadingView.setVisibility(View.GONE);
        }
    }

    public void setErrorListner(OnClickListener listener) {
        if (null == listener) {
            return;
        }
        if (null != errorView) {

            errorView.findViewById(R.id.data_error_button).setOnClickListener(listener);
        }
        if (null != netErrorView) {

            netErrorView.findViewById(R.id.netBT).setOnClickListener(listener);
        }
        if (null != noDataView) {

            noDataView.findViewById(R.id.data_error_button).setOnClickListener(listener);
        }

    }

    public void changeStatusView(ViewStatus status) {
        if (isFirstLoad) {
            switch (status) {
                case START:
                    loadStart();
                    break;
                case SUCCESS:
                    isFirstLoad = false;
                    loadSuccess();
                    break;
                case FAILURE:
                    loadError();
                    break;
                case EMPTY:
                    loadNoData();
                    break;
                case NOTNETWORK:
                    loadNotNetwork();
                    break;
            }
        }
    }
    public void setFirstLoad(){
        isFirstLoad = true;
    }
    public TextView getLoadingEmptyTv(){
        return loadingEmptyTv;
    }
    public TextView getLoadingEmptyTvTop(){
        return netErrorTv;
    }
    public void setLoadingEmptyTv(String value){
        if(loadingEmptyTv!=null){
            loadingEmptyTv.setText(value);
        }
    }
    public Button getLoadingEmptyBtn(){
        return loadingEmptyBtn;
    }
    public ImageView getLoadingEmptyImageView(){
        return loadingEmptyImageView;}
}
