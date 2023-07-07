package com.example.fitpeo.data.repository

import com.example.fitpeo.data.api.ApiInterface
import com.example.fitpeo.data.api.netwrokUtils.ApiState
import com.example.fitpeo.domain.PhotosResponseItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import java.util.ArrayList
import javax.inject.Inject

class PhotosRepo @Inject constructor(
    private val apiInterface: ApiInterface
) {

    suspend fun getPhotosList(): Flow<ApiState<ArrayList<PhotosResponseItem>>> {
        return flow {
            val response = apiInterface.getPhotosList()
            emit(ApiState.success(response))
        }.flowOn(Dispatchers.IO)
    }
}