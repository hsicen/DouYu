package com.utouu.douyudemo.presenter.home.impl;

import com.utouu.douyudemo.model.logic.home.bean.HomeColumnMoreAllList;
import com.utouu.douyudemo.net.callback.RxSubscriber;
import com.utouu.douyudemo.net.exception.ResponeThrowable;
import com.utouu.douyudemo.presenter.home.interfaces.HomeColumnMoreAllListContract;

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
public class HomeColumnMoreAllListPresenterImp extends HomeColumnMoreAllListContract.Presenter {
    @Override
    public void getPresenterColumnMoreAllList(String cate_id, int offset, int limint) {
        addSubscribe(mModel.getModelHomeColumnMoreAllList(mContext, cate_id, offset, limint).subscribe(new RxSubscriber<List<HomeColumnMoreAllList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreAllList> mHomeColumnMoreAllList) {
                mView.getViewHomeColumnAllList(mHomeColumnMoreAllList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }

    @Override
    public void getPresenterColumnMoreAllListLoadMore(String cate_id, int offset, int limit) {
        addSubscribe(mModel.getModelHomeColumnMoreAllList(mContext, cate_id, offset, limit).subscribe(new RxSubscriber<List<HomeColumnMoreAllList>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreAllList> mHomeColumnMoreAllList) {
                mView.getViewHomeColumnAllListLoadMore(mHomeColumnMoreAllList);
            }

            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
