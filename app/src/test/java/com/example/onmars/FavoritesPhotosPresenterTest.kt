package com.example.onmars

import com.example.onmars.mvp.model.cache.IFavoritesPhotoCache
import com.example.onmars.mvp.model.entity.scroll.SaveScroll
import com.example.onmars.mvp.presenter.FavoritesPhotosPresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class FavoritesPhotosPresenterTest {

    private lateinit var presenter: FavoritesPhotosPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        presenter = FavoritesPhotosPresenter()

        presenter.favoritesCache = mock(IFavoritesPhotoCache::class.java)
        presenter.position = mock(SaveScroll::class.java)
    }

    @Test
    fun getPosition_Test() {
        val position = presenter.position
        val a = 1

        `when`(position.getPosition("a")).thenReturn(2)
        `when`(presenter.getPosition()).thenReturn(1)

        val b: Int? = presenter.getPosition()
        assertEquals(a, b)
    }

    @Test
    fun saveScroll_Test() {
        presenter = mock(FavoritesPhotosPresenter::class.java)
        val pos = 1
        presenter.saveScroll(1)
        verify(presenter, times(1)).saveScroll(pos)
    }
}