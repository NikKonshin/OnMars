package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onmars.MainActivity
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.date.Date
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
        private const val DATE_ARG = "date"

        fun newInstance(rover: Rover, date: Date) = RoverFragment().apply {
            arguments = Bundle().apply {
                putParcelable(ROVER_ARG, rover)
                putParcelable(DATE_ARG, date)
            }
        }
    }

    private val presenter by moxyPresenter {
        val rover = arguments?.getParcelable<Rover>(ROVER_ARG) as Rover
        val date = arguments?.getParcelable<Date>(DATE_ARG) as Date
        RoverPresenter(rover, date).apply {
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
        val activity = activity as MainActivity
        activity.setSupportActionBar(toolbar_rover_fragment)
        val bar = activity.supportActionBar
        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.setDisplayShowHomeEnabled(true)
        rv_cameras_rover_fragment.layoutManager = LinearLayoutManager(context)
        adapter = CamerasRVAdapter(presenter.cameraListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        rv_cameras_rover_fragment.adapter = adapter
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> presenter.backPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun setName(text: String) {
        app_bar_tv_rover_fragment.text = text
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

    override fun initGetPicker(date: Date) {
        date_picker_rover_fragment.init(
            date.year,
            date.month,
            date.day,
            DatePicker.OnDateChangedListener { view, year, monthOfYear, dayOfMonth ->
                date.day = dayOfMonth
                date.month = monthOfYear
                date.year = year
                date.dateString = "$year-${monthOfYear + 1}-$dayOfMonth"
                presenter.setDate(date)
            })

    }

    override fun setRoverPhotoCuriosity() {
        iv_rover_photo_rover_fragment.setImageResource(R.drawable.rover_curiosity)
    }

    override fun setRoverPhotoSpirit() {
        iv_rover_photo_rover_fragment.setImageResource(R.drawable.rover_spirit)
    }

    override fun setRoverPhotoOpportunity() {
        iv_rover_photo_rover_fragment.setImageResource(R.drawable.rover_opportunity)
    }

    override fun setRoverPhotoPerseverance() {
        iv_rover_photo_rover_fragment.setImageResource(R.drawable.rover_perseverance)
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}