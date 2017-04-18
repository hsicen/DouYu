package com.utouu.douyudemo.model.logic.home;

import android.content.Context;

import com.utouu.douyudemo.api.home.HomeApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.home.bean.HomeRecommendHotCate;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.home.interfaces.HomeCateContract;

import java.util.List;

import rx.Observable;

/**
 * 作者：${User}
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：
 * 类描述：
 * 修改时间：${DATA}1605
 */

public class HomeCateModelLogic  implements HomeCateContract.Model {

    @Override
    public Observable<List<HomeRecommendHotCate>> getHomeCate(Context context, String identification) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(true)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getHomeCate(ParamsMapUtils.getHomeCate(identification))
//               进行预处理
                .compose(new DefaultTransformer<>());
    }
}
