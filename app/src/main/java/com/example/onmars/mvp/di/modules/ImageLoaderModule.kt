package com.example.onmars.mvp.di.modules

import android.widget.ImageView
import com.example.onmars.mvp.App
import com.example.onmars.mvp.model.image.IImageLoader
import com.example.onmars.mvp.model.image.ISaveImage
import com.example.onmars.mvp.model.image.SaveImage
import com.example.onmars.mvp.ui.image.GlideImageLoader
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ImageLoaderModule {

    @Singleton
    @Provides
    fun imageLoad(): IImageLoader<ImageView> = GlideImageLoader()

    @Singleton
    @Provides
    fun saveImage(il: IImageLoader<ImageView>, app: App): ISaveImage = SaveImage(il, app)
}