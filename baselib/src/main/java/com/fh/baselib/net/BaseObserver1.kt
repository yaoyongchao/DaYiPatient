package com.fh.baselib.http


import com.fh.baselib.http.entity.BaseEntity
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
//abstract class BaseObserver<T> (context: Context) : Observer<BaseEntity<T>> {
abstract class BaseObserver1<T> : Observer<BaseEntity<T>> {
//    private val mContext: Context
    val  TAG = "BaseObserver"
    private val SUCCESS_CODE = 0

    override fun onSubscribe(@NonNull d: Disposable) {

    }

    override fun onNext(@NonNull tBaseEntity: BaseEntity<T>) {
//        L.i("onNext")
//        if (SUCCESS_CODE == tBaseEntity.code) {
//            val t = tBaseEntity.data
//            onSuccess(t)
//        } else {
////            L.i("onNext--Failure--code:" + tBaseEntity.code + "--Message:" + tBaseEntity.msg)
//            onFail(tBaseEntity.msg!!)
//        }

    }

    override fun onError(@NonNull e: Throwable) {
//        L.e("TAG--onError--" + e.toString())
//        ExceptionHandle.handleException(e)
    }

    override fun onComplete() {
//        L.d("TAG--onComplete")

    }

    abstract fun onSuccess(t: T?)

    open fun onFail(msg: String) {
//        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }
}
