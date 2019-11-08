package com.fh.baselib.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter1<T>(val layoutResourceId: Int, val items: List<T>, val init: (View, T, Int) -> Unit) :
    RecyclerView.Adapter<BaseAdapter1.ViewHolder<T>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
        val view = LayoutInflater.from(parent.context).inflate(layoutResourceId,parent,false)
        return ViewHolder(view, init)
    }

    override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
        holder.bindForecast(items[position],position)
    }

    override fun getItemCount() = items.size

    class ViewHolder<in T>(view: View, val init: (View, T,Int) -> Unit) : RecyclerView.ViewHolder(view) {
        fun bindForecast(item: T,position: Int) {
                init(itemView, item,position)
        }
    }
}