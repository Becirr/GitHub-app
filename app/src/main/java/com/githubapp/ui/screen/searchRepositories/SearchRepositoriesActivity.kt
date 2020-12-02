package com.githubapp.ui.screen.searchRepositories

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.githubapp.Constants
import com.githubapp.R
import com.githubapp.data.cache.preferences.PreferencesManager
import com.githubapp.databinding.ActivitySearchRepositoriesBinding
import com.githubapp.di.component.AppComponent
import com.githubapp.domain.model.AccessToken
import com.githubapp.domain.model.Owner
import com.githubapp.domain.model.Repository
import com.githubapp.ui.adapter.RepositoryAdapter
import com.githubapp.ui.screen.base.BaseActivity
import com.githubapp.util.DeviceUtils
import javax.inject.Inject

class SearchRepositoriesActivity : BaseActivity<ActivitySearchRepositoriesBinding>(),
    SearchRepositoriesView {

    @Inject
    lateinit var searchRepositoriesPresenter: SearchRepositoriesPresenter

    @Inject
    lateinit var preferencesManager: PreferencesManager

    private var repositoryAdapter: RepositoryAdapter? = null

    private var sortItemId = R.id.sortByBestMatch

    private var code: String? = null

    override val layoutId: Int
        get() = R.layout.activity_search_repositories

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(SearchRepositoriesModule()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        code = intent.getStringExtra(CODE)
        if (code != null) {
            searchRepositoriesPresenter.getAccessToken(Constants.CLIENT_ID,
                Constants.CLIENT_SECRET,
                code!!)
        }
        setupUI()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        val uri = intent.data
        if (uri != null && uri.toString().startsWith(Constants.REDIRECT_URL)) {
            val code = uri.getQueryParameter("code")
            if (code != null) {
                viewDataBinding?.progress?.visibility = View.VISIBLE
                searchRepositoriesPresenter.getAccessToken(Constants.CLIENT_ID,
                    Constants.CLIENT_SECRET,
                    code)
            }
        }
    }

    private fun setupUI() {
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setSupportActionBar(viewDataBinding?.toolbar)
        val linearLayoutManager = LinearLayoutManager(this)
        repositoryAdapter = RepositoryAdapter(this)
        viewDataBinding?.repositoryRecycler?.layoutManager = linearLayoutManager
        viewDataBinding?.repositoryRecycler?.setHasFixedSize(true)
        viewDataBinding?.repositoryRecycler?.adapter = repositoryAdapter
        searchRepositoriesPresenter.onAttach(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sort_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId != R.id.sort) {
            sortItemId = item.itemId
            item.isChecked = true
            search(viewDataBinding?.search?.text.toString())
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupListeners() {
        viewDataBinding?.search?.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        viewDataBinding?.search?.setOnEditorActionListener(
            object : TextView.OnEditorActionListener {
                override fun onEditorAction(
                    textView: TextView?,
                    actionId: Int,
                    p2: KeyEvent?,
                ): Boolean {
                    if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                        if (textView != null) {
                            search(textView.text.toString())
                        }
                        return true
                    }
                    return false
                }
            }
        )
    }

    private fun search(query: String) {
        if (query.isEmpty()) {
            return Toast.makeText(this,
                resources.getString(R.string.query_is_empty),
                Toast.LENGTH_LONG).show()
        }
        searchRepositoriesPresenter.searchRepositories(query, getSortString())
        viewDataBinding?.userLayout?.visibility = View.GONE
        viewDataBinding?.search?.let { DeviceUtils.hideSoftKeyboard(it.context, it) }
        viewDataBinding?.progress?.visibility = View.VISIBLE
        repositoryAdapter?.clear()
    }

    private fun getSortString(): String? {
        return when (sortItemId) {
            R.id.sortByStars -> "stars"
            R.id.sortByForks -> "forks"
            R.id.sortByUpdated -> "updated"
            R.id.sortByBestMatch -> {
                null
            }
            else -> {
                null
            }
        }
    }

    override fun showRepositories(repositories: List<Repository>) {
        viewDataBinding?.progress?.visibility = View.GONE
        repositoryAdapter?.setItems(repositories)
    }

    override fun handleAccessToken(accessToken: AccessToken) {
        preferencesManager.setAccessToken(accessToken.accessToken)
        searchRepositoriesPresenter.getUser()
    }

    override fun showUser(owner: Owner) {
        viewDataBinding?.welcome?.text = resources.getString(R.string.welcome, owner.login)
        if (viewDataBinding != null) {
            Glide.with(this)
                .load(owner.avatarUrl)
                .circleCrop()
                .transition(DrawableTransitionOptions().crossFade(300))
                .into(viewDataBinding!!.thumbnail)
        }
        viewDataBinding?.userLayout?.animate()?.alpha(1f)?.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        searchRepositoriesPresenter.onDetach()
    }

    companion object {

        const val CODE = "Code"

        fun open(
            context: Context,
            code: String,
        ) {
            val intent = Intent(context, SearchRepositoriesActivity::class.java)
            intent.putExtra(CODE, code)
            context.startActivity(intent)
        }
    }

}