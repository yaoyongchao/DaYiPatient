package com.fh.baselib.utils

import java.lang.reflect.ParameterizedType

/**
 * Author: Austin
 * Time: 2018/7/18
 * Description:
 */

object CreatUtil {
    /**
     * 获取T类型然后自动new出对象
     * @param o
     * @param i
     * @param <T>
     * @return
    </T> */
    fun <T> getT(o: Any, i: Int): T? {

        try {
            return ((o.javaClass.genericSuperclass as ParameterizedType).actualTypeArguments[i] as Class<T>).newInstance()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null

    }
}
