package com.fh.baselib.widget

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.fh.baselib.R


class MyDivider constructor(context: Context, orientation: Int) : DividerItemDecoration(context,orientation){



    init {
        val drawable = ContextCompat.getDrawable(context, R.drawable.divider_shape)
        setDrawable(drawable!!)
    }

    constructor(context: Context):this(context,DividerItemDecoration.VERTICAL) {}

    constructor(@DrawableRes id: Int, context: Context):this(context,DividerItemDecoration.VERTICAL) {
        val drawable = ContextCompat.getDrawable(context, id)
        setDrawable(drawable!!)
    }

    constructor(@DrawableRes id: Int, context: Context,orientation: Int):this(context,orientation) {
        val drawable = ContextCompat.getDrawable(context, id)
        setDrawable(drawable!!)
    }

}