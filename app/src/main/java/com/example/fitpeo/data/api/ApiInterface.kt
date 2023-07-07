package com.example.fitpeo.data.api

import com.example.fitpeo.domain.PhotosResponseItem
import com.example.fitpeo.presentation.utils.ApiConstants
import retrofit2.http.GET
import java.util.ArrayList

interface ApiInterface {

    @GET(ApiConstants.PHOTOS)
    suspend fun getPhotosList(): ArrayList<PhotosResponseItem>
}