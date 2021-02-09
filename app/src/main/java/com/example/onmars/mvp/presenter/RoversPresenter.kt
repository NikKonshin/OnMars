package com.example.onmars.mvp.presenter

import android.util.Log
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.repo.IRoversRepo
import com.example.onmars.mvp.presenter.list.IRoverListPresenter
import com.example.onmars.mvp.view.RoversView
import com.example.onmars.mvp.view.list.RoverItemView
import com.example.onmars.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import javax.inject.Inject

private const val TAG = "RoversPresenter"

class RoversPresenter() : MvpPresenter<RoversView>() {
    @Inject
    lateinit var router: Router

    @Inject
    lateinit var roversRepo: IRoversRepo

    @Inject
    lateinit var mainThreadSchedulers: Scheduler

    class RoversListPresenter : IRoverListPresenter {
        val rovers = mutableListOf<Rover>()
        override var itemClickListener: ((RoverItemView) -> Unit)? = null

        override fun bindView(view: RoverItemView) {
            val rover = rovers[view.pos]
            rover.name.let { view.setName(it) }
        }

        override fun getCount() = rovers.size
    }

    val roversListPresenter = RoversListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        roversListPresenter.itemClickListener = { itemView ->
            router.navigateTo(Screens.RoverScreen(roversListPresenter.rovers[itemView.pos]))
        }
    }

    private fun loadData() {

        roversRepo.getRovers().observeOn(mainThreadSchedulers)
            .subscribe({ rovers ->
                roversListPresenter.rovers.clear()
                roversListPresenter.rovers.addAll(rovers.rovers)
                viewState.updateList()
            }, {
                Log.v(TAG, "Error: ${it.message}")
            })
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }
}
