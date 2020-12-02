package com.githubapp.ui.screen.searchRepositories

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubapp.R
import com.githubapp.databinding.ActivitySearchRepositoriesBinding
import com.githubapp.di.component.AppComponent
import com.githubapp.domain.model.Repository
import com.githubapp.ui.adapter.RepositoryAdapter
import com.githubapp.ui.screen.base.BaseActivity
import com.githubapp.util.DeviceUtils
import javax.inject.Inject

class SearchRepositoriesActivity : BaseActivity<ActivitySearchRepositoriesBinding>(),
    SearchRepositoriesView {

    @Inject
    lateinit var searchRepositoriesPresenter: SearchRepositoriesPresenter

    private var repositoryAdapter: RepositoryAdapter? = null

    override val layoutId: Int
        get() = R.layout.activity_search_repositories

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(SearchRepositoriesModule()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
        setupListeners()
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
                            searchRepositoriesPresenter.searchRepositories(textView.text.toString())
                            viewDataBinding?.search?.let { DeviceUtils.hideSoftKeyboard(it.context, it) }
                            viewDataBinding?.progress?.visibility = View.VISIBLE
                            repositoryAdapter?.clear()
                        }
                        return true
                    }
                    return false
                }
            }
        )
    }

    override fun showRepositories(repositories: List<Repository>) {
        viewDataBinding?.progress?.visibility = View.GONE
        repositoryAdapter?.setItems(repositories)
    }

    override fun onDestroy() {
        super.onDestroy()
        searchRepositoriesPresenter.onDetach()
    }

}