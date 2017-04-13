package com.utouu.douyudemo.presenter.home.impl;

import com.utouu.douyudemo.model.logic.home.bean.HomeFaceScoreColumn;
import com.utouu.douyudemo.net.callback.RxSubscriber;
import com.utouu.douyudemo.net.exception.ResponeThrowable;
import com.utouu.douyudemo.presenter.home.interfaces.HomeFaceScoreContract;

import java.util.List;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/1/18 下午3:35
 **/
public class HomeFaceScorePresenterImp extends HomeFaceScoreContract.Presenter {
    /**
     *   刷新数据
     * @param offset
     * @param limit
     */
    @Override
    public void getPresenterFaceScoreColumn(int offset, int limit) {
        addSubscribe(mModel.getModelFaceScoreColumn(mContext,offset,limit).subscribe(new RxSubscriber<List<HomeFaceScoreColumn>>() {
            @Override
            public void onSuccess(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
                mView.getViewFaceScoreColumn(homeFaceScoreColumns);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }

    /**
     *  加载更多
     * @param offset
     * @param limit
     */
    @Override
    public void getPresenterFaceScoreLoadMore(int offset, int limit) {
        addSubscribe(mModel.getModelFaceScoreColumn(mContext,offset,limit).subscribe(new RxSubscriber<List<HomeFaceScoreColumn>>() {
            @Override
            public void onSuccess(List<HomeFaceScoreColumn> homeFaceScoreColumns) {
                mView.getViewFaceScoreColumnLoadMore(homeFaceScoreColumns);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
