package com.utouu.douyudemo.presenter.live.interfaces;


import android.content.Context;

import com.utouu.douyudemo.base.BaseModel;
import com.utouu.douyudemo.base.BasePresenter;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.live.bean.LiveOtherColumn;

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
public interface LiveOtherColumnContract {
      interface View extends BaseView {
         void   getViewLiveOtherColumn(List<LiveOtherColumn> mLiveOtherColumns);
      }
      interface Model extends BaseModel {
            Observable<List<LiveOtherColumn>> getModelLiveOtherColumn(Context context);
      }
      abstract class Presenter extends BasePresenter<View,Model> {
//            获取直播其他栏目分类
            public abstract void  getPresenterLiveOtherColumn();

      }

}
