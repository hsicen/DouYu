package com.utouu.douyudemo.presenter.home.impl;

import com.utouu.douyudemo.model.logic.home.bean.HomeCateList;
import com.utouu.douyudemo.net.callback.RxSubscriber;
import com.utouu.douyudemo.net.exception.ResponeThrowable;
import com.utouu.douyudemo.presenter.home.interfaces.HomeCateListContract;

import java.util.List;

/**
 * Create by 黄思程 on 2017/4/13  17:12
 * Function：
 * Desc：首页数据加载
 */
public class HomeCateListPresenterImp extends HomeCateListContract.Presenter {

    @Override
    public void getHomeCateList() {
             addSubscribe(mModel.getHomeCateList(mContext).subscribe(new RxSubscriber<List<HomeCateList>>() {
                 @Override
                 public void onSuccess(List<HomeCateList> homeCateListList) {
                    mView.getHomeAllList(homeCateListList);
                 }
                 @Override
                 protected void onError(ResponeThrowable ex) {
                   mView.showErrorWithStatus(ex.message);
                 }
             }));
    }
}
