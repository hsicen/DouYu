package com.utouu.douyudemo.presenter.video.impl;

import com.utouu.douyudemo.model.logic.video.bean.VideoReClassify;
import com.utouu.douyudemo.net.callback.RxSubscriber;
import com.utouu.douyudemo.net.exception.ResponeThrowable;
import com.utouu.douyudemo.presenter.video.interfaces.VideoOtherCateContract;

import java.util.List;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class VideoOtherCatePresenterImpl extends VideoOtherCateContract.Presenter{
    @Override
    public void getPresenterVideoOtherCate(String cid) {
        addSubscribe(mModel.getModelVideoAllCate(mContext,cid).subscribe(new RxSubscriber<List<VideoReClassify>>() {
            @Override
            public void onSuccess(List<VideoReClassify> videoCateListList) {
                mView.getViewVideoOtherCate(videoCateListList);
            }
            @Override
            protected void onError(ResponeThrowable ex) {
                mView.showErrorWithStatus(ex.message);
            }
        }));
    }
}
