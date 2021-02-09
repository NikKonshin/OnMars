package com.example.onmars.mvp.di.modules

import android.widget.ImageView
import com.example.onmars.mvp.model.image.IImageLoader
import com.example.onmars.mvp.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun imageLoad(): IImageLoader<ImageView> = GlideImageLoader()
}