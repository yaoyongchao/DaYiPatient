package com.fh.cplib.common

import android.os.Environment
import java.io.File

object CommonParam {
//    var TOKEN_DEFAULT = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
    val IS_FIRST = "IsFirst"
    val TOKEN = "token"
    val WEB_URL = "webUrl"
    // 保存图片至指定路径
    val voicePath = Environment.getExternalStorageDirectory().absolutePath + File.separator + "dayi/"

    val PAGE_SIZE = 20
    var TERMS = "/passport/argeement?callsource=1"
    var ABOUT_US = "/about/?callsource=1#argeement"
    var QRCODE = "qrcode/"

    //


//    val USERID = "userId"
//    val TOKEN = "Token"
//    val CUR_STATION_ID = "curStationId"
//    val SHARED_NAME = "wc"
//    val DIGEST_TYPE = "md5"
//    val DIGEST_TYPE_EMPTY = "md5-empty"
//    val USER_PHONE = "UserPhone"
//    val URL = "url"
//    val LIMIT = 20
//    val WX_API = "WEIXINAPI"
//    val WX_CODE = "weixincode"
//
//    val USER_TYPE = "station"
//    val TERMINAL_ID = "TerminalId"
//    val STATION = "station"
}
