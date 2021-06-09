package com.example.onmars

import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.presenter.RoverPresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class RoverPresenterTest {
    @Mock
    private lateinit var presenter: RoverPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun setDate_Test() {
        val date = Date()
        presenter.setDate(date)
        verify(presenter, times(1)).setDate(date)
    }
}