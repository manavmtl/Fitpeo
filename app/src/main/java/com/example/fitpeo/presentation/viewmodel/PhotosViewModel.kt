package com.example.fitpeo.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpeo.data.api.netwrokUtils.ApiState
import com.example.fitpeo.data.api.netwrokUtils.NoConnectivityException
import com.example.fitpeo.data.repository.PhotosRepo
import com.example.fitpeo.domain.PhotosResponseItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotosViewModel @Inject constructor(
    private val repo: PhotosRepo
) : ViewModel() {

    private val _photosData = MutableLiveData<ApiState<ArrayList<PhotosResponseItem>>>()
    val photosData: LiveData<ApiState<ArrayList<PhotosResponseItem>>>
        get() = _photosData

    fun getPhotosList() = viewModelScope.launch {
        repo.getPhotosList().onStart {
            _photosData.value = ApiState.loading()
        }.catch {
            if (it is NoConnectivityException)
                _photosData.value = ApiState.error(it)
            else
                _photosData.value = ApiState.error(null)
        }.collect {
            _photosData.value = ApiState.success(it.data)
        }
    }
}