package com.fh.baselib.http.entity

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
class BaseEntity<T> {
    var errno: Int = -1
    var logid: Int = -1
    var errmsg: String = ""
    var servertime: Int = 0
    var data: T? = null
    override fun toString(): String {
        return "BaseEntity(errno=$errno, logid=$logid, errmsg='$errmsg', servertime=$servertime, data=$data)"
    }
}
