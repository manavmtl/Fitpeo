package com.example.fitpeo.presentation.activity

import android.os.Bundle
import com.example.fitpeo.R
import com.example.fitpeo.databinding.ActivityPhotoDetailsBinding
import com.example.fitpeo.domain.PhotosResponseItem
import com.example.fitpeo.presentation.base.BaseActivity
import com.example.fitpeo.presentation.utils.nul
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class PhotoDetailsActivity : BaseActivity<ActivityPhotoDetailsBinding>() {
    private lateinit var binding: ActivityPhotoDetailsBinding
    override fun getLayoutId(): Int {
        return R.layout.activity_photo_details
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewDataBinding()

        getIntentExtras()
    }

    private fun getIntentExtras() {
        if (intent.hasExtra(PHOTO_DATA)) {
            val data = intent?.extras?.getString(PHOTO_DATA, "")
            if (!data.isNullOrBlank()) {
                val photoData = Gson().fromJson(data, PhotosResponseItem::class.java)
                updateUi(photoData)
            }
        }
    }

    private fun updateUi(photoData: PhotosResponseItem?) {
        binding.apply {
            Picasso.get()
                .load(photoData?.url.nul())
                .placeholder(R.mipmap.ic_launcher)
                .into(ivImage)

            tvTitle.text=photoData?.title.nul()
        }
    }

    companion object {
        const val PHOTO_DATA = "photo_data"
    }
}