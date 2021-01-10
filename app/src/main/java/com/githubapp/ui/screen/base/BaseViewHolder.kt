package com.githubapp.ui.screen.base

import android.content.Context
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T> protected constructor(
    binding: ViewDataBinding,
    recyclerView: RecyclerView,
) :
    RecyclerView.ViewHolder(binding.root) {
    private val context: Context = itemView.context
    var item: T? = null
        private set
    private val adapter: RecyclerView.Adapter<*>? = recyclerView.adapter

    open fun onBind(onClickListener: BaseOnClickListener<T>?, item: T) {
        this.item = item

        // OnClickListener
        itemView.setOnClickListener {
            onClickListener?.onClick(item)
        }
    }

    fun onDetachFromWindow() {}

    protected val adapterSize: Int
        get() = adapter?.itemCount ?: 0

    protected fun getContext(): Context {
        return itemView.context
    }

    protected fun getString(resource: Int): String {
        return context.resources.getString(resource)
    }

    protected fun getString(resource: Int, vararg formatArgs: Any?): String {
        return context.resources.getString(resource, *formatArgs)
    }

    protected fun getDrawable(resource: Int): Drawable? {
        return ContextCompat.getDrawable(context, resource)
    }

    protected fun getFont(resource: Int): Typeface? {
        return ResourcesCompat.getFont(context, resource)
    }

    protected fun getDimension(resource: Int): Int {
        return context.resources.getDimensionPixelSize(resource)
    }

    protected fun getColor(resource: Int): Int {
        return if (resource <= 0) 0 else ContextCompat.getColor(context, resource)
    }

}
