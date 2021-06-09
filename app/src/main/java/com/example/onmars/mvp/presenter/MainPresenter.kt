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
        viewState.init()
        router.replaceScreen(Screens.RoversScreen())
    }

    fun backClicked() {
        router.exit()
    }

    fun replaceFragmentToFavorites(): Boolean {
        router.replaceScreen(Screens.FavoritesScreen())
        return true
    }

    fun replaceFragmentToRovers(): Boolean {
        router.replaceScreen(Screens.RoversScreen())
        return true
    }
}