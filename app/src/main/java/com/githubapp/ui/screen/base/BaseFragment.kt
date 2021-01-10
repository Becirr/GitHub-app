package com.githubapp.ui.screen.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.githubapp.App
import com.githubapp.di.component.AppComponent
import com.githubapp.domain.exception.Error

abstract class BaseFragment<T : ViewDataBinding> : Fragment(), MvpView {

    private var activity: BaseActivity<*>? = null

    /**
     * @return layout binding
     */
    protected lateinit var viewDataBinding: T

    /**
     * @return layout resource id
     */
    @get:LayoutRes
    protected abstract val layoutId: Int

    /**
     * Dependency injection
     */
    protected abstract fun inject(appComponent: AppComponent)

    /**
     * @return base activity
     */
    protected val baseActivity: BaseActivity<*>?
        get() = activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((activity?.application as App).getAppComponent())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return viewDataBinding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            activity = context
        }
    }

    override fun onDetach() {
        activity = null
        super.onDetach()
    }

    override fun showMessage(message: String) {
        if (activity != null) {
            activity?.showMessage(message)
        }
    }

    override fun showError(error: Error) {
        if (activity != null) {
            activity?.showError(error)
        }
    }

}