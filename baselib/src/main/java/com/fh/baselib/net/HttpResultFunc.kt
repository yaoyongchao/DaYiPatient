package com.fh.baselib.http

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */

import com.fh.baselib.http.entity.BaseEntity

import io.reactivex.functions.Function

/**
 * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
 *
 * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
</T> */
class HttpResultFunc<T> : Function<BaseEntity<T>, T> {

    /* @Override
    public T call(HttpResult<T> httpResult) {
        if (httpResult.getResultCode() != 0) {
            throw new ApiException(httpResult.getResultCode());
        }
        return httpResult.getData();
    }*/

    @Throws(Exception::class)
    override fun apply(tBaseEntity: BaseEntity<T>): T? {
        if (tBaseEntity.errno != 0) {
//            throw ApiException(tBaseEntity.msg)

        }
        return tBaseEntity.data
    }
}
