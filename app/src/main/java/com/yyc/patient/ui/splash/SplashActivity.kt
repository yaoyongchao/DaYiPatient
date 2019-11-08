package com.yyc.patient.ui.splash

import com.fh.baselib.base.BaseActivity
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import com.yyc.patient.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : BaseActivity() {
    override fun layoutId(): Int {
       return R.layout.activity_splash
    }

    override fun initView() {
        showToolbar(false)
        JumpUtil.jumpActivity(RouteUrl.guide)
    }

    override fun initData() {
    }

    override fun initListener() {
        tv1.setOnClickListener {
            JumpUtil.jumpActivity(RouteUrl.login)
        }
    }

    override fun isFullScreen(): Boolean {
        return true
    }
}
