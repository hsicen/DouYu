package com.utouu.douyudemo.presenter.home.impl;

import com.utouu.douyudemo.model.logic.home.bean.HomeColumnMoreOtherList;
import com.utouu.douyudemo.net.callback.RxSubscriber;
import com.utouu.douyudemo.net.exception.ResponeThrowable;
import com.utouu.douyudemo.presenter.home.interfaces.HomeColumnMoreOtherListContract;

import java.util.List;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2017/3/14 下午3:31
 **/
public class HomeColumnMoreOtherListPresenterImp extends HomeColumnMoreOtherListContract.Presenter {
    @Override
    public void getPresenterColumnMoreOtherList(String cate_id, int offset, int limint) {
        addSubscribe(mModel.getModelHomeColumnMoreOtherList(mContext, cate_id, offset, limint).subscribe(new RxSubscriber<List<HomeColumnMoreOtherList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreOtherList> mHomeColumnMoreOtherList) {
                mView.getViewHomeColumnOtherList(mHomeColumnMoreOtherList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }

    @Override
    public void getPresenterColumnMoreOtherListLoadMore(String cate_id, int offset, int limit) {
        addSubscribe(mModel.getModelHomeColumnMoreOtherList(mContext, cate_id, offset, limit).subscribe(new RxSubscriber<List<HomeColumnMoreOtherList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreOtherList> mHomeColumnMoreAllList) {
                mView.getViewHomeColumnOtherListLoadMore(mHomeColumnMoreAllList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
