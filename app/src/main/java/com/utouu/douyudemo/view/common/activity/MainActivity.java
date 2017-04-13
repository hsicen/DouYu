package com.utouu.douyudemo.view.common.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.ui.NavigateTabBar;
import com.utouu.douyudemo.view.follow.fragment.FollowFragment;
import com.utouu.douyudemo.view.home.fragment.HomeFragment;
import com.utouu.douyudemo.view.live.fragment.LiveFragment;
import com.utouu.douyudemo.view.user.fragment.UserFragment;
import com.utouu.douyudemo.view.video.fragment.VideoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Create by 黄思程 on 2017/4/13  17:08
 * Function：
 * Desc：主界面
 */
public class MainActivity extends  AppCompatActivity implements BaseView{

    @BindView(R.id.mainTabBar)
    NavigateTabBar mNavigateTabBar;

    private static final String TAG_PAGE_HOME = "首页";
    private static final String TAG_PAGE_LIVE= "直播";
    private static final String TAG_PAGE_VIDEO = "关注";
    private static final String TAG_PAGE_FOLLOW = "发现";
    private static final String TAG_PAGE_USER = "我的";
    protected Unbinder unbinder;
    private long exitTime = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        mNavigateTabBar.onRestoreInstanceState(savedInstanceState);
        mNavigateTabBar.addTab(HomeFragment.class, new NavigateTabBar.TabParam(R.drawable.home_pressed, R.drawable
                .home_selected, TAG_PAGE_HOME));
        mNavigateTabBar.addTab(LiveFragment.class, new NavigateTabBar.TabParam(R.drawable.live_pressed, R.drawable
                .live_selected, TAG_PAGE_LIVE));
        mNavigateTabBar.addTab(VideoFragment.class, new NavigateTabBar.TabParam(R.drawable.follow_pressed,
                R.drawable.follow_selected, TAG_PAGE_VIDEO));
        mNavigateTabBar.addTab(FollowFragment.class, new NavigateTabBar.TabParam(R.drawable.video,
                R.drawable.video_selected, TAG_PAGE_FOLLOW));
        mNavigateTabBar.addTab(UserFragment.class, new NavigateTabBar.TabParam(R.drawable.user_pressed, R.drawable
                .user_selected, TAG_PAGE_USER));
        mNavigateTabBar.setTabSelectListener(holder -> {
            switch (holder.tag) {
                case TAG_PAGE_HOME:
                    mNavigateTabBar.showFragment(holder);
                    break;
                case TAG_PAGE_LIVE:
                    mNavigateTabBar.showFragment(holder);
                    break;
                case TAG_PAGE_VIDEO:
                    mNavigateTabBar.showFragment(holder);
                    break;
                case TAG_PAGE_FOLLOW:
                    mNavigateTabBar.showFragment(holder);
                    break;
                case TAG_PAGE_USER:
                    if(mNavigateTabBar!=null)
                    mNavigateTabBar.showFragment(holder);
                    break;
            }
        });
        /*// 获取所有权限
        PermissionUtil.requestAllPermission(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {

            }

            @Override
            public void onRequestPermissionFailed() {

            }
        }, new RxPermissions(MainActivity.this),this);*/
    }

    /**
     * 拦截返回键，要求点击两次返回键才退出应用
     * @param keyCode 按键代码
     * @param event   点击事件
     * @return 是否处理本次事件
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            Toast.makeText(getApplicationContext(), "再按一次退出程序",
                    Toast.LENGTH_SHORT).show();
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    /**
     * 保存数据状态
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigateTabBar.onSaveInstanceState(outState);
    }

    @Override
    public void showErrorWithStatus(String msg) {

    }
}