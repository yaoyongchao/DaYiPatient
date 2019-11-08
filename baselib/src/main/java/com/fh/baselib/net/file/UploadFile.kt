package com.fh.baselib.net.file

import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File



/**
 * Author: YongChao
 * Date: 19-8-21 下午1:12
 * Description:
 */

object UploadFile {
    fun createDesc(desc:String):RequestBody {
        var description: RequestBody = RequestBody.create(MediaType.parse("multipart/form-data"),desc)
        return description
    }

    fun createBody(filePath:String):MultipartBody.Part {
        //构建要上传的文件
        var file = File(filePath)
        var requestFile = RequestBody.create(MediaType.parse("application/otcet-stream"),file)
        // 参数分别为， 请求key ，文件名称 ， RequestBody
        var body = MultipartBody.Part.createFormData("file",file.name,requestFile)
        return body
    }

}