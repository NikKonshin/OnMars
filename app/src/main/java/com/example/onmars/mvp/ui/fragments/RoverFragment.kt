package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.presenter.RoverPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.ui.adapter.CamerasRVAdapter
import com.example.onmars.mvp.view.RoverView
import kotlinx.android.synthetic.main.fragment_rover.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class RoverFragment : MvpAppCompatFragment(), RoverView, BackButtonListener {

    companion object {
        private const val ROVER_ARG = "rover"
        fun newInstance(rover: Rover) = RoverFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ROVER_ARG, rover)
            }
        }
    }

    private val presenter by moxyPresenter {
        val rover = arguments?.getParcelable<Rover>(ROVER_ARG) as Rover
        RoverPresenter(rover).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rover, container, false)
    }

    private var adapter: CamerasRVAdapter? = null

    override fun init() {
        rv_cameras_rover_fragment.layoutManager = LinearLayoutManager(context)
        adapter = CamerasRVAdapter(presenter.cameraListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        rv_cameras_rover_fragment.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setName(text: String) {
        tv_name_rover_rover_fragment.text = text
    }

    override fun setLandingDate(text: String) {
        tv_landing_date_value_rover_fragment.text = text
    }

    override fun setLaunchDate(text: String) {
        tv_launch_date_value_rover_fragment.text = text
    }

    override fun setStatus(text: String) {
        tv_status_value_rover_fragment.text = text
    }

    override fun setMaxSol(sol: Int) {
        tv_max_sol_value_rover_fragment.text = sol.toString()
    }

    override fun setMaxDate(text: String) {
        tv_max_date_value_rover_fragment.text = text
    }

    override fun setTotalPhoto(value: Int) {
        tv_total_photos_value_rover_fragment.text = value.toString()
    }

    override fun getDate() {
        var date = ""
        date_picker_rover_fragment.setOnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
            println("DATE: $year-$monthOfYear-$dayOfMonth")
            date = "$year-$monthOfYear-$dayOfMonth"
            presenter.getDate(date)
        }

    }

    override fun backPressed(): Boolean = presenter.backPressed()
}