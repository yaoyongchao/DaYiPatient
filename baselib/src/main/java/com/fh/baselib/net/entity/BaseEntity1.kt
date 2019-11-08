package com.fh.baselib.http.entity

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
class BaseEntity1<T> {
    var code: Int = -1
    lateinit var msg: String
    var data: T? = null

    override fun toString(): String {
        return "HttpResult{" +
                "code=" + code +
                ", msg='" + msg + '\''.toString() +
                ", data=" + data +
                '}'.toString()
    }
}
