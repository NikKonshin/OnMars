package com.example.onmars.navigation

import androidx.fragment.app.Fragment
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.model.entity.favorites.FavoritesPhoto
import com.example.onmars.mvp.ui.fragments.*
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class RoversScreen() : SupportAppScreen() {
        override fun getFragment() = RoversFragment.newInstance()
    }

    class RoverScreen(val rover: Rover, val date: Date) : SupportAppScreen() {
        override fun getFragment(): Fragment = RoverFragment.newInstance(rover, date)
    }

    class PhotosScreen(
        private val rover: Rover,
        private val camera: Camera,
        private val date: Date
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment? = PhotosFragment.newInstance(rover, camera, date)
    }

    class IfEmptyScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment? = IfEmptyFragment.newInstance()
    }

    class IfEmptyFavoritesScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment? = IfEmptyFavoritesFragment.newInstance()
    }

    class PhotoScreen(
        private val photo: FavoritesPhoto
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment? = PhotoFragment.newInstance(photo)
    }

    class FavoritesScreen() : SupportAppScreen() {
        override fun getFragment(): Fragment? = FavoritesPhotoFragment.newInstance()
    }
}
