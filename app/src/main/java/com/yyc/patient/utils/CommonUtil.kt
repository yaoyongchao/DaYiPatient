package com.fh.bdc.utils

import android.content.Context
import com.fh.baselib.BaseApplication
import com.fh.baselib.utils.SPObjUtil
import com.fh.cplib.common.CommonParam

/**
 * Author: YongChao
 * Date: 19-8-7 下午4:12
 * Description:
 */
object CommonUtil {
    val context: Context
        get() = BaseApplication.appContext

    /**
     * 0 表示第一次使用， 1表示不是第一次使用
     * @return
     */
    val isFirstUse: Boolean
        get() = if (SPObjUtil.getInt(context, CommonParam.IS_FIRST, 0) == 0) true else false

    fun saveFirstUse() {
        SPObjUtil.putInt(context, CommonParam.IS_FIRST, 1)
    }

    fun saveToken(token:String) {
        SPObjUtil.putString(context,CommonParam.TOKEN,token)
    }

    fun getToken():String {
//        if(BuildConfig.isTest)
//            return "999"
        return SPObjUtil.getString(context,CommonParam.TOKEN,"")
    }

    /*fun saveInfo(info: DoctorInfo) {
        SPObjUtil.putObject(context,info)
    }

    fun getInfo(): DoctorInfo {
        return SPObjUtil.getObject(context,DoctorInfo::class.java)
    }

    fun formatPhone(phone:String):String {
        return phone.replace("(\\d{3})\\d{4}(\\d{4})".toRegex(), "$1****$2")
    }


//判断是否安装了微信
    fun isWeixinAvilible(context: Context):Boolean {
    var mwxApi = WXAPIFactory.createWXAPI(context,AccountConfig.WX_APP_ID,true)
    if (mwxApi.isWXAppInstalled)
        return true
    return false
    }

    fun isQQAvailable(context:Context):Boolean {
        var packageManager = context.packageManager
        var pinfo = packageManager.getInstalledPackages(0)
        if (pinfo != null) {
            for (i in pinfo.indices) {
                var pn = pinfo.get(i).packageName
                if (pn.equals("com.tencent.mobileqq"))
                    return true
            }
        }
        return  false
    }
    fun isQZoneAvailable(context:Context):Boolean {
        var packageManager = context.packageManager
        var pinfo = packageManager.getInstalledPackages(0)
        if (pinfo != null) {
            for (i in pinfo.indices) {
                var pn = pinfo.get(i).packageName
                if (pn.equals("com.qzone"))
                    return true
            }
        }
        return  false
    }*/
}
