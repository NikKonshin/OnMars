package com.example.onmars.mvp.model.image

import android.content.ContentValues
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.core.net.toUri
import com.example.onmars.mvp.App
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

class SaveImage(private val imageLoader: IImageLoader<ImageView>, private val app: App) :
    ISaveImage {
    private var name: String = ""
    private val resolver = app.contentResolver

    override fun savePicture(
        url: String,
        date: String,
        roverName: String,
        cameraName: String,
        id: String
    ): Completable = Completable.fromAction {
        val bitmap: Bitmap = imageLoader.getBitmap(url, app)
        var fos: OutputStream? = null
        val imageName = "${roverName}_${cameraName}_${date}_${id}.jpg"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            resolver.also { res ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    res.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                name = imageUri.toString()
                fos = imageUri?.let { res.openOutputStream(it) }
            }

        } else {
            val imageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)

            val imageFile = File(imageDir, imageName)
            fos = FileOutputStream(imageFile)
        }
        fos?.use {
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, it)
        }
    }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

    override fun delete(): Completable = Completable.fromAction {
        resolver.also { res ->
            res.delete(name.toUri(), null, null)
        }
    }.subscribeOn(Schedulers.io())

    override fun getPath(): String = name
}
