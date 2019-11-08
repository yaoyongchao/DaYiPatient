package com.fh.baselib.mvp

import android.content.Context
import com.trello.rxlifecycle3.LifecycleTransformer
import java.lang.ref.WeakReference







abstract class BasePresenter<V: BaseView> {
    private var mView: WeakReference<V>? = null
    protected var mContext: Context ?= null
    public var lifecycleTransformer: LifecycleTransformer<V> ?= null

    /**
     * 绑定view，一般在初始化中调用该方法
     */
    fun attachView(view: V) {
//        if (view is BaseFragment) {
//            this.mContext = (view as BaseFragment).mContext
//            LogUtil.e("mView is Fragment")
//        } else {
//            LogUtil.e("mView is Activity")
//            this.mContext = (view as BaseActivity).mContext
//        }
        this.mView = WeakReference(view)


    }

    fun bindContext(context: Context) {
        this.mContext = context
    }

    /**
     * View是否绑定
     *
     * @return
     */
    fun isBind(): Boolean {
        return mView != null && mView!!.get() != null
    }

    /**
     * 解除绑定view，一般在onDestroy中调用
     */
    fun detachView() {
        if (isBind()) {
            mView!!.clear()
            mView = null
        }

    }

    fun unBindContext() {
        if (mContext != null)
            mContext == null
    }

    fun getView(): V? {
        return if (isBind()) mView!!.get() else null
    }


    fun bindToLife(ltf: LifecycleTransformer<V>) {
        lifecycleTransformer = ltf
    }

    fun unBindToLife() {
        lifecycleTransformer = null
    }

    fun <T> getBindToLife(): LifecycleTransformer<T> {
        return lifecycleTransformer as LifecycleTransformer<T>
    }
}