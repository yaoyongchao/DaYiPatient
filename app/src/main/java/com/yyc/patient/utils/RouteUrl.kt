package com.fh.bdc.utils

/**
 * @Author: Austin
 * @Date: 2018/10/24
 * @Description: 路由跳转地址工具类
 */
class RouteUrl {
    companion object {
        const val startup = "/login/startup"
        const val guide = "/login/guide"
        const val login = "/login/login"
        const val binding = "/login1/binding"
        const val home = "/home/home"
        const val personalStores = "/store/personalStores"
        const val productInfor = "/store/productInfor"
        const val settings = "/store/settings"
        const val beepSetting = "/store/beepSetting"
        const val printerSetting = "/store/printerSetting"
        const val orderList = "/ticket/orderList"
        const val award = "/ticket/award"


        const val invitePatients = "/workbench/invitePatients"
        const val reservationManager = "/workbench/reservationManager"
        const val voiceQuestionAnswer = "/workbench/voiceQuestionAnswer"
        const val voiceProblemes = "/workbench/voiceProblemes"
        const val recording = "/workbench/recording"
        const val recordingProofread = "/workbench/recordingProofread"
        const val requiredRead = "/workbench/requiredRead"


        //我的
        const val personalInformation = "/mine/information"
        const val mysetting = "/mine/mysetting"
        const val aboutUs = "/mine/aboutUs"
        const val terms = "/mine/terms"
        const val myvoice = "/mine/myvoice"
        const val myvoiceDetails = "/mine/myvoicedetails"
        const val myvideo= "/mine/myvideo"
        const val myvideoplay= "/mine/myvideoplay"

        //收益
        const val incomedetails = "/income/incomedetails"


        const val webView = "/web/webView"
    }
}