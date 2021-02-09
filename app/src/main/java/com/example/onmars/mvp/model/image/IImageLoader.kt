package com.example.onmars.mvp.model.image

interface IImageLoader<T> {
    fun loadInto(url: String, container: T)
}