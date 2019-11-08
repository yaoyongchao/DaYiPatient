package com.fh.baselib.widget

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import com.fh.baselib.R

/**
 * Loading
 */
class LoadingDialog constructor(context: Context) : AlertDialog(context, R.style.loading_dialog) {
    var mView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()

    }

    open fun initViews() {
        val inflater = LayoutInflater.from(context)
        mView = inflater.inflate(R.layout.dialog_loading, null)
        setContentView(mView)
        setCanceledOnTouchOutside(false)
//        ImgUtilWc.loadGifImg(context,R.drawable.loading,img)

        setDialogLayout(context,window)
    }

    override fun onStop() {
        super.onStop()
    }

    private fun setDialogLayout(context: Context, dialogWindow: Window) {
        //        Window dialogWindow = getWindow();
        val lp = dialogWindow.attributes
        val d = context.resources.displayMetrics // 获取屏幕宽、高用
        lp.width = (d.widthPixels * 0.8).toInt() // 高度设置为屏幕的0.6
        dialogWindow.attributes = lp
    }

}