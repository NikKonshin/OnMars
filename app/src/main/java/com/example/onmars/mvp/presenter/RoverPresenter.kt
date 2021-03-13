package com.example.onmars.mvp.presenter

import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.model.repo.IRoverData
import com.example.onmars.mvp.presenter.list.ICameraListPresenter
import com.example.onmars.mvp.view.RoverView
import com.example.onmars.mvp.view.list.CameraItemView
import com.example.onmars.navigation.Screens
import io.reactivex.rxjava3.core.Scheduler
import moxy.MvpPresenter
import ru.terrakok.cicerone.Router
import java.text.SimpleDateFormat
import javax.inject.Inject

private const val SPIRIT = "spirit"
private const val CURIOSITY = "curiosity"
private const val OPPORTUNITY = "opportunity"
private const val PERSEVERANCE = "perseverance"

class RoverPresenter(
    private val rover: Rover,
    private var date: Date
) : MvpPresenter<RoverView>() {

    @Inject
    lateinit var mainThreadScheduler: Scheduler

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var roverData: IRoverData

    private val maxDate = dateToLong(rover.maxDate)
    private val minDate = dateToLong(rover.landingDate)

    class CamerasListPresenter : ICameraListPresenter {
        val cameras = mutableListOf<Camera>()
        override var itemClickListener: ((CameraItemView) -> Unit)? = null

        override fun bindView(view: CameraItemView) {
            val camera = cameras[view.pos]
            camera.fullName.let { view.setName(it) }
        }

        override fun getCount(): Int = cameras.size
    }

    val cameraListPresenter: CamerasListPresenter = CamerasListPresenter()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        viewState.init()
        loadData()

        cameraListPresenter.itemClickListener = {
            router.navigateTo(
                Screens.PhotosScreen(
                    rover,
                    cameraListPresenter.cameras[it.pos],
                    date
                )
            )
        }
    }

    fun setDate(newDate: Date) {
        date = newDate
    }

    private fun loadData() {
        initRoverPhoto()
        viewState.setName(rover.name ?: "")
        viewState.setLandingDate(rover.landingDate ?: "")
        viewState.setLaunchDate(rover.launchDate ?: "")
        viewState.setMaxDate(rover.maxDate ?: "")
        viewState.setMaxSol(rover.maxSol ?: 0)
        viewState.setStatus(rover.status ?: "")
        viewState.setTotalPhoto(rover.totalPhotos ?: 0)
        viewState.initGetPicker(date, maxDate, minDate)

        cameraListPresenter.cameras.clear()
        cameraListPresenter.cameras.addAll(rover.cameras)
        viewState.updateList()
    }

    fun backPressed(): Boolean {
        router.exit()
        return true
    }

    private fun initRoverPhoto() {
        when (rover.name.toLowerCase()) {
            SPIRIT -> viewState.setRoverPhotoSpirit()
            CURIOSITY -> viewState.setRoverPhotoCuriosity()
            OPPORTUNITY -> viewState.setRoverPhotoOpportunity()
            PERSEVERANCE -> viewState.setRoverPhotoPerseverance()
        }
    }

    private fun dateToLong(date: String): Long {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val dateTest = sdf.parse(date)
        return dateTest.time
    }

    override fun onDestroy() {
        super.onDestroy()
        viewState.release()
    }
}