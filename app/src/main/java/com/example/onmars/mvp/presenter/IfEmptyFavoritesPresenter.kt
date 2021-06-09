package com.example.onmars.mvp.presenter

import com.example.onmars.mvp.view.IfEmptyFavoritesView
import com.example.onmars.navigation.Screens
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class IfEmptyFavoritesPresenter :
    MvpPresenter<IfEmptyFavoritesView>() {
    @Inject
    lateinit var router: Router

    fun backPressed(): Boolean {
        router.replaceScreen(Screens.RoversScreen())
        return true
    }
}