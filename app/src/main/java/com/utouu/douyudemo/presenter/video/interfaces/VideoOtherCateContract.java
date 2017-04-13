package com.utouu.douyudemo.presenter.video.interfaces;

import android.content.Context;
import com.utouu.douyudemo.base.BaseModel;
import com.utouu.douyudemo.base.BasePresenter;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.video.bean.VideoReClassify;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public interface VideoOtherCateContract {
    interface View extends BaseView {
        void getViewVideoOtherCate(List<VideoReClassify> cateLists);
    }

    interface Model extends BaseModel {
        Observable<List<VideoReClassify>> getModelVideoAllCate(Context context, String cId);
    }

    abstract class Presenter extends BasePresenter<VideoOtherCateContract.View, VideoOtherCateContract.Model> {

        public abstract void getPresenterVideoOtherCate(String cid);

    }
}
