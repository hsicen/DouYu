package com.utouu.douyudemo.model.logic.home;

import android.content.Context;

import com.utouu.douyudemo.api.home.HomeApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.home.bean.HomeColumnMoreOtherList;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.home.interfaces.HomeColumnMoreOtherListContract;

import java.util.List;

import rx.Observable;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2017/3/14 下午3:33
 **/
public class HomeColumnMoreOtherListModelLogic implements HomeColumnMoreOtherListContract.Model {

    /**
     * 全部直播列表
     *
     * @param context
     * @param cate_id
     * @return
     */
    @Override
    public Observable<List<HomeColumnMoreOtherList>> getModelHomeColumnMoreOtherList(Context context, String cate_id, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreOtherList(ParamsMapUtils.getHomeColumnMoreOtherList(cate_id, offset, limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreOtherList>>());
    }
}
