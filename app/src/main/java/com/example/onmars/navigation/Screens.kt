package com.example.onmars.navigation

import com.example.onmars.mvp.ui.fragments.RoversFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

class Screens {
    class RoversScreen() : SupportAppScreen() {
        override fun getFragment() = RoversFragment.newInstance()
    }
}
