package com.example.onmars.mvp.di.favorites

import com.example.onmars.mvp.di.favorites.module.FavoritesModule
import com.example.onmars.mvp.presenter.FavoritesPhotosPresenter
import com.example.onmars.mvp.presenter.IfEmptyFavoritesPresenter
import com.example.onmars.mvp.ui.adapter.FavoritesPhotoRVAdapter
import dagger.Subcomponent

@FavoritesScope
@Subcomponent(
    modules = [
        FavoritesModule::class
    ]
)
interface FavoritesSubComponent {
    fun inject(favoritesPhotosPresenter: FavoritesPhotosPresenter)
    fun inject(favoritesPhotoRVAdapter: FavoritesPhotoRVAdapter)
    fun inject(ifEmptyFavoritesPresenter: IfEmptyFavoritesPresenter)

}