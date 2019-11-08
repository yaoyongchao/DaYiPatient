package com.fh.bdc.utils

import android.os.Parcelable
import com.alibaba.android.arouter.launcher.ARouter

object JumpUtil {
    fun jumpActivity(path: String) {
        ARouter.getInstance().build(path)
                .navigation()
    }
    fun jumpActivityWithString(path: String,string: String) {
        ARouter.getInstance().build(path)
                .withString("0",string)
                .navigation()
    }
    fun jumpActivityWithLong(path: String,i: Long) {
        ARouter.getInstance().build(path)
                .withLong("0",i)
                .navigation()
    }
    fun jumpActivityWithInt(path: String,i: Int) {
        ARouter.getInstance().build(path)
                .withInt("0",i)
                .navigation()
    }

    fun jumpActivityWithObject(path: String,o: Any) {
        ARouter.getInstance().build(path)
                .withObject("0",o)
                .navigation()
    }
    fun jumpActivityWithParcelable(path: String,p: Parcelable) {
        ARouter.getInstance().build(path)
                .withParcelable("bean",p)
                .navigation()
    }

    /**
     * 跳转到微信
     */
    /*fun getWechatApi(context: Context) {
        try {
            val intent = Intent(Intent.ACTION_MAIN)
            val cmp = ComponentName("com.tencent.mm", "com.tencent.mm.ui.LauncherUI")
            intent.addCategory(Intent.CATEGORY_LAUNCHER)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.component = cmp
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            //
            ToastUtil.show("检查到您手机没有安装微信，请安装后使用该功能")
        }

    }

    fun jumpZfb(mContext: Context) {
        //        com.eg.android.AlipayGphone   支付宝的包名
        try {
            val intent = mContext.packageManager.getLaunchIntentForPackage("com.eg.android.AlipayGphone")
            mContext.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            ToastUtil.show("检查到您手机没有安装支付宝，请安装后使用该功能")
        }

    }

    *//**
     * 复制文本
     * @param str
     *//*
    fun copyText(context: Context, str: String): Boolean {
        val tvCopy = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        tvCopy.text = str

        return true
    }

    fun jumpActivityWebView(url: String) {
        ARouter.getInstance().build(RouteUtils.BaseWebViewActivity)
                .withString(CommonParam.URL,url)
                .navigation()
    }*/
}