package com.yyc.patient

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.fh.baselib.BaseApplication
import com.fh.baselib.utils.LogUtil

/**
 * Author: YongChao
 * Date: 19-11-5
 * Description:
 */
class DpApplication : BaseApplication() {
    override fun initViews() {
        super.initViews()
        ARouter.init(this)
        ARouter.openDebug()
        ARouter.openLog()
        LogUtil.initLogger(true)
    }

}