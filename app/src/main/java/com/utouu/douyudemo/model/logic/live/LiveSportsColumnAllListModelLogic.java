package com.utouu.douyudemo.model.logic.live;

import android.content.Context;

import com.utouu.douyudemo.api.live.LiveApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.live.bean.LiveSportsAllList;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.live.interfaces.LiveSportsColumnAllListContract;

import java.util.List;

import rx.Observable;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述： 全部直播
 *  备注消息：
 *  修改时间：2017/2/7 下午5:35
 **/
public class LiveSportsColumnAllListModelLogic implements LiveSportsColumnAllListContract.Model {

    /**
     *   获取全部视频
     * @param context
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Observable<List<LiveSportsAllList>> getModelLiveSportsColumnAllList(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(LiveApi.class)
                .getLiveSportsAllList(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<LiveSportsAllList>>());
    }
}
