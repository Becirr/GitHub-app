package com.githubapp.ui.screen.webView

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.webkit.*
import com.githubapp.R
import com.githubapp.databinding.ActivityWebViewBinding
import com.githubapp.di.component.AppComponent
import com.githubapp.ui.screen.base.BaseActivity

class WebViewActivity : BaseActivity<ActivityWebViewBinding>() {

    override val layoutId: Int = R.layout.activity_web_view

    override fun inject(appComponent: AppComponent) {
        appComponent.plus(WebViewModule()).inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val url = intent.getStringExtra(URL)
        setupUI(url)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupUI(url: String?) {
        viewDataBinding.webView.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                viewDataBinding.progress.visibility = View.GONE
            }

        }
        val webSettings: WebSettings = viewDataBinding.webView.settings
        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        viewDataBinding.webView.setBackgroundColor(Color.TRANSPARENT)
        url?.let { viewDataBinding.webView.loadUrl(it) }
    }

    companion object {

        private const val URL = "Url"

        fun open(
            context: Context,
            url: String?,
        ) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra(URL, url)
            context.startActivity(intent)
        }
    }

}