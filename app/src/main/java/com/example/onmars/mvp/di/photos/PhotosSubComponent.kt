package com.example.onmars.mvp.di.photos

import com.example.onmars.mvp.di.photos.module.PhotosModule
import com.example.onmars.mvp.presenter.IfEmptyPresenter
import com.example.onmars.mvp.presenter.PhotosPresenter
import com.example.onmars.mvp.ui.adapter.PhotosRVAdapter
import dagger.Subcomponent

@PhotosScope
@Subcomponent(
    modules = [
        PhotosModule::class
    ]
)
interface PhotosSubComponent {
    fun inject(photosPresenter: PhotosPresenter)
    fun inject(photosRVAdapter: PhotosRVAdapter)
    fun inject(ifEmptyPresenter: IfEmptyPresenter)
}