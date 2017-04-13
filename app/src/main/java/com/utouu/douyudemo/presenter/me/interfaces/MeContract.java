package com.utouu.douyudemo.presenter.me.interfaces;

import android.content.Context;

import com.utouu.douyudemo.base.BaseModel;
import com.utouu.douyudemo.base.BasePresenter;
import com.utouu.douyudemo.base.BaseView;
import com.utouu.douyudemo.model.logic.me.bean.PersonInfoBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/3/17 0017.
 */

public interface MeContract {
    interface View extends BaseView{
        void getViewPersonInfo(PersonInfoBean personInfoBean);
        void showLoginPopWindow();

    }
    interface Model extends BaseModel{
        Observable<PersonInfoBean> getModelPersonInfo(Context context ,String userName, String passWord);
    }

    abstract class Presenter extends BasePresenter<View,Model>{
        public abstract  void Login();
    }
}
