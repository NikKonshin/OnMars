package com.example.onmars.mvp

import android.app.Application
import com.example.onmars.mvp.di.AppComponent
import com.example.onmars.mvp.di.DaggerAppComponent
import com.example.onmars.mvp.di.favorites.FavoritesSubComponent
import com.example.onmars.mvp.di.modules.AppModule
import com.example.onmars.mvp.di.photo.PhotoSubComponent
import com.example.onmars.mvp.di.photos.PhotosSubComponent
import com.example.onmars.mvp.di.rover.RoverSubComponent
import com.example.onmars.mvp.di.rovers.RoversSubComponent

class App: Application() {

    lateinit var appComponent: AppComponent
    private set

    var roversSubComponent: RoversSubComponent? = null
        private set

    var roverSubComponent: RoverSubComponent? = null
        private set

    var photosSubComponent: PhotosSubComponent? = null
        private set

    var photoSubComponent: PhotoSubComponent? = null
        private set

    var favoritesSubComponent: FavoritesSubComponent? = null
        private set

    companion object{
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun initRoversSubComponent() = appComponent.roversSubComponent().also {
        roversSubComponent = it
    }
    fun initRoverSubComponent() = roversSubComponent?.roverSubcomponent().also {
        roverSubComponent = it
    }
    fun initPhotosSubComponent() = roverSubComponent?.photosSubcomponent().also {
        photosSubComponent = it
    }

    fun initPhotoSubComponent() = appComponent.photoSubComponent().also {
        photoSubComponent = it
    }

    fun initFavoritesSubComponent() = appComponent.favoritesSubComponent().also {
        favoritesSubComponent = it
    }

    fun releaseRoversSubComponent() {
        roversSubComponent = null
    }
    fun releaseRoverSubComponent() {
        roverSubComponent = null
    }
    fun releasePhotosSubComponent() {
        photosSubComponent = null
    }

    fun releasePhotoSubComponent() {
        photoSubComponent = null
    }

    fun releaseFavoritesSubComponent() {
        favoritesSubComponent = null
    }
}