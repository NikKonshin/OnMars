package com.example.onmars.mvp.model.image

import android.content.Context
import android.graphics.Bitmap
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import io.reactivex.rxjava3.core.Single

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
    fun setPicture(url: String, container: SubsamplingScaleImageView): Single<Bitmap>
    fun getBitmap(uri: String, context: Context): Bitmap
}