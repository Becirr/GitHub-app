package com.githubapp.ui.screen.userDetails

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.githubapp.R
import com.githubapp.databinding.ActivityUserDetailsBinding
import com.githubapp.di.component.AppComponent
import com.githubapp.domain.model.Owner
import com.githubapp.ui.screen.base.BaseActivity
import com.githubapp.ui.screen.webView.WebViewActivity
import javax.inject.Inject

class UserDetailsActivity : BaseActivity<ActivityUserDetailsBinding>(), UserDetailsView {

    @Inject
    lateinit var userDetailsPresenter: UserDetailsPresenter

    private var owner: Owner? = null

    override val layoutId: Int = R.layout.activity_user_details

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(UserDetailsModule()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        owner = intent.getSerializableExtra(OWNER) as? Owner
        setupUI()
        setupListeners()
        userDetailsPresenter.onAttach(this)
    }

    private fun setupUI() {
        viewDataBinding.name.text = owner?.login
        viewDataBinding.name.animate()?.alpha(1f)
        Glide.with(this)
            .load(owner?.avatarUrl)
            .transition(DrawableTransitionOptions().crossFade(300))
            .transform(CenterCrop(), RoundedCorners(50))
            .into(object : DrawableImageViewTarget(viewDataBinding.avatar) {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?,
                ) {
                    viewDataBinding.avatar.setImageDrawable(resource)
                }
            })
        owner?.login?.let { userDetailsPresenter.getUserDetails(it) }
    }

    private fun setupListeners() {
        viewDataBinding.back.setOnClickListener { super.onBackPressed() }
        viewDataBinding.blog.setOnClickListener {
            WebViewActivity.open(this, owner?.blog)
        }
        viewDataBinding.moreDetails.setOnClickListener {
            WebViewActivity.open(this, owner?.htmlUrl)
        }
    }

    override fun showUser(owner: Owner) {
        this.owner = owner
        if (!owner.location.isNullOrEmpty()) {
            viewDataBinding.location.text = owner.location
            viewDataBinding.location.visibility = View.VISIBLE
            viewDataBinding.location.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.location.visibility = View.GONE
        }
        if (!owner.email.isNullOrEmpty()) {
            viewDataBinding.email.text = owner.email
            viewDataBinding.email.visibility = View.VISIBLE
            viewDataBinding.email.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.email.visibility = View.GONE
        }
        if (!owner.company.isNullOrEmpty()) {
            viewDataBinding.company.text = owner.company
            viewDataBinding.company.visibility = View.VISIBLE
            viewDataBinding.company.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.company.visibility = View.GONE
        }
        if (owner.hireable != null && owner.hireable == true) {
            viewDataBinding.openToWork.visibility = View.VISIBLE
            viewDataBinding.openToWork.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.openToWork.visibility = View.GONE
        }
        if (!owner.bio.isNullOrEmpty()) {
            viewDataBinding.bio.text = owner.bio
            viewDataBinding.bioCardView.visibility = View.VISIBLE
            viewDataBinding.bioCardView.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.bioCardView.visibility = View.GONE
        }
        viewDataBinding.gistValue.text = owner.publicGists.toString()
        viewDataBinding.repoValue.text = owner.publicRepos.toString()
        viewDataBinding.followerValue.text = owner.followers.toString()
        viewDataBinding.followingValue.text = owner.following.toString()
        if (!owner.blog.isNullOrEmpty()) {
            viewDataBinding.blogLayout.visibility = View.VISIBLE
        } else {
            viewDataBinding.blogLayout.visibility = View.GONE
        }
        viewDataBinding.detailsCardView.animate()?.alpha(1f)?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        userDetailsPresenter.onDetach()
    }

    companion object {

        const val OWNER = "Owner"

        fun open(
            context: Context,
            owner: Owner,
        ) {
            val intent = Intent(context, UserDetailsActivity::class.java)
            intent.putExtra(OWNER, owner)
            context.startActivity(intent)
        }
    }

}