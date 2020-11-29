package com.githubapp.ui.screen.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.githubapp.App
import com.githubapp.di.component.AppComponent
import com.githubapp.domain.exception.Error
import com.githubapp.exception.ErrorFactory
import javax.inject.Inject

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity(), MvpView {

    @Inject
    protected lateinit var errorFactory: ErrorFactory

    /**
     * @return layout binding
     */
    protected var viewDataBinding: T? = null
        private set

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * Dependency injection
     */
    protected abstract fun inject(appComponent: AppComponent)

    override fun onCreate(savedInstanceState: Bundle?) {
        inject((application as App).getAppComponent())
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
    }

    override fun showMessage(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.show()
    }

    override fun showError(error: Error) {
        val message: String = errorFactory.create(error.getException())
        showMessage(message)
    }

}