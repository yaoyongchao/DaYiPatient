package com.fh.baselib.http


import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.fh.baselib.http.entity.BaseEntity
import com.fh.baselib.utils.ToastUtil
import io.reactivex.Observer
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.Disposable
import java.net.SocketTimeoutException

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
//abstract class BaseObserver<T> (context: Context) : Observer<BaseEntity<T>> {
abstract class BaseObserver<T> : Observer<BaseEntity<T>> {
//    private val mContext: Context
    val  TAG = "BaseObserver"
    private val SUCCESS_CODE = 0

    override fun onSubscribe(@NonNull d: Disposable) {
        logd("onSubscribe")

    }

    override fun onNext(@NonNull tBaseEntity: BaseEntity<T>) {
        logd("onNext")
        if (SUCCESS_CODE == tBaseEntity.errno) {
            val t = tBaseEntity.data
            onSuccess(t)
        } else if(70054 == tBaseEntity.errno || 70034 == tBaseEntity.errno) {//医生信息不存在,也就是账号未注册
            loge("账号未注册")
            onFail(tBaseEntity)
        } else {
            loge("onNext--Failure--code:" + tBaseEntity.errno + "--Message:" + tBaseEntity.errmsg)
            if (tBaseEntity.errno == 70010) {//token不存在或者失效
//                RxBus.get().send(70010)
                ARouter.getInstance().build("/login/login")
                    .navigation()
            }
            onFail(tBaseEntity.errmsg)
        }
    }

    override fun onError(@NonNull e: Throwable) {
        loge("TAG--onError--" + e.toString() + "\n" + e.message)
//        if (!NetWorkUtils.Companion.isConnectedByState(BaseApplication.appContext)) {
////            ToastUtil.show("网络异常请检查网络")
//            onFail("网络异常请检查网络")
//        } else {
//        }
        if (e is SocketTimeoutException || e is java.net.UnknownHostException) {
            loge("错误类型!!是超时")
            onFail("网络超时请重试或查看网络")
        } else {
            onFail(e.toString())
        }


//        ExceptionHandle.handleException(e)
    }

    override fun onComplete() {
        logd("TAG--onComplete")
    }

    abstract fun onSuccess(t: T?)

    open fun onFail(msg: String) {
        ToastUtil.show(msg)
    }

    open fun onFail(data: BaseEntity<T>) {
    }

    fun logd(str: String) {
        Log.d(TAG,str)
    }
    fun loge(str: String) {
        Log.e(TAG,str)
    }
}
