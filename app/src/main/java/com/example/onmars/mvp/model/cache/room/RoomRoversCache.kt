package com.example.onmars.mvp.model.cache.room

import com.example.onmars.mvp.model.cache.IRoversCache
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.Rovers
import com.example.onmars.mvp.model.entity.room.Database
import com.example.onmars.mvp.model.entity.room.RoomCamera
import com.example.onmars.mvp.model.entity.room.RoomRover
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class RoomRoversCache(private val db: Database) : IRoversCache {
    override fun insert(rovers: Rovers): Completable = Completable.fromAction {
        val roomRovers = rovers.rovers.map { rover ->
            RoomRover(
                rover.id,
                rover.name,
                rover.landingDate,
                rover.launchDate,
                rover.status,
                rover.maxSol,
                rover.maxDate,
                rover.totalPhotos,
            )
        }
        val roomCameras: List<RoomCamera> = rovers.rovers.flatMap { rover ->
            rover.cameras.map { camera ->
                RoomCamera(
                    camera.id,
                    camera.name,
                    camera.roverId,
                    camera.fullName
                )
            }
        }
        db.roverDao.insert(roomRovers)
        db.cameraDao.insert(roomCameras)

    }.subscribeOn(Schedulers.io())

    override fun getAllRovers(): Single<Rovers> = Single.fromCallable {
        val cameras: List<Camera> = db.cameraDao.getAll().map {
            Camera(
                it.id,
                it.name,
                it.roverId,
                it.fullName
            )
        }
        Rovers(
            db.roverDao.getAll().map { roomRover ->
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
        )

    }.subscribeOn(Schedulers.io())
}