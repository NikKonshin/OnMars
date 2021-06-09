package com.example.onmars.mvp.di.photo

import com.example.onmars.mvp.di.photo.module.PhotoModule
import com.example.onmars.mvp.presenter.PhotoPresenter
import com.example.onmars.mvp.ui.fragments.PhotoFragment
import dagger.Subcomponent

@PhotoScope
@Subcomponent(
    modules = [
        PhotoModule::class
    ])
interface PhotoSubComponent {
    fun inject(photoPresenter: PhotoPresenter)
    fun inject(photoFragment: PhotoFragment)
}