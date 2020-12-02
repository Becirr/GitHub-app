package com.githubapp.ui.adapter.viewHolder

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.githubapp.R
import com.githubapp.databinding.ItemRepositoryBinding
import com.githubapp.domain.model.Repository
import com.githubapp.ui.screen.base.BaseOnClickListener
import com.githubapp.ui.screen.base.BaseViewHolder
import com.githubapp.ui.screen.repositoryDetails.RepositoryDetailsActivity
import com.githubapp.ui.screen.userDetails.UserDetailsActivity
import com.githubapp.util.getFormattedDate

class RepositoryViewHolder(binding: ViewDataBinding, recyclerView: RecyclerView) :
    BaseViewHolder<Repository>(binding, recyclerView) {

    private val binding: ItemRepositoryBinding = binding as ItemRepositoryBinding

    override fun onBind(onClickListener: BaseOnClickListener<Repository>?, item: Repository) {
        super.onBind(onClickListener, item)
        loadRepository(item)
    }

    private fun loadRepository(item: Repository) {
        loadImage()
        binding.fullName.text = item.fullName
        binding.description.text = item.description
        binding.starValue.text = item.watchers.toString()
        binding.forksValue.text = item.forks.toString()
        binding.issueValue.text = item.openIssues.toString()
        binding.language.text = item.language
        binding.update.text = item.updatedAt.getFormattedDate(getContext(), R.string.updated)
        if (item.language.isNullOrEmpty()) {
            binding.language.visibility = View.GONE
        }
        if (adapterPosition == adapterSize - 1) {
            binding.divider.visibility = View.INVISIBLE
        }
        binding.thumbnail.setOnClickListener { view ->
            item.owner?.let { UserDetailsActivity.open(view.context, it) }
        }
        binding.fullName.setOnClickListener { view ->
            RepositoryDetailsActivity.open(view.context, item)
        }
        binding.description.setOnClickListener { view ->
            RepositoryDetailsActivity.open(view.context, item)
        }
    }

    private fun loadImage() {
        Glide.with(getContext())
            .load(item?.owner?.avatarUrl)
            .circleCrop()
            .transition(DrawableTransitionOptions().crossFade(300))
            .into(binding.thumbnail)
    }

}