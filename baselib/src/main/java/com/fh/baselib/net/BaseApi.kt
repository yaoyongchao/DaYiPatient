package com.fh.baselib.http

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
open class BaseApi {
    companion object {
//        const val BASE_RUL: String = "https://www.easy-mock.com/mock/59881797a1d30433d857c7a5/aa/"
//        const val BASE_RUL: String = "https://www.easy-mock.com/mock/59881797a1d30433d857c7a5/aa/"

        val DEBUG_URL = "https://c.xingdianbao.com/"
        val PRODUCT_URL = "https://m.vcaidian.com/"
//         open public var URL_DOMAIN = "https://c.xingdianbao.com/"
//         open public var URL_DOMAIN = if (DebugConfig.DEBUG) DEBUG_URL else PRODUCT_URL
         open public var URL_DOMAIN = "https://m.vcaidian.com/"
         public var BASE_RUL: String = URL_DOMAIN + "api/"

    }
}