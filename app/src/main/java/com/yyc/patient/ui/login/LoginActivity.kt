package com.yyc.patient.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.fh.baselib.base.BaseActivity
import com.fh.bdc.utils.JumpUtil
import com.fh.bdc.utils.RouteUrl
import com.yyc.patient.R
import kotlinx.android.synthetic.main.activity_login.*

@Route(path = RouteUrl.login)
class LoginActivity : BaseActivity() {
    override fun layoutId(): Int {
        return R.layout.activity_login
    }
    override fun initView() {}
    override fun initData() {}
    override fun initListener() {
        btn.setOnClickListener { JumpUtil.jumpActivity(RouteUrl.home) }
    }
}
