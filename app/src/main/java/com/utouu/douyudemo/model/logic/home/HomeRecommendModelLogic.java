package com.utouu.douyudemo.model.logic.home;

import android.content.Context;

import com.utouu.douyudemo.api.home.HomeApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.home.bean.HomeCarousel;
import com.utouu.douyudemo.model.logic.home.bean.HomeFaceScoreColumn;
import com.utouu.douyudemo.model.logic.home.bean.HomeHotColumn;
import com.utouu.douyudemo.model.logic.home.bean.HomeRecommendHotCate;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.home.interfaces.HomeRecommendContract;

import java.util.List;

import rx.Observable;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述： 首页 推荐页 业务逻辑
 * 备注消息：
 * 修改时间：2016/12/15 下午3:52
 **/
public class HomeRecommendModelLogic implements HomeRecommendContract.Model {
    /**
     * 获取首页轮播图
     *
     * @param context
     * @return
     */
    @Override
    public Observable<List<HomeCarousel>> getModelCarousel(Context context) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(false)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getCarousel(ParamsMapUtils.getHomeCarousel())
//               进行预处理
                .compose(new DefaultTransformer<List<HomeCarousel>>());
    }

    /**
     * 首页 ---推荐--最热
     *
     * @param context
     * @return
     */
    @Override
    public Observable<List<HomeHotColumn>> getModelHotColumn(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHotColumn(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<HomeHotColumn>>());
    }

    /**
     * 首页---推荐---颜值
     *
     * @param context
     * @return
     */
    @Override
    public Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(Context context,int offset,int limit  ) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getFaceScoreColumn(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeFaceScoreColumn>>());
    }

    /**
     *    首页---推荐---热门种类
     * @param context
     * @return
     */
    @Override
    public Observable<List<HomeRecommendHotCate>> getModelHotCate(Context context) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHotCate(ParamsMapUtils.getDefaultParams())
//               进行预处理
                .compose(new DefaultTransformer<List<HomeRecommendHotCate>>());
    }
}
