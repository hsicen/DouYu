package com.utouu.douyudemo.presenter.home.impl;


import com.utouu.douyudemo.presenter.home.interfaces.HomeContract;

/**
 *  作者：gaoyin
 *  电话：18810474975
 *  邮箱：18810474975@163.com
 *  版本号：1.0
 *  类描述：
 *  备注消息：
 *  修改时间：2016/11/14 下午3:22
 **/
public class HomePresenterImp extends HomeContract.Presenter {
    @Override
    public void message(String msg) {
          mView.getMessge(msg);
    }
    @Override
    public void columnDetail() {

    }


}
