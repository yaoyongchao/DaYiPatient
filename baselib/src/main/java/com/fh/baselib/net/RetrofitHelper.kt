package com.fh.baselib.http

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Author: Austin
 * Date: 2018/10/8
 * Description: 单例形式创建Retrofit实例
 */
class RetrofitHelper private constructor() {
    lateinit var retrofit: Retrofit
    init {
        initRetrofit()
    }

    companion object {
        val INSTANCE: RetrofitHelper by lazy (mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitHelper()
        }
        fun initRetrofit(url: String): Retrofit {
            return Retrofit.Builder()
                    .baseUrl(url)
                    .client(OkHttpHelper.instance.okHttpClient)
//                    .client(SSLUtils.getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
    private fun initRetrofit() {
        retrofit = Companion.initRetrofit(BaseApi.BASE_RUL)
        /*retrofit = Retrofit.Builder()
                .baseUrl(BaseApi.BASE_RUL)
                .client(OkHttpHelper.instance.okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()*/
    }

}
