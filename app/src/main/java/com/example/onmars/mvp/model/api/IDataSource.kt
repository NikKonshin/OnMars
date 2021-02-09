package com.example.onmars.mvp.model.api

import com.example.onmars.mvp.model.entity.Photos
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.Rovers
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val API_KEY = "tNkegIfsPFTY15g3UzOTknEr9gNHVs9RVnmPbvc4"

interface IDataSource {

    @GET("rovers?&api_key=$API_KEY")
    fun getRovers(): Single<Rovers>

    //"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity?sol=1000&api_key=DEMO_KEY"
    @GET("rovers/{roverName}?sol=1000&api_key=$API_KEY")
    fun getRover(@Path("roverName") roverName: String): Single<Rover>

    // https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2015-6-3&camera=fhaz&api_key=DEMO_KEY
    @GET("rovers/{roverName}/photos?sol=1000&api_key=$API_KEY")
    fun getPhotoFromCameraDate(
        @Path("roverName") roverName: String,
        @Query("earth_date") date: String,
        @Query("camera") cameraName: String,
    ): Single<Photos>

    //"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity?sol=1000&camera=fhaz&api_key=DEMO_KEY"
    @GET("rovers/{roverName}/photos?sol=1000&api_key=$API_KEY")
    fun getPhotoFromCamera(
        @Path("roverName") roverName: String,
        @Query("camera") cameraName: String
    ): Single<Photos>

    //"https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=DEMO_KEY"
    @GET("rovers/{roverName}/photos?sol=1000&api_key=$API_KEY")
    fun getAllPhotoFromRover(@Path("roverName") roverName: String): Single<Photos>
}