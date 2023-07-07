package com.example.fitpeo.data.api.netwrokUtils

import java.lang.Exception

data class ApiState<out T>(val ApiStatus: ApiStatus, val data: T?, val error:Exception?) {
    companion object {

        fun <T> success(data: T?): ApiState<T> {
            return ApiState(ApiStatus.SUCCESS, data,null)
        }

        fun <T> error(error: Exception?): ApiState<T> {
            return ApiState(ApiStatus.ERROR,null,error)
        }

        fun <T> loading(): ApiState<T> {
            return ApiState(ApiStatus.LOADING, null,null)
        }
    }
}

