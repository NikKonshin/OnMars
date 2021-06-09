package com.example.onmars.mvp.model.cache.room

import com.example.onmars.mvp.model.cache.IRoverCache
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.entity.room.RoomCamera
import com.example.onmars.mvp.model.entity.room.RoomRover
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RoomRoverCache(private val db: Database): IRoverCache {
    override fun insert(rover: Rover): Completable = Completable.fromAction {
        val roomRover = rover.let {
            RoomRover(
                it.id,
                it.name,
                it.landingDate,
                it.launchDate,
                it.status,
                it.maxSol,
                it.maxDate,
                it.totalPhotos
            )
        }

        val roomCameras: List<RoomCamera> = rover.let { rover ->
            rover.cameras.map { camera ->
                RoomCamera(
                    camera.id,
                    camera.name,
                    camera.roverId,
                    camera.fullName
                )
            }
        }

        db.roverDao.insert(roomRover)
        db.cameraDao.insert(roomCameras)

    }

    override fun getRover(roverName: String): Single<Rover> = Single.fromCallable {
        roverName.let { name->
            db.roverDao.findByName(name).let { roomRover ->
                val cameras: List<Camera> = db.cameraDao.findByRoverId(roomRover.id).map {
                    Camera(
                        it.id,
                        it.name,
                        it.roverId,
                        it.fullName
                    )
                }
                Rover(
                    roomRover.id,
                    roomRover.name,
                    roomRover.landingDate,
                    roomRover.launchDate,
                    roomRover.status,
                    roomRover.maxSol,
                    roomRover.maxDate,
                    roomRover.totalPhotos,
                    cameras
                )
            }
        }
    }
}