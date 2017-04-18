package com.utouu.douyudemo.view.live.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.live.LiveSportsColumnAllListModelLogic;
import com.utouu.douyudemo.model.logic.live.bean.LiveSportsAllList;
import com.utouu.douyudemo.presenter.live.impl.LiveSportsColumnAllListPresenterImp;
import com.utouu.douyudemo.presenter.live.interfaces.LiveSportsColumnAllListContract;
import com.utouu.douyudemo.ui.refreshview.XRefreshView;
import com.utouu.douyudemo.utils.ViewStatus;
import com.utouu.douyudemo.view.LoadDataView;
import com.utouu.douyudemo.view.live.adapter.LiveSportsColumnListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * 作者：${User}
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：
 * 类描述：
 * 修改时间：${DATA}1634
 */

public class LiveSportsColumnFragment extends BaseFragment<LiveSportsColumnAllListModelLogic, LiveSportsColumnAllListPresenterImp> implements LiveSportsColumnAllListContract.View {

    /**
     * 分页加载
     */
//    起始位置
    private int offset = 0;
    //    每页加载数量
    private int limit = 20;
    @BindView(R.id.livesports_content_recyclerview)
    RecyclerView livesSportsContentRecyclerView;
    @BindView(R.id.rtefresh_content)
    XRefreshView refreshContent;
    private LiveSportsColumnListAdapter mLiveSportsColumnListAdapter;
    private LoadDataView mLoadView;

    public static LiveSportsColumnFragment getInstance() {
        LiveSportsColumnFragment rf = new LiveSportsColumnFragment();
        return rf;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_sports;
    }

    @Override
    protected void onInitView(Bundle bundle) {
        setXrefViewConfig();
        livesSportsContentRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        mLiveSportsColumnListAdapter = new LiveSportsColumnListAdapter(getActivity());
        livesSportsContentRecyclerView.setAdapter(mLiveSportsColumnListAdapter);
        refreshContent.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
//                延迟500毫秒, 原因 用户体验好 !!!
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                }, 500);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                offset += limit;
                loadMore(offset, limit);
            }
        });
    }

    private void loadMore(int offset, int limit) {
        mPresenter.getPresenterLiveSportsColumnAllListLoadMore(offset, limit);
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {

//       重新开始计算
        offset = 0;
        mPresenter.getPresenterLiveSportsColumnAllList(0, limit);
    }

    @Override
    protected void onEvent() {

    }

    /**
     * 配置XRefreshView
     */
    protected void setXrefViewConfig() {
        refreshContent.setPinnedTime(2000);
        refreshContent.setPullLoadEnable(true);
        refreshContent.setPullRefreshEnable(true);
        refreshContent.setMoveForHorizontal(true);
        refreshContent.setPinnedContent(true);
//        滚动到底部 自动加载数据
        refreshContent.setSilenceLoadMore();

    }

    @Override
    protected BaseView getViewImp() {
        return this;
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
        refresh();
    }

    @Override
    public void getViewLiveSportsColumnAllListColumn(List<LiveSportsAllList> mLiveAllList) {
        if (refreshContent != null) {
            refreshContent.stopRefresh();
        }
        if (mLiveAllList!=null) {
            mLoadView.changeStatusView(ViewStatus.SUCCESS);
        }else {
            mLoadView.changeStatusView(ViewStatus.EMPTY);
        }
        mLiveSportsColumnListAdapter.getLiveLiveSportsColumnList(mLiveAllList);
    }

    @Override
    public void getViewLiveSportsColumnAllListLoadMore(List<LiveSportsAllList> mLiveAllList) {
        if (mLiveAllList!=null) {
            mLoadView.changeStatusView(ViewStatus.SUCCESS);
        }else {
            mLoadView.changeStatusView(ViewStatus.EMPTY);
        }
        if (refreshContent != null) {
            refreshContent.stopLoadMore();
        }
        mLiveSportsColumnListAdapter.getLiveSportsColumnListLoadMore(mLiveAllList);
    }

    @Override
    public void showErrorWithStatus(String msg) {
        if (refreshContent != null) {
            refreshContent.stopRefresh(false);
            refreshContent.stopLoadMore(false);
        }
        mLoadView.changeStatusView(ViewStatus.FAILURE);
    }
}
