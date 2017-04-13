package com.utouu.douyudemo.model.logic.me;

import android.content.Context;

import com.utouu.douyudemo.api.me.MeApi;
import com.utouu.douyudemo.model.ParamsMapUtils;
import com.utouu.douyudemo.model.logic.me.bean.PersonInfoBean;
import com.utouu.douyudemo.net.http.HttpUtils;
import com.utouu.douyudemo.net.transformer.DefaultTransformer;
import com.utouu.douyudemo.presenter.me.interfaces.MeContract;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public class MeModelLogic implements MeContract.Model {
    @Override
    public Observable<PersonInfoBean> getModelPersonInfo(Context context, String userName, String passWord) {
        return HttpUtils.getInstance(context)
                .setLoadDiskCache(false)
                .getRetofitClinet()
                .builder(MeApi.class)
                .getPersonInfos(ParamsMapUtils.getPersonInfo(userName,passWord))
//               进行预处理
                .compose(new DefaultTransformer<PersonInfoBean>());
    }
}
