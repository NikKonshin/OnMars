package com.example.onmars.mvp.presenter

import com.example.onmars.mvp.view.MainView
import com.example.onmars.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class MainPresenter() : MvpPresenter<MainView>() {

    @Inject
    lateinit var router: Router

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        router.replaceScreen(Screens.RoversScreen())
    }

    fun backClicked() {
        router.exit()
    }
}