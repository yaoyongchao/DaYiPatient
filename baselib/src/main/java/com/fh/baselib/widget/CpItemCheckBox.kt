package com.fh.baselib.widget

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CompoundButton
import android.widget.ImageView
import android.widget.LinearLayout
import com.fh.baselib.R
import kotlinx.android.synthetic.main.layout_cp_item_checkbox.view.*


/**
 * Author: Austin
 * Date: 19-5-6
 * Description:
 */
class CpItemCheckBox @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : LinearLayout(context, attrs, defStyleAttr) {
    private val isLog = true
    private val imgLeft: ImageView? = null
    private val IMG_LEFT_ID = 0x1000
    private val imgLeftSize = 40
    private lateinit var view: View
    private lateinit var attributes: TypedArray

    init {
        initViews(context, attrs)
    }

    private fun initViews(context: Context, attrs: AttributeSet?) {
        log("initViews")

        val mInflater = LayoutInflater.from(context)
        view = mInflater.inflate(R.layout.layout_cp_item_checkbox, null)
        addView(view)

        attributes = context.obtainStyledAttributes(attrs, R.styleable.CpItemCheckBox)
        if (attributes != null) {
            initTv()
            initBorder()
            initCkb()
            attributes.recycle()
        }
    }


    private fun initCkb() {
        val checked = attributes.getBoolean(R.styleable.CpItemCheckBox_cp_checked,true)
        view.ckb.isChecked = checked
    }

    public fun setOnCheckedChangeListener(lis: CompoundButton.OnCheckedChangeListener) {
        view?.ckb?.setOnCheckedChangeListener(lis)
    }


    private fun initTv() {
        val text = attributes.getString(R.styleable.CpItemCheckBox_ckb_text)
//        val textTips = attributes.getString(R.styleable.CpItemCheckBox_cp_text_tips)
        view.tv.setText(text)
//        view.tv_tips.setText(textTips)

    }

    private fun initBorder() {
        val showTop = attributes.getBoolean(R.styleable.CpItemCheckBox_show_ckb_border_top,false)
        val showBottom = attributes.getBoolean(R.styleable.CpItemCheckBox_show_ckb_border_bottom,true)
        view.border_top.visibility = if (showTop) View.VISIBLE else View.GONE
        view.border_bottom.visibility = if (showBottom) View.VISIBLE else View.GONE
        val imgLeftDrawable = attributes.getResourceId(R.styleable.SettingItemView_imgleft_drawable, -1)

        val showViewTop = attributes.getBoolean(R.styleable.CpItemCheckBox_show_ckb_view_top, false)
        view.view_top.visibility = if (showViewTop) View.VISIBLE else View.GONE

//        layoutParams = setViewMargin(this,0,0,20,0)


    }



    public fun setTv(text: CharSequence) {
        view.tv?.setText(text)
    }

    public fun setTv(resid: Int) {
        view.tv?.setText(resid)
    }


    /*
     * converts dip to px
     */
    private fun dip2Px(dip: Float): Int {
        return (dip * context.resources.displayMetrics.density + 0.5f).toInt()
    }

    private fun log(str: String) {
        if (isLog)
            Log.i(TAG, str)
    }

    companion object {
        private val TAG = "CpItemCheckBox"
    }
}
