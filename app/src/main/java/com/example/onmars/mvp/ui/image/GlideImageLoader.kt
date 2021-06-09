package com.example.onmars.mvp.ui.image

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.example.onmars.R
import com.example.onmars.mvp.model.image.IImageLoader
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GlideImageLoader : IImageLoader<ImageView> {
    override fun loadInto(url: String, container: ImageView) {
        Glide.with(container.context)
            .load(url)
            .placeholder(R.drawable.placeholder)
            .into(container)
    }

    override fun setPicture(url: String, container: SubsamplingScaleImageView): Single<Bitmap> =
        Single.fromCallable {
            Glide.with(container.context).asBitmap().load(url).submit().get()
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    override fun getBitmap(uri: String, context: Context): Bitmap =
        Glide.with(context).asBitmap().load(uri).submit().get()
}

