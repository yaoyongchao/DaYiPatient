package com.fh.baselib.widget
import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import com.fh.baselib.R
import kotlinx.android.synthetic.main.custom_toolbar_layout.view.*

/**
 * Author: Austin
 * Date: 2018/10/8
 * Description: 自定义ToolBar.
 */
class CustomToolBar: LinearLayout{
    lateinit var onClickLeftListener: OnClickLeftListener
    lateinit var onClickRightListener: OnClickRightListener
    lateinit var onClickTvRightListener: OnClickTvRightListener

    constructor(context: Context?) : this(context,null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initViews()
    }

    private fun initViews() {
        var view: View = LayoutInflater.from(context).inflate(R.layout.custom_toolbar_layout,this ,true)
        img_title_right.setOnClickListener{
            if (onClickRightListener != null)
                onClickRightListener.onClickRight(it)
        }
        img_title_left.setOnClickListener {
            if (onClickLeftListener != null)
                onClickLeftListener.onClickLeft(it)
        }
        tv_title_right.setOnClickListener {
            onClickTvRightListener?.onClickTvRight(it)
        }
    }

    public fun setOnClickLeftListener (listener: OnClickListener) {
        img_title_left.setOnClickListener(listener)
    }

    public fun setOnClickRightListener (listener: OnClickListener) {
        img_title_right.setOnClickListener(listener)
    }

    fun setOnClickTvRightListener (listener: OnClickListener) {
        tv_title_right.setOnClickListener(listener)
    }

    fun setTitle(title: String) {
        tv_title.text = title
    }

    fun setTitle(title: Int) {
        tv_title.text = context.resources.getString(title)
    }

    fun setTitleRight(title: String) {
        tv_title_right.text = title
    }

    fun setTitleRightBold() {
        tv_title_right.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD))
    }
    fun setTitleRightColor(@ColorInt color: Int) {
        tv_title_right.setTextColor(color)
    }

    fun setLeftIcon(@DrawableRes resId: Int) {
        img_title_left.setImageResource(resId)
    }

    fun setRightIcon(@DrawableRes resId: Int) {
        img_title_right.setImageResource(resId)
    }

    fun showLeftIcon(visibility: Int){
        img_title_left?.visibility = visibility
    }

    fun showRightIcon(visibility: Int) {
        img_title_right?.visibility = visibility
    }

    fun setCustomToolBarMargin(top : Int) {
        var layoutParams :LinearLayout.LayoutParams = root_bar.layoutParams as LayoutParams
        layoutParams.topMargin = top;
        root_bar.layoutParams = layoutParams
    }

    override fun setBackgroundColor(color: Int) {
        super.setBackgroundColor(color)
        toolbar.setBackgroundColor(color)
    }
    fun setBackgroundTvRight(background :Drawable){
        tv_title_right.background = background
    }
    /**
     * 左侧按钮点击事件接口
     */
    interface OnClickLeftListener {
        fun onClickLeft(view: View)
    }

    /**
     * 右侧按钮点击事件接口
     */
    interface OnClickRightListener {
        fun onClickRight(view: View)
    }

    /**
     * 右侧按钮点击事件接口
     */
    interface OnClickTvRightListener {
        fun onClickTvRight(view: View)
    }

}