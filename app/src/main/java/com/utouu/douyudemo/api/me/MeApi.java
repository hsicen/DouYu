package com.utouu.douyudemo.api.me;

import com.utouu.douyudemo.model.logic.me.bean.PersonInfoBean;
import com.utouu.douyudemo.net.response.HttpResponse;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

import static com.utouu.douyudemo.api.NetWorkApi.getPersonInfo;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public interface MeApi {
    /**
     * 推荐---最热
     *
     * @return
     */
    @GET(getPersonInfo)
    Observable<HttpResponse<PersonInfoBean>> getPersonInfos(@QueryMap Map<String, String> params) ;
}
