package com.utouu.douyudemo.model.logic.video;

import android.content.Context;

import com.utouu.douyudemo.api.video.VideoApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.video.bean.VideoHotAuthorColumn;
import com.utouu.douyudemo.model.logic.video.bean.VideoHotColumn;
import com.utouu.douyudemo.model.logic.video.bean.VideoRecommendHotCate;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.video.interfaces.VideoRerecommendContract;

import java.util.List;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/7 0007.
 */

public class VideoRecommendModelLogic implements VideoRerecommendContract.Model {
    @Override
    public Observable<List<VideoHotColumn>> getModelVideoHotColumn(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoHotColumn(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoHotColumn>>());

    }

    @Override
    public Observable<List<VideoHotAuthorColumn>> getModelVideoHotAuthorColumn(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoHotAuther(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoHotAuthorColumn>>());
    }

    @Override
    public Observable<List<VideoRecommendHotCate>> getModelVideoHotCate(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .setBaseUrl(" http://apiv2.douyucdn.cn")
                .builder(VideoApi.class)
                .getVideoHotCate(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<VideoRecommendHotCate>>());
    }
}
