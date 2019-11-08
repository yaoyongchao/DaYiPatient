package com.fh.baselib.utils

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import com.fh.baselib.BaseApplication

/**
 * Author: Austin
 * Date: 2018/10/9
 * Description:
 */
class ToastUtil {
    companion object {
        @SuppressLint("StaticFieldLeak")
        fun show(mContext: Context,msg: String) {
            Toast.makeText(mContext,msg,Toast.LENGTH_SHORT).show()
        }

        fun showLong(mContext: Context,msg: String) {
            Toast.makeText(mContext,msg,Toast.LENGTH_LONG).show()
        }

        fun show(msg:String) {
            show(BaseApplication.appContext,msg)
        }

        fun showLong(msg: String) {
            showLong(BaseApplication.appContext,msg)
        }
    }
}