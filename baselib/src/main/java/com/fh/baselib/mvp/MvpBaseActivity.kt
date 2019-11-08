package com.fh.baselib.mvp

import android.os.Bundle
import com.fh.baselib.base.BaseActivity
import com.fh.baselib.utils.CreatUtil

abstract class MvpBaseActivity<V: BaseView,P : BasePresenter<V> >: BaseActivity(){
    var mPresenter: P? =null//可空类型


    override fun onCreate(savedInstanceState: Bundle?) {
//        mPresenter = CreatUtil.getT(this,1)
        super.onCreate(savedInstanceState)
//        L.e( "mPresenter: $mPresenter")
//        mPresenter?.attachView(this as V)
//        mPresenter?.bindContext(mContext)
//        mPresenter?.bindToLife(this.bindToLifecycle())

    }

    override fun initView() {
        mPresenter = CreatUtil.getT(this,1)
        mPresenter?.attachView(this as V)
        mPresenter?.bindContext(mContext)
        mPresenter?.bindToLife(this.bindToLifecycle())
    }

    override fun initData() {
    }

    override fun onDestroy() {
        mPresenter?.detachView()
        mPresenter?.unBindToLife()
        mPresenter?.unBindContext()
        mPresenter = null
        super.onDestroy()

    }
}
