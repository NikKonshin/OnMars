package com.example.onmars.navigation

import androidx.fragment.app.Fragment
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.ui.fragments.IfEmptyFragment
import com.example.onmars.mvp.ui.fragments.PhotosFragment
import com.example.onmars.mvp.ui.fragments.RoverFragment
import com.example.onmars.mvp.ui.fragments.RoversFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class RoversScreen() : SupportAppScreen() {
        override fun getFragment() = RoversFragment.newInstance()
    }

    class RoverScreen(val rover: Rover, val date: Date) : SupportAppScreen() {
        override fun getFragment(): Fragment = RoverFragment.newInstance(rover, date)
    }

    class PhotoScreen(
        private val rover: Rover,
        private val camera: Camera,
        private val date: Date
    ) : SupportAppScreen() {
        override fun getFragment(): Fragment? = PhotosFragment.newInstance(rover, camera, date)
    }

    class IfEmptyScreen() : SupportAppScreen(){
        override fun getFragment(): Fragment? = IfEmptyFragment.newInstance()
    }
}
