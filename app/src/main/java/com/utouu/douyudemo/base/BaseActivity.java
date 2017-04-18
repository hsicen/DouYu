package com.utouu.douyudemo.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;
import com.utouu.douyudemo.model.ContractProxy;
import com.utouu.douyudemo.view.LoadDataView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 作者：gaoyin
 * 电话：18810474975
 * 邮箱：18810474975@163.com
 * 版本号：1.0
 * 类描述：
 * 备注消息：
 * 修改时间：2016/12/5 下午3:03
 **/
public abstract class BaseActivity<M extends BaseModel, P extends BasePresenter> extends RxAppCompatActivity {
    //    定义Presenter
    protected P mPresenter;
    protected Unbinder unbinder;
    private LoadDataView mLoadView;

    //    获取布局资源文件
    protected abstract int getLayoutId();

//    初始化数据

    protected abstract void onInitView(Bundle bundle);

//    初始化事件Event

    protected abstract void onEvent();

    //   获取抽取View对象
    protected abstract BaseView getView();

    //    获得抽取接口Model对象
    protected Class getModelClazz() {
        return (Class<M>) ContractProxy.getModelClazz(getClass(), 0);
    }

    //    获得抽取接口Presenter对象
    protected Class getPresenterClazz() {
        return (Class<P>) ContractProxy.getPresnterClazz(getClass(), 1);
    }

    protected abstract ViewGroup loadDataViewLayout();
    protected abstract void getLoadView(LoadDataView loadView);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
//            设置布局资源文件
            setContentView(getLayoutId());
//            注解绑定
            unbinder = ButterKnife.bind(this);
            initViewGroup();
            bindMVP();
            onInitView(savedInstanceState);
            onEvent();
        }
    }

    /**
     * 获取presenter 实例
     */
    private void bindMVP() {
        if (getPresenterClazz() != null) {
            mPresenter = getPresenterImpl();
            mPresenter.mContext = this;
            bindVM();
        }
    }

    private <T> T getPresenterImpl() {
        return ContractProxy.getInstance().presenter(getPresenterClazz());
    }

    @Override
    protected void onStart() {
        if (mPresenter == null) {
            bindMVP();
        }
        super.onStart();
    }

    private void bindVM() {
        if (mPresenter != null && !mPresenter.isViewBind() && getModelClazz() != null && getView() != null) {
            ContractProxy.getInstance().bindModel(getModelClazz(), mPresenter);
            ContractProxy.getInstance().bindView(getView(), mPresenter);
            mPresenter.mContext = this;
        }
    }
    /**
     * 嵌入loaddataview
     */
    private void initViewGroup() {
        ViewGroup view = loadDataViewLayout();
        if (null != view) {
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(view);
                this.mLoadView = new LoadDataView(this, view);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
//                params.addRule(RelativeLayout.BELOW, R.id.include_topbar);// TODO: 2017/4/17
                params.setMargins(0, 0, 0, 0);
                viewGroup.addView(this.mLoadView, params);
                getLoadView(this.mLoadView);
            }
        }
    }
    /**
     * activity摧毁
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
        if (mPresenter != null) {
            ContractProxy.getInstance().unbindView(getView(), mPresenter);
            ContractProxy.getInstance().unbindModel(getModelClazz(), mPresenter);
            mPresenter = null;
        }
    }
}
