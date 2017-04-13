package com.utouu.douyudemo.model.logic.video;

import android.content.Context;

import com.utouu.douyudemo.api.video.VideoApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.video.bean.VideoCateList;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.video.interfaces.VideoAllCateListContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/8 0008.
 */

public class VideoCateListLogic implements VideoAllCateListContract.Model {
    @Override
    public Observable<List<VideoCateList>> getModelVideoAllCate(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoCateList(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoCateList>>());

    }
}
