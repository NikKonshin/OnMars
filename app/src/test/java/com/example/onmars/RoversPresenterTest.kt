package com.example.onmars

import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.Rovers
import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.model.repo.IRoversRepo
import com.example.onmars.mvp.presenter.RoversPresenter
import com.nhaarman.mockito_kotlin.times
import com.nhaarman.mockito_kotlin.verify
import io.reactivex.rxjava3.observers.TestObserver
import io.reactivex.rxjava3.schedulers.TestScheduler
import io.reactivex.rxjava3.subscribers.TestSubscriber
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

class RoversPresenterTest {
    @Mock
    private lateinit var presenter: RoversPresenter

    @Mock
    lateinit var roversRepo: IRoversRepo

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

//        presenter = RoversPresenter()
    }

    @Test
    fun setDate_Test() {
        val date = Date()
        presenter.setDate(date)
        verify(presenter, times(1)).setDate(date)
    }

    // Попытался я протестировать RX попытки оказались тщетны,
    // пробовал несколько часов к сожалению ничено не вышло
    // на всякий случай оставил попытки тут.
    @Test
    fun loadData_Test() {
        val _presenter = RoversPresenter()
        val testScheduler = TestScheduler()
        val testSubscriber = TestSubscriber<Rovers>()
        val rover = mock(Rover::class.java)
//        val rovers = Rovers(listOf<Rover>(rover))
        val rovers = mock(Rovers::class.java)

//        testSubscriber.assertValue(rovers)
//        testSubscriber.assertComplete()
//        testSubscriber.onNext(rovers)
        val tesObserver = TestObserver<Rovers>()
        tesObserver.onNext(rovers)
        tesObserver.assertValue(rovers)
        tesObserver.hasSubscription()
        `when`(roversRepo.getRovers().subscribeOn(testScheduler).subscribe(tesObserver)).thenReturn(
            testSubscriber.onError(RuntimeException("aaa"))
        )

        presenter.loadData()

        verify(presenter, times(1)).loadData()
    }
}