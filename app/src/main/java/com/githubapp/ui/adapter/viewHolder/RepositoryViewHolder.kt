package com.githubapp.ui.adapter.viewHolder

import android.text.format.DateUtils
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
import com.githubapp.ui.screen.userDetails.UserDetailsActivity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

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
        binding.watchersValue.text = item.watchers.toString()
        binding.forksValue.text = item.forks.toString()
        binding.issueValue.text = item.openIssues.toString()
        binding.language.text = item.language
        setTimeUpdated(item.updatedAt)
        if (item.language.isNullOrEmpty()) {
            binding.language.visibility = View.GONE
        }
        if (adapterPosition == adapterSize - 1) {
            binding.divider.visibility = View.INVISIBLE
        }
        binding.thumbnail.setOnClickListener { view ->
            item.owner?.let { UserDetailsActivity.open(view.context, it) }
        }
    }

    private fun loadImage() {
        Glide.with(getContext())
            .load(item?.owner?.avatarUrl)
            .circleCrop()
            .transition(DrawableTransitionOptions().crossFade(300))
            .into(binding.thumbnail)
    }

    private fun setTimeUpdated(updatedAt: String) {
        val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("GMT")
        try {
            val time: Long? = sdf.parse(updatedAt)?.time
            val now = System.currentTimeMillis()
            if (time != null) {
                val ago = DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                binding.update.text = getContext().getString(R.string.updated, ago)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
        }
    }

}