package com.example.onmars.mvp.presenter

import com.example.onmars.mvp.view.IfEmptyView
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class IfEmptyPresenter : MvpPresenter<IfEmptyView>() {
    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}