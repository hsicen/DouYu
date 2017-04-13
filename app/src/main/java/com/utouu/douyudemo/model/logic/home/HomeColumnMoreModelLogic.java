package com.utouu.douyudemo.model.logic.home;

import android.content.Context;

import com.utouu.douyudemo.api.home.HomeApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.home.bean.HomeColumnMoreTwoCate;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.home.interfaces.HomeColumnMoreListContract;

import java.util.List;

import rx.Observable;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/3/14 下午3:33
 **/
public class HomeColumnMoreModelLogic implements HomeColumnMoreListContract.Model {

    /**
     *  栏目 更多 二级分类列表
     * @param context
     * @param cate_id
     * @return
     */
    @Override
    public Observable<List<HomeColumnMoreTwoCate>> getModelHomeColumnMoreTwoCate(Context context, String cate_id) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeColumnMoreCate(ParamsMapUtils.getColumnMoreCate(cate_id))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeColumnMoreTwoCate>>());
    }
}
