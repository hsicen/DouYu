package com.utouu.douyudemo.api.common;

import com.utouu.douyudemo.model.logic.common.bean.LiveVideoInfo;
import com.utouu.douyudemo.net.response.HttpResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

import static com.utouu.douyudemo.api.NetWorkApi.getLiveVideo;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2017/2/10 下午7:15
 **/
public interface CommonApi {
    /**
     *  直播播放页
     * @return
     */
    @GET(getLiveVideo+"{room_id}")
    Call<HttpResponse<LiveVideoInfo>> getLiveVideoInfo(@Path("room_id") String room_id, @QueryMap Map<String, String> params);

}
