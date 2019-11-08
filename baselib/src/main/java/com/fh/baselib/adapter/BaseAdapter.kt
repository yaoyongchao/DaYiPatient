package com.fh.baselib.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.fh.baselib.R

/**
 * Author: YongChao
 * Date: 19-8-30 下午4:29
 * Description: RecyclerView.Adapter 基础类封装
 */
open class BaseAdapter<T>(val layoutResourceId: Int, var items: List<T>, var bindView: (View, T,Int) -> Unit) :
    RecyclerView.Adapter<BaseAdapter.ViewHolder<T>>() {
    /**
     * 0:空布局  1： 非空布局
     */
    val VIEW_TYPE_ITEM = 1
    val VIEW_TYPE_EMPTY = 0
    var isShowEmptyView = true
    var emptyLayout = R.layout.layout_rv_empty
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        if (isShowEmptyView && viewType == VIEW_TYPE_EMPTY) {
            val viewEmpty = LayoutInflater.from(parent.context).inflate(emptyLayout,parent,false)
            return ViewHolder(viewEmpty,bindView)
        }
        val view = LayoutInflater.from(parent.context).inflate(layoutResourceId,parent,false)
        return ViewHolder(view, bindView)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        if (items.size != 0) {
            holder.bindForecast(items[position],position)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (isShowEmptyView && items.size == 0) VIEW_TYPE_EMPTY else VIEW_TYPE_ITEM
    }

    override fun getItemCount() = if (isShowEmptyView && items.size == 0) 1 else items.size

    class ViewHolder<in T>(view: View, val bindView: (View, T,Int) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindForecast(item: T,position: Int) {
                bindView(itemView, item,position)
        }
    }
    open fun setEmpytView(eLayout:Int) {
        this.emptyLayout = eLayout
    }
}