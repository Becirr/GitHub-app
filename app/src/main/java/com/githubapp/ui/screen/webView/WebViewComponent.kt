package com.githubapp.ui.screen.webView

import com.githubapp.di.scope.PerActivity
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [WebViewModule::class])
interface WebViewComponent {
    fun inject(webViewActivity: WebViewActivity): WebViewActivity
}