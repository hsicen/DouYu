package com.utouu.douyudemo.model.logic.video;

import android.content.Context;

import com.utouu.douyudemo.api.video.VideoApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.video.bean.VideoReClassify;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.video.interfaces.VideoOtherCateContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/9 0009.
 */

public class VideoOtherCateListLogic implements VideoOtherCateContract.Model{
    @Override
    public Observable<List<VideoReClassify>> getModelVideoAllCate(Context context ,String  cid) {

        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoReCateList(ParamsMapUtils.getVideoOtherTwoColumn(cid))

//               进行预处理
                .compose(new DefaultTransformer<List<VideoReClassify>>());
    }
}
