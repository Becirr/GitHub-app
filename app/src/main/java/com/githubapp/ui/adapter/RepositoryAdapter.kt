package com.githubapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.githubapp.R
import com.githubapp.domain.model.Repository
import com.githubapp.ui.adapter.viewHolder.RepositoryViewHolder
import com.githubapp.ui.screen.base.BaseAdapter
import com.githubapp.ui.screen.base.BaseViewHolder

class RepositoryAdapter(context: Context?) :
    BaseAdapter<Repository, BaseViewHolder<Repository>>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<Repository> {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater,
            R.layout.item_repository,
            parent,
            false)
        return RepositoryViewHolder(binding, parent as RecyclerView)
    }

    override fun getItemId(position: Int): Long {
        return getItems()[position].id.toLong()
    }

}