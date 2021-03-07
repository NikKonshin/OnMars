package com.example.onmars.mvp.model.entity.scroll

interface ISaveScroll {
    fun saveScroll(key: String, pos: Int)
    fun getPosition(key: String): Int?
}