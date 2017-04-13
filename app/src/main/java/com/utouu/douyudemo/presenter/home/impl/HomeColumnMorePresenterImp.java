package com.utouu.douyudemo.presenter.home.impl;

import com.utouu.douyudemo.model.logic.home.bean.HomeColumnMoreTwoCate;
import com.utouu.douyudemo.net.callback.RxSubscriber;
import com.utouu.douyudemo.net.exception.ResponeThrowable;
import com.utouu.douyudemo.presenter.home.interfaces.HomeColumnMoreListContract;

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
public class HomeColumnMorePresenterImp extends HomeColumnMoreListContract.Presenter {
    @Override
    public void getPresenterHomeColumnMoreTwoCate(String cate_id) {
        addSubscribe(mModel.getModelHomeColumnMoreTwoCate(mContext, cate_id).subscribe(new RxSubscriber<List<HomeColumnMoreTwoCate>>() {
            @Override
            public void onSuccess(List<HomeColumnMoreTwoCate> mHomeColumnMoreTwoCate) {
                mView.getViewHomeColumnMoreTwoCate(mHomeColumnMoreTwoCate);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));

    }
}
