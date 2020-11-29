package com.githubapp.ui.screen.base

interface MvpPresenter<View : MvpView> {
    fun onAttach(view: View)
    fun onDetach()
}