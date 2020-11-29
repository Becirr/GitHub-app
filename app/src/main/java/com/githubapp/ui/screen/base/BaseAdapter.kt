package com.githubapp.ui.screen.base

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import java.util.*

abstract class BaseAdapter<T, VH : BaseViewHolder<T>> protected constructor(context: Context?) :
    RecyclerView.Adapter<VH>() {

    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    private val items: MutableList<T> = ArrayList()

    protected var onClickListener: BaseOnClickListener<T>? = null
    protected var recyclerView: RecyclerView? = null

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        holder.onBind(onClickListener, item)
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        holder.onDetachFromWindow()
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        this.recyclerView = recyclerView
    }

    override fun getItemCount(): Int {
        return items.size
    }

    protected fun inflate(@LayoutRes layout: Int, parent: ViewGroup?): ViewDataBinding {
        return DataBindingUtil.inflate(layoutInflater, layout, parent, false)
    }

    fun getItems(): List<T> {
        return items
    }

    protected fun getByPosition(position: Int): T? {
        return if (items.size > 0) {
            items[position]
        } else {
            null
        }
    }

    fun setItems(items: List<T>?) {
        if (items == null) {
            return
        }
        this.items.clear()
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    fun add(item: T) {
        items.add(item)
        notifyItemInserted(items.size - 1)
    }

    protected fun add(position: Int, item: T) {
        items.add(position, item)
        notifyItemInserted(position)
    }

    fun addAll(items: List<T>) {
        this.items.addAll(items)
        notifyItemRangeInserted(itemCount, items.size)
        //notifyDataSetChanged();
    }

    fun addAll(position: Int, items: List<T>) {
        this.items.addAll(position, items)
        notifyItemRangeInserted(position, items.size)
        //notifyDataSetChanged();
    }

    fun remove(item: T) {
        val position = items.indexOf(item)
        if (position > -1) {
            remove(position)
        }
    }

    fun remove(position: Int) {
        items.removeAt(position)
        notifyItemRemoved(position)
    }

    fun removeRange(startPosition: Int, itemCount: Int) {
        for (i in 0 until itemCount) {
            items.removeAt(startPosition)
        }
        notifyItemRangeRemoved(startPosition, itemCount)
    }

    fun clear() {
        items.clear()
        notifyDataSetChanged()
    }

}