package com.utouu.douyudemo.view.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bigkoo.svprogresshud.SVProgressHUD;
import com.facebook.drawee.view.SimpleDraweeView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.home.HomeRecommendModelLogic;
import com.utouu.douyudemo.model.logic.home.bean.HomeCarousel;
import com.utouu.douyudemo.model.logic.home.bean.HomeFaceScoreColumn;
import com.utouu.douyudemo.model.logic.home.bean.HomeHotColumn;
import com.utouu.douyudemo.model.logic.home.bean.HomeRecommendHotCate;
import com.utouu.douyudemo.model.logic.home.bean.TabEntity;
import com.utouu.douyudemo.presenter.home.impl.HomeRecommendPresenterImp;
import com.utouu.douyudemo.presenter.home.interfaces.HomeRecommendContract;
import com.utouu.douyudemo.ui.refreshview.XRefreshView;
import com.utouu.douyudemo.utils.ToastUtils;
import com.utouu.douyudemo.view.common.activity.PcLiveVideoActivity;
import com.utouu.douyudemo.view.home.adapter.HomeCarouselAdapter;
import com.utouu.douyudemo.view.home.adapter.HomeRecommendAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Create by 黄思程 on 2017/4/17  9:46
 * Function：
 * Desc：首页 -- 推荐页面
 */
public class RecommendHomeFragment extends BaseFragment<HomeRecommendModelLogic, HomeRecommendPresenterImp>
        implements HomeRecommendContract.View, BGABanner.Delegate<SimpleDraweeView, String>,OnTabSelectListener {

    private static final String[] mTitles = {"排行榜", "消息", "活动", "全部直播"};
    private static final int[] bannerTabIcon = {
            R.drawable.icon_reco_rank, R.drawable.icon_reco_msg,
            R.drawable.ic_reco_active, R.drawable.icon_reco_all_live};

    @BindView(R.id.rtefresh_content) XRefreshView rtefreshContent;
    @BindView(R.id.recommend_content_recyclerview) RecyclerView recommed_recyclerview;

    SVProgressHUD svProgressHUD;
    private HomeRecommendAdapter adapter;
    private BGABanner recommed_banner;
    private CommonTabLayout bannerTab;

    private List<HomeCarousel> mHomeCarousel;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    public static RecommendHomeFragment getInstance() {
        Bundle args = new Bundle();
        RecommendHomeFragment fragment = new RecommendHomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    protected void onInitView(Bundle bundle) {

        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], bannerTabIcon[i], bannerTabIcon[i]));
        }

        svProgressHUD = new SVProgressHUD(getActivity());
        recommed_recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new HomeRecommendAdapter(getContext());
        mHomeCarousel=new ArrayList<>();
        pool.setMaxRecycledViews(adapter.getItemViewType(0), 500);
        recommed_recyclerview.setRecycledViewPool(pool);
        recommed_recyclerview.setAdapter(adapter);
        HomeCarouselAdapter mRecommedBannerAdapter = new HomeCarouselAdapter();

        View headView = adapter.setHeaderView(R.layout.item_home_recommend_banner, recommed_recyclerview);
        recommed_banner=(BGABanner) headView.findViewById(R.id.recommed_banner);
        bannerTab = (CommonTabLayout) headView.findViewById(R.id.bannerTab);
        bannerTab.setTabData(mTabEntities);

        bannerTab.setOnTabSelectListener(this);
        recommed_banner.setDelegate(this);
        recommed_banner.setAdapter(mRecommedBannerAdapter);
        setXrefeshViewConfig();
    }
    final RecyclerView.RecycledViewPool pool = new RecyclerView.RecycledViewPool() {
        @Override
        public void putRecycledView(RecyclerView.ViewHolder scrap) {
            super.putRecycledView(scrap);
        }

        @Override
        public RecyclerView.ViewHolder getRecycledView(int viewType) {
            final RecyclerView.ViewHolder recycledView = super.getRecycledView(viewType);
            return recycledView;
        }
    };

    /**
     * 配置XRefreshView
     */
    protected void setXrefeshViewConfig() {
        rtefreshContent.setPinnedTime(1000);
        rtefreshContent.setPullLoadEnable(false);
        rtefreshContent.setPullRefreshEnable(true);
        rtefreshContent.setMoveForHorizontal(true);
        rtefreshContent.setPinnedContent(true);
    }

    @Override
    protected void onEvent() {
        rtefreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(() -> refresh(), 500);
            }
        });
    }

    @Override
    protected BaseView getViewImp() {
        return this;
    }

    /**
     * 轮播图
     *
     * @param mHomeCarousel
     */
    @Override
    public void getViewCarousel(List<HomeCarousel> mHomeCarousel) {
        if (rtefreshContent != null) {
            rtefreshContent.stopRefresh();
        }
        this.mHomeCarousel.clear();
        this.mHomeCarousel.addAll(mHomeCarousel);
        ArrayList<String> pic_url = new ArrayList<>();
        for (int i = 0; i < mHomeCarousel.size(); i++) {
            pic_url.add(mHomeCarousel.get(i).getPic_url());
        }
        if (recommed_banner != null && pic_url.size() > 0) {

            recommed_banner.setData(R.layout.item_image_carousel, pic_url, null);
        }
        adapter.notifyDataSetChanged();
    }

    //最热
    @Override
    public void getViewHotColumn(List<HomeHotColumn> mHomeHotColumn) {
        adapter.getHomeHotColumn(mHomeHotColumn);
    }

    //颜值
    @Override
    public void getViewFaceScoreColumn(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
        adapter.getFaceScoreColmun(homeFaceScoreColumns);
    }

    //热门
    @Override
    public void getViewHotCate(List<HomeRecommendHotCate> homeRecommendHotCates) {
//        去掉颜值栏目
        homeRecommendHotCates.remove(1);
        adapter.getAllColumn(homeRecommendHotCates);
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {
        mPresenter.getPresenterCarousel();
        mPresenter.getPresenterHotColumn();
        mPresenter.getPresenterFaceScoreColumn(0, 4);
        mPresenter.getPresenterHotCate();
    }

    @Override
    protected void lazyFetchData() {
        refresh();
    }

    @Override
    public void showErrorWithStatus(String msg) {
        svProgressHUD.showErrorWithStatus(msg);
        rtefreshContent.stopRefresh(false);
    }

    @Override
    public void onBannerItemClick(BGABanner banner, SimpleDraweeView itemView, String model, int position) {
        Intent intent = new Intent(getActivity(), PcLiveVideoActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString("Room_id",mHomeCarousel.get(position).getRoom().getRoom_id());
        intent.putExtras(bundle);
        getActivity().startActivity(intent);
    }

    @Override
    public void onTabSelect(int position) {
        int currentTab = bannerTab.getCurrentTab();
        ToastUtils.showShort(mContext,"正在开发"+mTitles[currentTab]+"页面");
    }

    @Override
    public void onTabReselect(int position) {
        int currentTab = bannerTab.getCurrentTab();
        ToastUtils.showShort(mContext,"不要着急，正在开发"+mTitles[currentTab]+"页面");
    }
}
