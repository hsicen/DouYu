package com.utouu.douyudemo.presenter.live.interfaces;


import android.content.Context;

import com.utouu.douyudemo.base.BaseModel;
import com.utouu.douyudemo.base.BasePresenter;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.live.bean.LiveOtherTwoColumn;

import java.util.List;

import rx.Observable;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：   直播分类
 *  备注消息：
 *  修改时间：2016/11/14 下午3:29
 **/
public interface LiveOtherTwoColumnContract {
      interface View extends BaseView {

            void getViewLiveOtherTwoColumn(List<LiveOtherTwoColumn> mLiveOtherTwoCloumn);
      }
      interface Model extends BaseModel {
            Observable<List<LiveOtherTwoColumn>> getModelLiveOtherTwoColumn(Context context, String mCloumnName);
      }
      abstract class Presenter extends BasePresenter<View,Model> {

            public  abstract void getPresenterLiveOtherTwoColumn(String mCloumnName);

      }

}
