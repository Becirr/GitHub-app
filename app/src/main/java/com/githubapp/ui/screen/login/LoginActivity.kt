package com.githubapp.ui.screen.login

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import com.githubapp.Constants
import com.githubapp.R
import com.githubapp.databinding.ActivityLoginBinding
import com.githubapp.di.component.AppComponent
import com.githubapp.ui.screen.base.BaseActivity
import com.githubapp.ui.screen.searchRepositories.SearchRepositoriesActivity

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    override val layoutId: Int = R.layout.activity_login

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(LoginModule()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupUI()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupUI() {
        viewDataBinding.webview.settings.javaScriptEnabled = true
        viewDataBinding.webview.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?,
            ): Boolean {
                super.shouldOverrideUrlLoading(view, request)
                if (request?.url.toString().startsWith(Constants.REDIRECT_URL)) {
                    viewDataBinding.webview.visibility = View.GONE
                    viewDataBinding.authorizingLayout.visibility = View.VISIBLE
                    val url = request?.url.toString()
                    var code = url.substring(url.lastIndexOf("?code=") + 1)
                    code = code.split("=")[1]
                    SearchRepositoriesActivity.open(this@LoginActivity, code)
                } else {
                    viewDataBinding.authorizingLayout.visibility = View.GONE
                    viewDataBinding.webview.visibility = View.VISIBLE
                }
                return false
            }

        }

        viewDataBinding.webview.loadUrl(Constants.AUTHORIZE_URL)
    }
}