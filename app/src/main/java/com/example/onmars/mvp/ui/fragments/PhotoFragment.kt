package com.example.onmars.mvp.ui.fragments

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.ImageView
import android.widget.Toast
import com.davemorrissey.labs.subscaleview.ImageSource
import com.example.onmars.mvp.ui.MainActivity
import com.example.onmars.R
import com.example.onmars.mvp.App
import com.example.onmars.mvp.model.entity.favorites.FavoritesPhoto
import com.example.onmars.mvp.model.image.IImageLoader
import com.example.onmars.mvp.presenter.PhotoPresenter
import com.example.onmars.mvp.ui.BackButtonListener
import com.example.onmars.mvp.view.PhotoView
import kotlinx.android.synthetic.main.fragment_photo.*
import moxy.MvpAppCompatFragment
import moxy.ktx.moxyPresenter
import javax.inject.Inject

class PhotoFragment : MvpAppCompatFragment(), PhotoView, BackButtonListener {

    @Inject
    lateinit var imageLoader: IImageLoader<ImageView>
    private var myMenu: Menu? = null

    companion object {
        private const val PERMISSION_CODE = 1001
        private const val IMAGE_SAVE_COMPLETE = 5257
        private const val TAG = "photoFragment"
        private const val URL_ARG = "url"
        fun newInstance(favoritesPhoto: FavoritesPhoto) =
            PhotoFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(URL_ARG, favoritesPhoto)
                }
            }.apply {
                App.instance.appComponent.inject(this)
            }
    }

    private val presenter by moxyPresenter {
        val favoritesPhoto = arguments?.getParcelable<FavoritesPhoto>(URL_ARG)
        PhotoPresenter(favoritesPhoto).apply {
            App.instance.appComponent.inject(this)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        myMenu = menu
        inflater.inflate(R.menu.menu_photo, menu)
        if (presenter.isFavorites != null && presenter.isFavorites!!) {
            menu.findItem(R.id.is_favorites_menu_photo).icon =
                resources.getDrawable(R.drawable.ic_favorite)
        } else {
            menu.findItem(R.id.is_favorites_menu_photo).icon =
                resources.getDrawable(R.drawable.ic_not_favorite)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.is_favorites_menu_photo -> {
                presenter.itemClickListener()
            }
            R.id.share_menu_photo -> {
                if (activity?.checkSelfPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    val permission = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permission, PERMISSION_CODE)
                } else {
                    presenter.saveAndShareImage()
                }
                return true
            }
            R.id.save_menu_photo -> {
                presenter.saveImage()
                return true
            }
            else -> false
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    presenter.saveAndShareImage()
                } else {
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_SAVE_COMPLETE) {
            presenter.delete()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun init() {
        val activity = activity as MainActivity
        activity.setSupportActionBar(toolbar_photo_fragment)
        val bar = activity.supportActionBar
        bar?.setDisplayShowTitleEnabled(false)
        bar?.setDisplayHomeAsUpEnabled(true)
        bar?.setDisplayShowHomeEnabled(true)
    }

    override fun initPhoto(url: String) {
        imageLoader.setPicture(url, image_view_photo_fragment).subscribe({
            image_view_photo_fragment.setImage(ImageSource.bitmap(it))
        }, {
            Log.v(TAG, "${it.message}")
        })
    }

    override fun initPhotoIfEmpty() {
        image_view_photo_fragment.setImage(ImageSource.resource(R.drawable.placeholder_photo_fragment))
    }

    override fun isFavorites() {
        myMenu?.findItem(R.id.is_favorites_menu_photo)?.icon =
            resources.getDrawable(R.drawable.ic_favorite)
    }

    override fun isNotFavorites() {
        myMenu?.findItem(R.id.is_favorites_menu_photo)?.icon =
            resources.getDrawable(R.drawable.ic_not_favorite)

    }

    override fun startPushActivity(uri: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_STREAM, Uri.parse(uri))
        startActivityForResult(Intent.createChooser(intent, "Share Image"), IMAGE_SAVE_COMPLETE)
    }

    override fun showToastSave() {
        Toast.makeText(context, "Image save", Toast.LENGTH_SHORT).show()
    }

    override fun showToastError(text: String) {
        Toast.makeText(context, "$text", Toast.LENGTH_SHORT).show()
    }

    override fun backPressed(): Boolean = presenter.backPressed()
}
