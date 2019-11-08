package com.fh.baselib.http

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
class OkHttpHelper private constructor() {
    public lateinit var okHttpClient: OkHttpClient
    private val timeUnit: TimeUnit = TimeUnit.SECONDS
    private val connectTimeOut: Long = 10
    private val readTimeOut: Long = 10
    private val writeTimeOut: Long = 10

    init {
        initHttpClient()
    }
    companion object {
        val instance: OkHttpHelper by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            OkHttpHelper()
        }
    }

    private fun initHttpClient(): OkHttpClient? {
        val builder = OkHttpClient.Builder()
                .connectTimeout(connectTimeOut,timeUnit)
                .readTimeout(readTimeOut,timeUnit)
                .writeTimeout(writeTimeOut,timeUnit)
                .addInterceptor(LoggingInterceptor())
//                .addInterceptor(LoggerInterceptor())
        okHttpClient = builder.build()
        return okHttpClient
    }

}