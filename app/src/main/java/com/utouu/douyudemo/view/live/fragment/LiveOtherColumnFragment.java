package com.utouu.douyudemo.view.live.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.flyco.tablayout.SlidingTabLayout;
import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.live.LiveOtherTwoColumnModelLogic;
import com.utouu.douyudemo.model.logic.live.bean.LiveOtherColumn;
import com.utouu.douyudemo.model.logic.live.bean.LiveOtherTwoColumn;
import com.utouu.douyudemo.presenter.live.impl.LiveOtherTwoColumnPresenterImp;
import com.utouu.douyudemo.presenter.live.interfaces.LiveOtherTwoColumnContract;
import com.utouu.douyudemo.utils.ViewStatus;
import com.utouu.douyudemo.view.LoadDataView;
import com.utouu.douyudemo.view.live.adapter.LiveOtherTwoCloumnAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.utouu.douyudemo.R.id.img_popup_live;

/**
 * Create by 李俊鹏 on 2017/4/14 17:06
 * Function：
 * Desc：
 */
public class LiveOtherColumnFragment extends BaseFragment<LiveOtherTwoColumnModelLogic, LiveOtherTwoColumnPresenterImp> implements LiveOtherTwoColumnContract.View {
    private static List<LiveOtherColumnFragment> mLiveOtherColumnFragment = new ArrayList<LiveOtherColumnFragment>();
    @BindView(R.id.twocolumn_tablayout)
    SlidingTabLayout twoColumnTabLayout;
    @BindView(R.id.livetwocolumn_viewpager)
    ViewPager liveTwoColumnViewpager;
    @BindView(R.id.rl_twocolumn_bar)
    RelativeLayout rlTwoColumnBar;
    @BindView(img_popup_live)
    ImageView imgPopupLive;


    private List<LiveOtherTwoColumn> mLiveOtherTwoColumn;
    private LoadDataView mLoadView;

    public static LiveOtherColumnFragment getInstance(LiveOtherColumn mLiveOtherColumn, int position) {
        LiveOtherColumnFragment rf = new LiveOtherColumnFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("mLiveOtherColumn", mLiveOtherColumn);
        bundle.putInt("position", position);
        mLiveOtherColumnFragment.add(position, rf);
        rf.setArguments(bundle);
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_othercolumn;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        mLiveOtherTwoColumn = new ArrayList<LiveOtherTwoColumn>();
//        imgPopupLive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mLiveOtherTwoColumn != null) {
//                    Toast.makeText(getContext(), "直播", Toast.LENGTH_LONG).show();
//                    PopupLiveList liveList = new PopupLiveList(getActivity());
//                    liveList.showPopupWindow();
////            PopupLiveList.Builder builder = new PopupLiveList.Builder(getActivity());
////            builder.addList(mLiveOtherTwoColumn);
////            mPopupLiveList = builder.build();
////            mPopupLiveList.showPopupWindow();
////            mPopupLiveList.setOnListPopupItemClickListener(what -> {
////
////
//            });
//                }
//            }
//        });
    }

    @Override
    protected void onEvent() {

    }
//
//    /**
//     * 弹框 筛选列表
//     */
//    @OnClick(img_popup_live)
//    public void popupLiveList() {
//        if (mLiveOtherTwoColumn != null) {
//            Toast.makeText(getContext(), "直播", Toast.LENGTH_LONG).show();
//            PopupLiveList liveList = new PopupLiveList(getActivity());
//            liveList.showPopupWindow();
////            PopupLiveList.Builder builder = new PopupLiveList.Builder(getActivity());
////            builder.addList(mLiveOtherTwoColumn);
////            mPopupLiveList = builder.build();
////            mPopupLiveList.showPopupWindow();
////            mPopupLiveList.setOnListPopupItemClickListener(what -> {
////
////
////            });
//        }
//    }

    @Override
    protected BaseView getViewImp() {
        Bundle arguments = getArguments();
        return mLiveOtherColumnFragment.get(arguments.getInt("position"));
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

    @Override
    protected void lazyFetchData() {
        mLoadView.changeStatusView(ViewStatus.START);
        Bundle arguments = getArguments();
        LiveOtherColumn mLiveOtherColumn = (LiveOtherColumn) arguments.getSerializable("mLiveOtherColumn");
        mPresenter.getPresenterLiveOtherTwoColumn(mLiveOtherColumn.getShort_name());

    }

    @Override
    public void showErrorWithStatus(String msg) {
        mLoadView.changeStatusView(ViewStatus.FAILURE);
    }

    /**
     * 获取二级分类 栏目
     *
     * @param mLiveOtherTwoCloumn
     */
    @Override
    public void getViewLiveOtherTwoColumn(List<LiveOtherTwoColumn> mLiveOtherTwoCloumn) {
        if (mLiveOtherTwoCloumn != null && mLiveOtherTwoCloumn.size() != 0) {
            mLoadView.changeStatusView(ViewStatus.SUCCESS);
        } else {
            mLoadView.changeStatusView(ViewStatus.EMPTY);
        }
        String[] mTitle = new String[mLiveOtherTwoCloumn.size()];
        for (int i = 0; i < mLiveOtherTwoCloumn.size(); i++) {
            mTitle[i] = mLiveOtherTwoCloumn.get(i).getTag_name();
        }
        if (mTitle.length > 1) {
            rlTwoColumnBar.setVisibility(View.VISIBLE);
        }
        this.mLiveOtherTwoColumn.clear();
        this.mLiveOtherTwoColumn.addAll(mLiveOtherTwoCloumn);

        liveTwoColumnViewpager.setOffscreenPageLimit(mTitle.length);
        LiveOtherTwoCloumnAdapter mLiveOtherTwoColumnAdapter = new LiveOtherTwoCloumnAdapter(getChildFragmentManager(), mLiveOtherTwoCloumn, mTitle);
        liveTwoColumnViewpager.setAdapter(mLiveOtherTwoColumnAdapter);
        mLiveOtherTwoColumnAdapter.notifyDataSetChanged();
        twoColumnTabLayout.setViewPager(liveTwoColumnViewpager, mTitle);
    }
}
