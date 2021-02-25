package com.example.onmars.mvp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.onmars.MainActivity
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.model.entity.Camera
import com.example.onmars.mvp.model.entity.Rover
import com.example.onmars.mvp.model.entity.date.Date
import com.example.onmars.mvp.presenter.PhotosPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.ui.adapter.PhotosRVAdapter
import com.example.onmars.mvp.view.PhotosView
import kotlinx.android.synthetic.main.fragment_photos.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter

class PhotosFragment : MvpAppCompatFragment(), PhotosView, BackButtonListener {

    companion object {
        private const val CAMERA_ARG = "camera"
        private const val ROVER_NAME_ARG = "rover_name"
        private const val DATE_ARG = "date"
        fun newInstance(rover: Rover, camera: Camera, date: Date) = PhotosFragment().apply {
            arguments = Bundle().apply {
                putParcelable(CAMERA_ARG, camera)
                putParcelable(ROVER_NAME_ARG, rover)
                putParcelable(DATE_ARG, date)
            }

        }
    }

    private val presenter by moxyPresenter {
        val camera = arguments?.getParcelable<Camera>(CAMERA_ARG) as Camera
        val rover = arguments?.getParcelable<Rover>(ROVER_NAME_ARG)
        val date = arguments?.getParcelable<Date>(DATE_ARG)
        PhotosPresenter(rover!!, camera, date ?: Date()).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos, container, false)
    }

    private var adapter: PhotosRVAdapter? = null

    override fun init() {
        val activity = activity as MainActivity
        activity.setSupportActionBar(toolbar_photos_fragment)
        val bar = activity.supportActionBar
        bar?.setDisplayShowTitleEnabled(false)
        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.setDisplayShowHomeEnabled(true)
        rv_rovers_fragments_photos.layoutManager = LinearLayoutManager(context)
        adapter = PhotosRVAdapter(presenter.photosListPresenter).apply {
            App.instance.appComponent.inject(this)
        }
        rv_rovers_fragments_photos.adapter = adapter
    }

    override fun updateList() {
        adapter?.notifyDataSetChanged()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}