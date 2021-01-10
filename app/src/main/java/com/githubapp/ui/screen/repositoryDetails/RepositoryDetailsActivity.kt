package com.githubapp.ui.screen.repositoryDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.githubapp.R
import com.githubapp.databinding.ActivityRepositoryDetailsBinding
import com.githubapp.di.component.AppComponent
import com.githubapp.domain.model.Owner
import com.githubapp.domain.model.Repository
import com.githubapp.ui.screen.base.BaseActivity
import com.githubapp.ui.screen.userDetails.UserDetailsActivity
import com.githubapp.ui.screen.webView.WebViewActivity
import com.githubapp.util.getFormattedDate
import javax.inject.Inject

class RepositoryDetailsActivity : BaseActivity<ActivityRepositoryDetailsBinding>(),
    RepositoryDetailsView {

    @Inject
    lateinit var repositoryDetailsPresenter: RepositoryDetailsPresenter

    private var repository: Repository? = null

    override val layoutId: Int = R.layout.activity_repository_details

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(RepositoryDetailsModule()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = intent.getSerializableExtra(REPOSITORY) as? Repository
        setupUI()
        setupListeners()
        repositoryDetailsPresenter.onAttach(this)
    }

    private fun setupUI() {
        if (repository?.owner?.login != null && repository?.name != null) {
            repositoryDetailsPresenter.getRepositoryDetails(repository!!.owner!!.login,
                repository!!.name)
        }
        viewDataBinding.name.text = repository?.owner?.login
        viewDataBinding.repoName.text = repository?.name
        Glide.with(this)
            .load(repository?.owner?.avatarUrl)
            .circleCrop()
            .transition(DrawableTransitionOptions().crossFade(300))
            .into(viewDataBinding.thumbnail)
        viewDataBinding.starValue.text = repository?.watchers.toString()
        viewDataBinding.forkValue.text = repository?.forks.toString()
        viewDataBinding.issueValue.text = repository?.openIssues.toString()
        repository?.owner?.login?.let { repositoryDetailsPresenter.getUserDetails(it) }
    }

    private fun setupListeners() {
        viewDataBinding.back.setOnClickListener { super.onBackPressed() }
        viewDataBinding.viewDetails.setOnClickListener {
            WebViewActivity.open(this, repository?.htmlUrl)
        }
        viewDataBinding.thumbnail.setOnClickListener {
            repository?.owner?.let { UserDetailsActivity.open(this, it) }
        }
    }

    override fun showUserDetails(owner: Owner) {
        viewDataBinding.name.animate()?.alpha(1f)
        viewDataBinding.thumbnail.animate()?.alpha(1f)
        if (!owner.location.isNullOrEmpty()) {
            viewDataBinding.location.text = owner.location
            viewDataBinding.location.visibility = View.VISIBLE
            viewDataBinding.iconLocation.visibility = View.VISIBLE
            viewDataBinding.location.animate()?.alpha(1f)?.start()
            viewDataBinding.iconLocation.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.location.visibility = View.GONE
            viewDataBinding.iconLocation.visibility = View.GONE
        }
        if (!owner.email.isNullOrEmpty()) {
            viewDataBinding.email.text = owner.email
            viewDataBinding.email.visibility = View.VISIBLE
            viewDataBinding.iconEmail.visibility = View.VISIBLE
            viewDataBinding.email.animate()?.alpha(1f)?.start()
            viewDataBinding.iconEmail.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.email.visibility = View.GONE
            viewDataBinding.iconEmail.visibility = View.GONE
        }
        if (!owner.company.isNullOrEmpty()) {
            viewDataBinding.company.text = owner.company
            viewDataBinding.company.visibility = View.VISIBLE
            viewDataBinding.iconCompany.visibility = View.VISIBLE
            viewDataBinding.company.animate()?.alpha(1f)?.start()
            viewDataBinding.iconCompany.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.company.visibility = View.GONE
            viewDataBinding.iconCompany.visibility = View.GONE
        }
        viewDataBinding.numbersLayout.animate()?.alpha(1f)?.start()
        viewDataBinding.repoName.animate()?.alpha(1f)
        if (!repository?.description.isNullOrEmpty()) {
            viewDataBinding.description.text = repository?.description
            viewDataBinding.description.visibility = View.VISIBLE
            viewDataBinding.description.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.description.visibility = View.GONE
        }
        viewDataBinding.update.text =
            repository?.updatedAt?.getFormattedDate(this, R.string.updated_new_line)
        viewDataBinding.created.text =
            repository?.createdAt?.getFormattedDate(this, R.string.created_new_line)
        viewDataBinding.updatedCard.animate()?.alpha(1f)?.start()
        viewDataBinding.createdCard.animate()?.alpha(1f)?.start()
        if (!repository?.language.isNullOrEmpty()) {
            viewDataBinding.language.text = repository?.language
            viewDataBinding.languageCard.visibility = View.VISIBLE
            viewDataBinding.languageCard.animate()?.alpha(1f)?.start()
        } else {
            viewDataBinding.languageCard.visibility = View.GONE
        }
        viewDataBinding.viewDetailsCard.animate()?.alpha(1f)?.start()
    }

    override fun showRepositoryDetails(repository: Repository) {
        viewDataBinding.watchValue.text = repository.subscribersCount.toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        repositoryDetailsPresenter.onDetach()
    }

    companion object {

        const val REPOSITORY = "Repository"

        fun open(
            context: Context,
            repository: Repository,
        ) {
            val intent = Intent(context, RepositoryDetailsActivity::class.java)
            intent.putExtra(REPOSITORY, repository)
            context.startActivity(intent)
        }
    }

}