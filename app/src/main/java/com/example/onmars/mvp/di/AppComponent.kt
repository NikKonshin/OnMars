package com.example.onmars.mvp.di

import com.example.onmars.mvp.di.favorites.FavoritesSubComponent
import com.example.onmars.mvp.di.modules.*
import com.example.onmars.mvp.di.photo.PhotoSubComponent
import com.example.onmars.mvp.di.rovers.RoversSubComponent
import com.example.onmars.mvp.presenter.MainPresenter
import com.example.onmars.mvp.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ApiModule::class,
        AppModule::class,
        CiceroneModule::class,
        ImageLoaderModule::class,
        DatabaseModule::class,
    ]
)
interface AppComponent {
    fun roversSubComponent(): RoversSubComponent
    fun favoritesSubComponent(): FavoritesSubComponent
    fun photoSubComponent(): PhotoSubComponent
    fun inject(mainActivity: MainActivity)
    fun inject(mainPresenter: MainPresenter)
}