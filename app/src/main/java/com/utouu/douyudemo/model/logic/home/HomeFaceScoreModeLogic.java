package com.utouu.douyudemo.model.logic.home;

import android.content.Context;

import com.utouu.douyudemo.api.home.HomeApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.home.bean.HomeFaceScoreColumn;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.home.interfaces.HomeFaceScoreContract;

import java.util.List;

import rx.Observable;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/1/19 下午3:38
 **/
public class HomeFaceScoreModeLogic implements HomeFaceScoreContract.Model {
    /**
     *    获取颜值栏目
     * @param context
     * @param offset
     * @param limit
     * @return
     */
    @Override
    public Observable<List<HomeFaceScoreColumn>> getModelFaceScoreColumn(Context context, int offset, int limit) {
        return HttpUtils.getInstance(context)
                .getRetofitClinet()
                .builder(HomeApi.class)
                .getFaceScoreColumn(ParamsMapUtils.getHomeFaceScoreColumn(offset,limit))
//               进行预处理
                .compose(new DefaultTransformer<List<HomeFaceScoreColumn>>());
    }
}
