package com.utouu.douyudemo.view.live.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.utouu.douyudemo.R;
import com.utouu.douyudemo.base.BaseFragment;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.live.LiveAllListModelLogic;
import com.utouu.douyudemo.model.logic.live.bean.LiveAllList;
import com.utouu.douyudemo.presenter.live.impl.LiveAllListPresenterImp;
import com.utouu.douyudemo.presenter.live.interfaces.LiveAllListContract;
import com.utouu.douyudemo.ui.refreshview.XRefreshView;
import com.utouu.douyudemo.utils.ViewStatus;
import com.utouu.douyudemo.view.LoadDataView;
import com.utouu.douyudemo.view.live.adapter.LiveAllListAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Create by 李俊鹏 on 2017/4/14 15:47
 * Function：
 * Desc：直播模块所有页面
 */
public class LiveAllColumnFragment extends BaseFragment<LiveAllListModelLogic, LiveAllListPresenterImp> implements LiveAllListContract.View {

    /**
     * 分页加载
     */
//    起始位置
    private int offset = 0;
    //    每页加载数量
    private int limit = 20;
    @BindView(R.id.rtefresh_content)
    XRefreshView refreshContent;
    @BindView(R.id.livealllist_content_recyclerview)
    RecyclerView liveAllListContentRecyclerView;
    private LiveAllListAdapter mLiveAllListAdapter;
    private LoadDataView mLoadView;

    public static LiveAllColumnFragment getInstance() {
        return new LiveAllColumnFragment();
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_live_allcolumn;
    }

    @Override
    protected void onInitView(Bundle bundle) {

        setXrefViewConfig();
        liveAllListContentRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        mLiveAllListAdapter = new LiveAllListAdapter(getActivity());
        liveAllListContentRecyclerView.setAdapter(mLiveAllListAdapter);
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

    @Override
    protected void onEvent() {

    }

    private void loadMore(int offset, int limit) {
        mPresenter.getPresenterListAllListLoadMore(offset, limit);
    }

    /**
     * 刷新网络数据
     */
    private void refresh() {
//       重新开始计算
        offset = 0;
        mPresenter.getPresenterListAllList(0, 20);
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
    }

    @Override
    protected void lazyFetchData() {
        mLoadView.changeStatusView(ViewStatus.START);
        refresh();
    }

    @Override
    public void getViewLiveAllListColumn(List<LiveAllList> mLiveAllList) {
        if (refreshContent != null) {
            refreshContent.stopRefresh();
        }
        if (mLiveAllList != null && mLiveAllList.size() != 0) {
            mLoadView.changeStatusView(ViewStatus.SUCCESS);
        } else {
            mLoadView.changeStatusView(ViewStatus.EMPTY);
        }

        mLiveAllListAdapter.getLiveAllList(mLiveAllList);
    }

    @Override
    public void getViewLiveAllListLoadMore(List<LiveAllList> mLiveAllList) {
        if (refreshContent != null) {
            refreshContent.stopLoadMore();
        }
        if (mLiveAllList != null && mLiveAllList.size() != 0) {
            mLoadView.changeStatusView(ViewStatus.SUCCESS);
        } else {
            mLoadView.changeStatusView(ViewStatus.EMPTY);
        }
        mLiveAllListAdapter.getLiveAllListLoadMore(mLiveAllList);
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
