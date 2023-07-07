package com.example.fitpeo.presentation.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import com.example.fitpeo.R
import com.example.fitpeo.data.api.netwrokUtils.ApiStatus.*
import com.example.fitpeo.databinding.ActivityPhotosListBinding
import com.example.fitpeo.domain.PhotosResponseItem
import com.example.fitpeo.presentation.adapter.PhotosAdapter
import com.example.fitpeo.presentation.base.BaseActivity
import com.example.fitpeo.presentation.utils.gone
import com.example.fitpeo.presentation.utils.visible
import com.example.fitpeo.presentation.viewmodel.PhotosViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class PhotosListActivity : BaseActivity<ActivityPhotosListBinding>() {
    private lateinit var binding: ActivityPhotosListBinding
    private val photosVm: PhotosViewModel by viewModels()
    private var adapter: PhotosAdapter? = null
    override fun getLayoutId(): Int {
        return R.layout.activity_photos_list
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()

        handleBackPress()
        setUpAdapter()
        swipeRefresh()
        addObservers()
        getPhotosList()
    }

    private fun swipeRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            getPhotosList()
        }
    }

    private fun handleBackPress() {
        onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }

        })
    }

    private fun setUpAdapter() {
        adapter = PhotosAdapter { position: Int, item: PhotosResponseItem ->
            navigateToDetails(item)
        }
        binding.rvPhotos.adapter = adapter
    }

    private fun getPhotosList() {
        photosVm.getPhotosList()
    }

    private fun addObservers() {
        photosVm.photosData.observe(this) {
            when (it.ApiStatus) {
                SUCCESS -> {
                    binding.apply {
                        if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
                        progressBar.gone()
                        rvPhotos.visible()
                    }

                    it?.data?.let { photosList ->
                        updateUi(photosList)
                    }
                }

                ERROR -> {
                    binding.apply {
                        if (swipeRefresh.isRefreshing) swipeRefresh.isRefreshing = false
                        progressBar.gone()
                    }
                    it?.error?.let { exception ->
                        noInternet(exception)
                    }
                }

                LOADING -> {
                    binding.apply {
                        if (swipeRefresh.isRefreshing) {
                            binding.swipeRefresh.isRefreshing=false
                        }else{
                            binding.progressBar.visible()
                        }
                    }
                }
            }
        }
    }

    private fun updateUi(photosList: ArrayList<PhotosResponseItem>) {
        adapter?.updateList(photosList)
    }

    private fun navigateToDetails(item: PhotosResponseItem) {
        val intent = Intent(this, PhotoDetailsActivity::class.java)
        intent.putExtra(PhotoDetailsActivity.PHOTO_DATA, Gson().toJson(item))
        startActivity(intent)
    }
}